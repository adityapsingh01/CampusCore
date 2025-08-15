package com.collegeerp.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double score; // e.g., 85.5

    @Column(nullable = false, length = 50)
    private String type; // e.g., EXAM, ASSIGNMENT, QUIZ

    @Column(length = 255)
    private String description;

    // Many-to-one with Student
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Constructors
    public Grade() {}

    public Grade(Double score, String type, String description, Student student) {
        this.score = score;
        this.type = type;
        this.description = description;
        this.student = student;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    // toString, equals, hashCode
    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", score=" + score +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", studentId=" + (student != null ? student.getId() : null) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return Objects.equals(id, grade.id) &&
                Objects.equals(score, grade.score) &&
                Objects.equals(type, grade.type) &&
                Objects.equals(description, grade.description) &&
                Objects.equals(student, grade.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, type, description, student);
    }
}