package edu.gatech.coursemanagement.utils;

import edu.gatech.coursemanagement.dal.*;
import edu.gatech.coursemanagement.dto.CourseCatalog;
import edu.gatech.coursemanagement.dto.CourseCatalogCourseList;
import edu.gatech.coursemanagement.dto.CoursePrerequisite;
import edu.gatech.coursemanagement.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DBManager {

    private ApplicationDataModel dataModel;

    public DBManager(ApplicationDataModel dataModel) {
        this.dataModel = dataModel;
    }

    public void clearDB() {
        GeneralDAO dao = new GeneralDAO();
        dao.clearDB();
    }

    public void loadSavedState() {
        loadCourses();
        loadCoursePrerequisites();
        loadInstructors();
        loadDegreePrograms();
        loadDegreeProgramCourses();
        loadStudents();
        loadAcademicRecords();
        loadApplicationState();

        Term term = dataModel.getApplicationState().getTerm();
        int year = dataModel.getApplicationState().getYear();

        loadCourseOfferings(term, year);
    }

    public void loadCourses() {
        CourseDAO dao = new CourseDAO();
        try {
            for (edu.gatech.coursemanagement.dto.Course dto : dao.getAllCourses()) {
                Course entity = DtoMapper.mapCourseDtoToEntity(dto);
                dataModel.getCourseCatalog().addCourse(entity);
            }
        }
        catch (Exception ex)
        {
            CourseManagementUtil.logError(getClass(), ex.getMessage());
        }
    }

    public void loadCoursePrerequisites() {
        CourseDAO dao = new CourseDAO();
        try {
            Set<CoursePrerequisite> list = dao.getAllCoursesPrerequisites();
            for (CoursePrerequisite prereq : list) {
                Course course = dataModel.getCourse(prereq.getcourseID());
                Course prereqCourse = dataModel.getCourse(prereq.getprerequisiteID());

                if (course != null && prereqCourse != null) {
                    course.addPrerequisite(prereqCourse);
                }
            }
        }
        catch (Exception ex) {
            CourseManagementUtil.logError(getClass(), ex.getMessage());
        }
    }

    public void loadInstructors() {
        InstructorDAO dao = new InstructorDAO();
        try {
            Set<edu.gatech.coursemanagement.dto.Instructor> list = dao.getAllInstructors();
            for (edu.gatech.coursemanagement.dto.Instructor dto : list) {
                Instructor entity = DtoMapper.mapInstructorDtoToEntity(dto);
                dataModel.getInstructors().putIfAbsent(entity.getUuiid(), entity);
            }
        }
        catch (Exception ex) {
            CourseManagementUtil.logError(getClass(), ex.getMessage());
        }
    }

    public void loadDegreeProgramCourses() {
        DegreeProgramDAO dao = new DegreeProgramDAO();
        try {
            Set<edu.gatech.coursemanagement.dto.DegreeProgramCourse> list = dao.getAllDegreeProgramCourses();
            for (edu.gatech.coursemanagement.dto.DegreeProgramCourse dto : list) {
                DegreeProgram program = dataModel.getProgram(dto.getprogramID());
                Course course = dataModel.getCourse(dto.getcourseID());
                if (program != null && course != null) {
                    program.addRequiredCourse(course);
                }
            }
        }
        catch (Exception ex) {
            CourseManagementUtil.logError(getClass(), ex.getMessage());
        }
    }

    public void loadDegreePrograms() {
        DegreeProgramDAO dao = new DegreeProgramDAO();
        try {
            Set<edu.gatech.coursemanagement.dto.DegreeProgram> list = dao.getAllDegreeProgram();
            for (edu.gatech.coursemanagement.dto.DegreeProgram dto : list) {
                if (dto.getprogramID() == 0)
                    continue; // skip program with ID 0
                DegreeProgram entity = DtoMapper.mapDegreeDtoToEntity(dto);
                dataModel.getPrograms().putIfAbsent(entity.getDegreeId(), entity);
            }
        }
        catch (Exception ex) {
            CourseManagementUtil.logError(getClass(), ex.getMessage());
        }
    }

    public void loadStudents() {
        StudentDAO dao = new StudentDAO();
        try {
            Set<edu.gatech.coursemanagement.dto.Student> list = dao.getAllStudent();
            for (edu.gatech.coursemanagement.dto.Student dto : list) {
                Student entity = DtoMapper.mapStudentDtoToEntity(dto);
                DegreeProgram program = dataModel.getProgram(dto.getprogramID());
                if (program != null)
                    entity.setCurrentDegreeProgram(program);
                dataModel.getStudents().putIfAbsent(entity.getUusid(), entity);
            }
        }
        catch (Exception ex) {
            CourseManagementUtil.logError(getClass(), ex.getMessage());
        }
    }

    public void loadAcademicRecords() {
        AcademicRecordDAO dao = new AcademicRecordDAO();
        try {
            List<edu.gatech.coursemanagement.dto.AcademicRecord> list = dao.getAllRecords();
            for (edu.gatech.coursemanagement.dto.AcademicRecord dto : list) {
                Student student = dataModel.getStudent(dto.getUUSID());
                Course course = dataModel.getCourse(dto.getcourseID());
                Term term = Term.getFromInt(dto.getterm());
                int year = dto.getyear();
                Grade grade = Grade.parse(dto.getgrade());
                if (student != null && course != null) {
                    AcademicRecord record = new AcademicRecord(student, course, term, year, grade);
                    dataModel.getRecords().add(record);
                }
            }
        }
        catch (Exception ex) {
            CourseManagementUtil.logError(getClass(), ex.getMessage());
        }
    }

    public void loadCourseOfferings(Term term, int year) {
        CourseDAO dao = new CourseDAO();
        try {
            Administrator administrator = new Administrator();
            Set<edu.gatech.coursemanagement.dto.CourseOffering> list = dao.getCourseOffering(term, year);
            for (edu.gatech.coursemanagement.dto.CourseOffering dto : list) {
                Instructor instructor = dataModel.getInstructor(dto.getUUIID());
                Course course = dataModel.getCourse(dto.getcourseID());
                if (instructor != null && course != null) {
                    administrator.assignInstructor(instructor, course, term, year);
                }
            }
        }
        catch (Exception ex) {
            CourseManagementUtil.logError(getClass(), ex.getMessage());
        }
    }

    public void loadApplicationState() {
        GeneralDAO dao = new GeneralDAO();
        try {
            long id = dao.getApplicationStateNextId();
            edu.gatech.coursemanagement.dto.ApplicationState dto = dao.getApplicationState(id - 1);
            if (dto != null) {
                dataModel.getApplicationState().setCurrentlyProcessingTerm(dto.getcurrentlyProcessingTerm());
                dataModel.getApplicationState().setCurrentProcessState(ProcessState.getFromInt(dto.getsimulationstep()));
                dataModel.getApplicationState().setCurrentMode(ApplicationMode.getFromInt(dto.getcurrentmode()));
            }
        }
        catch (Exception ex) {
            CourseManagementUtil.logError(getClass(), ex.getMessage());
        }
    }

    public boolean hasApplicationState() {
        GeneralDAO dao = new GeneralDAO();
        long id = 1;
        try {
            id = dao.getApplicationStateNextId();
            return id > 1;
        }
        catch (Exception ex) {
            CourseManagementUtil.logError(getClass(), ex.getMessage());
            return false;
        }
    }

    public void saveInitialDataSet() {
        saveCourses();
        saveCoursePrerequisites();
        saveCourseCatalog();
        saveInstructors();
        saveDegreePrograms();
        saveDegreeProgramCourses();
        saveStudents();
    }

    public void saveApplicationState() {
        edu.gatech.coursemanagement.dto.ApplicationState dto = new edu.gatech.coursemanagement.dto.ApplicationState();

        GeneralDAO dao = new GeneralDAO();

        try {
            long id = dao.getApplicationStateNextId();
            dto.setid(id);
            dto.setcurrentlyProcessingTerm(dataModel.getApplicationState().getCurrentlyProcessingTerm());
            dto.setcurrentlyProcessingFile("");
            dto.setfilestoprocess("");
            dto.setfilesProcessed("");
            dto.setcurrentmode(dataModel.getApplicationState().getCurrentMode().getValue());
            dto.setsimulationstep(dataModel.getApplicationState().getCurrentProcessState().getValue());
            dao.insertApplicationState(dto);
        }
        catch (Exception ex) {
            CourseManagementUtil.logError(getClass(), ex.getMessage());
        }
    }

    public void saveCourseOfferings(Term term, int year) {
        CourseDAO dao = new CourseDAO();

        try {
            long id = dao.getCourseOfferingNextId();
            dao.deleteCourseOfferings(term, year);
            for (Instructor instructor : dataModel.getInstructorsList()) {
                if (instructor.isCurrentlyInstructing()) {
                    edu.gatech.coursemanagement.dto.CourseOffering dto = new edu.gatech.coursemanagement.dto.CourseOffering();
                    dto.setID(id);
                    dto.setcourseID(instructor.getCurrentCourseTaught().getCourseId());
                    dto.setUUIID(instructor.getUuiid());
                    dto.setterm(term.getValue());
                    dto.setyear(year);
                    dao.insertCourseOffering(dto);
                    id++;
                }
            }
        }
        catch (Exception ex) {
            CourseManagementUtil.logError(getClass(), ex.getMessage());
        }
    }

    public void saveAcademicRecords() {
        for (AcademicRecord record : dataModel.getNewRecords()) {
            edu.gatech.coursemanagement.dto.AcademicRecord dto = DtoMapper.mapAcademicRecordEntityToDto(record);
            AcademicRecordDAO dao = new AcademicRecordDAO();
            try {
                dao.insertAcademicRecord(dto);
            }
            catch (Exception ex) {
                CourseManagementUtil.logError(this.getClass(),ex.getMessage());
            }
        }
    }

    private void saveStudents() {
        for (Student student : dataModel.getStudentsList()) {
            edu.gatech.coursemanagement.dto.Student dto = DtoMapper.mapStudentEntityToDto(student);
            StudentDAO dao = new StudentDAO();
            try {
                dao.insertStudent(dto);
            }
            catch (Exception ex) {
                CourseManagementUtil.logError(getClass(), ex.getMessage());
            }
        }
    }

    private void saveInstructors() {
        for (Instructor instructor : dataModel.getInstructorsList()) {
            edu.gatech.coursemanagement.dto.Instructor dto = DtoMapper.mapInstructorEntityToDto(instructor);
            InstructorDAO dao = new InstructorDAO();
            try {
                dao.insertInstructor(dto);           }
            catch (Exception ex) {

            }
        }
    }

    private void saveDegreePrograms() {

        // insert default program
        edu.gatech.coursemanagement.dto.DegreeProgram dto = new edu.gatech.coursemanagement.dto.DegreeProgram();
        dto.setprogramID(0);
        dto.setname("Default");
        DegreeProgramDAO dao = new DegreeProgramDAO();
        try {
            dao.insertDegreeProgram(dto);           }
        catch (Exception ex) {
            CourseManagementUtil.logError(getClass(), ex.getMessage());
        }

        for (DegreeProgram program : dataModel.getProgramsList()) {
            dto = DtoMapper.mapDegreeEntityToDto(program);
            try {
                dao.insertDegreeProgram(dto);           }
            catch (Exception ex) {
                CourseManagementUtil.logError(getClass(), ex.getMessage());
            }
        }
    }

    private void saveDegreeProgramCourses() {
        int id = 1;
        for (DegreeProgram program : dataModel.getProgramsList()) {
            ArrayList<Course> requiredCourses = program.getRequiredCourses();
            if (requiredCourses != null && requiredCourses.size() > 0) {
                for (Course course : requiredCourses) {
                    edu.gatech.coursemanagement.dto.DegreeProgramCourse dto = new edu.gatech.coursemanagement.dto.DegreeProgramCourse();
                    dto.setID(id);
                    dto.setprogramID(program.getDegreeId());
                    dto.setcourseID(course.getCourseId());
                    DegreeProgramDAO dao = new DegreeProgramDAO();
                    try {
                        dao.insertDegreeProgramCourse(dto);
                        id++;
                    }
                    catch (Exception ex) {
                        CourseManagementUtil.logError(getClass(), ex.getMessage());
                    }
                }
            }
        }
    }

    private void saveCourses() {
        for (Course course : dataModel.getCourseCatalog().searchCatalog("")) {
            edu.gatech.coursemanagement.dto.Course dto = DtoMapper.mapCourseEntityToDto(course);
            CourseDAO dao = new CourseDAO();
            try {
                dao.insertCourse(dto);
            }
            catch (Exception ex) {
                CourseManagementUtil.logError(this.getClass(),ex.getMessage());
            }
        }
    }

    private void saveCoursePrerequisites() {

        int id = 1;
        for (Course course : dataModel.getCourseCatalog().searchCatalog("")) {
            ArrayList<Course> prerequisites = course.getCoursePrerequisites();
            if (prerequisites != null && prerequisites.size() > 0) {
                for (Course prereqCourse : prerequisites) {
                    edu.gatech.coursemanagement.dto.CoursePrerequisite dto = new CoursePrerequisite();
                    dto.setID(id);
                    dto.setcourseID(course.getCourseId());
                    dto.setprerequisiteID(prereqCourse.getCourseId());
                    id++;
                    CourseDAO dao = new CourseDAO();
                    try {
                        dao.insertCoursePrerequisite(dto);
                    }
                    catch (Exception ex) {
                        CourseManagementUtil.logError(getClass(), ex.getMessage());
                    }
                }
            }
        }
    }

    private void saveCourseCatalog() {
        edu.gatech.coursemanagement.entity.CourseCatalog courseCatalog = dataModel.getCourseCatalog();
        edu.gatech.coursemanagement.dto.CourseCatalog catalogDto = DtoMapper.mapCourseCatalogEntityToDto(courseCatalog);

        CourseDAO dao = new CourseDAO();
        try {
            long id = dao.getCourseCatalogNextId();
            catalogDto.setid(id);

            dao.insertCourseCatalog(catalogDto);
        }
        catch (Exception ex) {
            CourseManagementUtil.logError(getClass(), ex.getMessage());
        }

        long id = 1;
        try {
            id = dao.getCourseCatalogCourseListNextId();
        }
        catch (Exception ex) {
            CourseManagementUtil.logError(getClass(), ex.getMessage());
        }

        for (Course course : courseCatalog.searchCatalog("")) {
            edu.gatech.coursemanagement.dto.CourseCatalogCourseList dto = new CourseCatalogCourseList();
            dto.setid(id);
            dto.setcoursecatalogid(catalogDto.getid());
            dto.setcourseid(course.getCourseId());
            try {
                dao.insertCourseCatalogCourseList(dto);
                id++;
            }
            catch (Exception ex) {
                CourseManagementUtil.logError(getClass(), ex.getMessage());
            }
        }
    }
}
