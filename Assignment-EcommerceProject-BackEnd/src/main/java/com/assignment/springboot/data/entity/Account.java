package com.assignment.springboot.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "accounts")
public class Account implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "password")
	private String passWord;
	private String role;
	private String gmail;
	@OneToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	public Account(int id, String passWord, String role, String gmail, Employee employee, Customer customer) {
		this.id = id;
		this.passWord = passWord;
		this.role = role;
		this.gmail = gmail;
		this.employee = employee;
		this.customer = customer;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	@Override
	public String getPassword() {
		return null;
	}
	
	@Override
	public String getUsername() {
		return null;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return false;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return false;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}
	
	@Override
	public boolean isEnabled() {
		return false;
	}
}