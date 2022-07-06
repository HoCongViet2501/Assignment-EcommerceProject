package com.assignment.springboot.data.dto;

import com.assignment.springboot.data.entity.Product;
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
}
