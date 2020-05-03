package edu.gatech.dto;
public class Student {
	
	// Attributes for Student 
	private int UUSID;
	private String name;
	private String address;
	private long phoneNumber;
	private int programID ;
	
	// Get and set methods for each attributes
	public void setUUSID( int uusid) {
		this.UUSID = uusid; 
	}
	
	public int getUUSID() {
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
	
	public void setprogramID( int program) {
		this.programID = program; 
	}
	
	public int getprogramID() {
		return this.programID;
	}
	
	public Student() {
		
	}
}
