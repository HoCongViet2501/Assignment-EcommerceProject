package com.assignment.springboot.service;

import com.assignment.springboot.dto.RatingDTO;

public interface RatingService {
	RatingDTO saveRating(RatingDTO ratingDTO);
	
	RatingDTO updateRating(RatingDTO ratingDTO, int id);
}
