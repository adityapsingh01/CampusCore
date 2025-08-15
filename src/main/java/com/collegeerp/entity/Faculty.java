package com.collegeerp.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "faculties")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String employeeId;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    // One-to-one with User
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    // Many-to-one with Department
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    // Constructors
    public Faculty() {}

    public Faculty(String employeeId, String firstName, String lastName, User user, Department department) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
        this.department = department;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    // toString, equals, hashCode
    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", employeeId='" + employeeId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", user=" + (user != null ? user.getId() : null) +
                ", department=" + (department != null ? department.getId() : null) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(id, faculty.id) &&
                Objects.equals(employeeId, faculty.employeeId) &&
                Objects.equals(firstName, faculty.firstName) &&
                Objects.equals(lastName, faculty.lastName) &&
                Objects.equals(user, faculty.user) &&
                Objects.equals(department, faculty.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, firstName, lastName, user, department);
    }
}