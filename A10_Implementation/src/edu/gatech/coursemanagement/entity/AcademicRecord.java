package edu.gatech.coursemanagement.entity;

/**
 * CS6310 - Team 45
 * edu.gatech.coursemanagement.entity.AcademicRecord.java
 */

import java.util.ArrayList;

public class AcademicRecord {

    private int year;
    private Student student;
    private Course course;
    private Term term;
    private Grade grade;
    private ArrayList<AcademicRecord> academicRecords;

    public AcademicRecord(Student student, Course course, Term term, int year, Grade grade) {
        this.student = student;
        this.course = course;
        this.term = term;
        this.year = year;
        this.grade = grade;
    }

    public int getYear() {
        return year;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Term getTerm() {
        return term;
    }

    public Grade getGrade() {
        return grade;
    }

    public static boolean passingGrade(Grade grade) {
        return grade == Grade.A || grade == Grade.B || grade == Grade.C;
    }

    public void createRecord(Student student, Course course, Term term, int year, Grade grade) {
        AcademicRecord record = new AcademicRecord(this.student, this.course, this.term, this.year, this.grade);
        this.academicRecords.add(record);
    }

    public static Grade generateRandomGrade( int studentPosition, int totalNumOfStudent ) {
        double studPerc = (double)studentPosition/(double)totalNumOfStudent;

        if( studPerc <=0.35 )
        {
            return Grade.A;
        }
        else if( studPerc <= 0.8 )
        {
            return Grade.B;
        }
        else if( studPerc <= 0.9 )
        {
            return Grade.C;
        }
        else if (studPerc <=0.95)
        {
            return Grade.D;
        }
        return Grade.F;
    }

    /*
    //Test cases
    public static void main(String ar[])
    {
        System.out.println( AcademicRecord.generateRandomGrade(1,2) ); //A
        System.out.println( AcademicRecord.generateRandomGrade(2,2) );     //B
        System.out.println( AcademicRecord.generateRandomGrade(1,1) );   //C
        System.out.println( AcademicRecord.generateRandomGrade(1,1) );      //D
    }*/
}