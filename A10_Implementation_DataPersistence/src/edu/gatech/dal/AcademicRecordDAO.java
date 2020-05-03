package edu.gatech.dal;

import com.mysql.jdbc.*;
import edu.gatech.dto.AcademicRecord;

import java.sql.Connection;
import java.sql.SQLException;

public class AcademicRecordDAO {
	
	public boolean insertAcademicRecord(AcademicRecord a) throws SQLException
	{
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("INSERT INTO AcademicRecord VALUES (?, ?, ?, ?, ?)");
	        ps.setInt(1, a.getterm());
	        ps.setInt(2, a.getyear());
	        ps.setInt(3, a.getUUSID());
	        ps.setInt(4, a.getcourseID());
	        ps.setString(5, a.getgrade());
	       
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (Exception ex) {
	    	throw ex;
	    }
	    return false;
	}
	
	public boolean updateAcademicRecordGrade(AcademicRecord a) throws SQLException {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("UPDATE AcademicRecord SET grade = ?  WHERE uusid = ? and courseid = ? and year = ? and term = ?");
	        ps.setInt(5, a.getterm());
	        ps.setInt(4, a.getyear());
	        ps.setInt(2, a.getUUSID());
	        ps.setInt(3, a.getcourseID());
	        ps.setString(1, a.getgrade());
	        
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (Exception ex) {
	    	throw ex;
	    }
	    return false;
	}

}
