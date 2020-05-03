package edu.gatech.dal; 
 
import edu.gatech.dto.Student;

import com.mysql.jdbc.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class StudentDAO {
	
	public boolean insertStudent(Student s) throws SQLException {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("INSERT INTO Student VALUES (?, ?, ?, ?, ?)");
	        ps.setInt(1, s.getUUSID());
	        ps.setString(2, s.getname());
	        ps.setString(3, s.getaddress());
	        ps.setLong(4, s.getphoneNumber());
	        ps.setInt(5, s.getprogramID());
	       
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      } 
	    } catch (Exception ex) {
	    	throw ex;
	    }
	    return false;
	}
	
	public boolean deleteStudent(int uusid) throws SQLException {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("DELETE FROM Student WHERE UUSID = ?");
	        ps.setInt(1, uusid);
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (Exception ex) {
	    	 throw ex;
	    }
	    return false;
	}
	
	public boolean updateStudent(Student s) throws SQLException {
		
	    Connection connection = ConnectionFactory.getConnection();
	    try {
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("UPDATE Student SET studentname = ?, homeaddress = ?, phonenumber = ?, degreeprogram_id = ?  WHERE uusid = ?");
	        ps.setString(1, s.getname());
	        ps.setString(2, s.getaddress());
	        ps.setLong(3, s.getphoneNumber());
	        ps.setInt(4, s.getprogramID());
	        ps.setInt(5, s.getUUSID());
	        
	        int i = ps.executeUpdate();
	      if(i == 1) {
	        return true;
	      }
	    } catch (Exception ex) {
	    	 throw ex;
	    }
	    return false;
	}


	public Student getStudent(int id) throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = (Statement) connection.createStatement();
            ResultSet rs = stmt.executeQuery("select uusid,studentName,homeAddress,phoneNumber,DegreeProgram_id from Student where uusid = " + id);
            if(rs.next())
            {
                Student s = new Student();
                s.setUUSID(rs.getInt("uusid"));
                s.setname(rs.getString("studentName"));
                s.setaddress(rs.getString("homeAddress"));
                s.setphoneNumber(rs.getInt("phoneNumber"));
                s.setprogramID(rs.getInt("DegreeProgram_id") );
                return s;
            }
        } catch (Exception ex) {
            throw ex;
        }
    return null;
}
	public Set<Student> getAllStudent() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = (Statement) connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Student");
            Set<Student> students = new HashSet<Student>();
            
            while(rs.next())
            {
                Student s = new Student();
                s.setUUSID(rs.getInt("uusid"));
                s.setname(rs.getString("studentName"));
                s.setaddress(rs.getString("homeAddress"));
                s.setphoneNumber(rs.getInt("phoneNumber"));
                s.setprogramID(rs.getInt("DegreeProgram_id") );
                students.add(s);
            }
            
            return students;
            
        } catch (Exception ex) {
            throw ex;
        }
}
	
}
