package edu.gatech.dto;

public class CourseOffering {

	// Attributes for AcademicRecord
	private int id ;
	private int UUIID ;
	private int courseID ;
	private int term;
	private int year;
	private int totalstudents;

	// Get and set methods for each attributes
	
	public void setID( int id) {
		this.id = id; 
	}
	
	public int getID() {
		return this.id;
	}
	
	public void setUUIID( int uuiid) {
		this.UUIID = uuiid; 
	}
	
	public int getUUIID() {
		return this.UUIID;
	}
	
	public void setcourseID( int courseID) {
		this.courseID = courseID; 
	}
	
	public int getcourseID() {
		return this.courseID;
	}
	
	public void setterm( int term) {
		this.term = term; 
	}
	
	public int getterm() {
		return this.term;
	}
	
	// Year is set only if it is in-between 1950 and 2017
	public void setyear( int year) {
		if( year >= 1950 || year <= 2017 ) {
			this.year = year;
		}
	}
	
	public int getyear() {
		return this.year;
	}
	
	public void settotalstudents( int totalstudents) {
		this.totalstudents = totalstudents; 
	}
	
	public int gettotalstudents() {
		return this.totalstudents;
	}
	
	
	public CourseOffering() {
		
	}
}

