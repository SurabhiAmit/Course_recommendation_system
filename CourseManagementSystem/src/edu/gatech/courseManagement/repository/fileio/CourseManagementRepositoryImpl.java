package edu.gatech.courseManagement.repository.fileio;

import edu.gatech.courseManagement.entity.*;
import edu.gatech.courseManagement.repository.CourseManagementRepository;

import java.util.*;

public class CourseManagementRepositoryImpl implements CourseManagementRepository {
    private Map<Long, Course> allCourses = new HashMap<Long, Course>();     //reason for dictionary implementation O(1)
    private Map<Long, Instructor> allInstructors = new HashMap<Long, Instructor>();
    private ArrayList<Listing> allListings = new ArrayList<Listing>();
    private Map<String, Prerequisite> allPrerequisites = new HashMap<String, Prerequisite>();
    private ArrayList<Degree> allDegrees = new ArrayList<Degree>();
    private Map<String,Record> allRecords = new HashMap<String,Record>();
    private Map<Integer,Request> allRequests = new HashMap<Integer,Request>();
    private Map<Long, Student> allStudents = new HashMap<Long, Student>();
    private ArrayList<Course> courseBeingTaught = new ArrayList<Course>();       //quick way to get all courses being taught

    private int reqCount = 0;

    @Override
    public void add(Object o) {
        if( o instanceof Course )
        {
            addCourses((Course) o);
        }
        else if( o instanceof  Instructor )
        {
            addInstructor((Instructor) o);
        }
        else if( o instanceof  Listing )
        {
            addListing((Listing) o);
        }
        else if( o instanceof  Prerequisite )
        {
            addPrerequisites((Prerequisite) o );
        }
        else if( o instanceof Degree )
        {
            addDegree((Degree) o );
        }
        else if( o instanceof  Record )
        {
            addRecord((Record) o);
        }
        else if( o instanceof Request )
        {
            addRequest((Request) o);
        }
        else if( o instanceof Student )
        {
            addStudent((Student) o);
        }
        else{
            //no-op
        }
    }

    @Override
    public void sync() {
        linkInstructorAndCourses();
        linkStudentRecordCourses();
        linkCoursePreRequisites();
        linkRequestCourseAndStudent();
        processRequests();
    }

    private void linkRequestCourseAndStudent()
    {
        Course requestedCourse;
        Student student;
        for( Request request: this.allRequests.values() ) {
            requestedCourse = allCourses.get(request.getRequestedCourseID());
            student = allStudents.get(request.getUUSID());
            request.setCourse(requestedCourse);
            request.setStudent(student);
        }
    }

    private void processRequests() {
        Course requestedCourse;
        Student student;
        for( Request request: this.allRequests.values() ) {
            if( ! request.checkCourseEligiblity() )
            {
                request.setRequestStatus(Request.Status.DENIED);
            }
            else if(  !request.getCourse().isCurrentCourse() )
            {
                request.setRequestStatus(Request.Status.NOT_OFFERED);
            }
            else
            {
                request.setRequestStatus(Request.Status.GRANTED);
            }
        }
    }

    private void linkCoursePreRequisites() {
        Course course;
        Course preReq;
        for(Prerequisite preReqCourse: allPrerequisites.values())
        {
            course = allCourses.get(preReqCourse.getCourseID());
            preReq = allCourses.get(preReqCourse.getPrerequisiteID());
            preReqCourse.setCourse(course);
            preReqCourse.setPreReq(preReq);
            if(!course.getPrerequisiteCourse().contains(preReq))
                course.getPrerequisiteCourse().add(preReq);
        }
    }

    private void linkStudentRecordCourses() {
        Student student;
        Course course;
        for(Record record: allRecords.values())
        {
            student = allStudents.get(record.getUUSID());
            course = allCourses.get(record.getCourseID());
            if(!student.getRecord().contains(record))
                student.getRecord().add(record);
            Collections.sort( student.getRecord() );
            record.setStudent(student);
            record.setCourse( course );
        }
    }

    private void linkInstructorAndCourses() {
        Course course;
        courseBeingTaught.clear();      //reset before init
        for( Instructor instructor: allInstructors.values() )
        {
            if( instructor.isTeachingThisSemester() ) {
                course = allCourses.get(instructor.getCourseTaught());
                instructor.setCourse(course);
                if(!course.getInstructor().contains(instructor))
                    course.getInstructor().add(instructor);
                if(!courseBeingTaught.contains(course)) courseBeingTaught.add(course);      //helper list to get list of current courses
            }
        }
    }

    private void addCourses(Course c) {
        allCourses.put(c.getCourseID(), c);
    }

    private void addInstructor(Instructor i) { allInstructors.put(i.getUUIID(),i); }

    private void addListing(Listing l) {
        allListings.add(l);
    }

    private void addPrerequisites(Prerequisite p) {
        allPrerequisites.put(p.getCourseID() + "" + p.getPrerequisiteID(), p);
    }

    private void addDegree(Degree d) {
        allDegrees.add(d);
    }

    private void addRecord(Record r) {
        String unique = r.getUUSID() + "" + r.getCourseID() +
                "" + r.getYear()+ "" + r.getTerm();
        allRecords.put(unique, r);
    }

    private void addRequest(Request r) {
        allRequests.put( reqCount++, r);
    }

    private void addStudent(Student s) {
        allStudents.put( s.getUUSID(), s);
    }

    public List<Course> getCourseBeingTaught()
    {
        Collections.sort(courseBeingTaught);
        return courseBeingTaught;
    }

    public List<Student> getStudentStatusAndCost()
    {
        List<Student> studentStatusAndCost = new ArrayList<Student>();
        //second pass to check for students who has records
        for(Student stud: allStudents.values())
        {
            if(stud.hasRecords()) studentStatusAndCost.add(stud);
        }
        Collections.sort(studentStatusAndCost);
        return studentStatusAndCost;
    }

    public List<Request> getRequests(){
        List<Request> _allRequests = new ArrayList<Request>();
        for( Request request: this.allRequests.values() ) {

            _allRequests.add(request);
        }
        return _allRequests;
    }
}
