package com.assignment.springboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "ratings")
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "vote_star")
	private int voteStar;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	@OneToMany(mappedBy = "rating", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Comment> comments;
	@Column(name = "created_date")
	private Date createdDate;
	
	public Rating(long id, int voteStar, Customer customer, Product product, Date createdDate) {
		this.id = id;
		this.voteStar = voteStar;
		this.customer = customer;
		this.product = product;
		this.createdDate = createdDate;
	}
}
