package edu.gatech.Application;
import java.io.Console;
import java.lang.System;
import java.sql.SQLException;

import edu.gatech.dal.*;
import edu.gatech.dto.*;

public class Application {

    public static void main(String[] args) {
        //Connection connection = ConnectionFactory.getConnection();
        
        DegreeProgram dp = new DegreeProgram();
        dp.setname("test program");
        dp.setprogramID(1);
         
        DegreeProgramDAO dpdao = new DegreeProgramDAO();
        try{
        	//dpdao.insertDegreeProgram(dp);
            //dpdao.deleteDegreeProgram(18);
            dpdao.updateDegreeProgram(11, "test new test");
        }
        catch (SQLException ex) 
        {
	        ex.printStackTrace();
	    }
       
        
        Course c = new Course();
        c.setcourseID(5);
        c.setname("test name");
        c.setdescription("test description");
        c.setcost(300);
        
        CourseDAO cdao = new CourseDAO();
        try {
        	//cdao.insertCourse(c);
            //cdao.deleteCourse(3);
            //cdao.updateCourse(c);
            Course ccc = cdao.getCourse(2);
        }
        catch (SQLException ex) 
        {
	        ex.printStackTrace();
	    }
       
        
        Instructor i  = new Instructor();
        i.setUUIID(4);
        i.setname("test1");
        i.setemail("test1@test.com");
        
        InstructorDAO idao = new InstructorDAO();
        try {
        	 //idao.insertInstructor(i);
             //idao.deleteInstructor(3);
             idao.updateInstructor(i);
        }
        catch (SQLException ex) 
        {
	        ex.printStackTrace();
	    }
       
        
        Student s = new Student();
        s.setUUSID(6);
        s.setname("test name");
        s.setaddress("test address");
        s.setphoneNumber(1234567000);
        s.setprogramID(11);
        
        StudentDAO sdao = new StudentDAO();
        try{
        	//sdao.insertStudent(s);
            //sdao.deleteStudent(4);
            //sdao.updateStudent(s);
            Student stu = sdao.getStudent(5);
            //String ss = stu.getaddress();
        }
        catch (SQLException ex) 
        {
	        ex.printStackTrace();
	    }
        	
   
        AcademicRecord a = new AcademicRecord();
        a.setcourseID(1); 
        a.setterm(1);
        a.setyear(2015);
        a.setgrade("A");
        a.setUUSID(2);
        
        AcademicRecordDAO adao = new AcademicRecordDAO();
       /* try{
        	
        	adao.insertAcademicRecord(a);
            adao.updateAcademicRecordGrade(a);
        	
        }
        catch (SQLException ex) 
        {
	        ex.printStackTrace();
	    }
        */
        
        DegreeProgramCourse dpc = new DegreeProgramCourse();
        dpc.setcourseID(1);
        dpc.setprogramID(8);
        dpc.setID(3);
        
        /*try{
            
        	dpdao.insertDegreeProgramCourse(dpc);
        }
        catch (SQLException ex) 
        {
	        ex.printStackTrace();
	    }*/

      
        
        CoursePrerequisite cp = new CoursePrerequisite();
        cp.setcourseID(1);
        cp.setprerequisiteID(2);
        cp.setID(11);
        
        /*try{
            
            cdao.insertCoursePrerequisite(cp);
        }
        catch (SQLException ex) 
        {
	        ex.printStackTrace();
	    }
		*/
   
        
        CourseOffering co = new CourseOffering();
        co.setID(10);
        co.setcourseID(2);
        co.setUUIID(2);
        co.setterm(1);
        co.setyear(2016);
        co.settotalstudents(10);
        
       /* try{
            
        	cdao.insertCourseOffering(co);
        }
        catch (SQLException ex) 
        {
	        ex.printStackTrace();
	    } */

        
        
        CourseCatalog cc = new CourseCatalog();
        cc.setid(10);
        cc.setterm(2);
        cc.setyear(2016);
        
       /* try{
            
        	cdao.insertCourseCatalog(cc);
        }
        catch (SQLException ex) 
        {
	        ex.printStackTrace();
	    }
       */ 
               
        
        CourseCatalogCourseList ccl = new CourseCatalogCourseList();
        ccl.setid(13);
        ccl.setcoursecatalogid(1);
        ccl.setcourseid(2);
        
       /* try{
            
        	cdao.insertCourseCatalogCourseList(ccl);
        }
        catch (SQLException ex) 
        {
	        ex.printStackTrace();
	    }*/
       
 
        InstructorOfficeHour ih = new InstructorOfficeHour();
        ih.setid(110);
        ih.setuuiid(2);
        ih.setofficehours("FRIDAY 6pm PST");
        
       /* try{
        
        	idao.insertInstructorOfficeHours(ih);
        }
        catch (SQLException ex) 
        {
	        ex.printStackTrace();
	    }*/
        
        
        ApplicationState ap = new ApplicationState();
        ap.setid(1);
        ap.setcurrentmode(1);
        ap.setcurrentlyProcessingTerm(3);
        ap.setcurrentlyProcessingFile("request2.csv");
        ap.setsimulationstep(6);
        ap.setfilestoprocess("request3.csv,request4.csv,request5.csv");
        ap.setfilesProcessed("request1.csv");
        
        GeneralDAO g = new GeneralDAO();
        try{
        	//g.clearApplicationState();
        	//g.insertApplicationState(ap);
        	ApplicationState app = g.getApplicationState();
        }
        catch (SQLException ex) 
        {
	        ex.printStackTrace();
	    }
        
        
    }
}
