package edu.gatech.courseManagement.repository.fileio.creator.impl;

import edu.gatech.courseManagement.entity.Listing;
import edu.gatech.courseManagement.repository.EntityCreationStrategy;

public class ListingCreator implements EntityCreationStrategy {
    @Override
    public Object create(final Object[] o) {
        Listing l = new Listing(){{
            setProgramID(Long.parseLong(o[0].toString()));
            setCourseID(Long.parseLong(o[1].toString()));
        }};
        return l;
    }
}
