package src.edu.gatech.coursemanagement.test;
import edu.gatech.coursemanagement.dal.*;
import edu.gatech.coursemanagement.dto.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

public class PersistenceTest {

    private static void clearDB()
    {
        GeneralDAO g = new GeneralDAO();
        g.clearDB();
    }

    @BeforeClass
    public static void setUpClass()
    {
        clearDB();
    }

    @AfterClass
    public static void tearDownClass()
    {
        clearDB();
    }

    @Test
    public void testPersistence() {
        
        DegreeProgram dp = new DegreeProgram();
        dp.setname("test program");
        dp.setprogramID(18);
         
        // sample method calls to check data flow is correct
        // uncomment the method call which you need to test/use
        
        DegreeProgramDAO dpdao = new DegreeProgramDAO();
        //dpdao.insertDegreeProgram(dp);
        //dpdao.deleteDegreeProgram(10);
        //dpdao.updateDegreeProgram(11, "test");
        
        Course c = new Course();
        c.setcourseID(2);
        c.setname("test name");
        c.setdescription("test description");
        c.setcost(new java.math.BigDecimal(300));
        
        CourseDAO cdao = new CourseDAO();
        //cdao.insertCourse(c);
        //cdao.deleteCourse(1);
        //cdao.updateCourse(c);
        
        Instructor i  = new Instructor();
        i.setUUIID(2);
        i.setname("test1");
        i.setemail("test1@test.com");
        
        InstructorDAO idao = new InstructorDAO();
        //idao.insertInstructor(i);
        //idao.deleteInstructor(1);
        //idao.updateInstructor(i);
        
        Student s = new Student();
        s.setUUSID(2);
        s.setname("test name");
        s.setaddress("test address");
        s.setphoneNumber(1234567000);
        s.setprogramID(11);
        
        StudentDAO sdao = new StudentDAO();
        //sdao.insertStudent(s);
        //sdao.deleteStudent(1);
        //sdao.updateStudent(s);
        
        AcademicRecord a = new AcademicRecord();
        a.setcourseID(1);
        a.setterm(1);
        a.setyear(2017);
        a.setgrade("B");
        a.setUUSID(2);
        
        AcademicRecordDAO adao = new AcademicRecordDAO();
        //adao.insertAcademicRecord(a);
        //adao.updateAcademicRecordGrade(a);
        
        DegreeProgramCourse dpc = new DegreeProgramCourse();
        dpc.setcourseID(1);
        dpc.setprogramID(8);
        dpc.setID(1);
        
       //dpdao.insertDegreeProgramCourse(dpc);
        
        CoursePrerequisite cp = new CoursePrerequisite();
        cp.setcourseID(1);
        cp.setprerequisiteID(2);
        cp.setID(1);
        
        //cdao.insertCoursePrerequisite(cp);
        
        CourseOffering co = new CourseOffering();
        co.setID(1);
        co.setcourseID(2);
        co.setUUIID(2);
        co.setterm(1);
        co.setyear(2016);
        co.settotalstudents(10);
        
        //cdao.insertCourseOffering(co);
        
        CourseCatalog cc = new CourseCatalog();
        cc.setid(1);
        cc.setterm(2);
        cc.setyear(2016);
        
        //cdao.insertCourseCatalog(cc);
        
        CourseCatalogCourseList ccl = new CourseCatalogCourseList();
        ccl.setid(3);
        ccl.setcoursecatalogid(1);
        ccl.setcourseid(2);
        
        //cdao.insertCourseCatalogCourseList(ccl);
        
        InstructorOfficeHour ih = new InstructorOfficeHour();
        ih.setid(1);
        ih.setuuiid(2);
        ih.setofficehours("Thursday 6pm PST");
        
        //idao.insertInstructorOfficeHours(ih);
        
        ApplicationState ap = new ApplicationState();
        ap.setid(2);
        ap.setcurrentmode(1);
        ap.setcurrentlyProcessingTerm(3);
        ap.setcurrentlyProcessingFile("request2.csv");
        ap.setsimulationstep(6);
        ap.setfilestoprocess("request3.csv,request4.csv,request5.csv");
        ap.setfilesProcessed("request1.csv");
        
        GeneralDAO g = new GeneralDAO();
        //g.insertApplicationState(ap);
    }
}
