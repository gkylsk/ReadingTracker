package com.example.demo.Users;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Users.JWTService;
import com.example.demo.Users.UserRepository;
import com.example.demo.Users.Users;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JWTService jwtService;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	public void register(String username, String password) {
//		user.setPassword(encoder.encode(user.getPassword()));
//		return userRepository.save(user);
		if (Optional.ofNullable(userRepository.findByUsername(username)).isPresent()) 
			throw new RuntimeException("User exists!");
		userRepository.save(new Users(username, encoder.encode(password)));
	}
	
	public String login(String username, String password) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(username);
        } else {
        	throw new RuntimeException("Invalid Credentials");
//            return "fail";
        }
    }
}
