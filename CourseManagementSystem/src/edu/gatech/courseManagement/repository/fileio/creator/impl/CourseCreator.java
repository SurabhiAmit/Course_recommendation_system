package edu.gatech.courseManagement.repository.fileio.creator.impl;

import edu.gatech.courseManagement.entity.Course;
import edu.gatech.courseManagement.repository.EntityCreationStrategy;

public class CourseCreator implements EntityCreationStrategy {
    @Override
    public Object create(final Object[] o) {
        Course c = new Course(){{
            setCourseID(Long.parseLong(o[0].toString()));
            setCourseTitle(o[1].toString());
            setCost(Float.parseFloat(o[2].toString()));
        }};
        return c;
    }
}
