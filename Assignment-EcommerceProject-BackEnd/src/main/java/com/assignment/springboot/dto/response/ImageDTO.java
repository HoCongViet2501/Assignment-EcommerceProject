package com.assignment.springboot.dto.response;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class ImageDTO {
    private long id;
    private String fileType;
    private byte[] file;
    private String url;
}
