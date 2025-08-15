package com.cwm.studentmanagement.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwm.studentmanagement.model.Enrollment;

/*
 * Copyright (c) 2026 Mahesh Shet
 * Licensed under the MIT License.
 */

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
	
	boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
	
	
	@Query("""
			select count(distinct e.student.id) from 
			Enrollment e 
			where e.enrolledDate between :startDate and :endDate
			""")
	long countDistinctStudentByEnrollDateBetween(@Param("startDate") LocalDateTime startDate, 
						@Param("endDate") LocalDateTime endDate);


}
