package edu.gatech.coursemanagement.data;

public class ApplicationState {
    private int currentTerm;

    public void setCurrentTerm(int value) {
        currentTerm = value;
    }

    public int getCurrentTerm() {
        return currentTerm;
    }
}
