package com.example.demo.Users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
	
	@GetMapping("/api/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/api/register")
	public String register() {
		return "register";
	}
}
