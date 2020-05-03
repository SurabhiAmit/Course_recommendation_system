package edu.gatech.coursemanagement.dto;

public class DegreeProgramCourse {

	// Attributes for Degree Program
	private long id;
	private long programID ;
	private long courseID;

	// Get and set methods for each attributes
	public void setprogramID( long programeID) {
		this.programID = programeID; 
	}
	
	public long getprogramID() {
		return this.programID;
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
	
	public DegreeProgramCourse() {
		
	}
}	
