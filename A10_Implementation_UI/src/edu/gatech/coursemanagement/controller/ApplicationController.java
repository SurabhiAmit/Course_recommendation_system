package edu.gatech.coursemanagement.controller;

import edu.gatech.coursemanagement.utils.Trace;

public class ApplicationController {
    protected MainController parentController;

    public void setParentController(MainController parentController) {
        this.parentController = parentController;
    }

    public void initialize() {
        Trace.TraceMessage("Inside initialize of " + this.getClass().getName() + " controller!");
    }
}
