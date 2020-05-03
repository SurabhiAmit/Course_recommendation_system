package edu.gatech.coursemanagement.dal;

import com.mysql.jdbc.*;

import edu.gatech.coursemanagement.dto.ApplicationState;
import edu.gatech.coursemanagement.dto.Course;
import edu.gatech.coursemanagement.exception.CourseManagementRuntimeException;
import edu.gatech.coursemanagement.utils.CourseManagementUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneralDAO {

	public void clearDB()
	{
		CourseManagementUtil.logInfo(this.getClass(),"will clear the db");
		Connection connection = ConnectionFactory.getConnection();
		String allTablesToTrunc[] = {
				"AcademicRecord", "DegreeProgramCourses",
				"CourseOffering", "InstructorOfficeHours",
				"CoursePrerequisites",
				"CourseCatalogCourseList","CourseCatalog",
				"ApplicationState",
				"Instructor","Course", "Student", "DegreeProgram"

		};
		PreparedStatement ps = null;
		try {
			boolean isAutoCommitEnabled = connection.getAutoCommit();
			//connection.setAutoCommit(false);
			for(String tableName: allTablesToTrunc) {

					ps = (PreparedStatement) connection.prepareStatement("delete from " + tableName);
					CourseManagementUtil.logWarn(this.getClass(),"Clearing table " + tableName);
					int i = ps.executeUpdate();
					CourseManagementUtil.logWarn(this.getClass(),"Cleared table " + tableName);
					ps.close();
			}
			//connection.commit();
			//connection.setAutoCommit(isAutoCommitEnabled);
		} catch (SQLException ex) {
			CourseManagementUtil.logError(this.getClass(), ex.getMessage());
		}
		finally {
			if( ps != null )
			{
				try {
					ps.close();
				}catch(SQLException e)
				{
					//no-op
				}
			}
		}
	}

	public long getApplicationStateNextId () throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		try {
			Statement stmt = (Statement) connection.createStatement();
			ResultSet rs = stmt.executeQuery("select max(id) as id From A10db.ApplicationState");
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

	public boolean insertApplicationState(ApplicationState ap) {
		//ConnectionFactory connector = new ConnectionFactory();
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("INSERT INTO ApplicationState VALUES (?, ?, ?, ?, ?, ?, ?)");
	        ps.setLong(1, ap.getid());
	        ps.setInt(2, ap.getcurrentmode());
	        ps.setInt(3, ap.getcurrentlyProcessingTerm());
	        ps.setString(4, ap.getcurrentlyProcessingFile());
	        ps.setInt(5, ap.getsimulationstep());
	        ps.setString(6, ap.getfilestoprocess());
	        ps.setString(7, ap.getfilesProcessed());
	        
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false;
	}

	public ApplicationState getApplicationState (long id) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		try {
			Statement stmt = (Statement) connection.createStatement();
			ResultSet rs = stmt.executeQuery("select id, currentlyProcessingTerm From A10db.ApplicationState where id = " + id);
			if(rs.next())
			{
				ApplicationState s = new ApplicationState();
				s.setid(rs.getLong("id"));
				s.setcurrentlyProcessingTerm(rs.getInt("currentlyProcessingTerm"));
				return s;
			}
		} catch (Exception ex) {
			throw ex;
		}
		return null;
	}
}
