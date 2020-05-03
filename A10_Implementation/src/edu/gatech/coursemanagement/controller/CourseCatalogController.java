package edu.gatech.coursemanagement.controller;

import edu.gatech.coursemanagement.entity.Course;
import edu.gatech.coursemanagement.utils.ApplicationManager;
import edu.gatech.coursemanagement.utils.Trace;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class CourseCatalogController extends ApplicationController {

    @FXML private TableView<Course> courseCatalogTable;
    @FXML private TableColumn<Course, String> courseIdColumn;
    @FXML private TableColumn<Course, String> courseTitleColumn;
    @FXML private TableColumn<Course, String> courseCostColumn;
    @FXML private TableColumn<Course, String> coursePreReqColumn;

    @Override
    public void initialize() {

        super.initialize();

        courseIdColumn.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        courseTitleColumn.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
        courseCostColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        coursePreReqColumn.setCellValueFactory(new PropertyValueFactory<>("preReq"));
    }

    public void displayCourseCatalog() {

        Trace.TraceMessage("Inside display course catalog in course catalog controller!");
        ArrayList<Course> courses = ApplicationManager.getInstance().getCourseCatalog().searchCatalog("");


        ObservableList<Course> coursesData = FXCollections.observableArrayList(courses);

        courseCatalogTable.setItems(coursesData);
    }
}
