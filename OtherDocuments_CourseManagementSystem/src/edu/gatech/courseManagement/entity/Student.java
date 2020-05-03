package edu.gatech.courseManagement.entity;

import java.util.ArrayList;
import java.util.List;

public class Student implements Comparable<Student>{

    private String name;
    private String homeAddress;
    private String phoneNumber;
    private long UUSID;
    private long enrolledProgramID=0;
    private ArrayList<Course> currentCourse = new ArrayList<Course>();
    private List<Record> record = new ArrayList<Record>();
    private List<Course> passedCourse = new ArrayList<Course>();
    public List<Record> getRecord() {
        return record;
    }

    public List<Course> getPassedCourse() {
        return passedCourse;
    }

    public void setPassedCourse(List<Course> passedCourse) {
        this.passedCourse = passedCourse;
    }

    public boolean hasRecords()
    {
        return !record.isEmpty();
    }

    public float getTotalRecordCost() {
        float totalRecordCost = 0;
        for(Record itrRecord: record)
        {
            totalRecordCost += itrRecord.getCourse().getCost();
        }
        return totalRecordCost;
    }

    public int getPassedCount()
    {
        int count = 0;;
        for(Record itrRecord: record)
        {
            if( itrRecord.isPass() && !passedCourse.contains( itrRecord.getCourse() ))
            {
                passedCourse.add( itrRecord.getCourse() );
                count++;
            }
        }
        return count;
    }

    public void setRecord(List<Record> record) {
        this.record = record;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getUUSID() {
        return UUSID;
    }

    public void setUUSID(long UUSID) {
        this.UUSID = UUSID;
    }


    public long getEnrolledProgramID() {
        return enrolledProgramID;
    }

    public void setEnrolledProgramID(long enrolledProgramID) {
        this.enrolledProgramID = enrolledProgramID;
    }

    public ArrayList<Course> getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(ArrayList<Course> currentCourse) {
        this.currentCourse = currentCourse;
    }


    @Override
    public int compareTo(Student student) {
        if( student.getUUSID() == this.getUUSID() ) return 0;
        else if( getUUSID() > student.getUUSID() ) return 1;
        else return -1;
    }
}

