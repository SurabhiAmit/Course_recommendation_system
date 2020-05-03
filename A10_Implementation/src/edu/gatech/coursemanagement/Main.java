package edu.gatech.coursemanagement;

import edu.gatech.coursemanagement.utils.Trace;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Parent root = FXMLLoader.load(classLoader.getResource("view/main.fxml"));
        primaryStage.setTitle("Course Management System (Team 45)");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Trace.setEnabled(true);
        launch(args);
    }
}
