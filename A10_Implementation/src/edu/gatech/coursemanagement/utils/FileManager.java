package edu.gatech.coursemanagement.utils;

import edu.gatech.coursemanagement.entity.*;
import edu.gatech.coursemanagement.exception.CourseManagementRuntimeException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class FileManager {

    private ApplicationDataModel dataModel;

    String[] managementSystemFiles = {
            "courses.csv",
            "prereqs.csv",
            "instructors.csv",
            "programs.csv",
            "listings.csv",
            "students.csv"};

    public FileManager(ApplicationDataModel dataModel) {
        this.dataModel = dataModel;
    }

    public ValidationResult loadDataFiles() {

        ValidationResult result = new ValidationResult();

        for (String nextFileName : managementSystemFiles) {
            loadFileContents(nextFileName, result);
        }

        return result;
    }

    public ValidationResult loadRequestsFile(int sequence) {

        ValidationResult result = new ValidationResult();

        String filename = "requests" + sequence + ".csv";
        if (!exists(filename)) {
            result.setSuccessful(false);
            result.addValidationMessage("File " + filename + " does not exist");
            return result;
        }

        loadFileContents(filename, result);
        return result;
    }

    public static boolean exists(String inputFileName) {
        File file = new File(inputFileName);
        return file.exists();
    }

    private void loadFileContents(String inputFileName, ValidationResult result) {

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
                // Get all tokens available in line
                String[] tokens = line.split(DELIMITER);
                //displayFileContents(inputFileName, tokens);
                if (inputFileName.startsWith("requests"))
                    loadFileContents("requests.csv", tokens, result);
                else
                    loadFileContents(inputFileName, tokens, result);
            }

            result.setSuccessful(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            result.addValidationMessage(e.getMessage());
            result.setSuccessful(false);
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadFileContents(String inputFileName, String[] tokens, ValidationResult result) {
        switch (inputFileName) {
            case "courses.csv":
                loadCourses(tokens, result);
                break;
            case "instructors.csv":
                loadInstructors(tokens, result);
                break;
            case "listings.csv":
                loadListings(tokens, result);
                break;
            case "prereqs.csv":
                loadPrerequisites(tokens, result);
                break;
            case "programs.csv":
                loadPrograms(tokens, result);
                break;
            case "students.csv":
                loadStudents(tokens, result);
                break;
            case "requests.csv":
                loadRequests(tokens, result);
                break;
            default:
                //System.out.println("# error: unknown input file name");
                result.setSuccessful(false);
                result.addValidationMessage("Unknown input file name: " + inputFileName);
                break;
        }
    }

    private void loadCourses(String[] tokens, ValidationResult result) {
        Course course = new Course();
        course.setCourseId(Integer.parseInt(tokens[0]));
        course.setCourseTitle(tokens[1]);
        course.setCost(new BigDecimal(tokens[2]));
        course.setCourseDescription( course.getCourseTitle() );

        try {
            dataModel.getCourseCatalog().addCourse(course);
        }
        catch (Exception ex) {
            result.addValidationMessage("Error processing course (" + course.getCourseId() + "). " + ex.getMessage());
        }
    }

    private void loadPrerequisites(String[] tokens, ValidationResult result) {
        int courseId = Integer.parseInt(tokens[0]);
        int prereqId = Integer.parseInt(tokens[1]);

        if (courseId == prereqId) {
            result.addValidationMessage("Loading prerequisites: course " + courseId + " cannot have a prerequisite course with the same ID");
            return;
        }

        CourseCatalog courseCatalog = dataModel.getCourseCatalog();

        Course course = null;
        Course coursePrereq = null;

        try {
            course = courseCatalog.findCourseById(courseId);
        }
        catch (Exception ex) {}

        try {
            coursePrereq = courseCatalog.findCourseById(prereqId);
        }
        catch (Exception ex) {}

        if (course != null && coursePrereq != null) {
            course.addPrerequisite(coursePrereq);
        }
        else {
            if (course == null)
                result.addValidationMessage("Loading prerequisites: Course (" + courseId + ") is invalid.");
            if (coursePrereq == null)
                result.addValidationMessage("Loading prerequisites: Prerequisite Course (" + prereqId + ") is invalid.");
        }
    }

    private void loadInstructors(String[] tokens, ValidationResult result) {
        Instructor instructor = new Instructor();
        instructor.setUuiid(Integer.parseInt(tokens[0]));
        instructor.setInstructorName(tokens[1]);
        instructor.setOfficeHours(tokens[2]);
        instructor.setEmailAddress(tokens[3]);

        if (dataModel.getInstructors().containsKey(instructor.getUuiid()))
            result.addValidationMessage("Unique ID duplication error:  first record with instructor ID " + instructor.getUuiid() + " is considered and duplicated records discarded");

        dataModel.getInstructors().putIfAbsent(instructor.getUuiid(), instructor);
    }

    private void loadPrograms(String[] tokens, ValidationResult result) {
        DegreeProgram program = new DegreeProgram(Integer.parseInt(tokens[0]), tokens[1]);
        if (dataModel.getPrograms().containsKey(program.getDegreeId()))
            result.addValidationMessage("Unique ID duplication error:  first record with program ID " + program.getDegreeId() + " is considered and duplicated records discarded");
        dataModel.getPrograms().putIfAbsent(program.getDegreeId(), program);
    }

    private void loadListings(String[] tokens, ValidationResult result) {
        int programId = Integer.parseInt(tokens[0]);
        int courseId = Integer.parseInt(tokens[1]);

        try {
            Course course = dataModel.getCourseCatalog().findCourseById(courseId);
            HashMap<Integer, DegreeProgram> programs = dataModel.getPrograms();

            if (programs.containsKey(programId) && course != null) {
                programs.get(programId).addRequiredCourse(course);
            }
            else
                result.addValidationMessage("Load listings: Program (" + programId + ") is invalid");
        }
        catch (Exception ex) {
            result.addValidationMessage("Load listings: course (" + courseId + ") - " + ex.getMessage());
        }
    }

    private void loadStudents(String[] tokens, ValidationResult result) {
        Student student = new Student(Long.parseLong(tokens[0]), tokens[1], tokens[2], Long.parseLong(tokens[3]), null);

        int programId = Integer.parseInt(tokens[4]);
        HashMap<Integer, DegreeProgram> programs = dataModel.getPrograms();

        if (programs.containsKey(programId)) {
            student.setCurrentDegreeProgram(programs.get(programId));
        }

        if (dataModel.getStudents().containsKey(student.getUusid()))
            result.addValidationMessage("Unique ID duplication error:  first record with student ID " + student.getUusid() + " is considered and duplicated records discarded");
        dataModel.getStudents().putIfAbsent(student.getUusid(), student);
    }

    private void loadRequests(String[] tokens, ValidationResult result) {
        long studentId = Long.parseLong(tokens[0]);
        long courseId = Long.parseLong(tokens[1]);

        Student student = dataModel.getStudent(studentId);
        Course course = dataModel.getCourse(courseId);

        if (student != null && course != null) {
            dataModel.getRequests().add(new CourseRequest(student, course));
        }
        else {
            if (student == null)
                result.addValidationMessage("Load Requests: Student (" + studentId + ") is invalid");
            if (course == null)
                result.addValidationMessage("Load Requests: Course (" + courseId + ") is invalid");
        }
    }
}
