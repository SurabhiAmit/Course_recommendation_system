package edu.gatech.coursemanagement.entity;

import edu.gatech.coursemanagement.utils.TermHelper;

import java.util.ArrayList;

/**
 * CS6310 - Team 45
 * edu.gatech.coursemanagement.entity.ApplicationState.java
 */

public class ApplicationState {

    private int currentlyProcessingTerm;
    private String currentlyProcessingFile, simulationStep;
    private ApplicationMode currentMode;
    private ProcessState currentProcessState;
    ArrayList<String> filesToProcess = new ArrayList<>();
    ArrayList<String> fileProcessed = new ArrayList<>();
    public String getfilesProcessed() {
    	String files ="";
    	for(String f:fileProcessed) {
    		files = files + f + "," ;
    	}
    	
    	return files;
    }
    
    public String getfilesToProcess() {
    	String files ="";
    	for(String f:filesToProcess) {
    		files = files + f + "," ;
    	}
    	
    	return files;
    }

    public void startNewTerm() {
        currentlyProcessingTerm++;
        currentProcessState = ProcessState.Started;
    }

    public Term getTerm() {
        return TermHelper.getTerm(currentlyProcessingTerm);
    }

    public int getYear() {
        return TermHelper.getYear(currentlyProcessingTerm);
    }
    
    public int getCurrentlyProcessingTerm() {
        return currentlyProcessingTerm;
    }

    public void setCurrentlyProcessingTerm(int currentlyProcessingTerm) {
        this.currentlyProcessingTerm = currentlyProcessingTerm;
    }

    public String getCurrentlyProcessingFile() {
        return currentlyProcessingFile;
    }

    public void setCurrentlyProcessingFile(String currentlyProcessingFile) {
        this.currentlyProcessingFile = currentlyProcessingFile;
    }

    public String getSimulationStep() {
        return simulationStep;
    }

    public void setSimulationStep(String simulationStep) {
        this.simulationStep = simulationStep;
    }

    public ApplicationMode getCurrentMode() {
        return currentMode;
    }

    public void setCurrentMode(ApplicationMode currentMode) {
        this.currentMode = currentMode;
    }

    public ProcessState getCurrentProcessState() {
        return currentProcessState;
    }

    public void setCurrentProcessState(ProcessState currentProcessState) {
        this.currentProcessState = currentProcessState;
    }

    public void saveState() {

        // TODO: Method to save the current state of the system

    }

}
