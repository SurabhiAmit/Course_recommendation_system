package edu.gatech.dto;

public class CourseCatalog {

	// Attributes for AcademicRecord
	private int id;
	private int term;
	private int year;

	// Get and set methods for each attributes
	public void setid( int id) {
		this.id = id; 
	}
	
	public int getid() {
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

