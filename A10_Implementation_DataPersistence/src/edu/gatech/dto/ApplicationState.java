package edu.gatech.dto;

public class ApplicationState {

	
	private int id ;
	private int currentmode ;
	private int currentlyProcessingTerm;
	private String currentlyProcessingFile;
	private int simulationstep;
	private String filesToProcess;
	private String filesProcessed;

	// Get and set methods for each attributes
	public void setid( int id) {
		this.id = id; 
	}
	
	public int getid() {
		return this.id;
	}
	
	public void setcurrentmode( int currentmode) {
		this.currentmode = currentmode; 
	}
	
	public int getcurrentmode() {
		return this.currentmode;
	}
	
	public void setcurrentlyProcessingTerm( int currentlyProcessingTerm) {
		this.currentlyProcessingTerm = currentlyProcessingTerm; 
	}
	
	public int getcurrentlyProcessingTerm() {
		return this.currentlyProcessingTerm;
	}
	
	public void setcurrentlyProcessingFile( String currentlyProcessingFile) {
		this.currentlyProcessingFile = currentlyProcessingFile; 
	}
	
	public String getcurrentlyProcessingFile() {
		return this.currentlyProcessingFile;
	}
	
	public void setsimulationstep( int simulationstep) {
		this.simulationstep = simulationstep; 
	}
	
	public int getsimulationstep() {
		return this.simulationstep;
	}
	
	public void setfilestoprocess( String filestoprocess) {
		this.filesToProcess = filestoprocess; 
	}
	
	public String getfilestoprocess() {
		return this.filesToProcess;
	}
	
	public void setfilesProcessed( String filesProcessed) {
		this.filesProcessed = filesProcessed; 
	}
	
	public String getfilesProcessed() {
		return this.filesProcessed;
	}
	
	public ApplicationState() {
		
	}
}
