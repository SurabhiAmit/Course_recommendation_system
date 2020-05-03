package edu.gatech.coursemanagement.dto;


public class CourseCatalogCourseList {

	// Attributes for AcademicRecord
	private long id;
	private long coursecatalogid;
	private long courseid;

	// Get and set methods for each attributes
	public void setid( long id) {
		this.id = id; 
	}
	
	public long getid() {
		return this.id;
	}
	
	public void setcoursecatalogid( long ccid) {
		this.coursecatalogid = ccid; 
	}
	
	public long getcoursecatalogid() {
		return this.coursecatalogid;
	}
	
	// Year is set only if it is in-between 1950 and 2017
	public void setcourseid( long cid) {
		this.courseid = cid;
	}
	
	public long getcourseid() {
		return this.courseid;
	}
	
	public CourseCatalogCourseList() {
		
	}
}

