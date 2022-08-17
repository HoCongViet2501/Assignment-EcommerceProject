package com.assignment.springboot.service.impl;

import com.assignment.springboot.dto.request.RatingDtoRequest;
import com.assignment.springboot.dto.response.RatingDtoResponse;
import com.assignment.springboot.entity.Rating;
import com.assignment.springboot.exceptions.ResourceNotFoundException;
import com.assignment.springboot.repository.RatingRepository;
import com.assignment.springboot.service.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RatingServiceImpl implements RatingService {
	private final RatingRepository ratingRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public RatingServiceImpl(RatingRepository ratingRepository, ModelMapper modelMapper) {
		this.ratingRepository = ratingRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public RatingDtoResponse saveRating(RatingDtoRequest ratingDtoRequest) {
		ratingDtoRequest.setCreatedDate(new Date());
		Rating rating = modelMapper.map(ratingDtoRequest, Rating.class);
		this.ratingRepository.save(rating);
		return modelMapper.map(rating, RatingDtoResponse.class);
	}
	
	@Override
	public RatingDtoResponse updateRating(RatingDtoRequest ratingDtoRequest, long id) {
		Rating rating = this.ratingRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("not.found.rating.have.id " + id));
		modelMapper.map(ratingDtoRequest, rating);
		this.ratingRepository.save(rating);
		return modelMapper.map(rating, RatingDtoResponse.class);
	}
}
