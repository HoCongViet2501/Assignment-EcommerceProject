package com.assignment.springboot.controller;

import com.assignment.springboot.dto.requestdto.LoginRequest;
import com.assignment.springboot.dto.requestdto.RegisterRequest;
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
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getGmail(), request.getPassword()));
			
			User user = userService.findByEmail(request.getGmail());
			String userRole = user.getRoles().iterator().next().name();
			String token = jwtProvider.createToken(request.getGmail(), userRole);
			
			Map<Object, Object> response = new HashMap<>();
			response.put("email", request.getGmail());
			response.put("token", token);
			response.put("userRole", userRole);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		} catch (AuthenticationException e) {
			return new ResponseEntity<>("Incorrect password or email", HttpStatus.FORBIDDEN);
		}
	}
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest, BindingResult bindingResult){
		if (bindingResult.hasErrors()){
			return ResponseEntity.badRequest().build();
		}
		userService.registerUser(registerRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(registerRequest);
	}
	@PostMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		securityContextLogoutHandler.logout(request, response, null);
	}
}
