package edu.gatech.coursemanagement.controller;

import edu.gatech.coursemanagement.utils.Trace;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

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
    @FXML
    private Tab requestsTab;
    @FXML
    private Tab degreeProgramsTab;
    @FXML
    private TabPane tabPane;

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
    @FXML
    private RequestsController requestsTabViewController;
    @FXML
    private DegreeProgramController degreeProgramTabViewController;

    public InstructorController getInstructorsTabViewController() {
        return instructorsTabViewController;
    }

    public void initialize() {
        Trace.TraceMessage("Inside initialize of main controller!");

        homeTabViewController.setParentController(this);

        tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
            if (newTab.getId().equalsIgnoreCase("courseCatalogTab")) {
                courseCatalogTabViewController.displayCourseCatalog();
            }

            if (newTab.getId().equalsIgnoreCase("instructorsTab")) {
                instructorsTabViewController.displayInstructors();
            }

            if (newTab.getId().equalsIgnoreCase("studentsTab")) {
                studentsTabViewController.displayStudents();
            }

            if (newTab.getId().equalsIgnoreCase("requestsTab")) {
                requestsTabViewController.displayRequests();
            }

            if (newTab.getId().equalsIgnoreCase("recordsTab")) {
                recordsTabViewController.displayRecords();
            }

            if (newTab.getId().equalsIgnoreCase("degreeProgramsTab")) {
                degreeProgramTabViewController.displayPrograms();
            }
        });

        initializeControls();
    }

    private void initializeControls() {
        disableTabsOnStartup();

    }

    private void disableTabsOnStartup() {
        courseCatalogTab.setDisable(true);
        analysisTab.setDisable(true);
        instructorsTab.setDisable(true);
        studentsTab.setDisable(true);
        recordsTab.setDisable(true);
        requestsTab.setDisable(true);
        degreeProgramsTab.setDisable(true);
    }

    public void enableTabs() {
        courseCatalogTab.setDisable(false);
        analysisTab.setDisable(false);
        instructorsTab.setDisable(false);
        studentsTab.setDisable(false);
        recordsTab.setDisable(false);
        requestsTab.setDisable(false);
        degreeProgramsTab.setDisable(false);
    }

    public void displayRequests() {
        requestsTabViewController.displayRequests();
        tabPane.getSelectionModel().select(requestsTab);
    }

    public void displayRecords() {
        recordsTabViewController.displayRecords();
        tabPane.getSelectionModel().select(recordsTab);
    }

    public void displayInstructors() {
        instructorsTabViewController.displayInstructors();
        tabPane.getSelectionModel().select(instructorsTab);
    }
}
