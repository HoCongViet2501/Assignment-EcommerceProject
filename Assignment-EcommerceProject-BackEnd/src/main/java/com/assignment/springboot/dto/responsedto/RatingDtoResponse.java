package com.assignment.springboot.dto.responsedto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class RatingDtoResponse {
    private long id;
    private int voteStar;
    private CustomerDtoResponse customer;
    private ProductDtoResponse product;
    private Date createdDate;
}
