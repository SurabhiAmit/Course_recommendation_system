package edu.gatech.coursemanagement.controller;

import edu.gatech.coursemanagement.data.ApplicationState;
import edu.gatech.coursemanagement.utils.ApplicationLoader;
import edu.gatech.coursemanagement.utils.Trace;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

public class HomeController extends ApplicationController {
    @FXML private RadioButton rdoInitialMode;
    @FXML private RadioButton rdoResumeMode;
    @FXML private Label lblCurrentTerm;
    @FXML private Button btnStartSimulation;

    @Override
    public void initialize() {

        super.initialize();

        ApplicationState applicationState = ApplicationLoader.getInstance().getApplicationState();

        lblCurrentTerm.setText(Integer.toString(applicationState.getCurrentTerm()));
        rdoInitialMode.setSelected(true);
        btnStartSimulation.setOnAction(this::handleStartSimulation);
    }

    public void handleStartSimulation(ActionEvent event) {
        Trace.TraceMessage("Inside onStartSimulationClicked of home controller!");

        if (rdoInitialMode.isSelected()) {
            // load initial dataset from files
        }

        if (rdoResumeMode.isSelected()) {
            // load data from database to resume
        }

        parentController.enableTabs();

    }
}
