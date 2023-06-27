package com.assignment.springboot.service.impl;

import com.assignment.springboot.dto.request.RatingDtoRequest;
import com.assignment.springboot.dto.request.UserDtoRequest;
import com.assignment.springboot.entity.Product;
import com.assignment.springboot.entity.Rating;
import com.assignment.springboot.entity.User;
import com.assignment.springboot.enums.Role;
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

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
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
        User user = userRepository.findByUsername(userDtoRequest.getUsername()).orElseThrow(
                () -> new ResourceNotFoundException("not.found.username")
        );
        user.setPassword(passwordEncoder.encode(userDtoRequest.getPassword()));
        user.setRole(Role.CUSTOMER);
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
    public User findByEmail(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("not.found.email"));
    }


}
