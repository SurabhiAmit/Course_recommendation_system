package edu.gatech.courseManagement.repository.fileio.creator.impl;

import edu.gatech.courseManagement.entity.Degree;
import edu.gatech.courseManagement.repository.EntityCreationStrategy;

public class DegreeCreator implements EntityCreationStrategy {
    @Override
    public Object create(final Object[] o) {
        Degree d = new Degree(){{
            setProgramID(Long.parseLong(o[0].toString()));
            setName(o[1].toString());
        }};
        return d;
    }
}
