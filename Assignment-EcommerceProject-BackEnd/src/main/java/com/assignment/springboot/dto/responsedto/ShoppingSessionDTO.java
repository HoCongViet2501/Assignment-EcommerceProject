package com.assignment.springboot.dto.responsedto;

import com.assignment.springboot.entity.User;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class ShoppingSessionDTO {
    private long id;
    private User customer;
    private Date createdDate;
    private Date updatedDate;
}
