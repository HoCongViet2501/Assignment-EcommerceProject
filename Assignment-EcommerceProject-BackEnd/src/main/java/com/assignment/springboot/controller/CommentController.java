package com.assignment.springboot.controller;

import com.assignment.springboot.dto.CommentDTO;
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
	public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO commentDTO) {
		commentService.saveComment(commentDTO);
		return ResponseEntity.ok().body(commentDTO);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "update comment")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "updated.comment"),
			@ApiResponse(responseCode = "404", description = "not.found.comment")
	}
	)
	public ResponseEntity<CommentDTO> updateComment(@Valid @RequestBody CommentDTO commentDTO, @PathVariable int id) {
		commentService.updateComment(commentDTO, id);
		return ResponseEntity.ok().body(commentDTO);
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
