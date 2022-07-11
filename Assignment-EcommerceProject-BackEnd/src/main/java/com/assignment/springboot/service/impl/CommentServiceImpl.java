package com.assignment.springboot.service.impl;

import com.assignment.springboot.dto.CommentDTO;
import com.assignment.springboot.entity.Comment;
import com.assignment.springboot.exception.ResourceNotFoundException;
import com.assignment.springboot.repository.CommentRepository;
import com.assignment.springboot.service.CommentService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public CommentDTO saveComment(CommentDTO commentDto) {
		Comment comment=modelMapper.map(commentDto,Comment.class);
		 this.commentRepository.save(comment);
		 return commentDto;
	}
	
	@Override
	public CommentDTO updateComment(CommentDTO commentDTO, int id) {
		this.commentRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("not.found.comment.id "+ id));
		this.commentRepository.save(modelMapper.map(commentDTO,Comment.class));
		return commentDTO;
	}
	
	@Override
	public boolean deleteComment(int id) {
		Comment comment=this.commentRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("not.found.comment.id "+ id));
		this.commentRepository.delete(comment);
		return true;
	}
}
