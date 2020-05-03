package edu.gatech.courseManagement.repository.fileio.creator.impl;

import edu.gatech.courseManagement.entity.Record;
import edu.gatech.courseManagement.repository.EntityCreationStrategy;

public class RecordCreator implements EntityCreationStrategy {
    @Override
    public Object create(final Object[] o) {
        Record r = new Record(){{
            setUUSID(Long.parseLong(o[0].toString()));
            setCourseID(Long.parseLong(o[1].toString()));
            setLetterGrade(o[2].toString().charAt(0));
            setYear(Integer.parseInt(o[3].toString()));
            setTerm(Integer.parseInt(o[4].toString()));
        }};
        return r;
    }
}
