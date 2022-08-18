package com.assignment.springboot.controller;

import com.assignment.springboot.dto.request.LoginRequest;
import com.assignment.springboot.dto.request.UserDtoRequest;
import com.assignment.springboot.dto.response.LoginResponse;
import com.assignment.springboot.dto.response.UserResponse;
import com.assignment.springboot.service.AuthenticationService;
import com.assignment.springboot.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@CrossOrigin(value = "https://localhost:3000/")
@RequestMapping("/api/auth")
public class AuthController {
	private final AuthenticationService authenticationService;
	private final UserService userService;
	private final ModelMapper modelMapper;
	
	@Autowired
	public AuthController(AuthenticationService authenticationService, UserService userService, ModelMapper modelMapper) {
		this.authenticationService = authenticationService;
		this.userService = userService;
		this.modelMapper = modelMapper;
	}
	
	@PostMapping("/login")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "login.successfully"),
			@ApiResponse(responseCode = "302",description = "Forbidden.login.failed")
	})
	public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginRequest request) {
		Map<String, Object> credentials = authenticationService.login(request.getEmail(), request.getPassword());
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setUser(convertToResponse(credentials.get("user"), UserResponse.class));
		loginResponse.setToken((String) credentials.get("token"));
		return ResponseEntity.ok().body(loginResponse);
	}
	
	@PostMapping("/register")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "login.successfully"),
			@ApiResponse(responseCode = "500",description = "register.failed")
	})
	public ResponseEntity<?> register(@Valid @RequestBody UserDtoRequest registerRequest) {
		userService.registerUser(registerRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body("register.success");
	}
	
	@PostMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		securityContextLogoutHandler.logout(request, response, null);
	}
	
	<T, S> S convertToResponse(T data, Class<S> type) {
		return modelMapper.map(data, type);
	}
}
