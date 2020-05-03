package edu.gatech.courseManagement.repository.fileio;

import edu.gatech.courseManagement.repository.EntityCreationStrategy;
import edu.gatech.courseManagement.util.CourseManagementUtil;

class EntityCreationFactory
{
    private final static String FILENAME_PROC = "coursemanagement.repository.store.%s.creator";
    public static EntityCreationStrategy create(String id)
    {
        String creatorClass = CourseManagementUtil.getProperty( String.format( FILENAME_PROC, id ) );
        return (EntityCreationStrategy)CourseManagementUtil.createRuntimeObject( creatorClass );
    }
}
