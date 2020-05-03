package edu.gatech.coursemanagement.entity;

import edu.gatech.coursemanagement.exception.CourseManagementRuntimeException;

/**
 * CS6310 - Team 45
 * edu.gatech.coursemanagement.entity.Instructor.java
 */

public class Instructor {

    private long uuiid;
    private String instructorName, emailAddress;
    private Course currentCourseTaught;
    private String officeHours;

    public long getUuiid() {
        return uuiid;
    }

    public void setUuiid(long uuiid) {
        this.uuiid = uuiid;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public String getAssignedCourseText() {
        if (currentCourseTaught == null)
            return "None";

        return currentCourseTaught.toString();
    }

    public boolean isCurrentlyInstructing() {

        if (currentCourseTaught != null)
            return true;

        return false;
    }

    public Course getCurrentCourseTaught() {
        return currentCourseTaught;
    }

    public void setCurrentCourseTaught(Course course) {

        if (course != null && isCurrentlyInstructing()) {
            throw new CourseManagementRuntimeException("Instructor is already assigned a course");
        }

        this.currentCourseTaught = course;
    }
}