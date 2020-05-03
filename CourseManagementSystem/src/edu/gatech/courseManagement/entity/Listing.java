package edu.gatech.courseManagement.entity;

public class Listing {

    private long programID;
    private long courseID;
    private Degree program;

    public Degree getProgram() {
        return program;
    }

    public void setProgram(Degree program) {
        this.program = program;
    }

    public long getProgramID() {
        return programID;
    }

    public void setProgramID(long programID) {
        this.programID = programID;
    }

    public long getCourseID() {
        return courseID;
    }

    public void setCourseID(long courseID) {
        this.courseID = courseID;
    }
}
