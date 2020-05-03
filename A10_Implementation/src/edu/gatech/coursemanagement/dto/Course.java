package edu.gatech.coursemanagement.dto;

import java.math.BigDecimal;

public class Course {

	// Attributes for Course
	private long courseID ;
	private String name;
	private BigDecimal cost;
	private String description;

	// Get and set methods for each attributes
	public void setcourseID( long courseID) {
		this.courseID = courseID; 
	}
	
	public long getcourseID() {
		return this.courseID;
	}
	
	public void setname( String name) {
		this.name = name; 
	}
	
	public String getname() {
		return this.name;
	}
	
	public void setcost( BigDecimal cost) {
		this.cost = cost; 
	}
	
	public BigDecimal getcost() {
		return this.cost;
	}
	
	public void setdescription( String description) {
		this.description = description; 
	}
	
	public String getdescription() {
		return this.description;
	}
	
	public Course() {
		
	}
}
