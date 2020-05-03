package edu.gatech.courseManagement.entity;

public class Instructor implements Comparable<Instructor>{
    private String name;
    private long UUIID;
    private String officeHour;
    private String emailAddress;
    private Boolean isInstructing;
    private long courseTaught = 0;
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUUIID() {
        return UUIID;
    }

    public void setUUIID(long UUIID) {
        this.UUIID = UUIID;
    }

    public String getOfficeHour() {
        return officeHour;
    }

    public void setOfficeHour(String officehour) {
        this.officeHour = officehour;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Boolean getInstructing() {
        return isInstructing;
    }

    public void setInstructing(Boolean instructing) {
        isInstructing = instructing;
    }

    public long getCourseTaught() {
        return courseTaught;
    }

    public void setCourseTaught(long courseTaught) {
        this.courseTaught = courseTaught;
    }

    //Domain method
    public boolean isTeachingThisSemester()
    {
        return getCourseTaught() != 0;      //will return true if teaching any course this semester
    }

    @Override
    public int compareTo(Instructor instructor) {
        if( instructor.getUUIID() == getUUIID() ) return 0;
        else if( this.getUUIID() > instructor.getUUIID() ) return 1;
        else return -1;
    }
}
