package edu.gatech.coursemanagement.dto;

public class AcademicRecord {

	// Attributes for AcademicRecord
	private long UUSID ;
	private long courseID ;
	private String grade;
	private int term;
	private int year;

	// Get and set methods for each attributes
	public void setUUSID( long uusid) {
		this.UUSID = uusid; 
	}
	
	public long getUUSID() {
		return this.UUSID;
	}
	
	public void setcourseID( long courseID) {
		this.courseID = courseID; 
	}
	
	public long getcourseID() {
		return this.courseID;
	}
	
	public void setgrade( String grade) {
		this.grade = grade; 
	}
	
	public String getgrade() {
		return this.grade;
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
	
	public AcademicRecord() {
		
	}
}
