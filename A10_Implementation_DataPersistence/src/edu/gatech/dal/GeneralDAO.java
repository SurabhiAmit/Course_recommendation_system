package edu.gatech.dal;

import com.mysql.jdbc.*;
import edu.gatech.dto.ApplicationState;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneralDAO {
	
	public boolean insertApplicationState(ApplicationState ap) throws SQLException {
		//ConnectionFactory connector = new ConnectionFactory();
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("INSERT INTO ApplicationState VALUES (?, ?, ?, ?, ?, ?, ?)");
	        ps.setInt(1, ap.getid());
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
	    } catch (Exception ex) {
	    	throw ex;
	    }
	    return false;
	}
	
	public ApplicationState getApplicationState () throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = (Statement) connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from ApplicationState");
            if(rs.next())
            {
                ApplicationState s = new ApplicationState();
                s.setid(rs.getInt("id"));
                s.setcurrentmode(rs.getInt("currentmode"));
                s.setcurrentlyProcessingTerm(rs.getInt("currentlyProcessingTerm"));
                s.setcurrentlyProcessingFile(rs.getString("currentlyProcessingFile"));
                s.setsimulationstep(rs.getInt("simulationstep"));
                s.setfilestoprocess(rs.getString("filestoprocess") );
                s.setfilesProcessed(rs.getString("filesProcessed") );
                return s;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return null;
	}
	
	public boolean clearApplicationState() throws SQLException {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("DELETE FROM ApplicationState");
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
