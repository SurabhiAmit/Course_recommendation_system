package edu.gatech.courseManagement.entity;
import java.util.ArrayList;
public class Degree {
    private long programID;
    private String name;
    private ArrayList<Course> requiredCourse = new ArrayList<Course>();
    private int studentCount;

    public long getProgramID() {
        return programID;
    }

    public void setProgramID(long ProgramID) {
        programID = ProgramID;
    }

    public ArrayList<Course> getRequiredCourse() {
        return requiredCourse;
    }

    public void setRequiredCourse(ArrayList<Course> requiredCourse) {
        this.requiredCourse = requiredCourse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }
}
