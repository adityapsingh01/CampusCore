package com.collegeerp.controller;

import com.collegeerp.entity.Department;
import com.collegeerp.entity.Faculty;
import com.collegeerp.entity.User;
import com.collegeerp.repository.FacultyRepository;
import com.collegeerp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacultyController {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/faculty/dashboard")
    public String facultyDashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("User not found: " + currentUsername));

        Faculty faculty = facultyRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Faculty not found for user: " + currentUsername));

        Department department = faculty.getDepartment();

        model.addAttribute("username", user.getUsername());
        model.addAttribute("fullName", faculty.getFirstName() + " " + faculty.getLastName());
        model.addAttribute("departmentName", department.getDeptName());
        model.addAttribute("departmentCode", department.getDeptCode());

        return "faculty/dashboard";
    }
}