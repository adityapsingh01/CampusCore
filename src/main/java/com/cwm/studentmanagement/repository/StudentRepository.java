package com.cwm.studentmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cwm.studentmanagement.model.Students;

/*
 * Copyright (c) 2026 Mahesh Shet
 * Licensed under the MIT License.
 */

public interface StudentRepository extends JpaRepository<Students, Long> {
	
	boolean existsByEmailIgnoreCase(String email);
	
	boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
	
	Page<Students> findByActiveTrue(Pageable pageable);
	
	List<Students> findByActiveTrue();
	
	
	@EntityGraph(attributePaths = {"enrollments", "enrollments.course"})
	@Query(value = """                      
			select distinct s
			from Students s 
			join s.enrollments e
		""",
		countQuery = """
					select count(distinct s)
					from Students s 
					join s.enrollments e
				""")
	Page<Students> findEnrolledStudents(Pageable pageable);
	
	
	
	@Query("""
			select s
			from Students s
			join fetch s.enrollments e
			join fetch e.course c
			where s.id = :id
			""")
	Optional<Students> findEnrolledStudentCourseDetails(@Param("id") Long id);
	
	
	
	
	
	
	

}
