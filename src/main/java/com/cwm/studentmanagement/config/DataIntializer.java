package com.cwm.studentmanagement.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cwm.studentmanagement.model.Users;
import com.cwm.studentmanagement.repository.UsersRepository;

/*
 * Copyright (c) 2026 Mahesh Shet
 * Licensed under the MIT License.
 */

@Configuration
public class DataIntializer {
	
	@Bean
	CommandLineRunner loadSampleData(UsersRepository usersRepository,
			PasswordEncoder passwordEncoder) {
		
		return args -> {
			if(!usersRepository.existsByUsername("Admin")) {
				Users users = new Users();
				users.setUsername("Admin");
				users.setPassword(passwordEncoder.encode("admin@123"));
				users.setActive(true);
				usersRepository.save(users);
			}
		};
	}

}
