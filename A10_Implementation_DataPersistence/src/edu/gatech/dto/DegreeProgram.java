package edu.gatech.dto;

public class DegreeProgram {

	// Attributes for Degree Program
	private int programID ;
	private String name;

	// Get and set methods for each attributes
	public void setprogramID( int programeID) {
		this.programID = programeID; 
	}
	
	public int getprogramID() {
		return this.programID;
	}
	
	public void setname( String name) {
		this.name = name; 
	}
	
	public String getname() {
		return this.name;
	}
		
	public DegreeProgram() {
		
	}
}	
