package com.assignment.springboot.controller;


import com.assignment.springboot.dto.request.LoginRequest;
import com.assignment.springboot.dto.request.UserDtoRequest;
import com.assignment.springboot.dto.response.LoginResponse;
import com.assignment.springboot.dto.response.UserResponse;
import com.assignment.springboot.service.AuthService;
import com.assignment.springboot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = "login user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "login successfully!"),
            @ApiResponse(responseCode = "401", description = "incorrect username or password!")
    })
    public ResponseEntity<Object> authenticate(@RequestBody LoginRequest request) {
        LoginResponse loginResponse = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.accepted().body(loginResponse);
    }

    @PostMapping("/register")
    @Operation(summary = "register user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "20", description = "login successfully!"),
            @ApiResponse(responseCode = "401", description = "incorrect username or password!")
    })
    public ResponseEntity<?> register(@Valid @RequestBody UserDtoRequest userDtoRequest) {
        UserResponse userResponse = this.authService.register(userDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
