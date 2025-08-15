package com.collegeerp.controller;

import com.collegeerp.entity.Department;
import com.collegeerp.entity.Student;
import com.collegeerp.entity.User;
import com.collegeerp.repository.StudentRepository;
import com.collegeerp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/student/dashboard")
    public String studentDashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("User not found: " + currentUsername));

        Student student = studentRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Student not found for user: " + currentUsername));

        Department department = student.getDepartment();

        model.addAttribute("username", user.getUsername());
        model.addAttribute("fullName", student.getFirstName() + " " + student.getLastName());
        model.addAttribute("departmentName", department.getDeptName());
        model.addAttribute("departmentCode", department.getDeptCode());
        model.addAttribute("year", student.getYear());

        return "student/dashboard";
    }
}