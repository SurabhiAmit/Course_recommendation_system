package edu.gatech.coursemanagement.entity; /**
 * CS6310 - Team 45
 * edu.gatech.coursemanagement.entity.DegreeProgram.java
 */

import java.util.ArrayList;

public class DegreeProgram {

    int degreeId;
    String name;
    ArrayList<Course> requiredCourses = new ArrayList<>();

    public DegreeProgram(int degreeId, String name) {
        this.degreeId = degreeId;
        this.name = name;
    }

    public int getDegreeId() {
        return degreeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Course> getRequiredCourses() {
        return requiredCourses;
    }

    public void setRequiredCourses(ArrayList<Course> requiredCourses) {
        this.requiredCourses = requiredCourses;
    }

    public void addRequiredCourse(Course course) {
        requiredCourses.add(course);
    }
}