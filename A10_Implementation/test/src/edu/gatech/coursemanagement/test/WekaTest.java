package src.edu.gatech.coursemanagement.test;


import edu.gatech.coursemanagement.dal.*;
import edu.gatech.coursemanagement.dto.AcademicRecord;
import edu.gatech.coursemanagement.dto.Course;
import edu.gatech.coursemanagement.dto.DegreeProgram;
import edu.gatech.coursemanagement.dto.Student;
import edu.gatech.coursemanagement.utils.CourseManagementUtil;
import edu.gatech.coursemanagement.weka.CourseManagementApriori;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;


public class WekaTest
{
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

    private void setupData() throws SQLException
    {
        AcademicRecordDAO academicRecordDAO = new AcademicRecordDAO();
        CourseDAO courseDAO = new CourseDAO();
        StudentDAO studentDAO = new StudentDAO();

        Object academicRecord[][] = {
                {1012,6},
                {1012,5},
                {1012,3},
                {1013,4},
                {1013,5},
                {1016,4},
                {1016,7},
                {1018,1},
                {1018,2},
        };
        DegreeProgram degreeProgramData = new DegreeProgram();
        degreeProgramData.setprogramID(1);
        degreeProgramData.setname("Test");
        DegreeProgramDAO degreeProgramDAO = new DegreeProgramDAO();
        degreeProgramDAO.insertDegreeProgram(degreeProgramData);
        HashMap<Integer,Integer> courses = new HashMap<Integer,Integer>();
        HashMap<Integer,Integer> students = new HashMap<Integer,Integer>();
        for(Object ar[]: academicRecord)
        {
            int studentID = (int)ar[0];
            int courseId = (int)ar[1];

            if( !courses.containsKey(courseId) ) {
                Course courseData = new Course();
                courseData.setcourseID(courseId);
                courseData.setcost(new java.math.BigDecimal(10));
                courseData.setdescription("Test");
                courseData.setname("Test");
                courseDAO.insertCourse(courseData);
                courses.put(courseId, 0);
            }

            if( !students.containsKey(studentID) ) {
                Student studentData = new Student();
                studentData.setUUSID(studentID);
                studentData.setaddress("A");
                studentData.setname("B");
                studentData.setphoneNumber(1L);
                studentData.setprogramID(1);
                studentDAO.insertStudent(studentData);
                students.put(studentID, 0);
            }

            AcademicRecord ardata = new AcademicRecord();
            ardata.setUUSID(studentID);
            ardata.setcourseID(courseId);
            ardata.setgrade("A");
            ardata.setterm(1);
            ardata.setyear(1);
            academicRecordDAO.insertAcademicRecord(ardata);
        }

    }

    @Test
    public void testWeka() throws Exception
    {
        setupData();
        CourseManagementApriori apriori = new CourseManagementApriori();
        String rules = apriori.runWekaAlgo( 50 );
        System.out.println("Apriori output :");
        System.out.println(rules);
        Assert.assertTrue("Test Weka Passed",!rules.isEmpty());
    }
}
