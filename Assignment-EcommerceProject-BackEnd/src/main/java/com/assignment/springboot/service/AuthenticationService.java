package com.assignment.springboot.service;

import java.util.Map;

public interface AuthenticationService {
	Map<String, Object> login(String email, String password);
}
