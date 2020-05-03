package edu.gatech.dal;


import com.mysql.jdbc.*;

import edu.gatech.dto.DegreeProgram;
import edu.gatech.dto.DegreeProgramCourse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DegreeProgramDAO {
	
	public boolean insertDegreeProgram(DegreeProgram dp) throws SQLException  {
	
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("INSERT INTO DegreeProgram VALUES (?, ?)");
	        ps.setString(1, dp.getname());
	        ps.setInt(2, dp.getprogramID());
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (Exception ex) {
	    	throw ex;
	    }
	    return false;
	}
	
	public boolean deleteDegreeProgram(int id) throws SQLException  {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("DELETE FROM DegreeProgram WHERE ID = ?");
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
	
	public boolean updateDegreeProgram(int id, String name) throws SQLException  {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("UPDATE DegreeProgram SET name = ? WHERE ID = ?");
	        ps.setString(1, name);
	        ps.setInt(2, id);
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (Exception ex) {
	    	throw ex;
	    }
	    return false;
	}

	public boolean insertDegreeProgramCourse(DegreeProgramCourse dpc) throws SQLException  {
	
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("INSERT INTO DegreeProgramCourses VALUES (?, ?, ?)");
	      
	        ps.setInt(2, dpc.getprogramID());
	        ps.setInt(3, dpc.getcourseID());
	        ps.setInt(1, dpc.getID());
	        
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (Exception ex) {
	    	throw ex;
	    }
	    return false;
	}
	
	public Set<DegreeProgram> getAllDegreeProgram() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = (Statement) connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from DegreeProgram");
            Set<DegreeProgram> programs = new HashSet<DegreeProgram>();
            
            while(rs.next())
            {
            	DegreeProgram i = new DegreeProgram();

            	i.setprogramID(rs.getInt("id"));
            	i.setname(rs.getString("name"));
            	programs.add(i);
            	
            }            
            return programs;
            
        } catch (Exception ex) {
            throw ex;
        }
}
	
	public Set<DegreeProgramCourse> getAllDegreeProgramCourses() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = (Statement) connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from DegreeProgramCourses");
            Set<DegreeProgramCourse> dpcourses = new HashSet<DegreeProgramCourse>();
            
            while(rs.next())
            {
            	DegreeProgramCourse d = new DegreeProgramCourse();

            	d.setID(rs.getInt("id"));
            	d.setprogramID(rs.getInt("Degreeprogram_id"));
            	d.setcourseID(rs.getInt("Courseid"));            	
            	dpcourses.add(d);
            	
            }            
            return dpcourses;
            
        } catch (Exception ex) {
            throw ex;
        }
}
	
	
}
