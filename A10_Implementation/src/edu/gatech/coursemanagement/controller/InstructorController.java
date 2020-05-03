package edu.gatech.coursemanagement.controller;

import edu.gatech.coursemanagement.entity.Administrator;
import edu.gatech.coursemanagement.entity.Course;
import edu.gatech.coursemanagement.entity.Instructor;
import edu.gatech.coursemanagement.exception.CourseManagementRuntimeException;
import edu.gatech.coursemanagement.utils.ApplicationManager;
import edu.gatech.coursemanagement.utils.Trace;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collections;

public class InstructorController extends ApplicationController {
    @FXML private TableView<Instructor> instructorsTable;
    @FXML private TableColumn<Instructor, String> idColumn;
    @FXML private TableColumn<Instructor, String> nameColumn;
    @FXML private TableColumn<Instructor, String> emailColumn;
    @FXML private TableColumn<Instructor, String> officeHoursColumn;
    @FXML private TableColumn<Instructor, String> assignedCourseColumn;
    @FXML private Label lblTeachesCourse;
    @FXML private Label lblInstructorName;
    @FXML private Label lblInstructions;
    @FXML private AnchorPane paneInstructorDetails;
    @FXML private ComboBox cboAssignToCourse;
    @FXML private Button btnAssignCourse;
    @FXML private Pane paneInstructorAssign;

    private Instructor currentInstructor;

    public Pane getPaneInstructorAssign() {
        return paneInstructorAssign;
    }

    @Override
    public void initialize() {

        super.initialize();

        //paneInstructorDetails.setVisible(false);
        paneInstructorAssign.setVisible(false);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("uuiid"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("instructorName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        officeHoursColumn.setCellValueFactory(new PropertyValueFactory<>("officeHours"));
        assignedCourseColumn.setCellValueFactory(new PropertyValueFactory<>("assignedCourseText"));

        instructorsTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    Instructor ins = instructorsTable.getSelectionModel().getSelectedItem();
                    if (ins != null) {
                        currentInstructor = ins;
                        lblInstructorName.setText(ins.getInstructorName());
                        lblInstructions.setVisible(false);
                        paneInstructorAssign.setVisible(true);
                        //paneInstructorDetails.setVisible(true);
                        long courseIdSelect = -1;
                        if (ins.isCurrentlyInstructing()) {
                            lblTeachesCourse.setText(ins.getCurrentCourseTaught().getCourseTitle());
                            courseIdSelect = ins.getCurrentCourseTaught().getCourseId();
                        } else {
                            lblTeachesCourse.setText("None");
                        }

                        ArrayList<Course> courses = ApplicationManager.getInstance().getCourseCatalog().searchCatalog("");
                        ObservableList<Course> coursesData = FXCollections.observableArrayList(courses);


                        int selectedIndex = -1;
                        if( courseIdSelect != -1 )
                        {
                            for(Course itrCourse: courses)
                            {
                                selectedIndex++;
                                if( itrCourse.getCourseId() == courseIdSelect)
                                {
                                    break;
                                }
                            }
                        }
                        cboAssignToCourse.setItems(coursesData);
                        cboAssignToCourse.getSelectionModel().select(selectedIndex);
                    }
                });

        btnAssignCourse.setOnAction(this::handleAssignCourse);
    }

    private void handleAssignCourse(ActionEvent event) {

        if (currentInstructor == null)
            return;

        if (!ApplicationManager.getInstance().canReassignInstructor()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Instructor assignment is not allowed");
            alert.setContentText("Maximum number of optional reassignments reached.");
            alert.showAndWait();
            return;
        }

        Course selectedCourse = (Course)cboAssignToCourse.getSelectionModel().getSelectedItem();

        Administrator administrator = new Administrator();

        try {
            if (selectedCourse.getCourseId() == 0) {// none course, remove assignment
                administrator.removeInstructorAssignment(currentInstructor);
            } else {
                administrator.removeInstructorAssignment(currentInstructor);
                administrator.assignInstructor(
                        currentInstructor,
                        selectedCourse,
                        ApplicationManager.getInstance().getTerm(),
                        ApplicationManager.getInstance().getYear());
            }
            ApplicationManager.getInstance().trackInstructorAssignments();
            displayInstructors();
        }
        catch (CourseManagementRuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot assign instructor to course");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }

        lblTeachesCourse.setText(selectedCourse.getCourseTitle());
    }

    public void displayInstructors() {
        ArrayList<Instructor> instructors = Collections.list(Collections.enumeration(ApplicationManager.getInstance().getInstructors().values()));
        ObservableList<Instructor> instructorsData = FXCollections.observableArrayList(instructors);

        instructorsTable.getItems().clear();
        instructorsTable.setItems(instructorsData);
    }
}
