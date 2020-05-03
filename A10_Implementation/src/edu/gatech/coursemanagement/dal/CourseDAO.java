package edu.gatech.coursemanagement.dal;

import com.mysql.jdbc.*;

import edu.gatech.coursemanagement.dto.Course;
import edu.gatech.coursemanagement.dto.CourseCatalog;
import edu.gatech.coursemanagement.dto.CourseCatalogCourseList;
import edu.gatech.coursemanagement.dto.CourseOffering;
import edu.gatech.coursemanagement.dto.CoursePrerequisite;
import edu.gatech.coursemanagement.entity.Term;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CourseDAO {
	
	public boolean insertCourse(Course c) throws SQLException {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("INSERT INTO Course VALUES (?, ?, ?, ?)");
	        ps.setLong(1, c.getcourseID());
	        ps.setString(2, c.getname());
	        ps.setString(3, c.getdescription());
	        ps.setBigDecimal(4, c.getcost());
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (Exception ex) {
	    	throw ex;
	    }
	    return false;
	}
	
	public boolean deleteCourse(long id) throws SQLException {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("DELETE FROM Course WHERE courseID = ?");
	        ps.setLong(1, id);
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
	        ps.setBigDecimal(3, c.getcost());
	        ps.setLong(4, c.getcourseID());
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
                c.setcourseID(rs.getLong("courseid"));
                c.setname(rs.getString("courseTitle"));
                c.setdescription(rs.getString("courseDescription"));
                c.setcost(rs.getBigDecimal("courseCost"));
               
                return c;
            }
	    } catch (Exception ex) {
	    	throw ex;
	    }
    return null;
}

	public long getCourseCatalogNextId () throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		try {
			Statement stmt = (Statement) connection.createStatement();
			ResultSet rs = stmt.executeQuery("select max(id) as id From A10db.CourseCatalog");
			if(rs.next())
			{
				long maxId = 0;
				if (rs.getObject("id") != null)
					maxId = rs.getLong("id");
				return maxId + 1;
			}
		} catch (Exception ex) {
			throw ex;
		}
		return 0;
	}

	public long getCourseCatalogCourseListNextId () throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		try {
			Statement stmt = (Statement) connection.createStatement();
			ResultSet rs = stmt.executeQuery("select max(id) as id From A10db.CourseCatalogCourseList");
			if(rs.next())
			{
				long maxId = 0;
				if (rs.getObject("id") != null)
					maxId = rs.getLong("id");
				return maxId + 1;
			}
		} catch (Exception ex) {
			throw ex;
		}
		return 0;
	}
	
	public ArrayList<Course> getAllCourses() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = (Statement) connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Course");
            ArrayList<Course> courses = new ArrayList<>();
            
            while(rs.next())
            {
               	Course c = new Course();
                c.setcourseID(rs.getInt("courseid"));
                c.setname(rs.getString("courseTitle"));
                c.setdescription(rs.getString("courseDescription"));
                c.setcost(rs.getBigDecimal("courseCost"));
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
	        ps.setLong(1, c.getprerequisiteID());
	        ps.setLong(2, c.getID());
	        ps.setLong(3, c.getcourseID());
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
	public long getCourseOfferingNextId () throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		try {
			Statement stmt = (Statement) connection.createStatement();
			ResultSet rs = stmt.executeQuery("select max(id) as id From A10db.CourseOffering");
			if(rs.next())
			{
				long maxId = 0;
				if (rs.getObject("id") != null)
					maxId = rs.getLong("id");
				return maxId + 1;
			}
		} catch (Exception ex) {
			throw ex;
		}
		return 0;
	}
	
    public boolean insertCourseOffering(CourseOffering c) throws SQLException {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("INSERT INTO CourseOffering VALUES (?, ?, ?, ?, ?, ?)");
	        ps.setLong(1, c.getID());
	        ps.setLong(2, c.getcourseID());
	        ps.setLong(3, c.getUUIID());
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
               cp.setcourseID(rs.getLong("courseid"));
               cp.setUUIID(rs.getLong("uuiid"));
               cp.settotalstudents(rs.getInt("totalstudents"));
               
               coursespreq.add(cp);
           }
           
           return coursespreq;
           
       } catch (Exception ex) {
           throw ex;
       }
}

	public Set<CourseOffering> getCourseOffering(Term term, int year) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		try {
			Set<CourseOffering> courseOfferings = new HashSet<CourseOffering>();
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement("select * from CourseOffering where term = ? and year = ?");
			ps.setInt(1, term.getValue());
			ps.setInt(2, year);
			ResultSet rs = ps.executeQuery();

			while(rs.next())
			{
				CourseOffering cp = new CourseOffering();
				cp.setID(rs.getInt("id"));
				cp.setterm(rs.getInt("term"));
				cp.setyear(rs.getInt("year"));
				cp.setcourseID(rs.getLong("courseid"));
				cp.setUUIID(rs.getLong("uuiid"));
				cp.settotalstudents(rs.getInt("totalstudents"));

				courseOfferings.add(cp);
			}

			return courseOfferings;

		} catch (Exception ex) {
			throw ex;
		}
	}

	public boolean deleteCourseOfferings(Term term, int year) throws SQLException {

		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement("DELETE FROM CourseOffering WHERE term = ? and year = ?");
			ps.setInt(1, term.getValue());
			ps.setInt(2, year);
			int i = ps.executeUpdate();
			if(i == 1) {
				return true;
			}
		} catch (Exception ex) {
			throw ex;
		}
		return false;
	}
    
    public boolean insertCourseCatalog(CourseCatalog c) throws SQLException {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("INSERT INTO CourseCatalog VALUES (?, ?, ?)");
	        ps.setLong(1, c.getid());
	        ps.setLong(2, c.getterm());
	        ps.setLong(3, c.getyear());

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
	        ps.setLong(1, cl.getid());
	        ps.setLong(2, cl.getcoursecatalogid());
	        ps.setLong(3, cl.getcourseid());

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
