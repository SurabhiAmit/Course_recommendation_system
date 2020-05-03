package edu.gatech.coursemanagement.controller;

import edu.gatech.coursemanagement.utils.ApplicationManager;
import edu.gatech.coursemanagement.utils.Trace;
import edu.gatech.coursemanagement.utils.ValidationResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class HomeController extends ApplicationController {
    @FXML private RadioButton rdoInitialMode;
    @FXML private RadioButton rdoResumeMode;
    @FXML private Label lblCurrentTerm;
    @FXML private Button btnStartSimulation;
    @FXML private Button btnProcessRequests;
    @FXML private Button btnValidateRequests;
    @FXML private Button btnGenerateGrades;
    @FXML private Button btnStartNextTerm;
    @FXML private Hyperlink lnkInstructorsTab;

    @Override
    public void initialize() {

        super.initialize();

        prepareNewTermView();
        prepareEventHandlers();
    }

    private void prepareNewTermView() {
        lblCurrentTerm.setText(ApplicationManager.getInstance().getCurrentTermText());
        rdoInitialMode.setSelected(true);
        btnProcessRequests.setDisable(true);
        btnValidateRequests.setDisable(true);
        btnGenerateGrades.setDisable(true);
        btnStartNextTerm.setDisable(true);
        lnkInstructorsTab.setDisable(true);
    }

    private void prepareNextTermView() {
        lblCurrentTerm.setText(ApplicationManager.getInstance().getCurrentTermText());
        btnProcessRequests.setDisable(false);
        getParentController().getInstructorsTabViewController().getPaneInstructorAssign().setVisible(false);
        //parentController.clearRequests();
    }

    private void prepareEventHandlers() {
        btnStartSimulation.setOnAction(this::handleStartSimulation);
        btnProcessRequests.setOnAction(this::handleProcessRequests);
        btnValidateRequests.setOnAction(this::handleValidateRequests);
        btnGenerateGrades.setOnAction(this::handleGenerateGrades);
        btnStartNextTerm.setOnAction(this::handleStartNextTerm);
        lnkInstructorsTab.setOnAction(this::handleInstructorsTab);
    }

    private void handleInstructorsTab(ActionEvent event) {
        parentController.displayInstructors();
    }

    private void handleStartSimulation(ActionEvent event) {
        Trace.TraceMessage("Inside onStartSimulationClicked of home controller!");

        if (rdoInitialMode.isSelected()) {
            ValidationResult result = ApplicationManager.getInstance().initializeFirstRun();

            if (result.getValidationMessages().size() > 0)
                displayValidationResult(result);

            if (!result.isSuccessful())
                return;
        }

        if (rdoResumeMode.isSelected()) {
            if (ApplicationManager.getInstance().hasSavedState()) {
                ApplicationManager.getInstance().resumeFromSavedState();
                lblCurrentTerm.setText(ApplicationManager.getInstance().getCurrentTermText());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Could not resume from saved state");
                alert.setContentText("Failed to load application data. Consider initial mode.");
                alert.showAndWait();
                return;
            }
        }

        parentController.enableTabs();
        btnStartSimulation.setDisable(true);
        btnProcessRequests.setDisable(false);
        lnkInstructorsTab.setDisable(false);
    }

    private void displayValidationResult(ValidationResult result) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            fxmlLoader.setLocation(classLoader.getResource("view/validationMessagesView.fxml"));

            ValidationMessagesController controller = new ValidationMessagesController();
            controller.setData(result);
            fxmlLoader.setController(controller);

            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Validation Messages");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleProcessRequests(ActionEvent event) {
        ValidationResult result = ApplicationManager.getInstance().loadRequestData();
        if (result.isSuccessful()) {
            parentController.displayRequests();
            btnProcessRequests.setDisable(true);
            btnValidateRequests.setDisable(false);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not process the request file");
            alert.setContentText("Check if the file exists and is properly formatted.");
            alert.showAndWait();
        }
    }

    private void handleValidateRequests(ActionEvent event) {
        ApplicationManager.getInstance().validateRequestData();
        parentController.displayRequests();
        btnValidateRequests.setDisable(true);
        btnGenerateGrades.setDisable(false);
    }

    private void handleGenerateGrades(ActionEvent event) {
        ApplicationManager.getInstance().generateAcademicRecords();
        parentController.displayRecords();
        btnGenerateGrades.setDisable(true);
        btnStartNextTerm.setDisable(false);
    }

    private void handleStartNextTerm(ActionEvent event) {
        ApplicationManager.getInstance().startNewTerm();
        prepareNextTermView();
        btnStartNextTerm.setDisable(true);
    }
}
