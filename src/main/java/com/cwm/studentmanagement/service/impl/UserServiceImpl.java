package com.cwm.studentmanagement.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cwm.studentmanagement.model.Users;
import com.cwm.studentmanagement.repository.UsersRepository;

/*
 * Copyright (c) 2026 Mahesh Shet
 * Licensed under the MIT License.
 */

@Service
public class UserServiceImpl implements UserDetailsService {
	
	private UsersRepository usersRepository;
	
	public UserServiceImpl(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = usersRepository.findByUsername(username)
		.orElseThrow(() -> new UsernameNotFoundException("Invalid username"));
		
		return User.withUsername(username)
				.password(users.getPassword())
				.disabled(!users.isActive())
				.build();
	}
	
}
