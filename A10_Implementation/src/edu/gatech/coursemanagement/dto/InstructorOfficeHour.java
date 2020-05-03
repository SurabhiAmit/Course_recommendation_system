package edu.gatech.coursemanagement.dto;


public class InstructorOfficeHour {

	// Attributes for Course
	private int id ;
	private int uuiid;
	private String officehours;

	// Get and set methods for each attributes
	public void setid( int id) {
		this.id = id; 
	}
	
	public int getid() {
		return this.id;
	}
	
	public void setuuiid( int uuiid) {
		this.uuiid = uuiid; 
	}
	
	public int getuuiid() {
		return this.uuiid;
	}
	
	public void setofficehours( String officehours) {
		this.officehours = officehours; 
	}
	
	public String getofficehours() {
		return this.officehours;
	}
	
	public InstructorOfficeHour() {
		
	}
}
