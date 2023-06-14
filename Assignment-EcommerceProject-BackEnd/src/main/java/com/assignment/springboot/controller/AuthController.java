package com.assignment.springboot.controller;


import com.assignment.springboot.dto.request.LoginRequest;
import com.assignment.springboot.dto.request.UserDtoRequest;
import com.assignment.springboot.entity.User;
import com.assignment.springboot.security.jwt.JwtProvider;
import com.assignment.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;
	
	private final UserService userService;
	
	@Autowired
	public AuthController(AuthenticationManager authenticationManager, JwtProvider jwtTokenUtil, UserService userService) {
		this.authenticationManager = authenticationManager;
		this.jwtProvider = jwtTokenUtil;
		this.userService = userService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody LoginRequest request) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
			
			User user = userService.findByEmail(request.getEmail());
			String userRole = user.getRoles().iterator().next().name();
			String token = jwtProvider.createToken(request.getEmail(), userRole);
			
			Map<Object, Object> response = new HashMap<>();
			response.put("email", request.getEmail());
			response.put("token", token);
			response.put("userRole", userRole);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		} catch (AuthenticationException e) {
			return new ResponseEntity<>("Incorrect password or email", HttpStatus.FORBIDDEN);
		}
	}
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody UserDtoRequest userDtoRequest, BindingResult bindingResult){
		if (bindingResult.hasErrors()){
			return ResponseEntity.badRequest().build();
		}
		userService.registerUser(userDtoRequest);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	@PostMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		securityContextLogoutHandler.logout(request, response, null);
	}
}
