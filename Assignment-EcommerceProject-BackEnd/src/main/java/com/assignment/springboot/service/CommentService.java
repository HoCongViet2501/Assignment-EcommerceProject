package com.assignment.springboot.service;

import com.assignment.springboot.dto.request.CommentDtoRequest;
import com.assignment.springboot.dto.response.CommentDtoResponse;

public interface CommentService {
	CommentDtoResponse saveComment(CommentDtoRequest commentDtoRequest);
	CommentDtoResponse updateComment(CommentDtoRequest commentDtoRequest, long id);
	void deleteComment(long id);
}
