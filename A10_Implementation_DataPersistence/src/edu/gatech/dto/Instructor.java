package edu.gatech.dto;

public class Instructor {

	// Attributes for Instructor
	private int UUIID;
	private String name;
	private String email;

	// Get and set methods for each attributes
	public void setUUIID( int uuiid) {
		this.UUIID = uuiid; 
	}
	
	public int getUUIID() {
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
