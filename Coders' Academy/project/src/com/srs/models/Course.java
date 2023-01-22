package com.srs.models;

public class Course {
	
	int courseId;
	String course;
	String courseDesc;
	int fee;

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Course(int courseId, String course, String courseDesc, int fee) {
		super();
		this.courseId = courseId;
		this.course = course;
		this.courseDesc = courseDesc;
		this.fee = fee;
	}

	@Override
	public String toString() {
		return "| "+this.courseId + " | " + this.course + " | " + this.courseDesc + " | " + this.fee + " |";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}