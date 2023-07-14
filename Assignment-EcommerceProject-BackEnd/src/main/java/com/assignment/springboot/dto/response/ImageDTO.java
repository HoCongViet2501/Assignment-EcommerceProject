package com.assignment.springboot.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDTO {
    private long id;
    private String url;
}
