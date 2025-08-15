package com.cwm.studentmanagement.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentSummaryDTO {
	
	  private Long studentId;
	  private String studentName;
	  private String email;
	  private int courseCount;
	  private BigDecimal totalFee;
	  private List<CourseDTO> courseList = new ArrayList<>();
	  
	  public Long getStudentId() {
		  return studentId;
	  }
	  public void setStudentId(Long studentId) {
		  this.studentId = studentId;
	  }
	  public String getStudentName() {
		  return studentName;
	  }
	  public void setStudentName(String studentName) {
		  this.studentName = studentName;
	  }
	  public String getEmail() {
		  return email;
	  }
	  public void setEmail(String email) {
		  this.email = email;
	  }
	  public int getCourseCount() {
		  return courseCount;
	  }
	  public void setCourseCount(int courseCount) {
		  this.courseCount = courseCount;
	  }
	  public BigDecimal getTotalFee() {
		  return totalFee;
	  }
	  public void setTotalFee(BigDecimal totalFee) {
		  this.totalFee = totalFee;
	  }
	  public List<CourseDTO> getCourseList() {
		  return courseList;
	  }
	  public void setCourseList(List<CourseDTO> courseList) {
		  this.courseList = courseList;
	  }
	  
	  

}
