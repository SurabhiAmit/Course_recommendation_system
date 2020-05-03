package edu.gatech.coursemanagement.utils;

import java.util.ArrayList;

public class ValidationResult {
    private boolean isSuccessful;
    private ArrayList<String> validationMessages;

    public ValidationResult() {
        isSuccessful = false;
        validationMessages = new ArrayList<>();
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public ArrayList<String> getValidationMessages() {
        return validationMessages;
    }

    public void addValidationMessage(String message) {
        validationMessages.add(message);
    }

    public String getMessages() {
        StringBuilder sb = new StringBuilder();
        for (String message : validationMessages) {
            sb.append(message);
            sb.append("\r\n");
        }

        return sb.toString();
    }
}
