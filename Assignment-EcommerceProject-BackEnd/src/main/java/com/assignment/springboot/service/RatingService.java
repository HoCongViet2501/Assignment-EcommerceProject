package com.assignment.springboot.service;

import com.assignment.springboot.dto.request.RatingDtoRequest;
import com.assignment.springboot.dto.response.RatingDtoResponse;

public interface RatingService {
	RatingDtoResponse saveRating(RatingDtoRequest ratingDTO);
	
	RatingDtoResponse updateRating(RatingDtoRequest ratingDTO, long id);
}
