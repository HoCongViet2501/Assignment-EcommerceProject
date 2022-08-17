package com.assignment.springboot.security;

import com.assignment.springboot.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
@Data
public class UserPrincipal implements UserDetails {
	private final Long id;
	private final String gmail;
	private final String password;
	private final Collection<? extends GrantedAuthority> authorities;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public static UserPrincipal create(User user) {
		String userRole = user.getRoles().iterator().next().toString();
		List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(userRole));
		return new UserPrincipal(user.getId(), user.getEmail(), user.getPassWord(), authorities);
	}
	@Override
	public String getPassword() {
		return this.password;
	}
	
	@Override
	public String getUsername() {
		return this.gmail;
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
