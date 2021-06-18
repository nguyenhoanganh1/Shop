package com.eshop;


import java.net.URLEncoder;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.eshop.jwt.JwtAuthenticationEntryPoint;
import com.eshop.jwt.JwtRequestFilter;
import com.eshop.security.BeforeAuthenticationFilter;
import com.eshop.security.CustomOAuth2UserService;
import com.eshop.security.CustomerDetailsService;
import com.eshop.security.OAuth2LoginSuccessHandler;

@EnableWebSecurity
@Configuration
public class OAuthSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private CustomOAuth2UserService oauth2UserService;
    
	@Autowired
	private CustomerDetailsService customerDetailsService;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
 	private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(customerDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean 
	public UserDetailsService userDetailsService() {
		return customerDetailsService;
	}
	
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(passwordEncoder());
		authProvider.setUserDetailsService(userDetailsService());
		return authProvider;
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		auth.userDetailsService(userDetailsService());
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**");		
	}
	
	@Autowired
	private BeforeAuthenticationFilter beforeAuthenticationFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	       http 
	       		.csrf().disable()
	        	.authorizeRequests()
	        	.antMatchers("/order/**", "/account/change", "/account/edit", "/account/logoff").authenticated()
	        	.antMatchers("/admin/**").hasAuthority("admin")  
				.antMatchers("/admin/**").hasAnyAuthority("admin","editor")
	            .anyRequest().permitAll()
	            .and()
	            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
	            .formLogin()
	            	.loginPage("/login")
	            	.loginProcessingUrl("/account/login")
	            	.usernameParameter("username")
	            	.passwordParameter("password")
	            	.permitAll()
	            	.defaultSuccessUrl("/")
	            	.failureUrl("/account/login?message=" + URLEncoder.encode("Vui lòng đăng nhập lại", "utf-8"))    
	            .and()
	            .oauth2Login()
	                .loginPage("/account/login")
	                .userInfoEndpoint()
	                    .userService(oauth2UserService)
	                .and()
	        	.successHandler(oAuth2LoginSuccessHandler)
	        	
	            .and()
	        	.logout()
	        		.logoutUrl("/accouont/logoff")
	        		.logoutSuccessUrl("/account/login")	        		
	        	   	.invalidateHttpSession(true)	        	   	
	        	   	.deleteCookies("JSESSIONID")
	        	   	.logoutRequestMatcher(new AntPathRequestMatcher("/account/logoff"))	        	   	
	        	   	.permitAll()
//	        	.and()
//	        	 	.rememberMe().tokenRepository(persistentTokenRepository())
                .and()            
            	.exceptionHandling().accessDeniedPage("/error/403")
            	;
	       http.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(1296000);
	    }	
	   	
	
	 	@Bean
		public PersistentTokenRepository persistentTokenRepository() {
			JdbcTokenRepositoryImpl tokenRepositoryImpl= new JdbcTokenRepositoryImpl();
			tokenRepositoryImpl.setDataSource(dataSource);
			return tokenRepositoryImpl;
		}
		
	 
	 
	 	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	 	@Autowired
	 	public AuthenticationManager authenticationManager() throws Exception {
	 		return super.authenticationManager();
	 	}
	 	
		
		
}
