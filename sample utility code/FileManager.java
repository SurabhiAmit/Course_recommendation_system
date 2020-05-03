package edu.gatech.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import edu.gatech.pojo.AcademicRecord;
import edu.gatech.pojo.Address;
import edu.gatech.pojo.Course;
import edu.gatech.pojo.DegreeProgram;
import edu.gatech.pojo.Instructor;
import edu.gatech.pojo.Listing;
import edu.gatech.pojo.Prerequsite;
import edu.gatech.pojo.Request;
import edu.gatech.pojo.Student;

public class FileManager {

	public FileManager() { }
	
    // This method has be taken from the scratchpad code provided in the assignment
    private static Object processFileContents(String inputFileName, String[] tokens) {
		
    	// Build specific objects based on the name of the input file being processed
    	switch (inputFileName) {
        case "courses.csv":
    			Course c = new Course();
        	    c.setcourseID(Integer.parseInt(tokens[0]));
        	    c.setname(tokens[1]);;
        	    c.setcost(Integer.parseInt(tokens[2]));
        	    return c;
    			
        case "instructors.csv":
        		Instructor i = new Instructor();
        	    i.setUUIID(Integer.parseInt(tokens[0]));
        	    i.setname(tokens[1]);
        	    i.setofficeHours(tokens[2]);
        	    i.setemail(tokens[3]);
        	    i.setcourseID(Integer.parseInt(tokens[4]));
        	    return i;
        		
    	case "listings.csv":
    			Listing l = new Listing();
    			l.setcourseID(Integer.parseInt(tokens[0]));
    			l.setprogrameID(Integer.parseInt(tokens[1]));
    			return l;
    			
    	case "prereqs.csv":
    			Prerequsite p = new Prerequsite();
				p.setcourseID(Integer.parseInt(tokens[0]));
				p.setprereqCourseID(Integer.parseInt(tokens[1]));
				return p;
    			
		case "programs.csv":
				DegreeProgram d = new DegreeProgram();
				d.setprogramID(Integer.parseInt(tokens[0]));
				d.setname(tokens[1]);
				return d;
    		
		case "records.csv":
    			AcademicRecord a = new AcademicRecord();
    			a.setUUSID(Integer.parseInt(tokens[0]));
    			a.setcourseID(Integer.parseInt(tokens[1]));
				switch (tokens[2]) {
					case "A" : a.setgrade(Utility.Grade.A); break;
					case "B" : a.setgrade(Utility.Grade.B); break;
					case "C" : a.setgrade(Utility.Grade.C); break;
					case "D" : a.setgrade(Utility.Grade.D); break;
					case "F" : a.setgrade(Utility.Grade.F); break;
				}
				
				a.setyear(Integer.parseInt(tokens[3]));

				switch (tokens[4]) {
					case "Spring" : a.setterm(Utility.Term.Spring);
					case "Winter" : a.setterm(Utility.Term.Winter);
					case "Summer" : a.setterm(Utility.Term.Summer);
					case "Fall" : a.setterm(Utility.Term.Fall);
				}
				return a;
    			
		case "requests.csv":
    			Request r = new Request();
    			r.setUUSID(Integer.parseInt(tokens[0]));
    			r.setcourseID(Integer.parseInt(tokens[1]));
    			return r;
    			
		case "students.csv":
				Student s = new Student();
				s.setUUSID(Integer.parseInt(tokens[0]));
				s.setname(tokens[1]);	
				Address ad = new Address();
				ad.parseAddress(tokens[2]);
				s.setaddress(ad);
				s.setphoneNumber(Long.parseLong(tokens[3]));
				s.setprogramID(Integer.parseInt(tokens[4]));
				return s;            	
    	}
    	
    	return new Object();
    }
        
    // This method has be taken as is from the scratchpad code provided in the assignment
    @SuppressWarnings("unchecked")
    public static <T> void uploadFileContentsToList(String inputFileName, List<T> inputList) {
		
    	// Input file which needs to be parsed
    	String fileToParse = inputFileName;
    	BufferedReader fileReader = null;
    
    	// Delimiter used in CSV file
    	final String DELIMITER = ",";
    	try {
    		String line = "";
    		// Create the file reader
    		fileReader = new BufferedReader(new FileReader(fileToParse));

    		// Read the file line by line
    		while ((line = fileReader.readLine()) != null) {
    			T myObject = null;
    			// Get all tokens available in line
    			String[] tokens = line.split(DELIMITER);
    			myObject = (T)processFileContents(inputFileName, tokens);
    			inputList.add(myObject);
    		}
    	} 
    	catch (Exception e) {
    		e.printStackTrace();
    	} 
    	finally {
    		try { fileReader.close(); 
    		} 
    		catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }    
}
