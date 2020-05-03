package edu.gatech.coursemanagement.utils;

//import edu.gatech.coursemanagement.data.ApplicationState;
//import edu.gatech.coursemanagement.data.ProcessState;
import edu.gatech.coursemanagement.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// This class acts as a mediator
public class ApplicationManager {

    private static ApplicationManager instance;
    public boolean isInitialized;
    private ApplicationDataModel dataModel;
    private int instructorReassignments;
    private boolean allowInstructorReassigments;

    private ApplicationManager() {
        isInitialized = false;
        dataModel = new ApplicationDataModel();
    }

    public static ApplicationManager getInstance() {
        if (instance == null) {
            instance = new ApplicationManager();
            instance.setInitialState();
        }
        return instance;
    }

    public ValidationResult initializeFirstRun() {
        FileManager fileManager = new FileManager(dataModel);
        ValidationResult result = fileManager.loadDataFiles();
        if (!result.isSuccessful())
            return result;

        CourseCatalog catalog = dataModel.getCourseCatalog();
        //catalog.setId(applicationState.getSequenceCounter());
        catalog.setCurrentTerm(dataModel.getApplicationState().getTerm());
        catalog.setYear(dataModel.getApplicationState().getYear());

        DBManager dbManager = new DBManager(dataModel);
        dbManager.clearDB();
        dbManager.saveInitialDataSet();

        allowInstructorReassigments = true;
        instructorReassignments = 0;

        dataModel.getApplicationState().setCurrentProcessState(ProcessState.Started);
        dataModel.getApplicationState().setCurrentMode(ApplicationMode.INITIAL_MODE);

        return result;
    }

    public boolean hasSavedState() {
        DBManager dbManager = new DBManager(dataModel);
        return dbManager.hasApplicationState();
    }

    public void resumeFromSavedState() {
        DBManager dbManager = new DBManager(dataModel);
        dbManager.loadSavedState();

        allowInstructorReassigments = true;
        instructorReassignments = 0;

        dataModel.getApplicationState().setCurrentMode(ApplicationMode.RESUME_MODE);
    }

    public ValidationResult loadRequestData() {

        saveInstructorAssignments();

        FileManager fileManager = new FileManager(dataModel);
        ValidationResult result = fileManager.loadRequestsFile(dataModel.getApplicationState().getCurrentlyProcessingTerm());

        if (result.isSuccessful())
            dataModel.getApplicationState().setCurrentProcessState(ProcessState.ProcessedRequests);

        return result;
    }

    public void validateRequestData() {

        saveInstructorAssignments();

        for (CourseRequest request : dataModel.getRequests()) {
            ArrayList<Course> prerequisitesNotMet = new ArrayList<Course>();
            if (!request.getCourse().prerequisitesMet(request.getStudent(), prerequisitesNotMet)) {
                String validationMessage = "Denied: missing prerequisites -> ";
                for (Course course : prerequisitesNotMet) {
                    validationMessage = validationMessage + " - " + course.toString();
                }
                request.setValidationMessage(validationMessage, false);
            }
            else {
                ArrayList<CourseOffering> courseOfferings = request.getCourse().getCourseOfferings();
                if (courseOfferings != null && courseOfferings.size() > 0) {
                    request.setValidationMessage("Request granted", true);
                } else {
                    request.setValidationMessage("Denied: course is not being offered", false);
                }
            }
        }

        dataModel.getApplicationState().setCurrentProcessState(ProcessState.ValidatedRequests);
        allowInstructorReassigments = false;
    }

    public void generateAcademicRecords() {
        ArrayList<CourseRequest> grantedRequests = new ArrayList<>();
        for (CourseRequest request : dataModel.getRequests()) {
            if (request.isGranted()) {
                grantedRequests.add(request);
            }
        }

        //Map<Long,Integer> courseCount = new HashMap<Long,Integer>();
        int nextCount = 0;
        for (CourseRequest request : grantedRequests) {
            long currCourse = request.getCourse().getCourseId();
            /*if( !courseCount.containsKey( currCourse ) )
            {
                courseCount.put(currCourse, 0);
            }
            int nextCount  = courseCount.get( currCourse ) + 1;
            courseCount.put(currCourse, nextCount);*/
            Grade grade = AcademicRecord.generateRandomGrade( ++nextCount, grantedRequests.size() );
            AcademicRecord record = new AcademicRecord(request.getStudent(), request.getCourse(), dataModel.getApplicationState().getTerm(), dataModel.getApplicationState().getYear(), grade);
            dataModel.getRecords().add(record);
            dataModel.getNewRecords().add(record);
        }

        dataModel.getApplicationState().setCurrentProcessState(ProcessState.AssignedGrades);
    }

    public void saveInstructorAssignments() {
        DBManager dbManager = new DBManager(dataModel);
        dbManager.saveCourseOfferings(dataModel.getApplicationState().getTerm(), dataModel.getApplicationState().getYear());
    }

    public boolean canReassignInstructor() {
        return allowInstructorReassigments && instructorReassignments < 1;
    }

    public void trackInstructorAssignments() {
        if (canReassignInstructor() && dataModel.getApplicationState().getCurrentProcessState() == ProcessState.ProcessedRequests) {
            instructorReassignments++;
        }
    }

    public void startNewTerm() {
        // save to DB
        DBManager dbManager = new DBManager(dataModel);
        dbManager.saveAcademicRecords();
        dbManager.saveApplicationState();

        // prepare new term data
        dataModel.getApplicationState().startNewTerm();

        // clear requests
        dataModel.getRequests().clear();

        // clear new records
        dataModel.getNewRecords().clear();

        // clear instructor assignment
        Administrator administrator = new Administrator();
        for (Instructor instructor : dataModel.getInstructorsList()) {
            administrator.removeInstructorAssignment(instructor);
        }

        // reset reassignment flags
        allowInstructorReassigments = true;
        instructorReassignments = 0;
    }

    private void setInitialState() {
        dataModel.getApplicationState().setCurrentlyProcessingTerm(0);
    }

    public Term getTerm() {
        return dataModel.getApplicationState().getTerm();
    }

    public int getYear() {
        return dataModel.getApplicationState().getYear();
    }

    public ProcessState getState() {
        return dataModel.getApplicationState().getCurrentProcessState();
    }

    public String getCurrentTermText() {
        return dataModel.getApplicationState().getTerm() + " " + dataModel.getApplicationState().getYear();
    }

    public CourseCatalog getCourseCatalog() {
        return dataModel.getCourseCatalog();
    }

    public HashMap<Long, Instructor> getInstructors() {
        return dataModel.getInstructors();
    }

    public HashMap<Integer, DegreeProgram> getPrograms() {
        return dataModel.getPrograms();
    }

    public HashMap<Long, Student> getStudents() {
        return dataModel.getStudents();
    }

    public ArrayList<CourseRequest> getRequests() {
        return dataModel.getRequests();
    }

    public ArrayList<AcademicRecord> getRecords() {
        return dataModel.getRecords();
    }
}
