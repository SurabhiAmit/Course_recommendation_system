package edu.gatech.coursemanagement.utils;

import edu.gatech.coursemanagement.entity.Course;
import edu.gatech.coursemanagement.entity.Student;

public class CourseRequest {

    private Student student;
    private Course course;
    private String validationMessage;
    private boolean isGranted;

    public CourseRequest(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.validationMessage = "Waiting for validation...";
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage, boolean isRequestGranted) {
        this.validationMessage = validationMessage;
        this.isGranted = isRequestGranted;
    }

    public boolean isGranted() {
        return isGranted;
    }

    public void setGranted(boolean granted) {
        isGranted = granted;
    }
}
