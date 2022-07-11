package com.assignment.springboot.controller;

import com.assignment.springboot.dto.request.LoginRequest;
import com.assignment.springboot.dto.response.JwtResponse;
import com.assignment.springboot.repository.AccountRepository;
import com.assignment.springboot.service.impl.UserDetailsImpl;
import com.assignment.springboot.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	AuthenticationManager authenticationManager;
	JwtTokenUtil jwtTokenUtil;
	@Autowired
	AccountRepository accountRepository;
	
//	@Autowired
//	public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
//		this.authenticationManager = authenticationManager;
//		this.jwtTokenUtil = jwtTokenUtil;
//	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getGmail(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// generate jwt to return to client
		String jwt = jwtTokenUtil.generateAccessToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getGmail(),
				roles));
	}
	
	@GetMapping("/test")
	public ResponseEntity<?> getAccount(@RequestParam String gmail) {
		return ResponseEntity.ok().body(accountRepository.findByGmail(gmail));
	}
}
