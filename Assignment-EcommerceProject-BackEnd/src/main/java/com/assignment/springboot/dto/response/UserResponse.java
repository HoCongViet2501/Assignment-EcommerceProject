package com.assignment.springboot.dto.response;

import com.assignment.springboot.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserResponse {
	private long id;
	
	private String userName;
	
	private String gmail;
	
	private String phoneNumber;
	
	private String address;
	
	private String passWord;
	
	private Set<Role> roles;
}
