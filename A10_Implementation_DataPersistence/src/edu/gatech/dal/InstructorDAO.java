package edu.gatech.dal;

import com.mysql.jdbc.*;

import edu.gatech.dto.Instructor;
import edu.gatech.dto.InstructorOfficeHour;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class InstructorDAO {
	
	public boolean insertInstructor(Instructor ins) throws SQLException {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("INSERT INTO Instructor VALUES (?, ?, ?)");
	        ps.setInt(1, ins.getUUIID());
	        ps.setString(2, ins.getname());
	        ps.setString(3, ins.getemail());
	       
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (Exception ex) {
	    	throw ex;
	    }
	    return false;
	}
	
	public boolean deleteInstructor(int uuiid) throws SQLException {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("DELETE FROM Instructor WHERE UUIID = ?");
	        ps.setInt(1, uuiid);
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (Exception ex) {
	    	throw ex;
	    }
	    return false;
	}
	
	public boolean updateInstructor(Instructor ins) throws SQLException {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("UPDATE Instructor SET instructorname = ?, emailaddress = ? WHERE uuiid = ?");
	        ps.setString(1, ins.getname());
	        ps.setString(2, ins.getemail());
	        ps.setInt(3, ins.getUUIID());
	        
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (Exception ex) {
	    	throw ex;
	    }
	    return false;
	}

	public boolean insertInstructorOfficeHours(InstructorOfficeHour ins) throws SQLException {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("INSERT INTO InstructorOfficeHours VALUES (?, ?, ?)");
	        ps.setInt(1, ins.getid());
	        ps.setInt(2, ins.getuuiid());
	        ps.setString(3, ins.getofficehours());
	       
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (Exception ex) {
	    	throw ex;
	    }
	    return false;
	}
	
	public Set<Instructor> getAllInstructors() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = (Statement) connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Instructor");
            Set<Instructor> instructors = new HashSet<Instructor>();
            
            while(rs.next())
            {
            	Instructor i = new Instructor();

            	i.setUUIID(rs.getInt("uuiid"));
            	i.setname(rs.getString("instructorName"));
            	i.setemail(rs.getString("emailaddress"));
            	
            	instructors.add(i);
            	
            }
            
            return instructors;
            
        } catch (Exception ex) {
            throw ex;
        }
}
	
	
	
}
