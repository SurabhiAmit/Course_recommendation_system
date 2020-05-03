package edu.gatech.coursemanagement.controller;

import edu.gatech.coursemanagement.entity.*;
import edu.gatech.coursemanagement.utils.ApplicationManager;
import edu.gatech.coursemanagement.utils.CourseRequest;
import edu.gatech.coursemanagement.utils.Trace;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class RecordController extends ApplicationController {

    @FXML private TableView recordsTable;
    @FXML private TableColumn<Student, String> studentColumn;
    @FXML private TableColumn<Course, String> courseColumn;
    @FXML private TableColumn<Term, String> termColumn;
    @FXML private TableColumn<Integer, String> yearColumn;
    @FXML private TableColumn<Grade, String> gradeColumn;

    @Override
    public void initialize() {
        super.initialize();

        studentColumn.setCellValueFactory(new PropertyValueFactory<>("student"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        termColumn.setCellValueFactory(new PropertyValueFactory<>("term"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
    }

    public void displayRecords() {
        Trace.TraceMessage("Inside display records in record controller!");
        ArrayList<AcademicRecord> records = ApplicationManager.getInstance().getRecords();

        ObservableList<AcademicRecord> recordsData = FXCollections.observableArrayList(records);

        recordsTable.getItems().clear();
        recordsTable.setItems(recordsData);
    }
}
