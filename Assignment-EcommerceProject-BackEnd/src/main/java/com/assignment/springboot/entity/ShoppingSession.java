package com.assignment.springboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "shopping_session")
public class ShoppingSession implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private long id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "updated_date")
	private Date updatedDate;
	@OneToMany(mappedBy = "session", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CartDetail> cartDetails = new ArrayList<>();
	
	public ShoppingSession(long id, User user, Date createdDate, Date updatedDate) {
		this.id = id;
		this.user = user;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}
}
