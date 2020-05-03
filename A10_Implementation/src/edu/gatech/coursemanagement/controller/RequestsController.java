package edu.gatech.coursemanagement.controller;

import edu.gatech.coursemanagement.entity.Course;
import edu.gatech.coursemanagement.entity.Student;
import edu.gatech.coursemanagement.utils.ApplicationManager;
import edu.gatech.coursemanagement.utils.CourseRequest;
import edu.gatech.coursemanagement.utils.Trace;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class RequestsController extends ApplicationController {

    @FXML private TableView requestsTable;
    @FXML private TableColumn<Student, String> studentColumn;
    @FXML private TableColumn<Course, String> courseColumn;
    @FXML private TableColumn<String, String> messageColumn;

    @Override
    public void initialize() {
        super.initialize();

        studentColumn.setCellValueFactory(new PropertyValueFactory<>("student"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("validationMessage"));
    }

    public void displayRequests() {
        Trace.TraceMessage("Inside display requests in requests controller!");
        ArrayList<CourseRequest> courseRequests = ApplicationManager.getInstance().getRequests();

        ObservableList<CourseRequest> requestsData = FXCollections.observableArrayList(courseRequests);

        requestsTable.getItems().clear();
        requestsTable.setItems(requestsData);
    }
}
