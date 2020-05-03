package edu.gatech.coursemanagement.controller;

import edu.gatech.coursemanagement.utils.ValidationResult;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ValidationMessagesController {
    @FXML private TextArea txtValidationMessages;

    public ValidationResult getData() {
        return data;
    }

    public void setData(ValidationResult data) {
        this.data = data;
    }

    private ValidationResult data;

    public void initialize() {
        txtValidationMessages.setText(data.getMessages());
    }
}
