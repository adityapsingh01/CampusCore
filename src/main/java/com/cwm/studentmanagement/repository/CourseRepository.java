package com.cwm.studentmanagement.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cwm.studentmanagement.model.Courses;

/*
 * Copyright (c) 2026 Mahesh Shet
 * Licensed under the MIT License.
 */

public interface CourseRepository extends JpaRepository<Courses, Long> {

	boolean existsByCourseCodeIgnoreCase(String code);
	
	boolean existsByCourseCodeIgnoreCaseAndIdNot(String code, Long id);
	
	Page<Courses> findByActiveTrue(Pageable pageable);
	
	List<Courses> findByActiveTrue(Sort sort);
}
