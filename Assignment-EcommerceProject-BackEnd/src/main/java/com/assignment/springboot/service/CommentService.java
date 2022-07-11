package com.assignment.springboot.service;

import com.assignment.springboot.dto.CommentDTO;

public interface CommentService {
	CommentDTO saveComment(CommentDTO commentDTO);
	CommentDTO updateComment(CommentDTO commentDTO,int id);
	boolean deleteComment(int id);
}
