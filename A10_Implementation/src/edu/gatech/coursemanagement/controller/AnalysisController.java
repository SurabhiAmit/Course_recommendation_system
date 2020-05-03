package edu.gatech.coursemanagement.controller;

import edu.gatech.coursemanagement.utils.Trace;
import edu.gatech.coursemanagement.weka.CourseManagementApriori;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AnalysisController extends ApplicationController {
    @FXML
    TextField numOfRules;
    @FXML
    TextArea generatedRules;
    @FXML
    Button generateButton;

    @Override
    public void initialize() {
        super.initialize();


        generateButton.setOnAction(this::handleRuleGeneration);

        numOfRules.setText("10");
        numOfRules.selectAll();
        Trace.TraceMessage("Inside initialize of analysis controller!");
    }

    public void handleRuleGeneration(ActionEvent event) {
        int numOfRulesData = Integer.parseInt( numOfRules.getText() );
        CourseManagementApriori apriori = new CourseManagementApriori();
        try {
            String rules = apriori.runWekaAlgo(numOfRulesData);
            generatedRules.setText(rules);
        }catch(Exception e)
        {
            generatedRules.setText(e.getMessage());
        }
    }
}
