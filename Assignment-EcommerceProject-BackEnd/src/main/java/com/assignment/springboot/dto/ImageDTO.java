package com.assignment.springboot.dto;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class ImageDTO {
    private int id;
    private String fileType;
    private byte[] file;
    private String url;
}
