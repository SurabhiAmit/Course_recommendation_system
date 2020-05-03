package edu.gatech.coursemanagement.controller;

import edu.gatech.coursemanagement.utils.ApplicationLoader;
import edu.gatech.coursemanagement.utils.Trace;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

public class MainController {

    // Inject tab
    @FXML
    private Tab homeTab;
    @FXML
    private Tab courseCatalogTab;
    @FXML
    private Tab analysisTab;
    @FXML
    private Tab instructorsTab;
    @FXML
    private Tab studentsTab;
    @FXML
    private Tab recordsTab;

    // Inject controller
    @FXML
    private HomeController homeTabViewController;
    @FXML
    private CourseCatalogController courseCatalogTabViewController;
    @FXML
    private AnalysisController analysisTabViewController;
    @FXML
    private InstructorController instructorsTabViewController;
    @FXML
    private StudentController studentsTabViewController;
    @FXML
    private RecordController recordsTabViewController;

    public void initialize() {
        Trace.TraceMessage("Inside initialize of main controller!");

        homeTabViewController.setParentController(this);

        disableTabsOnStartup();
    }

    private void disableTabsOnStartup() {
        courseCatalogTab.setDisable(true);
        analysisTab.setDisable(true);
        instructorsTab.setDisable(true);
        studentsTab.setDisable(true);
        recordsTab.setDisable(true);
    }

    public void enableTabs() {
        courseCatalogTab.setDisable(false);
        analysisTab.setDisable(false);
        instructorsTab.setDisable(false);
        studentsTab.setDisable(false);
        recordsTab.setDisable(false);
    }
}
