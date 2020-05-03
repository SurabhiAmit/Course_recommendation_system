package edu.gatech.coursemanagement.dal;

import com.mysql.jdbc.*;

import edu.gatech.coursemanagement.dto.AcademicRecord;
import edu.gatech.coursemanagement.utils.CourseManagementUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AcademicRecordDAO {

	public List<AcademicRecord> getAllRecords()
	{
		String sql = "select * from AcademicRecord";
		List<AcademicRecord> allRecords = new ArrayList<AcademicRecord>();
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				AcademicRecord record = new AcademicRecord();
				record.setUUSID( rs.getLong("uusid") );
				record.setcourseID(rs.getLong("courseid"));
				record.setgrade(rs.getString("grade"));
				record.setyear(rs.getInt("year"));
				record.setterm(rs.getInt("term"));
				allRecords.add(record);
			}
		}catch(SQLException e)
		{
			CourseManagementUtil.logError( this.getClass(), e.getMessage() );
		}
		return allRecords;
	}

	public boolean insertAcademicRecord(AcademicRecord a)
	{
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("INSERT INTO AcademicRecord VALUES (?, ?, ?, ?, ?)");
	        ps.setInt(1, a.getterm());
	        ps.setInt(2, a.getyear());
	        ps.setLong(3, a.getUUSID());
	        ps.setLong(4, a.getcourseID());
	        ps.setString(5, a.getgrade());
	       
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;
	}
	
	public boolean updateAcademicRecordGrade(AcademicRecord a) {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("UPDATE AcademicRecord SET grade = ?  WHERE uusid = ? and courseid = ? and year = ? and term = ?");
	        ps.setInt(5, a.getterm());
	        ps.setInt(4, a.getyear());
	        ps.setLong(2, a.getUUSID());
	        ps.setLong(3, a.getcourseID());
	        ps.setString(1, a.getgrade());
	        
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;
	}
}
