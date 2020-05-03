package edu.gatech.coursemanagement.controller;

import edu.gatech.coursemanagement.entity.DegreeProgram;
import edu.gatech.coursemanagement.entity.Student;
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
import java.util.Collections;

public class DegreeProgramController extends ApplicationController {

    @FXML private TableView programsTable;
    @FXML private TableColumn<DegreeProgram, String> programIdColumn;
    @FXML private TableColumn<DegreeProgram, String> programNameColumn;

    @Override
    public void initialize() {
        super.initialize();

        programIdColumn.setCellValueFactory(new PropertyValueFactory<>("degreeId"));
        programNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    public void displayPrograms() {
        Trace.TraceMessage("Inside display programs in degree programs controller!");
        ArrayList<DegreeProgram> programs = Collections.list(Collections.enumeration(ApplicationManager.getInstance().getPrograms().values()));
        ObservableList<DegreeProgram> requestsData = FXCollections.observableArrayList(programs);

        programsTable.getItems().clear();
        programsTable.setItems(requestsData);
    }
}
