package com.example.justinSpringBoot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class HomeController {

	@GetMapping("/")
	public String HomePage() {
		return "Welcome to my homepage";
	}
	
	@GetMapping("/dashboard")
	public String dashPage() {
		return "<div style=' background-color: green'><h1>hi</h1></div>";
	}
}
