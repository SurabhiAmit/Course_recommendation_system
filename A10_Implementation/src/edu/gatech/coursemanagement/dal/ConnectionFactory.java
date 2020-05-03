package edu.gatech.coursemanagement.dal;

import com.mysql.jdbc.Driver;
import edu.gatech.coursemanagement.utils.CourseManagementUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static final String URL = CourseManagementUtil.getProperty("jdbcURL");
    public static final String USER = CourseManagementUtil.getProperty("username");
    public static final String PASS = CourseManagementUtil.getProperty("password");

    public static Connection getConnection()
    {
      try {
          DriverManager.registerDriver(new Driver());
          return DriverManager.getConnection(URL, USER, PASS);
      } catch (SQLException ex) {
          throw new RuntimeException("Error connecting to the database", ex);
      }
    }

    
    
}
