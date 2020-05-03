package edu.gatech.dal;

import com.mysql.jdbc.*;

import edu.gatech.dto.Course;
import edu.gatech.dto.CourseCatalog;
import edu.gatech.dto.CourseCatalogCourseList;
import edu.gatech.dto.CourseOffering;
import edu.gatech.dto.CoursePrerequisite;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class CourseDAO {
	
	public boolean insertCourse(Course c) throws SQLException {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("INSERT INTO Course VALUES (?, ?, ?, ?)");
	        ps.setInt(1, c.getcourseID());
	        ps.setString(2, c.getname());
	        ps.setString(3, c.getdescription());
	        ps.setInt(4, c.getcost());
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (Exception ex) {
	    	throw ex;
	    }
	    return false;
	}
	
	public boolean deleteCourse(int id) throws SQLException {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("DELETE FROM Course WHERE courseID = ?");
	        ps.setInt(1, id);
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (Exception ex) {
	    	throw ex;
	    }
	    return false;
	}
	
	public boolean updateCourse(Course c) throws SQLException {
		//ConnectionFactory connector = new ConnectionFactory();
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("UPDATE Course SET courseTitle = ?, coursedescription = ?, coursecost = ? WHERE courseID = ?");
	        ps.setString(1, c.getname());
	        ps.setString(2, c.getdescription());
	        ps.setInt(3, c.getcost());
	        ps.setInt(4, c.getcourseID());
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (Exception ex) {
	    	throw ex;
	    }
	    return false;
	}
	
	public Course getCourse (int id) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = (Statement) connection.createStatement();
            ResultSet rs = stmt.executeQuery("select courseid, courseTitle , courseDescription, courseCost From A10db.Course where courseid = " + id);
            if(rs.next())
            {
            	Course c = new Course();
                c.setcourseID(rs.getInt("courseid"));
                c.setname(rs.getString("courseTitle"));
                c.setdescription(rs.getString("courseDescription"));
                c.setcost(rs.getInt("courseCost"));
               
                return c;
            }
	    } catch (Exception ex) {
	    	throw ex;
	    }
    return null;
}
	
	public Set<Course> getAllCourses() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = (Statement) connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Course");
            Set<Course> courses = new HashSet<Course>();
            
            while(rs.next())
            {
               	Course c = new Course();
                c.setcourseID(rs.getInt("courseid"));
                c.setname(rs.getString("courseTitle"));
                c.setdescription(rs.getString("courseDescription"));
                c.setcost(rs.getInt("courseCost"));
                courses.add(c);
            }
            
            return courses;
            
        } catch (Exception ex) {
            throw ex;
        }
}
	

	public boolean insertCoursePrerequisite(CoursePrerequisite c) throws SQLException {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("INSERT INTO CoursePrerequisites VALUES (?, ?, ?)");
	        ps.setInt(1, c.getprerequisiteID());
	        ps.setInt(2, c.getID());
	        ps.setInt(3, c.getcourseID());
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (Exception ex) {
	    	throw ex;
	    }
	    return false;
	}

	public Set<CoursePrerequisite> getAllCoursesPrerequisites() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = (Statement) connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from CoursePrerequisites");
            Set<CoursePrerequisite> coursespreq = new HashSet<CoursePrerequisite>();
            
            while(rs.next())
            {
            	CoursePrerequisite cp = new CoursePrerequisite();
                cp.setcourseID(rs.getInt("courseid"));
                cp.setID(rs.getInt("id"));
                cp.setprerequisiteID(rs.getInt("prerequisiteid"));
                coursespreq.add(cp);
            }
            
            return coursespreq;
            
        } catch (Exception ex) {
            throw ex;
        }
}
	
	
    public boolean insertCourseOffering(CourseOffering c) throws SQLException {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("INSERT INTO CourseOffering VALUES (?, ?, ?, ?, ?, ?)");
	        ps.setInt(1, c.getID());
	        ps.setInt(2, c.getcourseID());
	        ps.setInt(3, c.getUUIID());
	        ps.setInt(4, c.getterm());
	        ps.setInt(5, c.getyear());
	        ps.setInt(6, c.gettotalstudents());
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (Exception ex) {
	    	throw ex;
	    }
	    return false;
	}
    
    public Set<CourseOffering> getAllCourseOffering() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
       try {
           Statement stmt = (Statement) connection.createStatement();
           ResultSet rs = stmt.executeQuery("select * from CourseOffering");
           Set<CourseOffering> coursespreq = new HashSet<CourseOffering>();
           
           while(rs.next())
           {
        	   CourseOffering cp = new CourseOffering();
               cp.setID(rs.getInt("id"));
               cp.setterm(rs.getInt("term"));
               cp.setyear(rs.getInt("year"));
               cp.setcourseID(rs.getInt("courseid"));
               cp.setUUIID(rs.getInt("uuiid"));
               cp.settotalstudents(rs.getInt("totalstudents"));
               
               coursespreq.add(cp);
           }
           
           return coursespreq;
           
       } catch (Exception ex) {
           throw ex;
       }
}
   
    
    
    public boolean insertCourseCatalog(CourseCatalog c) throws SQLException {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("INSERT INTO CourseCatalog VALUES (?, ?, ?)");
	        ps.setInt(1, c.getid());
	        ps.setInt(2, c.getterm());
	        ps.setInt(3, c.getyear());

	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (Exception ex) {
	    	throw ex;
	    }
	    return false;
	}
    
    public Set<CourseCatalog> getAllCourseCatalog() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
       try {
           Statement stmt = (Statement) connection.createStatement();
           ResultSet rs = stmt.executeQuery("select * from CourseCatalog");
           Set<CourseCatalog> coursespreq = new HashSet<CourseCatalog>();
           
           while(rs.next())
           {
        	   CourseCatalog cp = new CourseCatalog();
               cp.setid(rs.getInt("id"));
               cp.setterm(rs.getInt("term"));
               cp.setyear(rs.getInt("year"));
               coursespreq.add(cp);
           }
           
           return coursespreq;
           
       } catch (Exception ex) {
           throw ex;
       }
}
   
    
   public boolean insertCourseCatalogCourseList(CourseCatalogCourseList cl) throws SQLException {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("INSERT INTO CourseCatalogCourseList VALUES (?, ?, ?)");
	        ps.setInt(1, cl.getid());
	        ps.setInt(2, cl.getcoursecatalogid());
	        ps.setInt(3, cl.getcourseid());

	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (Exception ex) {
	    	throw ex;
	    }
	    return false;
	}
    

   public Set<CourseCatalogCourseList> getAllCourseCatalogCourseList() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
       try {
           Statement stmt = (Statement) connection.createStatement();
           ResultSet rs = stmt.executeQuery("select * from CourseCatalogCourseList");
           Set<CourseCatalogCourseList> coursespreq = new HashSet<CourseCatalogCourseList>();
           
           while(rs.next())
           {
        	   CourseCatalogCourseList cp = new CourseCatalogCourseList();
               cp.setid(rs.getInt("id"));
               cp.setcourseid(rs.getInt("courseid"));
               cp.setcoursecatalogid(rs.getInt("coursecatalogid"));
               coursespreq.add(cp);
           }
           
           return coursespreq;
           
       } catch (Exception ex) {
           throw ex;
       }
}
   
    
}
