package edu.gatech.courseManagement;

import edu.gatech.courseManagement.service.CourseManagementUiService;
import edu.gatech.courseManagement.util.CourseManagementUtil;

public class CourseManagementMain {
    private static final String UI_START ="coursemanagement.ui.run";

    public static void main(String args[])
    {
        String clazz = CourseManagementUtil.getProperty(UI_START);
        CourseManagementUiService uiService = ( CourseManagementUiService )
                CourseManagementUtil.createRuntimeObject(clazz);
        uiService.run();
    }
}
