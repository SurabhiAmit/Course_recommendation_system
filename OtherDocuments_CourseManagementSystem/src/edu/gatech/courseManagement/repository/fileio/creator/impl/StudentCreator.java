package edu.gatech.courseManagement.repository.fileio.creator.impl;

import edu.gatech.courseManagement.entity.Student;
import edu.gatech.courseManagement.repository.EntityCreationStrategy;

public class StudentCreator implements EntityCreationStrategy {
    @Override
    public Object create(final Object[] o) {
        Student s = new Student(){{
            setUUSID(Long.parseLong(o[0].toString()));
            setName(o[1].toString());
            setHomeAddress(o[2].toString());
            setPhoneNumber(o[3].toString());
            setEnrolledProgramID(Long.parseLong(o[4].toString()));
        }};
        return s;
    }
}
