package com.assignment.springboot.dto;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class RatingDTO {
    private int id;
    @Min(value = 0)
    @Max(value = 5)
    private int voteStar;
    @NotNull(message = "Required")
    private CustomerDTO customer;
    @NotNull(message = "Required")
    private ProductDTO product;
}
