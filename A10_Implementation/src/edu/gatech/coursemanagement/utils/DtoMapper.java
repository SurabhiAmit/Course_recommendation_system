package edu.gatech.coursemanagement.utils;

import edu.gatech.coursemanagement.dto.AcademicRecord;
import edu.gatech.coursemanagement.dto.ApplicationState;
import edu.gatech.coursemanagement.dto.Course;
import edu.gatech.coursemanagement.dto.CourseCatalog;
import edu.gatech.coursemanagement.dto.CourseOffering;
import edu.gatech.coursemanagement.dto.DegreeProgram;
import edu.gatech.coursemanagement.dto.Instructor;
import edu.gatech.coursemanagement.dto.Student;
import edu.gatech.coursemanagement.entity.Term;

public class DtoMapper {
    public static Student mapStudentEntityToDto(edu.gatech.coursemanagement.entity.Student entity) {
        Student dto = new Student();
        dto.setUUSID(entity.getUusid());
        dto.setname(entity.getStudentName());
        dto.setaddress(entity.getHomeAddress());
        dto.setphoneNumber(entity.getPhoneNumber());
        if (entity.getCurrentDegreeProgram() != null) {
            dto.setprogramID(entity.getCurrentDegreeProgram().getDegreeId());
        } else {
          dto.setprogramID(0);
        }
        return dto;
    }

    public static edu.gatech.coursemanagement.entity.Student mapStudentDtoToEntity(Student dto) {
        edu.gatech.coursemanagement.entity.Student entity =
                new edu.gatech.coursemanagement.entity.Student(
                        dto.getUUSID(),
                        dto.getname(),
                        dto.getaddress(),
                        dto.getphoneNumber(),
                        null
                );

        return entity;
    }

    public static Instructor mapInstructorEntityToDto(edu.gatech.coursemanagement.entity.Instructor entity) {
        Instructor dto = new Instructor();
        dto.setUUIID(entity.getUuiid());
        dto.setname(entity.getInstructorName());
        dto.setemail(entity.getEmailAddress());

        return dto;
    }

    public static edu.gatech.coursemanagement.entity.Instructor mapInstructorDtoToEntity(Instructor dto) {
        edu.gatech.coursemanagement.entity.Instructor entity = new edu.gatech.coursemanagement.entity.Instructor();
        entity.setUuiid(dto.getUUIID());
        entity.setInstructorName(dto.getname());
        entity.setEmailAddress(dto.getemail());
        return entity;
    }
    
     public static Course mapCourseEntityToDto(edu.gatech.coursemanagement.entity.Course entity) {
        Course dto = new Course();
        dto.setcourseID(entity.getCourseId());
        dto.setname(entity.getCourseTitle());
        dto.setcost(entity.getCost());
        dto.setdescription(entity.getCourseDescription());

        return dto;
    }

    public static edu.gatech.coursemanagement.entity.Course mapCourseDtoToEntity(Course dto) {
        edu.gatech.coursemanagement.entity.Course entity = new edu.gatech.coursemanagement.entity.Course();
        entity.setCourseId(dto.getcourseID());
        entity.setCourseTitle(dto.getname());
        entity.setCost(dto.getcost());
        entity.setCourseDescription(dto.getdescription());
        return entity;
    }
    
    public static CourseCatalog mapCourseCatalogEntityToDto(edu.gatech.coursemanagement.entity.CourseCatalog entity) {
        CourseCatalog dto = new CourseCatalog();
        dto.setid(entity.getId());
        Term t = entity.getCurrentTerm();
        dto.setterm(t.getValue());
        dto.setyear(entity.getYear());
        return dto;
    }
    
    public static DegreeProgram mapDegreeEntityToDto(edu.gatech.coursemanagement.entity.DegreeProgram entity) {
    	DegreeProgram dto = new DegreeProgram();        
        dto.setprogramID(entity.getDegreeId());
        dto.setname(entity.getName());
        return dto;
    }

    public static edu.gatech.coursemanagement.entity.DegreeProgram mapDegreeDtoToEntity(DegreeProgram dto) {
        edu.gatech.coursemanagement.entity.DegreeProgram entity =
                new edu.gatech.coursemanagement.entity.DegreeProgram(
                        dto.getprogramID(),
                        dto.getname()
                );
        return entity;
    }
    
    public static CourseOffering mapCourseOfferingEntityToDto(edu.gatech.coursemanagement.entity.CourseOffering entity) {
    	CourseOffering dto = new CourseOffering();
        dto.setterm(entity.getTerm());
        dto.setyear(entity.getYear());
        dto.setUUIID(entity.getUUIID());
        dto.setcourseID(entity.getcourseid());
        dto.settotalstudents(entity.getTotalStudents());
      //dto.setid(entity.getid()); // entity does not have getid method        
        return dto;
    }
    
    public static AcademicRecord mapAcademicRecordEntityToDto(edu.gatech.coursemanagement.entity.AcademicRecord entity) {
    	AcademicRecord dto = new AcademicRecord();
        dto.setterm(entity.getTerm().getValue());
        dto.setyear(entity.getYear());
        dto.setUUSID(entity.getStudent().getUusid());
        dto.setcourseID(entity.getCourse().getCourseId());
        dto.setgrade(entity.getGrade().name());       
        return dto;
    }

    public static ApplicationState mapApplicationStateEntityToDto(edu.gatech.coursemanagement.entity.ApplicationState entity) {
    	ApplicationState dto = new ApplicationState();
        dto.setcurrentmode(entity.getCurrentMode().getValue());
        //dto.setcurrentlyProcessingTerm(entity.getCurrentlyProcessingTerm()); This should be int in entity
        dto.setcurrentlyProcessingFile(entity.getCurrentlyProcessingFile());
        //dto.setsimulationstep(entity.getSimulationStep()); // this should also be int corresponding to the step number specified in the assignment text
        //dto.setid(entity.getid()); // getid missing in entity
        dto.setfilesProcessed(entity.getfilesProcessed());
        dto.setfilestoprocess(entity.getfilesToProcess());
        return dto;
    }
}
