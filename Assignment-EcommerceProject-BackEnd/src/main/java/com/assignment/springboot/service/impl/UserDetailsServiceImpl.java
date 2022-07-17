package com.assignment.springboot.service.impl;

import com.assignment.springboot.entity.User;
import com.assignment.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserRepository accountRepository;
	
	@Autowired
	public UserDetailsServiceImpl(UserRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = accountRepository.findByGmail(username).orElseThrow(
				() -> new UsernameNotFoundException("Not.found.account.have.gmail " + username));
		return UserDetailsImpl.build(user);
	}
}
