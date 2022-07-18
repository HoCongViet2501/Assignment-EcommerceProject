package com.assignment.springboot.service.impl;

import com.assignment.springboot.dto.requestdto.CommentDtoRequest;
import com.assignment.springboot.dto.responsedto.CommentDtoResponse;
import com.assignment.springboot.entity.Comment;
import com.assignment.springboot.exceptions.ResourceNotFoundException;
import com.assignment.springboot.repository.CommentRepository;
import com.assignment.springboot.service.CommentService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {
	
	private final CommentRepository commentRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
		this.commentRepository = commentRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public CommentDtoResponse saveComment(CommentDtoRequest commentDtoRequest) {
		commentDtoRequest.setCreatedDate(new Date());
		commentDtoRequest.setUpdatedDate(new Date());
		Comment comment=modelMapper.map(commentDtoRequest, Comment.class);
		 this.commentRepository.save(comment);
		 return modelMapper.map(comment,CommentDtoResponse.class);
	}
	
	@Override
	public CommentDtoResponse updateComment(CommentDtoRequest commentDtoRequest, long id) {
		commentDtoRequest.setUpdatedDate(new Date());
		Comment comment=this.commentRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("not.found.comment.id "+ id));
		modelMapper.map(commentDtoRequest,comment);
		this.commentRepository.save(comment);
		return modelMapper.map(comment,CommentDtoResponse.class);
	}
	
	@Override
	public void deleteComment(long id) {
		Comment comment=this.commentRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("not.found.comment.id "+ id));
		this.commentRepository.delete(comment);
	}
}
