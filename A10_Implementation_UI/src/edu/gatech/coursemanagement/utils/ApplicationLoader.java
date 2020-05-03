package edu.gatech.coursemanagement.utils;

import edu.gatech.coursemanagement.data.ApplicationState;

public class ApplicationLoader {

    private static ApplicationLoader instance;

    private ApplicationLoader() {

    }

    private ApplicationState applicationState;

    public ApplicationState getApplicationState() {
        return applicationState;
    }

    public static ApplicationLoader getInstance() {
        if (instance == null) {
            instance = new ApplicationLoader();
            instance.setInitialState();
        }
        return instance;
    }

    private void setInitialState() {
        applicationState = new ApplicationState();
        applicationState.setCurrentTerm(0);
    }
}
