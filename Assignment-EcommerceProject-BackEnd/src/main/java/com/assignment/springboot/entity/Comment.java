package com.assignment.springboot.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "rating_id")
    private Rating rating;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "updated_dated")
    private Date updatedDate;

}
