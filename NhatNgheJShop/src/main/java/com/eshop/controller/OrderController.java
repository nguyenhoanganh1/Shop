package com.eshop.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshop.bean.CartItem;
import com.eshop.dao.CustomerDAO;
import com.eshop.dao.OrderDAO;
import com.eshop.dao.OrderDetailDAO;
import com.eshop.dao.ProductDAO;
import com.eshop.entity.Customer;
import com.eshop.entity.Order;
import com.eshop.entity.OrderDetail;
import com.eshop.entity.Product;
import com.eshop.security.CustomerDetails;
import com.eshop.service.MailerService;
import com.eshop.service.SessionService;
import com.eshop.service.ShoppingCartService;
import com.eshop.service.UploadService;
import com.eshop.utils.QRCodeGenerator;
import com.eshop.utils.XDate;
import com.google.zxing.WriterException;

import net.bytebuddy.utility.RandomString;

@Controller
public class OrderController {
	@Autowired
	UploadService upload;
	@Autowired
	MailerService mailer;
	@Autowired
	ShoppingCartService cart;
	@Autowired
	SessionService session;
	@Autowired
	CustomerDAO cdao;
	@Autowired
	OrderDAO odao;
	@Autowired
	ProductDAO pdao;
	@Autowired
	OrderDetailDAO ddao;
	
	@GetMapping("/order/checkout")
	public String checkout(Model model) {
		return "forward:/cart/view";
	}
	
	@RequestMapping("/order/purchase")
	public String purchase(Model model, Order order, @AuthenticationPrincipal CustomerDetails principal,
			@AuthenticationPrincipal OAuth2User auth, HttpServletResponse resp) throws WriterException, IOException {
		// Tạo đơn hàng
		//Customer user = session.getAttribute("user");
		Customer user = new Customer();
		if(principal == null)
		{
			user = cdao.findCustomer(auth.getAttribute("email"));
		}
		else
		{
			user = cdao.findCustomer(principal.getUsername());
		}
		
		order.setAmount(cart.getAmount());
		order.setCustomer(user);
		order.setOrderDate(XDate.now());
		odao.save(order);
		
		// Chi tiết đơn hàng
		Collection<CartItem> items =  cart.getItems();
		for (CartItem item : items) {
			Product p = pdao.getOne(item.getId());
			OrderDetail detail = new OrderDetail();
			detail.setProduct(p);
			detail.setOrder(order);
			detail.setDiscount(item.getDisc());
			detail.setQuantity(item.getQty());
			detail.setUnitPrice(item.getPrice());			
			ddao.save(detail);
		}	
		cart.clear();
		
		return "redirect:/order/detail/"+order.getId();
	}
	
//	@ResponseBody
//	@RequestMapping()
//	public void QR() throws WriterException, IOException{
//		String random = RandomString.make(8);
//		String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";
//		QRCodeGenerator.generateQRCodeImage(random,QR_CODE_IMAGE_PATH);
//	}
	
	@RequestMapping(value="order/qrcode/{id}")
	public void QRCodeImage(HttpServletResponse resp, @PathVariable("id") String id) throws IOException, WriterException{
		resp.setContentType("image/png");
		OutputStream outputStream = resp.getOutputStream();
		outputStream.write(QRCodeGenerator.getQRCodeImage(id));
		outputStream.flush();
		outputStream.close();
		
	}
	
	@RequestMapping("/order/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Order order = odao.getOne(id);
		model.addAttribute("order", order);
		return "order/detail";
	}
	
	@RequestMapping("/order/cancel/{id}")
	public String cancel(@PathVariable("id") Integer id) {
		Order order = odao.getOne(id);
		order.setStatus(-1);
		odao.save(order);
		return "redirect:/order/list/";
	}
	
	@RequestMapping("/order/list")
	public String list(Model model,  @AuthenticationPrincipal CustomerDetails principal,
			 @AuthenticationPrincipal OAuth2User auth) {
		//Customer user = session.getAttribute("user");
		Customer user = new Customer();
		if(principal == null)
		{
			user = cdao.findCustomer(auth.getAttribute("email"));			
		}
		else
		{
			user = cdao.findCustomer(principal.getUsername());	
		}
		List<Order> list = odao.findByCustomer(user);
			model.addAttribute("list", list);
		return "order/list";
	}
	
	@RequestMapping("/order/items")
	public String items(Model model,  @AuthenticationPrincipal CustomerDetails principal,
			 @AuthenticationPrincipal OAuth2User auth) {
		//	Customer user = session.getAttribute("user");
		Customer user = new Customer();
		if(principal == null)
		{
			 user = cdao.findCustomer(auth.getAttribute("email"));
			
		}
		else
		{
			 user = cdao.findCustomer(principal.getUsername());	
		}
		List<Product> list = pdao.findByCustomer(user);
			model.addAttribute("cateProduct", list);
		
		return "order/items";
	}
	
	@ModelAttribute("status")
	public Map<Integer, String> getStatus (){
		Map<Integer, String> map = 	new HashMap<>();
		map.put(-1, "Canceled");
		map.put(0, "New");
		map.put(1, "Confirming");
		map.put(2, "Confirmed");
		map.put(3, "Shipping");
		map.put(4, "Completed");		
		return map;
	}
}
