package com.example.test2mouseactions;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class StageCreator {

    private Pane mainPane;
    private ObjectContextMenu mainObjectsContextMenu;
    double scnX;
    double scnY;
    ArrayList<GridObject> listOfAllGridObjects;

    //TMP Flags:
    boolean isSameWindow;
    int isSameWindowIndex;

    private HelloApplication parentClass;

    public StageCreator(HelloApplication mainClass, String title, Stage mainStage) {
        parentClass = mainClass;
        mainStage.setTitle(title);

        //Array of all Instances of GridObject:
        listOfAllGridObjects = new ArrayList<>();
        isSameWindow = false;

        //Main Object Context Menu:
        mainObjectsContextMenu = new ObjectContextMenu();

        //Scene
        mainPane = new Pane();

        //Add first object:
        GridObject firstObject = new GridObject(this, mainPane, mainObjectsContextMenu);

        //Create Window
        Scene mainScene = new Scene(mainPane, 1000, 800);
        mainScene.getStylesheets().add(getClass().getResource("/defaultStyle.css").toExternalForm());

        //Right Click Menu:
        final ContextMenu mainContextMenu = createDefaultContextMenu();
        mainPane.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getButton() == MouseButton.PRIMARY || e.getButton() == MouseButton.SECONDARY) {
                scnX = e.getSceneX();
                scnY = e.getSceneY();
            }
            if (e.getButton() == MouseButton.PRIMARY) {
                mainPane.requestFocus();
            }
            if (e.getButton() == MouseButton.SECONDARY && !mainObjectsContextMenu.isShowing()) {
                System.out.println(mainObjectsContextMenu.isShowing() + "");
                mainContextMenu.setY(e.getScreenY());
                mainContextMenu.setX(e.getScreenX());
                mainContextMenu.show(mainPane.getScene().getWindow());
                mainPane.requestFocus();
            }
        });
        /*mainPane.setOnMouseMoved(e -> {
            scnX = e.getSceneX();
            scnY = e.getSceneY();
        });

         */

        // Event Listener:
        // This will be called once
        mainPane.setOnDragEntered(e -> {
            System.out.println("onDragEntered");
            // show to the user that it is an actual gesture target
            isSameWindow = false;

            System.out.println(listOfAllGridObjects);
            for (int i = 0; i < listOfAllGridObjects.size(); i++) {
                // Look for the Dragging object and check if this is its own window Pane
                if (listOfAllGridObjects.get(i).getObjectsVBox() == e.getGestureSource()) {
                    if (listOfAllGridObjects.get(i).getParentPane() == mainPane) {
                        System.out.println("THIS IS THE SAME WINDOW");
                        isSameWindow = true;
                        isSameWindowIndex = i;
                    }
                }
            }
            //If other application
            if (e.getGestureSource() != mainPane && e.getDragboard().hasString() && !isSameWindow) {
                mainPane.setStyle("-fx-background-color: #0dbd00; -fx-border-color: #b0feaa;");
            }
        });
        mainPane.setOnDragExited(event -> {
            mainPane.setStyle("");
        });
        mainPane.setOnDragOver(event -> {
            if (event.getDragboard().hasString() && !isSameWindow) {
                //System.out.println("onDragOver");
                //allow for both copying and moving, whatever user chooses
                event.acceptTransferModes(TransferMode.COPY);
            } else if (isSameWindow) {
                event.acceptTransferModes(TransferMode.MOVE);
                listOfAllGridObjects.get(isSameWindowIndex).getObjectsVBox().relocate(event.getSceneX() - listOfAllGridObjects.get(isSameWindowIndex).getObjX(), event.getSceneY() - listOfAllGridObjects.get(isSameWindowIndex).getObjY());
            }
        });
        mainPane.setOnDragDropped(e -> {
            System.out.println("onDragDropped");
            // if there is a string data on dragboard, read it and use it
            Dragboard db = e.getDragboard();
            boolean success = false;
            if (e.getTransferMode() == TransferMode.COPY) {
                try {
                    if (db.hasString()) {
                        scnX = e.getSceneX();
                        scnY = e.getSceneY();
                        pasteNewObject(db.getString());
                        success = true;
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            } else if (e.getTransferMode() == TransferMode.MOVE) {
                success = true;
            }
            // let the source know whether the string was successfully transfered
            //How do I use this ?
            e.setDropCompleted(success);
            //event.consume();
        });

        //Remove Focus from first Object:
        mainPane.requestFocus();

        mainStage.setScene(mainScene);
        mainStage.show();
    }

    public double getScnX() {
        return scnX;
    }
    public double getScnY() {
        return scnY;
    }

    public void addToTheListOfAllGridObjects (GridObject thisObject) {
        listOfAllGridObjects.add(thisObject);
    }

    private ContextMenu createDefaultContextMenu() {
        // create a menu
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.setAutoHide(true);
        // create menu items
        MenuItem menuItem1 = new MenuItem("Add Object");
        MenuItem menuItem2 = new MenuItem("Paste Object");
        //Actions:
        menuItem1.setOnAction((event) -> {
            System.out.println("Adding Object");
            //Add first object:
            GridObject newObject = new GridObject(this, mainPane, mainObjectsContextMenu);
        });
        menuItem2.setOnAction((event) -> {
            System.out.println("Paste Object:");
            Clipboard clipboard = Clipboard.getSystemClipboard();
            if (clipboard.hasString()) {
                String text = clipboard.getString();
                pasteNewObject(text);
            }
        });
        //Add them to the Context Box
        contextMenu.getItems().addAll(menuItem1, menuItem2);

        return contextMenu;
    }

    private void pasteNewObject(String text) {
        System.out.println("Text: " + text);

        ArrayList<ArrayList<String>> newObject = new ArrayList<>();
        ArrayList<String> allArrays = new ArrayList<>(Arrays.asList(text.split(",,")));
        for (int i=0; i < allArrays.size(); i++) { //Each Array in a Loop
            int j = 0;
            String tmpArray = allArrays.get(i);
            //System.out.println("tmpArray: " + tmpArray);
            String thisArray = tmpArray.substring(1, tmpArray.length() - 1); //get rid of [...]
            StringTokenizer eachArray = new StringTokenizer(thisArray, ",");
            ArrayList<String> newArray = new ArrayList<>();
            //System.out.println("thisArray: " + thisArray);
            while (eachArray.hasMoreTokens()) { //Each String of an Array in a Loop
                String arrChild = eachArray.nextToken();
                //System.out.println("adding this att: " + arrChild);
                newArray.add(j, arrChild.trim());
                j++;
            }
            newObject.add(i, newArray);
        }
        if (!newObject.isEmpty()) {
            GridObject newCreatedObject = new GridObject(this, mainPane, mainObjectsContextMenu);
            newCreatedObject.setGridFromThisArray(newObject);
            newCreatedObject.getObjectsVBox().relocate(scnX, scnY);
        }
    }
}
