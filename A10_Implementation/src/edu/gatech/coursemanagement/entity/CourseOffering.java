package edu.gatech.coursemanagement.entity;

import edu.gatech.coursemanagement.exception.CourseManagementRuntimeException;

import java.util.ArrayList;

/**
 * CS6310 - Team 45
 * edu.gatech.coursemanagement.entity.CourseOffering.java
 */

public class CourseOffering extends Course {

    public int totalStudents;
    public int year;
    public Course course;
    public Instructor instructor;
    public Term term;

    private ArrayList<Course> prerequisitesNotMet = new ArrayList<>();

     public int getYear() {
		return this.year;
	}
    
    public int getTerm() {
		return this.term.value;
	}
    
    public int getTotalStudents() {
		return this.totalStudents;
	}
    
    public long getUUIID() {
		return this.instructor.getUuiid();
	}
    
    public long getcourseid() {
		return this.course.getCourseId();
	}
    
    public CourseOffering(Course course, Instructor instructor, Term term, int year) {
        this.course = course;
        this.instructor = instructor;
        this.term = term;
        this.year = year;
    }

    public Course getCourse() {
        return course;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public ArrayList<Course> getPrerequisitesNotMet() {
        return prerequisitesNotMet;
    }

    public boolean isRequestValid(Student student) throws CourseManagementRuntimeException {
        prerequisitesNotMet.clear();
        if (course.prerequisitesMet(student, prerequisitesNotMet)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void enrollStudent(Student student) throws CourseManagementRuntimeException {

        // If isRequestValid = true, enroll the student in course
        if( !isRequestValid(student) )
        {
            throw new CourseManagementRuntimeException("Student request is invalid");
        }
    }
}
