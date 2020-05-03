package edu.gatech.dto;

public class Course {

	// Attributes for Course
	private Integer courseID ;
	private String name;
	private int cost;
	private String description;

	// Get and set methods for each attributes
	public void setcourseID( Integer courseID) {
		this.courseID = courseID; 
	}
	
	public Integer getcourseID() {
		return this.courseID;
	}
	
	public void setname( String name) {
		this.name = name; 
	}
	
	public String getname() {
		return this.name;
	}
	
	public void setcost( int cost) {
		this.cost = cost; 
	}
	
	public int getcost() {
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
