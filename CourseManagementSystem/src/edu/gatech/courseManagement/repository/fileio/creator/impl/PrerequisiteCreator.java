package edu.gatech.courseManagement.repository.fileio.creator.impl;

import edu.gatech.courseManagement.entity.Prerequisite;
import edu.gatech.courseManagement.repository.EntityCreationStrategy;

public class PrerequisiteCreator implements EntityCreationStrategy{
    @Override
    public Object create(final Object[] o) {
        Prerequisite p = new Prerequisite(){{
            setCourseID(Long.parseLong(o[0].toString()));
            setPrerequisiteID(Long.parseLong(o[1].toString()));
        }};

        return p;
    }
}
