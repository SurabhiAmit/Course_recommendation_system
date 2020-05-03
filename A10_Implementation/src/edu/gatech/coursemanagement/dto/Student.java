package edu.gatech.coursemanagement.dto;
public class Student {
	
	// Attributes for Student 
	private long UUSID;
	private String name;
	private String address;
	private long phoneNumber;
	private long programID ;
	
	// Get and set methods for each attributes
	public void setUUSID(long uusid) {
		this.UUSID = uusid; 
	}
	
	public long getUUSID() {
		return this.UUSID;
	}
	
	public void setname( String name) {
		this.name = name; 
	}
	
	public String getname() {
		return this.name;
	}
	
	public void setaddress( String address) {
		this.address = address; 
	}
	
	public String getaddress() {
		return this.address;
	}
	
	public void setphoneNumber( long phone) {
		this.phoneNumber = phone; 
	}
	
	public long getphoneNumber() {
		return this.phoneNumber;
	}
	
	public void setprogramID( long program) {
		this.programID = program; 
	}
	
	public long getprogramID() {
		return this.programID;
	}
	
	public Student() {
		
	}
}
