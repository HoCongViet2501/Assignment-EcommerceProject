package com.assignment.springboot.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "file_type")
    private String fileType;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private byte[] file;


}
