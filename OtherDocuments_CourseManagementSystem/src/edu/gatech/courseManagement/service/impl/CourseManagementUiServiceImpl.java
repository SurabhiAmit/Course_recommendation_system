package edu.gatech.courseManagement.service.impl;

import edu.gatech.courseManagement.entity.Course;
import edu.gatech.courseManagement.entity.Instructor;
import edu.gatech.courseManagement.entity.Request;
import edu.gatech.courseManagement.entity.Student;
import edu.gatech.courseManagement.repository.CourseManagementRepository;
import edu.gatech.courseManagement.repository.CourseManagementStore;
import edu.gatech.courseManagement.service.CourseManagementUiService;
import edu.gatech.courseManagement.util.CourseManagementUtil;

import java.text.DecimalFormat;
import java.util.List;

public class CourseManagementUiServiceImpl implements CourseManagementUiService {
    private static final String STORE ="coursemanagement.repository.store";

    @Override
    public void run() {
        CourseManagementStore store = (CourseManagementStore)
                CourseManagementUtil.createRuntimeObject( CourseManagementUtil.getProperty(STORE) );
        CourseManagementRepository repo = store.getRepository();

        System.out.println("courses being taught");
        courseBeingTaught(repo);

        System.out.println("student status and costs");
        studentStatusAndCost(repo);

        System.out.println("request processing");
        processRequests(repo);
    }

    private void processRequests(CourseManagementRepository repo) {
        for(Request req: repo.getRequests())
        {
            if( req.getRequestStatus() == Request.Status.NOT_OFFERED )
            {
                System.out.println(req.getUUSID()+", "+ req.getRequestedCourseID()+": denied: not being offered");
            }
            else if( req.getRequestStatus() == Request.Status.GRANTED )
            {
                System.out.println(req.getUUSID()+", "+ req.getRequestedCourseID()+": granted");
            }
            else
            {
                System.out.println(req.getUUSID()+", "+ req.getRequestedCourseID()+": denied: missing prerequisites");
            }
        }
    }

    private void studentStatusAndCost(CourseManagementRepository repo) {
        List<Student> studentStatusAndCost = repo.getStudentStatusAndCost();
        for(Student student: studentStatusAndCost)
        {
            DecimalFormat format = new DecimalFormat("#.#");
            System.out.println(
                    student.getUUSID() + ": " + student.getName()+
                            " passed " + student.getPassedCount() + " course(s) for $" +
                             format.format( student.getTotalRecordCost() )
            );
        }
    }

    private void courseBeingTaught(CourseManagementRepository repo) {
        List<Course> courseBeingTaught = repo.getCourseBeingTaught();
        for(Course courses: courseBeingTaught)
        {
            for( Instructor courseInstructor: courses.getInstructor() ) {
                System.out.println(courses.getCourseID() +
                        ": " + courses.getCourseTitle() +
                        " by " + courseInstructor.getUUIID() + ": " + courseInstructor.getName());
            }
        }
    }
}
