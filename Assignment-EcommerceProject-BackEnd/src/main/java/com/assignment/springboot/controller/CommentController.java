package com.assignment.springboot.controller;

import com.assignment.springboot.dto.requestdto.CommentDtoRequest;
import com.assignment.springboot.dto.responsedto.CommentDtoResponse;
import com.assignment.springboot.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user/comment/")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping()
	@Operation(summary = "create comment")
	@ApiResponse(responseCode = "200", description = "created.comment")
	public ResponseEntity<CommentDtoResponse> createComment(@Valid @RequestBody CommentDtoRequest commentDtoRequest) {
		CommentDtoResponse response=commentService.saveComment(commentDtoRequest);
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "update comment")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "updated.comment"),
			@ApiResponse(responseCode = "404", description = "not.found.comment")
	}
	)
	public ResponseEntity<CommentDtoResponse> updateComment(@Valid @RequestBody CommentDtoRequest commentDtoRequest, @PathVariable int id) {
		CommentDtoResponse response=commentService.updateComment(commentDtoRequest, id);
		return ResponseEntity.ok().body(response);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "delete comment")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "deleted.comment"),
			@ApiResponse(responseCode = "404", description = "not.found.comment")
	})
	public ResponseEntity<String> removeComment(@PathVariable int id) {
		commentService.deleteComment(id);
		return ResponseEntity.ok().body("delete.success.comment.have.id " + id);
	}
}
