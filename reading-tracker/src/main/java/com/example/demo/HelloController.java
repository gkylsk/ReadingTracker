package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("api/home")
public class HelloController {

	@GetMapping
	public String home() {
		return "home";
	}
	
	@GetMapping("view")
	public String view(@RequestParam Long id) {
		return "viewBook";
	}
	
	@GetMapping("add")
	public String addBook() {
		return "addBook";
	}
	
	@GetMapping("update")
	public String updateBook() {
		return "updateBook";
	}
	
	@GetMapping("genres")
	public String booksByGenre() {
		return "genres";
	}
}
