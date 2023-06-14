package com.assignment.springboot.service;

import com.assignment.springboot.dto.request.UserDtoRequest;
import com.assignment.springboot.dto.response.LoginResponse;
import com.assignment.springboot.dto.response.UserResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {
    LoginResponse login(String username, String password);

    UserResponse register(UserDtoRequest userDtoRequest);

    void logout(HttpServletRequest request, HttpServletResponse response);
}
