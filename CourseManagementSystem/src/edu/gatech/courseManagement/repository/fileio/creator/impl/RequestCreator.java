package edu.gatech.courseManagement.repository.fileio.creator.impl;

import edu.gatech.courseManagement.entity.Request;
import edu.gatech.courseManagement.repository.EntityCreationStrategy;

public class RequestCreator implements EntityCreationStrategy {
    @Override
    public Object create(final Object[] o) {
        Request rq = new Request(){{
            setUUSID(Long.parseLong(o[0].toString()));
            setRequestedCourseID(Long.parseLong(o[1].toString()));
        }};
        return rq;
    }
}
