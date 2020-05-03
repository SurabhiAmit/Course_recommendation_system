package edu.gatech.coursemanagement.entity;

/**
 * CS6310 - Team 45
 * edu.gatech.coursemanagement.entity.Course.java
 */

import java.math.BigDecimal;
import java.util.ArrayList;

public class Course {

    private long courseId;
    private String courseTitle, courseDescription;
    private BigDecimal cost;
    private ArrayList<Course> coursePrerequisites = new ArrayList<>();
    private ArrayList<CourseOffering> courseOfferings = new ArrayList<>();
    //private Instructor assignedInstructor;
    private String preReq;

    public ArrayList<CourseOffering> getCourseOfferings() {
        return courseOfferings;
    }

    public String getPreReq() {
        preReq = "";
        for(Course preReqCourse: coursePrerequisites)
        {
            if( preReq.equals("") ) {
                preReq = preReqCourse.getCourseId() + "";
            }
            else
            {
                preReq += "," + preReqCourse.getCourseId();
            }
        }
        return preReq;
    }

    public void setPreReq(String preReq) {
        //no-op
    }

    public Course() {
        coursePrerequisites = new ArrayList<>();
    }

    public Course(long courseId, String courseTitle, BigDecimal cost) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.cost = cost;
    }

    /*
    public Instructor getAssignedInstructor() {
        return assignedInstructor;
    }
    */

    /*
    public void setAssignedInstructor(Instructor assignedInstructor) {
        this.assignedInstructor = assignedInstructor;
    }
    */

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void addPrerequisite(Course prerequisite){
        coursePrerequisites.add(prerequisite);
    }

    public ArrayList<Course> getCoursePrerequisites() {
        return coursePrerequisites;
    }

    public boolean prerequisitesMet(Student student, ArrayList<Course> prerequisitesNotMet) {
        boolean pass = true;
        if (coursePrerequisites.isEmpty()) {
            return pass;
        }
        else {
            for (Course course : coursePrerequisites) {
                if (!student.verifyCompletion(course)) {
                    prerequisitesNotMet.add(course);
                    pass = false;
                }

                if (!course.prerequisitesMet(student, prerequisitesNotMet)) {
                    prerequisitesNotMet.add(course);
                    pass = false;
                }
            }
        }
        return pass;
    }

    public CourseOffering getCourseOffering() {
        if (courseOfferings == null || courseOfferings.size() == 0)
            return null;

        // based on requirements, we can assume unlimited class capacity which eliminates the need
        // for multiple course offering, hence assuming a single course offering
        return courseOfferings.get(0);
    }

    public void createCourseOffering(Instructor instructor, Term term, int year) {
        CourseOffering offering = new CourseOffering(this, instructor, term, year);
        courseOfferings.add(offering);
    }

    public void removeCourseOffering() {
        courseOfferings.clear();
    }

    @Override
    public String toString() {
        return "(" + this.courseId + ") " + this.getCourseTitle();
    }
}