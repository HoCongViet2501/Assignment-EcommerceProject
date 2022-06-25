package com.assignment.springboot.data.dto;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CommentDTO {
    private int id;
    private String comments;
    private RatingDTO rating;
}
