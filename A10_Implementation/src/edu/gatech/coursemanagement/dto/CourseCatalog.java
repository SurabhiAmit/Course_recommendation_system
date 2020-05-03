package edu.gatech.coursemanagement.dto;

public class CourseCatalog {

	// Attributes for AcademicRecord
	private long id;
	private int term;
	private int year;

	// Get and set methods for each attributes
	public void setid( long id) {
		this.id = id; 
	}
	
	public long getid() {
		return this.id;
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
	
	public CourseCatalog() {
		
	}
}

