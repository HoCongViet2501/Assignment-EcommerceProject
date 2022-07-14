package com.assignment.springboot.dto.response;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CommentDtoResponse {
	private long id;
	private String comment;
	private RatingDtoResponse ratingDtoResponse;
	private Date createdDate;
	private Date updatedDate;
}
