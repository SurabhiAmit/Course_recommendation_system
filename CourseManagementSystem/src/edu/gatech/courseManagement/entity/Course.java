package edu.gatech.courseManagement.entity;

import java.util.ArrayList;
public class Course extends CourseCatalog implements Comparable<Course>{
    private long courseID;
    private String courseTitle;
    private String courseDescription;
    private ArrayList<Course> prerequisiteCourse = new ArrayList<Course>();
    private ArrayList<Instructor> instructor = new ArrayList<Instructor>();
    private float cost;
    private int studentCount ;
    private int term;
    private int year;

    public boolean hasPreRequisite()
    {
        return !prerequisiteCourse.isEmpty();
    }

    public boolean isCurrentCourse()
    {
        return !instructor.isEmpty();
    }

    public long getCourseID() {
        return courseID;
    }

    public void setCourseID(long courseID) {
        this.courseID = courseID;
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

    public ArrayList<Course> getPrerequisiteCourse() {
        return prerequisiteCourse;
    }

    public void setPrerequisiteCourse(ArrayList<Course> prerequisiteCourse) {
        this.prerequisiteCourse = prerequisiteCourse;
    }

    public ArrayList<Instructor> getInstructor() {
        return instructor;
    }

    public void setInstructor(ArrayList<Instructor> instructor) {
        this.instructor = instructor;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int compareTo(Course course) {
        if( course.getCourseID() == getCourseID() ) return 0;
        else if( getCourseID() > course.getCourseID() ) return 1;
        else return -1;
    }
}
