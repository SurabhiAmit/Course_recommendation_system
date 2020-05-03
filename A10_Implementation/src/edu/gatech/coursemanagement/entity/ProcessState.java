package edu.gatech.coursemanagement.entity;

/**
 * CS6310 - Team 45
 * ProcessState.java
 */

public enum ProcessState {
    Started(1),
    ProcessedRequests(2),
    ValidatedRequests(3),
    AssignedGrades(4),
    Done(5);

    int value;

    ProcessState(int value)
    {
        this.value = value;
    }

    // TODO: Determine status of processing state
    public int getValue(){
        return this.value;
    }

    public static ProcessState getFromInt(int value) {
        if (value == 1)
            return ProcessState.Started;
        if (value == 2)
            return ProcessState.ProcessedRequests;
        if (value == 3)
            return ProcessState.ValidatedRequests;
        if (value == 4)
            return ProcessState.AssignedGrades;
        if (value == 5)
            return ProcessState.Done;

        return ProcessState.Started;
    }
}