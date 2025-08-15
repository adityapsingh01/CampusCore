package com.collegeerp.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String deptCode;

    @Column(nullable = false, length = 100)
    private String deptName;

    // Constructors
    public Department() {}

    public Department(String deptCode, String deptName) {
        this.deptCode = deptCode;
        this.deptName = deptName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    // toString, equals, hashCode
    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", deptCode='" + deptCode + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department department = (Department) o;
        return Objects.equals(id, department.id) &&
                Objects.equals(deptCode, department.deptCode) &&
                Objects.equals(deptName, department.deptName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deptCode, deptName);
    }
}