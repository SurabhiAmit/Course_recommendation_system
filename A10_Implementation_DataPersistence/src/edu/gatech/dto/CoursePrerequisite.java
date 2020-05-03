package edu.gatech.dto;

public class CoursePrerequisite {

	// Attributes for Degree Program
	private int id;
	private int prerequisiteID ;
	private int courseID;

	// Get and set methods for each attributes
	public void setprerequisiteID( int prerequisiteID) {
		this.prerequisiteID = prerequisiteID; 
	}
	
	public int getprerequisiteID() {
		return this.prerequisiteID;
	}
	
	public void setcourseID( int courseID) {
		this.courseID = courseID; 
	}
	
	public int getcourseID() {
		return this.courseID;
	}
	
	
	public void setID( int ID) {
		this.id = ID; 
	}
	
	public int getID() {
		return this.id;
	}
	
	public CoursePrerequisite() {
		
	}
}	
