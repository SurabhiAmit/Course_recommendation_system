package edu.gatech.coursemanagement.utils;

import edu.gatech.coursemanagement.entity.*;
import edu.gatech.coursemanagement.exception.CourseManagementRuntimeException;

import java.util.ArrayList;
import java.util.HashMap;

public class ApplicationDataModel {
    private ApplicationState applicationState;
    private CourseCatalog courseCatalog;
    private HashMap<Long, Instructor> instructors;
    private HashMap<Integer, DegreeProgram> programs;
    private HashMap<Long, Student> students;
    private ArrayList<CourseRequest> requests;
    private ArrayList<AcademicRecord> records;
    private ArrayList<AcademicRecord> newRecords;

    public ApplicationDataModel() {
        courseCatalog = new CourseCatalog();
        programs = new HashMap<>();
        students = new HashMap<>();
        instructors = new HashMap<>();
        requests = new ArrayList<>();
        records = new ArrayList<>();
        newRecords = new ArrayList<>();
        applicationState = new ApplicationState();
    }

    public ApplicationState getApplicationState() {
        return applicationState;
    }

    public CourseCatalog getCourseCatalog() {
        return courseCatalog;
    }

    public void setCourseCatalog(CourseCatalog courseCatalog) {
        this.courseCatalog = courseCatalog;
    }

    public boolean hasCourse(long courseId) {
        try {
            Course course = courseCatalog.findCourseById(courseId);
            return course != null;
        }
        catch (CourseManagementRuntimeException ex) {
            return false;
        }
    }

    public Course getCourse(long courseId) {
        try {
            return courseCatalog.findCourseById(courseId);
        }
        catch (CourseManagementRuntimeException ex) {
            return null;
        }
    }

    public HashMap<Long, Instructor> getInstructors() {
        return instructors;
    }

    public boolean hasInstructor(long instructorId) {
        if (instructors.containsKey(instructorId))
            return true;

        return false;
    }

    public ArrayList<Instructor> getInstructorsList() {
        ArrayList<Instructor> list = new ArrayList<>(instructors.values());
        return list;
    }

    public Instructor getInstructor(long instructorId) {
        if (hasInstructor(instructorId))
            return instructors.get(instructorId);

        return null;
    }

    public void setInstructors(HashMap<Long, Instructor> instructors) {
        this.instructors = instructors;
    }

    public HashMap<Integer, DegreeProgram> getPrograms() {
        return programs;
    }

    public ArrayList<DegreeProgram> getProgramsList() {
        ArrayList<DegreeProgram> list = new ArrayList<>(programs.values());
        return list;
    }

    public boolean hasProgram(long programId) {
        if (programs.containsKey(programId))
            return true;

        return false;
    }

    public DegreeProgram getProgram(long programId) {
        if (hasInstructor(programId))
            return programs.get(programId);

        return null;
    }

    public void setPrograms(HashMap<Integer, DegreeProgram> programs) {
        this.programs = programs;
    }

    public HashMap<Long, Student> getStudents() {
        return students;
    }

    public ArrayList<Student> getStudentsList() {
        ArrayList<Student> list = new ArrayList<>(students.values());
        return list;
    }

    public boolean hasStudent(long studentId) {
        if (students.containsKey(studentId))
            return true;

        return false;
    }

    public Student getStudent(long studentId) {
        if (hasStudent(studentId))
            return students.get(studentId);

        return null;
    }

    public void setStudents(HashMap<Long, Student> students) {
        this.students = students;
    }

    public ArrayList<CourseRequest> getRequests() {
        return requests;
    }

    public ArrayList<AcademicRecord> getRecords() {
        return records;
    }

    public ArrayList<AcademicRecord> getNewRecords() {
        return newRecords;
    }
}
