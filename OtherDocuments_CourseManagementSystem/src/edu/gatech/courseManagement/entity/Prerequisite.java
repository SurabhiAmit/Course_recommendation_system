package edu.gatech.courseManagement.entity;

public class Prerequisite implements Comparable<Prerequisite>{
    private long courseID;
    private long prerequisiteID;
    private Course course;
    private Course preReq;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Course getPreReq() {
        return preReq;
    }

    public void setPreReq(Course preReq) {
        this.preReq = preReq;
    }

    public long getCourseID() {
        return courseID;
    }

    public void setCourseID(long courseID) {
        this.courseID = courseID;
    }

    public long getPrerequisiteID() {
        return prerequisiteID;
    }

    public void setPrerequisiteID(long prerequisiteID) {
        this.prerequisiteID = prerequisiteID;
    }

    @Override
    public int compareTo(Prerequisite prerequisite) {
        if( prerequisite.getCourseID() == getCourseID() &&
                prerequisite.getPrerequisiteID() == getPrerequisiteID() )
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }
}
