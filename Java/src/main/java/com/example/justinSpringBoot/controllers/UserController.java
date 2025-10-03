package com.example.justinSpringBoot.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.justinSpringBoot.entity.userEntity;
import com.example.justinSpringBoot.exceptions.resourseNotFound;
//import com.example.justinSpringBoot.models.userModel;
import com.example.justinSpringBoot.repos.userRepo;

@RestController
@RequestMapping("/api/users")	
public class UserController {
	@Autowired
	private userRepo repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public List<userEntity> getUsers() {
//		return Arrays.asList(new userModel(12L,"justin",21),new userModel(10L,"manikiraj",21));
		return repository.findAll();
	}
	@PostMapping
	public userEntity createUser(@RequestBody userEntity user) {
//		System.out.println("User Data: "+ user.getId());
//		System.out.println("User Age: "+ user.getAge());
//		System.out.println("User Name: "+ user.getName());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(user);		
	}
	
	@GetMapping("/{id}")
	public userEntity getUserId(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new resourseNotFound("user not found"+id));
	}
	
	@PutMapping("/{id}")
	public userEntity updateUser(@RequestBody userEntity user,@PathVariable Long id) {
		userEntity data = repository.findById(id).orElseThrow(() -> new resourseNotFound("user not found"+id));
		data.setName(user.getName());
		data.setAge(user.getAge());
		return repository.save(data);
//		return null;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		userEntity data = repository.findById(id).orElseThrow(() -> new resourseNotFound("user not found to delte "+id));
		repository.delete(data);
		return ResponseEntity.ok().build();
	}
}
