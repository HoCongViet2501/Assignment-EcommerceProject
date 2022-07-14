package com.assignment.springboot.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class CommentDtoRequest {
	@NotNull(message = "required comment")
	private String comment;
	@NotNull(message = "required rating")
	private long ratingId;
	private Date createdDate;
	private Date updatedDate;
}
