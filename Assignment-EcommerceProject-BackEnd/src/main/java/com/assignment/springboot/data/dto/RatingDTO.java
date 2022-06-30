package com.assignment.springboot.data.dto;
import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class RatingDTO {
    private int id;
    private int voteStar;
    private CustomerDTO customer;
    private ProductDTO product;
}
