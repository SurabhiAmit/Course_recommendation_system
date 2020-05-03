package edu.gatech.dto;


public class CourseCatalogCourseList {

	// Attributes for AcademicRecord
	private int id;
	private int coursecatalogid;
	private int courseid;

	// Get and set methods for each attributes
	public void setid( int id) {
		this.id = id; 
	}
	
	public int getid() {
		return this.id;
	}
	
	public void setcoursecatalogid( int ccid) {
		this.coursecatalogid = ccid; 
	}
	
	public int getcoursecatalogid() {
		return this.coursecatalogid;
	}
	
	// Year is set only if it is in-between 1950 and 2017
	public void setcourseid( int cid) {
		this.courseid = cid;
	}
	
	public int getcourseid() {
		return this.courseid;
	}
	
	public CourseCatalogCourseList() {
		
	}
}

