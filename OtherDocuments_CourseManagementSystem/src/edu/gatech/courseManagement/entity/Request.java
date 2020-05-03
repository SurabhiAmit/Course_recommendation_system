package edu.gatech.courseManagement.entity;

public class Request {
    private long UUSID;
    private long requestedCourseID;
    public enum Status{ GRANTED, DENIED, NOT_OFFERED };
    private Status requestStatus = Status.DENIED;
    private Student student;
    private Course course;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setRequestStatus(Status requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Status getRequestStatus(){
        return requestStatus;
    }
    public long getUUSID() {
        return UUSID;
    }

    public void setUUSID(long UUSID) {
        this.UUSID = UUSID;
    }

    public long getRequestedCourseID() {
        return requestedCourseID;
    }

    public void setRequestedCourseID(long requestedCourseID) {
        this.requestedCourseID = requestedCourseID;
    }

    public boolean checkCourseEligiblity()
    {
        boolean eligible = false;
        if( course.hasPreRequisite() ) {
            for (Course preReqCourse : course.getPrerequisiteCourse()) {
                eligible = false;
                for (Record studRecord : student.getRecord()) {
                    if (preReqCourse.equals(studRecord.getCourse()) && studRecord.isPass()) {
                        eligible = true;
                        break;
                    }
                }
                if (!eligible)
                    break;
            }
        }
        else
        {
            eligible = true;
        }
        return eligible;
    }

}


