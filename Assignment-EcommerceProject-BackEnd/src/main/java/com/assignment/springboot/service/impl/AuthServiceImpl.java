package com.assignment.springboot.service.impl;

import com.assignment.springboot.dto.request.UserDtoRequest;
import com.assignment.springboot.dto.response.LoginResponse;
import com.assignment.springboot.dto.response.UserResponse;
import com.assignment.springboot.entity.User;
import com.assignment.springboot.enums.Role;
import com.assignment.springboot.exceptions.ForbiddenException;
import com.assignment.springboot.repository.UserRepository;
import com.assignment.springboot.security.jwt.JwtProvider;
import com.assignment.springboot.service.AuthService;
import com.assignment.springboot.utils.MappingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthServiceImpl implements AuthService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private JwtProvider jwtProvider;

    @Autowired
    AuthServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public LoginResponse login(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String role = authentication.getAuthorities().iterator().next().getAuthority();
            String token = jwtProvider.createToken(username, String.valueOf(role));
            return new LoginResponse(role, token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ForbiddenException("username or password is incorrect");
        }
    }

    @Override
    public UserResponse register(UserDtoRequest userDtoRequest) {
        try {
            User user = MappingData.mapObject(userDtoRequest, User.class);
            user.setPassWord(passwordEncoder.encode(userDtoRequest.getPassWord()));
            user.setUserName(user.getUserName());
            User userSaved = this.userRepository.save(user);
            return MappingData.mapObject(userSaved, UserResponse.class);
        }catch (Exception e){
            e.printStackTrace();
            throw new ForbiddenException("");
        }

    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {

    }
}
