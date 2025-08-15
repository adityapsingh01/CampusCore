package com.cwm.studentmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwm.studentmanagement.model.Users;

/*
 * Copyright (c) 2026 Mahesh Shet
 * Licensed under the MIT License.
 */

public interface UsersRepository extends JpaRepository<Users, Long> {

	boolean existsByUsername(String username);
	
	Optional<Users> findByUsername(String username);
}
