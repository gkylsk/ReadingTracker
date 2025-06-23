package com.example.demo.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTFilter jwtFilter;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
//				.formLogin(httpForm -> {
//					httpForm
//					.loginPage("/login")
//					.loginProcessingUrl("/login")
//					.defaultSuccessUrl("/home",true)
//					.failureUrl("/login.html?error=true")
//					.permitAll();
//				})
				.logout(logout -> logout
			            .logoutSuccessUrl("/api/login?logout=true")
			            .permitAll()
			     )
				.csrf(csrf ->csrf.disable())
				.authorizeHttpRequests(registry -> {
					registry.requestMatchers(
							"/api/register", 
							"/api/login", 
							"/api/home", 
							"/api/home/books", 
							"/api/home/view", 
							"/api/home/view/book", 
							"/api/home/add",
							"/api/home/update", 
							"/api/home/genres",
							"/api/home/genres/list",
							"/api/home/genres/books",
							"/api/home/search",
							"/css/**", 
							"/images/**",
							"/js/**", 
							"/uploads/**"
					)
					.permitAll()
					.anyRequest()
					.authenticated();
				})
//				.httpBasic(Customizer.withDefaults())
				.httpBasic(AbstractHttpConfigurer::disable)
		        .formLogin(AbstractHttpConfigurer::disable)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //makes HTTP stateless. no need to use csrf
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		return provider;
		
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
