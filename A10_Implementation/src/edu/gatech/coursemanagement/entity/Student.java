package edu.gatech.coursemanagement.entity;

import edu.gatech.coursemanagement.exception.CourseManagementRuntimeException;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * CS6310 - Team 45
 * edu.gatech.coursemanagement.entity.Student.java
 */

public class Student {

    long uusid;
    long phoneNumber;
    String studentName, homeAddress;
    DegreeProgram currentDegreeProgram;
    private ArrayList<AcademicRecord> academicRecords = new ArrayList<>();

    public Student(long uusid, String studentName, String homeAddress,
                    long phoneNumber, DegreeProgram currentDegreeProgram) {
        this.uusid = uusid;
        this.studentName = studentName;
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
        this.currentDegreeProgram = currentDegreeProgram;
    }

    public long getUusid() {
        return uusid;
    }

    public void setUusid(long uusid) {
        this.uusid = uusid;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public DegreeProgram getCurrentDegreeProgram() {
        return currentDegreeProgram;
    }

    public void setCurrentDegreeProgram(DegreeProgram currentDegreeProgram) {
        this.currentDegreeProgram = currentDegreeProgram;
    }

    public void switchDegreeProgram(DegreeProgram dp) {
        setCurrentDegreeProgram(dp);
    }

    public boolean requestEnrollment(Term term, CourseOffering offering) {
        if (offering.isRequestValid(this)) {
            return true;
        } else {
            return false;
        }
    }

    public BigDecimal caclulateTermCost(Student student, Term term) {
        BigDecimal termCost = BigDecimal.valueOf(0);

        for (AcademicRecord record : academicRecords) {
            if (academicRecords.contains(term)) {
                termCost = termCost.add(record.getCourse().getCost());
            }
        }

        return termCost;
    }

    public BigDecimal calculateTotalCost(Student student) {
        BigDecimal totalCost = BigDecimal.valueOf(0);

        for (AcademicRecord record : academicRecords) {
            totalCost = totalCost.add(record.getCourse().getCost());
        }

        return totalCost;
    }

    public boolean verifyCompletion(Course course) {
        for (AcademicRecord record : academicRecords) {
            if (record.getCourse().getCourseId() == course.getCourseId()) {
                if (AcademicRecord.passingGrade(record.getGrade()))
                    return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + this.uusid + ") " + this.studentName;
    }
}