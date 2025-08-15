package com.cwm.studentmanagement.dto;

public class DashboardStatsDTO {
	
	 private long totalStudents;
	 private long totalCourses;
	 private String topPerformingCourse;
	 private long studentsEnrolledThisMonth;
	 
	 public long getTotalStudents() {
		 return totalStudents;
	 }
	 public void setTotalStudents(long totalStudents) {
		 this.totalStudents = totalStudents;
	 }
	 public long getTotalCourses() {
		 return totalCourses;
	 }
	 public void setTotalCourses(long totalCourses) {
		 this.totalCourses = totalCourses;
	 }
	 public String getTopPerformingCourse() {
		 return topPerformingCourse;
	 }
	 public void setTopPerformingCourse(String topPerformingCourse) {
		 this.topPerformingCourse = topPerformingCourse;
	 }
	 public long getStudentsEnrolledThisMonth() {
		 return studentsEnrolledThisMonth;
	 }
	 public void setStudentsEnrolledThisMonth(long studentsEnrolledThisMonth) {
		 this.studentsEnrolledThisMonth = studentsEnrolledThisMonth;
	 }
	 
	 

}
