package com.cwm.studentmanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.cwm.studentmanagement.config.ModelMapperConfig;
import com.cwm.studentmanagement.dto.StudentDTO;
import com.cwm.studentmanagement.service.StudentService;

import jakarta.validation.Valid;

/*
 * Copyright (c) 2026 Mahesh Shet
 * Licensed under the MIT License.
 */

@Controller
@RequestMapping("/students")
public class StudentController {

    private final ModelMapperConfig modelMapperConfig;
	
	private static final Logger log = LoggerFactory.getLogger(StudentController.class);
	
	private final StudentService studentService;
	
	public StudentController(StudentService studentService, ModelMapperConfig modelMapperConfig) {
		this.studentService = studentService;
		this.modelMapperConfig = modelMapperConfig;
	}
	
	@GetMapping("/new")
	public String showCreateStudent(Model model) {
		log.info("Get /new - showing create student page");
		
		model.addAttribute("studentDto", new StudentDTO());
		return "add-student";
	}
	
	@GetMapping("/list")
	public String listStudent(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size,
			Model model,
			@RequestParam(value="message", required = false) String message) 
	{
		log.info("Get /list - showing list student page");
		
		Page<StudentDTO> students = studentService.getStudents(page, size);
		model.addAttribute("students", students);
		model.addAttribute("message", message);
		
		return "students";
	}
	
	@PostMapping("/save")
	public String createStudent(@Valid @ModelAttribute("studentDto") StudentDTO studentDTO,
			BindingResult bindingResult,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		log.info("Post /save - create student request received");
		
		if(bindingResult.hasErrors()) {
			return "add-student";
		}
		
		if(studentService.existsByEmailIgnoreCase(studentDTO.getEmail())) {
			log.error("Post /save - email must be unique.");
			
			bindingResult.rejectValue("email", null, "email must be unique");
			
			return "add-student";
		}
		
		
		studentService.createStudent(studentDTO);
		redirectAttributes.addAttribute("message", "Student is added successfully!!");
		
		return "redirect:/students/list";
		
	}
	
	@GetMapping("/{id}")
	public String getStudentById(@PathVariable Long id, Model model) {
		StudentDTO student = studentService.getStudentById(id);
		model.addAttribute("student", student);
		
		return "view-student";
		
	}
	
	
	@GetMapping("/{id}/edit")
	public String editStudent(@PathVariable Long id, Model model) {
		StudentDTO student = studentService.getStudentById(id);
		model.addAttribute("studentDto", student);
		
		return "edit-student";
		
	}
	
	@PostMapping("/{id}/update")
	public String updateStudent(@PathVariable Long id, 
			@Valid @ModelAttribute("studentDto") StudentDTO studentDTO,
			BindingResult bindingResult,
			Model model,
			RedirectAttributes redirectAttributes) {
		
		log.info("Post /update - update student request received");
		
		if(bindingResult.hasErrors()) {
			return "edit-student";
		}
		
		if(studentService.existsByEmailIgnoreCaseAndIdNot(studentDTO.getEmail(), id)) {
			log.error("Post /update - email must be unique.");
			
			bindingResult.rejectValue("email", null, "email must be unique");
			
			return "edit-student";
		}
		
		
		studentService.updateStudent(id, studentDTO);
		redirectAttributes.addAttribute("message", "Student is updated successfully!!");
		
		return "redirect:/students/list";
		
		
	}
	
	
	
	
	
	
	
	
	

}
