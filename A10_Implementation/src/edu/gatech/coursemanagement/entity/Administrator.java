package edu.gatech.coursemanagement.entity;

/**
 * CS6310 - Team 45
 * edu.gatech.coursemanagement.entity.Administrator.java
 */

import edu.gatech.coursemanagement.exception.CourseManagementRuntimeException;
import edu.gatech.coursemanagement.utils.CourseManagementUtil;

import java.io.File;
import java.util.ArrayList;

public class Administrator {

    // Method to assign instructor to course
    public void assignInstructor(Instructor instructor, Course course, Term term, int year)
            throws CourseManagementRuntimeException
    {
        // skip assignment if assigning to same course
        if (instructor.getCurrentCourseTaught() != null) {
            if (instructor.getCurrentCourseTaught().getCourseId() == course.getCourseId())
                return;
        }

        removeInstructorAssignment(instructor);

        // Verify course is not being offered already, instructor is not teaching course and
        // assign instructor to course
        CourseOffering courseOffering = course.getCourseOffering();
        if (courseOffering != null) {
            throw new CourseManagementRuntimeException( "Course already has an instructor assigned" );
        }

        if(instructor.isCurrentlyInstructing()) {
            throw new CourseManagementRuntimeException( "Instructor is already assigned a course" );
        }

        course.createCourseOffering(instructor, term, year);
        instructor.setCurrentCourseTaught(course);
    }

    public void removeInstructorAssignment(Instructor instructor) {
        if (!instructor.isCurrentlyInstructing())
            return;

        instructor.getCurrentCourseTaught().removeCourseOffering();
        instructor.setCurrentCourseTaught(null);
    }

    // Method to register student in course
    public void registerStudent(Student student, Course course)
            throws CourseManagementRuntimeException
    {

        // Verify course is offered
        // Verify student meets all requirements.
        boolean assigned = false;
        for (CourseOffering offering: course.getCourseOfferings() )
        {
            try {
                offering.enrollStudent(student);
                assigned = true;
                break;
            }catch(CourseManagementRuntimeException e)
            {
                CourseManagementUtil.logWarn(Administrator.class, e.getMessage());
            }
        }
        if( !assigned )
        {
            throw new CourseManagementRuntimeException("Student could not be registered");
        }
    }

    // Method to generate course report for student
    public File generateCourseReport(Student student)
            throws CourseManagementRuntimeException
    {

        // TODO: Generate a course report for student

        return null; // Placeholder
    }

    // Method to generate payment report for student
    public File generatePaymentreport(Student student)
            throws CourseManagementRuntimeException
    {

        // TODO: Generate a payment report for student

        return null; // Placeholder
    }

    // Method to generate enrollment report for student
    public File generateEnrollmentReport (Student student)
            throws CourseManagementRuntimeException
    {

        // TODO: Generate a enrollment report for student

        return null; // Placeholder
    }

}