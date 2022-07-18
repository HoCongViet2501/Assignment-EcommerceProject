package com.assignment.springboot.service.impl;

import com.assignment.springboot.dto.requestdto.RatingDtoRequest;
import com.assignment.springboot.dto.requestdto.RegisterRequest;
import com.assignment.springboot.dto.requestdto.UserDtoRequest;
import com.assignment.springboot.entity.Product;
import com.assignment.springboot.entity.Rating;
import com.assignment.springboot.entity.User;
import com.assignment.springboot.enums.Role;
import com.assignment.springboot.exceptions.EmailException;
import com.assignment.springboot.exceptions.PasswordException;
import com.assignment.springboot.exceptions.ResourceNotFoundException;
import com.assignment.springboot.repository.ProductRepository;
import com.assignment.springboot.repository.RatingRepository;
import com.assignment.springboot.repository.UserRepository;
import com.assignment.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements  UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final ProductRepository productRepository;
	private final RatingRepository ratingRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ProductRepository productRepository, RatingRepository ratingRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.productRepository = productRepository;
		this.ratingRepository = ratingRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public void addRatingToProduct(RatingDtoRequest ratingDtoRequest, long productId) {
		Product product = productRepository.findById(productId).orElseThrow(
				() -> new ResourceNotFoundException("not.found.productId")
		);
		Rating rating = modelMapper.map(ratingDtoRequest, Rating.class);
		rating.setProduct(product);
		ratingRepository.save(rating);
	}
	
	@Override
	public boolean addUser(UserDtoRequest userDtoRequest) {
		User user = userRepository.findByGmail(userDtoRequest.getGmail()).orElseThrow(
				() -> new ResourceNotFoundException("not.found.productId")
		);
		user.setPassWord(passwordEncoder.encode(userDtoRequest.getPassWord()));
		user.setRoles(Collections.singleton(Role.CUSTOMER));
		userRepository.save(user);
		return true;
	}
	
	@Override
	public User getOne(long id) {
		return userRepository.getUserById(id);
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByGmail(email).orElseThrow(
				() -> new UsernameNotFoundException("not.found.email"));
	}
	
	@Override
	public RegisterRequest registerUser(RegisterRequest registerRequest) {
		if (registerRequest.getPassword() != null && !registerRequest.getPassword().equals(registerRequest.getPasswordConfirm())) {
			throw new PasswordException("password.do.not.match");
		} else if (userRepository.findByGmail(registerRequest.getEmail()).isPresent()) {
			throw new EmailException("email.already.exist");
		}
		User user = modelMapper.map(registerRequest, User.class);
		user.setRoles(Collections.singleton(Role.CUSTOMER));
		user.setPassWord(passwordEncoder.encode(user.getPassWord()));
		userRepository.save(user);
		return registerRequest;
	}
}
