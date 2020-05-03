package src.edu.gatech.coursemanagement.test;


import edu.gatech.coursemanagement.dto.CourseOffering;
import edu.gatech.coursemanagement.entity.*;
import org.junit.Test;

public class AdministratorTest {

    @Test
    public void testAssignInstructor()
    {
        Instructor instructor = new Instructor();
        Course course = new Course();
        Administrator admin = new Administrator();

        admin.assignInstructor(instructor,course, Term.FALL, 2017);
    }

    @Test
    public void testRegisterStudent()
    {
        Administrator admin = new Administrator();
        CourseOffering courseOffering = new CourseOffering();
        Student stud = new Student(1, "A", "B",
        1L, null);
        Instructor instructor = new Instructor();

        Course course = new Course();
        course.createCourseOffering(instructor, Term.FALL, 2017);
        admin.registerStudent(stud,course);
    }

}
