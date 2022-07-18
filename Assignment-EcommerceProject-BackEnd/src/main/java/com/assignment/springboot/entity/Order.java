package com.assignment.springboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private long id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	private String payment;
	private String status;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "updated_date")
	private Date updatedDate;
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderDetails> orderDetails = new ArrayList<>();
	
	public Order(Integer id, User user, String payment, String status, Date createdDate, Date updatedDate) {
		this.id = id;
		this.user = user;
		this.payment = payment;
		this.status = status;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}
}
