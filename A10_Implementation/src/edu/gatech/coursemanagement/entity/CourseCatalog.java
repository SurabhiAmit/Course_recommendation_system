package edu.gatech.coursemanagement.entity;

/**
 * CS6310 - Team 45
 * edu.gatech.coursemanagement.entity.CourseCatalog.java
 */

import edu.gatech.coursemanagement.exception.CourseManagementRuntimeException;

import java.util.ArrayList;

public class CourseCatalog {

    private long id;
    private int year;
    private Term currentTerm;
    private ArrayList<Course> courseList = new ArrayList<>();

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Term getCurrentTerm() {
        return currentTerm;
    }

    public void setCurrentTerm(Term currentTerm) {
        this.currentTerm = currentTerm;
    }

    public ArrayList<Course> searchCatalog(String keyword) throws CourseManagementRuntimeException {
        ArrayList<Course> searchResults = new ArrayList<Course>();
        if (keyword.equalsIgnoreCase(""))
            return courseList;

        for (Course course : courseList) {
            if (course.getCourseTitle().contains(keyword)) {
                searchResults.add(course);
            }
        }

        if (searchResults.size() > 0) {
            return searchResults;
        }
        else {
            throw new CourseManagementRuntimeException("Course not found");
        }
    }

    public Course findCourseById(long courseId) throws CourseManagementRuntimeException{
        for (Course course : courseList) {
            if (course.getCourseId() == courseId) {
                return course;
            }
        }
        throw new CourseManagementRuntimeException("Course Id not found");
    }

    public void addCourse(Course course) throws CourseManagementRuntimeException{
        if (courseList.contains(course)) {
            throw new CourseManagementRuntimeException("Course already in list");
        }
        else {
            courseList.add(course);
        }
    }

    public void updateCourse(Course course) throws CourseManagementRuntimeException {
        if (courseList.contains(course)) {
            // TODO: Update course information in course catalog
        }
        else {
            throw new CourseManagementRuntimeException("Course is not in catalog");
        }
    }

    public void removeCourse(Course course) throws CourseManagementRuntimeException {
        if (courseList.contains(course)) {
            courseList.remove(course);
        }
        else {
            throw new CourseManagementRuntimeException("Course is not in catalog");
        }
    }

}