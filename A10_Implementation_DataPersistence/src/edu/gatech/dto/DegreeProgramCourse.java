package edu.gatech.dto;

public class DegreeProgramCourse {

	// Attributes for Degree Program
	private int id;
	private int programID ;
	private int courseID;

	// Get and set methods for each attributes
	public void setprogramID( int programeID) {
		this.programID = programeID; 
	}
	
	public int getprogramID() {
		return this.programID;
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
	
	public DegreeProgramCourse() {
		
	}
}	
