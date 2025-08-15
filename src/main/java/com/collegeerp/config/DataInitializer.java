package com.collegeerp.config;

import com.collegeerp.entity.Department;
import com.collegeerp.entity.Faculty;
import com.collegeerp.entity.Role;
import com.collegeerp.entity.Student;
import com.collegeerp.entity.User;
import com.collegeerp.repository.DepartmentRepository;
import com.collegeerp.repository.FacultyRepository;
import com.collegeerp.repository.RoleRepository;
import com.collegeerp.repository.StudentRepository;
import com.collegeerp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
public class DataInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Bean
    public CommandLineRunner initData(PasswordEncoder passwordEncoder) {
        return args -> {
            // 1. Create default roles if they don't exist
            String[] roleNames = {"ROLE_HOD", "ROLE_FACULTY", "ROLE_STUDENT"};
            for (String roleName : roleNames) {
                Role role = roleRepository.findByName(roleName).orElse(null);
                if (role == null) {
                    role = new Role();
                    role.setName(roleName);
                    roleRepository.save(role);
                }
            }

            // 2. Create sample Department if not exists
            Department cseDept = departmentRepository.findByDeptCode("CSE").orElseGet(() -> {
                Department dept = new Department();
                dept.setDeptCode("CSE");
                dept.setDeptName("Computer Science and Engineering");
                return departmentRepository.save(dept);
            });

            // 3. Create sample HOD user
            User hodUser = userRepository.findByUsername("hod_cse").orElse(null);
            if (hodUser == null) {
                hodUser = new User();
                hodUser.setUsername("hod_cse");
                hodUser.setPassword(passwordEncoder.encode("password123"));
                hodUser.setEmail("hod_cse@example.com");
                hodUser.setEnabled(true);
                // Assign ROLE_HOD
                Role hodRole = roleRepository.findByName("ROLE_HOD")
                        .orElseThrow(() -> new RuntimeException("ROLE_HOD not found"));
                hodUser.getRoles().add(hodRole);
                userRepository.save(hodUser);

                // Create Faculty record for HOD
                Faculty hodFaculty = new Faculty();
                hodFaculty.setEmployeeId("HOD001");
                hodFaculty.setFirstName("HOD");
                hodFaculty.setLastName("CSE");
                hodFaculty.setUser(hodUser);
                hodFaculty.setDepartment(cseDept);
                facultyRepository.save(hodFaculty);
            }

            // 4. Create sample Faculty member
            User facultyUser = userRepository.findByUsername("faculty_cse").orElse(null);
            if (facultyUser == null) {
                facultyUser = new User();
                facultyUser.setUsername("faculty_cse");
                facultyUser.setPassword(passwordEncoder.encode("password123"));
                facultyUser.setEmail("faculty_cse@example.com");
                facultyUser.setEnabled(true);
                // Assign ROLE_FACULTY
                Role facultyRole = roleRepository.findByName("ROLE_FACULTY")
                        .orElseThrow(() -> new RuntimeException("ROLE_FACULTY not found"));
                facultyUser.getRoles().add(facultyRole);
                userRepository.save(facultyUser);

                // Create Faculty record
                Faculty faculty = new Faculty();
                faculty.setEmployeeId("FAC001");
                faculty.setFirstName("Faculty");
                faculty.setLastName("CSE");
                faculty.setUser(facultyUser);
                faculty.setDepartment(cseDept);
                facultyRepository.save(faculty);
            }

            // 5. Create sample Student
            User studentUser = userRepository.findByUsername("student_cse").orElse(null);
            if (studentUser == null) {
                studentUser = new User();
                studentUser.setUsername("student_cse");
                studentUser.setPassword(passwordEncoder.encode("password123"));
                studentUser.setEmail("student_cse@example.com");
                studentUser.setEnabled(true);
                // Assign ROLE_STUDENT
                Role studentRole = roleRepository.findByName("ROLE_STUDENT")
                        .orElseThrow(() -> new RuntimeException("ROLE_STUDENT not found"));
                studentUser.getRoles().add(studentRole);
                userRepository.save(studentUser);

                // Create Student record
                Student student = new Student();
                student.setStudentId("STU001");
                student.setFirstName("Student");
                student.setLastName("CSE");
                student.setYear(3);
                student.setUser(studentUser);
                student.setDepartment(cseDept);
                studentRepository.save(student);
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}