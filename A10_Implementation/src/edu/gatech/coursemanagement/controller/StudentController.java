package edu.gatech.coursemanagement.controller;

import edu.gatech.coursemanagement.entity.Student;
import edu.gatech.coursemanagement.utils.ApplicationManager;
import edu.gatech.coursemanagement.utils.Trace;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Collections;

public class StudentController extends ApplicationController {
    @FXML
    private TableView<Student> studentsTable;
    @FXML private TableColumn<Student, String> idColumn;
    @FXML private TableColumn<Student, String> nameColumn;
    @FXML private TableColumn<Student, String> phoneColumn;

    @Override
    public void initialize() {

        super.initialize();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("uusid"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }

    public void displayStudents() {
        ArrayList<Student> students = Collections.list(Collections.enumeration(ApplicationManager.getInstance().getStudents().values()));
        ObservableList<Student> studentsData = FXCollections.observableArrayList(students);

        studentsTable.getItems().clear();
        studentsTable.setItems(studentsData);
    }
}
