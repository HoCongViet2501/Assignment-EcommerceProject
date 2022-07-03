package com.assignment.springboot.controller;

import com.assignment.springboot.data.entity.Account;
import com.assignment.springboot.data.request.AuthRequest;
import com.assignment.springboot.data.response.AuthResponse;
import com.assignment.springboot.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {
	AuthenticationManager authenticationManager;
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
	}
	
	@PostMapping("/api/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequest authRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getGmail(), authRequest.getPassword()));
			Account account= (Account) authentication.getPrincipal();
			String accessToken= jwtTokenUtil.generateAccessToken(account);
			AuthResponse authResponse=new AuthResponse(account.getGmail(),accessToken);
			return ResponseEntity.ok().body(authResponse);
		}catch (BadCredentialsException e){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}
