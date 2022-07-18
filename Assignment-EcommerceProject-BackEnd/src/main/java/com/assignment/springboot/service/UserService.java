package com.assignment.springboot.service;

import com.assignment.springboot.dto.requestdto.RatingDtoRequest;
import com.assignment.springboot.dto.requestdto.RegisterRequest;
import com.assignment.springboot.dto.requestdto.UserDtoRequest;
import com.assignment.springboot.entity.User;

import java.util.List;

public interface UserService {
	void addRatingToProduct(RatingDtoRequest rating, long productId);
	boolean addUser(UserDtoRequest user);
	
	User getOne(long id);
	
	List<User> findAll();
	
	User findByEmail(String email);
	
	RegisterRequest registerUser(RegisterRequest registerRequest);
}
