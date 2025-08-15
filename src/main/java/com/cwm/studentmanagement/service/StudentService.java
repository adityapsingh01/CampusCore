package com.cwm.studentmanagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cwm.studentmanagement.dto.EnrollmentSummaryDTO;
import com.cwm.studentmanagement.dto.StudentDTO;

/*
 * Copyright (c) 2026 Mahesh Shet
 * Licensed under the MIT License.
 */

public interface StudentService {
	
	boolean existsByEmailIgnoreCase(String email);
	
	boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);

	StudentDTO createStudent(StudentDTO studentDTO);
	
	Page<StudentDTO> getStudents(int page, int size);
	
	StudentDTO getStudentById(Long id);
	
	StudentDTO updateStudent(Long id, StudentDTO studentDTO);
	
	List<StudentDTO> getAllStudents();

}
