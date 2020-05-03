package edu.gatech.coursemanagement.dto;

public class Instructor {

	// Attributes for Instructor
	private long UUIID;
	private String name;
	private String email;

	// Get and set methods for each attributes
	public void setUUIID( long uuiid) {
		this.UUIID = uuiid; 
	}
	
	public long getUUIID() {
		return this.UUIID;
	}
	
	public void setname( String name) {
		this.name = name; 
	}
	
	public String getname() {
		return this.name;
	}
	
	public void setemail( String email) {
		this.email = email; 
	}
	
	public String getemail() {
		return this.email;
	}
	
	public Instructor() {
		
	}
}
