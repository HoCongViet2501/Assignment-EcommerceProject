package com.assignment.springboot.service;

import com.assignment.springboot.dto.requestdto.RatingDtoRequest;
import com.assignment.springboot.dto.responsedto.RatingDtoResponse;

public interface RatingService {
	RatingDtoResponse saveRating(RatingDtoRequest ratingDTO);
	
	RatingDtoResponse updateRating(RatingDtoRequest ratingDTO, long id);
}
