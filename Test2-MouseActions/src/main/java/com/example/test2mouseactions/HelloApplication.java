package com.example.test2mouseactions;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        StageCreator firstStage = new StageCreator(this, "DragAndDrop", primaryStage);

        Stage copyOfStage = new Stage();
        StageCreator secondStage = new StageCreator(this, "DropHere", copyOfStage);
    }

}