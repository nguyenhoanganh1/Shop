package com.eshop.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.eshop.dao.CustomerDAO;
import com.eshop.dao.RoleDAO;
import com.eshop.entity.Customer;
import com.eshop.entity.Provider;
import com.eshop.entity.Role;
import com.eshop.jwt.JwtRequest;
import com.eshop.jwt.JwtResponse;
import com.eshop.jwt.JwtUtil;
import com.eshop.security.CustomerDetails;
import com.eshop.security.CustomerDetailsService;
import com.eshop.security.InvalidReCaptchaTokenException;
import com.eshop.security.RecaptchaResponse;
import com.eshop.security.RecaptchaResponseV3;
import com.eshop.service.CookieService;
import com.eshop.service.CustomerService;
import com.eshop.service.MailerService;
import com.eshop.service.SessionService;
import com.eshop.service.UploadService;
import com.eshop.utils.PasswordEncoder;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import com.sun.istack.Nullable;

@Transactional
@Controller
public class AccountController {

	private Customer customer;

	//private JwtRequest authenticationRequest;

	@Autowired
	private JwtUtil jwtTokenUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	RoleDAO rdao;
	@Autowired
	CustomerDAO cdao;
	@Autowired
	SessionService session;
	@Autowired
	CookieService cookie;
	@Autowired
	UploadService upload;
	@Autowired
	MailerService mailer;
	@Autowired
	CustomerService customerService;
	@Autowired
	private CustomerDetailsService customerDetailsService;
	@Autowired
	HttpServletRequest req;
	@Autowired
	HttpServletResponse resp;

	@Value("https://www.google.com/recaptcha/api/siteverify")
	private String url;

	@Value("6Ld99rUaAAAAAMyC7TJmjQn5Z85GU0gKU2ByrkDn")
	private String secretKey;

	@Value("6LepSLYaAAAAAIjYRkwdFnqbmk-1Vzi4v2njkqa7")
	private String secretKeyV3;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Autowired
	private RestTemplate template;

	private boolean verifyReCAPTCHA(String gRecaptchaResponse) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("secret", secretKey);
		map.add("response", gRecaptchaResponse);

		HttpEntity<MultiValueMap<String, String>> http = new HttpEntity<>(map, headers);

