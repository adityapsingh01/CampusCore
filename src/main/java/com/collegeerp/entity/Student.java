package com.collegeerp.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String studentId;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    // Year of study (e.g., 1,2,3,4)
    @Column(nullable = false)
    private Integer year;

    // One-to-one with User
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    // Many-to-one with Department
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    // One-to-many with Attendance (assuming we have Attendance entity)
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attendance> attendances = new ArrayList<>();

    // One-to-many with Grade (assuming we have Grade entity)
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grade> grades = new ArrayList<>();

    // Constructors
    public Student() {}

    public Student(String studentId, String firstName, String lastName, Integer year, User user, Department department) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = year;
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    // Helper methods to add/remove attendance and grade
    public void addAttendance(Attendance attendance) {
        attendances.add(attendance);
        attendance.setStudent(this);
    }

    public void removeAttendance(Attendance attendance) {
        attendances.remove(attendance);
        attendance.setStudent(null);
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
        grade.setStudent(this);
    }

    public void removeGrade(Grade grade) {
        grades.remove(grade);
        grade.setStudent(null);
    }

    // toString, equals, hashCode
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", year=" + year +
                ", user=" + (user != null ? user.getId() : null) +
                ", department=" + (department != null ? department.getId() : null) +
                ", attendancesSize=" + attendances.size() +
                ", gradesSize=" + grades.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(studentId, student.studentId) &&
                Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(year, student.year) &&
                Objects.equals(user, student.user) &&
                Objects.equals(department, student.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, firstName, lastName, year, user, department);
    }
}