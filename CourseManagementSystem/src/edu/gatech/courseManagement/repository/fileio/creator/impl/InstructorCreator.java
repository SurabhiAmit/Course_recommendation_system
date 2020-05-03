package edu.gatech.courseManagement.repository.fileio.creator.impl;

import edu.gatech.courseManagement.entity.Instructor;
import edu.gatech.courseManagement.repository.EntityCreationStrategy;

public class InstructorCreator implements EntityCreationStrategy {
    @Override
    public Object create(final Object[] o) {
        Instructor i = new Instructor(){{
            setUUIID(Long.parseLong(o[0].toString()));
            setName(o[1].toString());
            setOfficeHour(o[2].toString());
            setEmailAddress(o[3].toString());
            setCourseTaught(Long.parseLong(o[4].toString()));
        }};
        return i;
    }
}