		RecaptchaResponse response = template.postForObject(url, http, RecaptchaResponse.class);
		System.out.println(response.isSuccess());
		System.out.println(response.getHostname());
		System.out.println(response.getChallenge_ts());
		System.out.println(response.getErrorCodes());
		if (response.getErrorCodes() != null) {
			for (String error : response.getErrorCodes()) {
				System.out.println("\t" + error);
			}
		}
		return response.isSuccess();
	}

	public float verifyV3(String recaptchaFormResponse) throws InvalidReCaptchaTokenException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("secret", secretKeyV3);
		map.add("response", recaptchaFormResponse);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		RecaptchaResponseV3 response = template.postForObject(url, request, RecaptchaResponseV3.class);
		System.out.println("isSuccess: " + response.isSuccess());
		System.out.println("getHostname: " + response.getHostname());
		System.out.println("getChallenge_ts: " + response.getChallenge_ts());
		System.out.println("getErrorCodes: " + response.getErrorCodes());
		System.out.println("getScore: " + response.getScore());
		System.out.println("recapcha Handler Called .. ..");
		System.out.println("g-recapcha response .. .." + recaptchaFormResponse);

		if (response.getErrorCodes() != null) {
			System.out.println("Error code: ");
			for (String error : response.getErrorCodes()) {
				System.out.println("\t" + error);
			}
		}
		if (!response.isSuccess()) {
			throw new InvalidReCaptchaTokenException("Invalid ReCaptcha Token.");
		}
		return 0.44f;
		 //return response.getScore();
	}

	@Nullable
	@GetMapping("/account/login")
	public String login(Model model) {
		Map<String, String> user = cookie.getUserSecurity();
		model.addAttribute("form", user);
		return "account/login";
	}

	@GetMapping("/chat")
	public String chat(Model model) {

		return "user/chat/chat-box";
	}

	

	@PostMapping("/account/authen")
	public ResponseEntity<?> login1(@RequestBody JwtRequest authenticationRequest) throws Exception {
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		final UserDetails userDetails = customerDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		System.out.println("Json Web Token: " + token);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	@PostMapping("/account/login")
	public String login(Model model, @RequestParam("username") String username,
			@RequestParam("password") String password, @AuthenticationPrincipal CustomerDetails customerDetails) {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			final UserDetails userDetails = customerDetailsService.loadUserByUsername(username);
			final String token = jwtTokenUtil.generateToken(userDetails);
			System.out.println("Json Web Token: " + token);
			/*
			 * Customer user = cdao.getOne(username); if
			 * (!user.getPassword().equals(password)) { model.addAttribute("message",
			 * "Sai t??i kho???n ho???c m???t kh???u"); } else if (!user.isActivated()) {
			 * model.addAttribute("message", "T??i kho???n ch??a ???????c k??ch ho???t"); }
			 * 
			 * else { session.setAttribute("user", user); model.addAttribute("message",
			 * "????ng nh???p th??nh c??ng"); if (remember) { cookie.setUser(username, password);
			 * } else { cookie.delete("user"); }
			 * 
			 * String authUri = session.getAttribute("auth-uri"); if(authUri != null) {
			 * return "redirect:" + authUri; } else { return "redirect:/"; }
			 * 
			 * }
			 * 
			 * if (remember) { cookie.setUser(username, password); } else {
			 * cookie.delete("user"); }
			 */

			

			String authUri = session.getAttribute("auth-uri");
			if (authUri != null) {
				return "redirect:" + authUri;
			} else {
				return "redirect:/";
			}

		} catch (Exception e) {
			model.addAttribute("message", "Sai t??n ????ng nh???p");
		}

		return "login";
	}

	@RequestMapping("/account/logoff")
	public String logoff() throws UnsupportedEncodingException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null || authentication instanceof AnonymousAuthenticationToken) {
			new SecurityContextLogoutHandler().logout(req, resp, authentication);

		}

		// session.removeAttribute("user");
		session.removeAttribute("auth-uri");
		return "redirect:/account/login?message=" + URLEncoder.encode("????ng xu???t th??nh c??ng", "utf-8");
	}

	@GetMapping("/account/register")
	public String register(Model model) {
		model.addAttribute("form", new Customer());
		return "account/register";
	}

	@PostMapping("/account/register")
	public String register(Model model, @ModelAttribute("form") Customer user,
			@RequestParam("photo_file") MultipartFile photo, @RequestParam("confirm") String confirm) {

		if (!confirm.equals(user.getPassword())) {
			model.addAttribute("message", "X??c nh???n m???t kh???u kh??ng ????ng");
		} else {
			try {
				String gRecaptchaResponse = req.getParameter("g-recaptcha-response");
				if (!verifyReCAPTCHA(gRecaptchaResponse)) {
					return "redirect:/account/register?message="
							+ URLEncoder.encode("Vui l??ng k??ch ho???t recaptcha", "utf-8");
				}

				File file = upload.save(photo, "/static/images/users/");

				if (file == null) {
					user.setPhoto("user.png");
				} else {
					user.setPhoto(file.getName());
				}

				Role role = rdao.findByName("customer");
				user.addRole(role);
				user.setProvider(Provider.local);
				user.setPassword(PasswordEncoder.setBCryptPasswordEncoder(user.getPassword()));
				String url = req.getRequestURL().toString().replace("register", "activate/" + user.getId());

				cdao.save(user);
				model.addAttribute("message", "????ng k?? th??nh c??ng");
				String from = "EShop <hoanganh25022@gmail.com>";
				String to = user.getEmail();
				String subject = "Welcome to website EShop";
				String body = "Ch??c m???ng b???n ???? tr??? th??nh th??nh vi??n c???a ch??ng t??i.<hr/>" + "Vui l??ng <a href='" + url
						+ "'> click v??o ????y</a> ????? k??ch ho???t t??i kho???n tr?????c khi s??? d???ng";
				mailer.send(from, to, subject, body);
				model.addAttribute("message", "????ng k?? th??nh c??ng. Vui l??ng ki???m tra email");
				return "redirect:/account/login?message="
						+ URLEncoder.encode("????ng k?? th??nh c??ng. Vui l??ng ki???m tra email", "utf-8");
			} catch (Exception e) {
				model.addAttribute("message", "????ng k?? th???t b???i");
			}
		}

		return "account/register";
	}

	@RequestMapping("/account/activate/{id}")
	public String activate(@PathVariable("id") String username) throws UnsupportedEncodingException {
		Customer user = cdao.getOne(username);
		user.setActivated(true);
		cdao.save(user);
		return "redirect:/account/login?message=" + URLEncoder.encode("K??ch ho???t t??i kho???n th??nh c??ng", "utf-8");
	}

	@RequestMapping("/account/forgot")
	public String forgot() {
		return "account/forgot";
	}

	@PostMapping("/account/forgot")
	public String forgot(Model model, @RequestParam("username") String username, @RequestParam("email") String email) {
		try {
			Customer user = cdao.getOne(username);
			if (!user.getEmail().equals(email)) {
				model.addAttribute("message", "Sai Email");
			} else if (!user.isActivated()) {
				model.addAttribute("message", "T??i kho???n ch??a ???????c k??ch ho???t");
			} else {
				model.addAttribute("message", "M???t kh???u c???a b???n ???? ???????c g???i qua email");
//				String recaptchaFormResponse = req.getParameter("g-recaptcha-response");
//				try {
//					float score = verifyV3(recaptchaFormResponse);
//					if (score < 0.5) {
						customerService.forgotPassword(user);
//						return "redirect:/account/login?message=" + URLEncoder.encode("Vui l??ng nh???p m?? OTP", "utf-8")
//								+ "&username=" + user.getId();
//					}
//				} catch (InvalidReCaptchaTokenException e) {
//					try {
//						resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
//					e.printStackTrace();
//				}

				return "redirect:/account/login?message="
						+ URLEncoder.encode("M???t kh???u c???a b???n ???? ???????c g???i qua email", "utf-8");
			}
		} catch (Exception e) {
			model.addAttribute("message", "T??i kho???n kh??ng t???n t???i" + e);
		}
		return "account/forgot";
	}

	@GetMapping("/account/change")
	public String change() {
		return "account/change";
	}

	@PostMapping("/account/change")
	public String change(Model model, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("newpwd") String newpwd,
			@RequestParam("confirm") String confirm) {

		Customer user = cdao.getOne(username);
		if (!newpwd.equals(confirm)) {
			model.addAttribute("message", "X??c nh???n m???t kh???u kh??ng ????ng");
		} else {
			try {
				user.setPassword(PasswordEncoder.setBCryptPasswordEncoder(newpwd));
				cdao.save(user);
				model.addAttribute("message", "?????i m???t kh???u th??nh c??ng");
				return "redirect:/account/change?message=" + URLEncoder.encode("Thay ?????i m???t kh???u th??nh c??ng!", "utf-8");
			} catch (Exception e) {
				model.addAttribute("message", "T??i kho???n kh??ng t???n t???i");
			}
		}

		return "account/change";
	}

	@GetMapping("/account/edit")
	public String edit(Model model, @AuthenticationPrincipal CustomerDetails principal,
			@AuthenticationPrincipal OAuth2User auth) {

		Customer customer = new Customer();
		if (principal != null) {
			customer = cdao.findCustomer(principal.getUsername());

		} else {
			customer = cdao.findCustomer(auth.getAttribute("email"));

		}
		// model.addAttribute("userInfo", customer);
		model.addAttribute("form", customer);
		return "account/edit";
	}

	@PostMapping("/account/edit")
	public String edit(Model model, @ModelAttribute("form") Customer user,
			@RequestParam("photo_file") MultipartFile photo) {
		try {
			File file = upload.save(photo, "/static/images/users");
			if (file != null) {
				user.setPhoto(file.getName());
			}

			cdao.save(user);
		} catch (Exception e) {
			model.addAttribute("message", "C???p nh???t t??i kho???n th???t b???i");
		}

		return "account/edit";
	}

}
