package com.assignment.springboot.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CommentDTO {
    private int id;
    @NotNull(message = "required comment")
    private String comment;
    @NotNull(message = "required rating")
    private RatingDTO rating;
}
