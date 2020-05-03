package edu.gatech.coursemanagement.dto;

public class CoursePrerequisite {

	// Attributes for Degree Program
	private long id;
	private long prerequisiteID ;
	private long courseID;

	// Get and set methods for each attributes
	public void setprerequisiteID( long prerequisiteID) {
		this.prerequisiteID = prerequisiteID; 
	}
	
	public long getprerequisiteID() {
		return this.prerequisiteID;
	}
	
	public void setcourseID( long courseID) {
		this.courseID = courseID; 
	}
	
	public long getcourseID() {
		return this.courseID;
	}
	
	
	public void setID( long ID) {
		this.id = ID; 
	}
	
	public long getID() {
		return this.id;
	}
	
	public CoursePrerequisite() {
		
	}
}	
