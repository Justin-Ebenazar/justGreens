package com.example.justinSpringBoot.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.justinSpringBoot.security.JwtFilter;
import com.example.justinSpringBoot.services.CustomerUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	public DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(authz -> 
			authz.requestMatchers(HttpMethod.POST,"/api/users").permitAll()
			.requestMatchers("/api/users/**").authenticated()
			.anyRequest().permitAll()	
			)
	//	.formLogin(form-> form.permitAll().defaultSuccessUrl("/dashboard"))
		.csrf(csrf-> csrf.disable())
		.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
//		session management
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailService() {
//		PasswordEncoder passwordEncoder = passwordEncoder();
//		UserDetails user = User.withUsername("justin").password(passwordEncoder.encode("justin@77"))
//				.roles("USER").build();
//		UserDetails admin = User.withUsername("admin").password(passwordEncoder.encode("admin@123"))
//				.roles("ADMIN").build();
//		return new InMemoryUserDetailsManager(user,admin);
		
		return new CustomerUserDetailsService();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticateManager() {
		return new ProviderManager(List.of(authenticationProvider()));
	}
}
