package edu.gatech.courseManagement.repository;

import edu.gatech.courseManagement.entity.Course;
import edu.gatech.courseManagement.entity.Request;
import edu.gatech.courseManagement.entity.Student;

import java.util.List;

public interface CourseManagementRepository {


    public List<Course> getCourseBeingTaught();

    public List<Student> getStudentStatusAndCost();

    public List<Request> getRequests();

    public void add( Object o );

    public void sync();


}
