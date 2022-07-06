package com.assignment.springboot.service.impl;

import com.assignment.springboot.data.entity.Account;
import com.assignment.springboot.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final AccountRepository accountRepository;
	
	@Autowired
	public UserDetailsServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByGmail(username).orElseThrow(
				() -> new UsernameNotFoundException("Not found account have gmail " + username));
		return UserDetailsImpl.build(account);
	}
}
