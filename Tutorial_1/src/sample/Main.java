package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Scene sceneOne, sceneTwo;
    Button button1, button2, button3, button4, button99;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Hello World");

        Label label1 = new Label("Welcome to the first scene");

        //Button 1
        button1 = new Button();
        button1.setText("Go to scene 2");
        button1.setOnAction(e -> {
            window.setScene(sceneTwo);
        });

        //Button 2
        button2 = new Button();
        button2.setText("Go to scene 1");
        button2.setOnAction(e -> {
            window.setScene(sceneOne);
        });

        //Button 3 - Start the Alert Box
        button3 = new Button();
        button3.setText("Start the alert box");
        button3.setOnAction(e -> {
            AlertBox.display("New Title", "This is goodie messagues");
        });

        //Button 4 - Start the Confirm Box
        button4 = new Button();
        button4.setText("Start the confirm box");
        button4.setOnAction(e -> {
            boolean confirmation = ConfirmBox.display("Please confirm!", "Are you sure you want to continue?");
            System.out.println(confirmation);
        });

        //Button 99 - EXIT
        button99 = new Button();
        button99.setText("Exit");
        button99.setOnAction(e -> {
            boolean confirmation = ConfirmBox.display("Exit", "Are you sure you want to exit?");
            if (confirmation) closeProgram();
        });
        // This is "X" closing button
        window.setOnCloseRequest(e -> {
            // consume will stop java actions and continue only the code!
            e.consume();
            boolean confirmation = ConfirmBox.display("Exit", "Are you sure you want to exit?");
            if (confirmation) closeProgram();
        });

        // Setting the Layout One - Vertical column:
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1, button3, button4, button99);
        sceneOne = new Scene(layout1, 600, 400);

        //Setting the Layout Two
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        sceneTwo = new Scene(layout2, 600, 300);

        //StackPane layout = new StackPane();
        //layout.getChildren().add(button1);
        //Scene scene = new Scene(layout, 300, 250);

        //Setting Scene One to start first
        window.setScene(sceneOne);
        window.show();
    }


    private void closeProgram(){
        System.out.println("File saved!");
        window.close();
    }

}
