package com.assignment.springboot.dto.responsedto;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class CategoryDtoResponse {
    private long id;
    private String name;
    private String description;
}
