package com.example.demo.Users;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired 
	private UserService userService;

    @PostMapping("/api/register")
    public ResponseEntity<?> register(@RequestBody Users user) {
    	userService.register(user.getUsername(), user.getPassword());
    	System.out.print(user.getUsername());
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        String token = userService.login(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
