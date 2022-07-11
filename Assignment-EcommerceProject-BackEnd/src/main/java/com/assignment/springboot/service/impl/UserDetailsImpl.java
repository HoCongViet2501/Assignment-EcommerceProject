package com.assignment.springboot.service.impl;

import com.assignment.springboot.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Setter
@Getter
public class UserDetailsImpl implements UserDetails {
	private int id;
	private String gmail;
	@JsonIgnore
	private String passWord;
	private Collection<? extends GrantedAuthority> authorities;
	
	public static UserDetailsImpl build(Account account) {
		List<GrantedAuthority> grantedAuthorities = account.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());
		return new UserDetailsImpl(
				account.getId(),
				account.getGmail(),
				account.getPassWord(),
				grantedAuthorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return passWord;
	}
	
	@Override
	public String getUsername() {
		return gmail;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
}
