package com.example.justinSpringBoot.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.justinSpringBoot.entity.userEntity;

@Component
public class CustomerUserDetailsService implements UserDetailsService {
	
	@Autowired
	private com.example.justinSpringBoot.repos.userRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		userEntity user = repo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		
		
		return new User(user.getUsername(),user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("User_Role")));
	}

}
