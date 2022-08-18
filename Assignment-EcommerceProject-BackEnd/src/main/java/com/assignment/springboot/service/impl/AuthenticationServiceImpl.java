package com.assignment.springboot.service.impl;

import com.assignment.springboot.entity.User;
import com.assignment.springboot.exceptions.ForbiddenException;
import com.assignment.springboot.exceptions.ResourceNotFoundException;
import com.assignment.springboot.repository.UserRepository;
import com.assignment.springboot.security.jwt.JwtProvider;
import com.assignment.springboot.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;
	private final UserRepository userRepository;
	
	@Autowired
	public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtProvider jwtProvider, UserRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.jwtProvider = jwtProvider;
		this.userRepository = userRepository;
	}
	
	@Override
	public Map<String, Object> login(String email, String password) {
		try{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
			User user=userRepository.findByEmail(email).orElseThrow(
					()-> new ResourceNotFoundException("not.found.user.have.gmail")
			);
			String userRole=user.getRoles().iterator().next().name();
			String token= jwtProvider.createToken(email,userRole);
			Map<String ,Object> response=new HashMap<>();
			response.put("user",user);
			response.put("token",token);
			return response;
		}catch (AuthenticationException e){
			e.printStackTrace();
			throw new ForbiddenException("incorrect.password.or.email",e);
		}
	}
}
