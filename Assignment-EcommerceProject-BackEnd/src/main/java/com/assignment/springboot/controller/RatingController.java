package com.assignment.springboot.controller;

import com.assignment.springboot.dto.RatingDTO;
import com.assignment.springboot.service.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user/rating/")
public class RatingController {
	@Autowired
	private RatingService ratingService;
	
	@PostMapping
	@ApiResponses(
			@ApiResponse(responseCode = "200", description = "create.success")
	)
	@Operation(summary = "create rating")
	public ResponseEntity<RatingDTO> createRating(@Valid @RequestBody RatingDTO ratingDTO) {
		ratingService.saveRating(ratingDTO);
		return ResponseEntity.ok().body(ratingDTO);
	}
	
	@PutMapping("/{id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "update.success"),
			@ApiResponse(responseCode = "404", description = "not.found.rating.id")
	})
	@Operation(summary = "update rating")
	public ResponseEntity<RatingDTO> updateRating(@Valid @RequestBody RatingDTO ratingDTO, @PathVariable int id) {
		ratingService.updateRating(ratingDTO, id);
		return ResponseEntity.ok().body(ratingDTO);
	}
}
