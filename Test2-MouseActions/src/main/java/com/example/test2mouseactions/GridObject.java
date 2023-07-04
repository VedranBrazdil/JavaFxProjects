package com.example.test2mouseactions;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.robot.Robot;

import java.util.ArrayList;

public class GridObject {

    private int g3TR, g3MR, g3BR;
    private GridPane gridPane3Top, gridPane3Mid, gridPane3Bot;
    public VBox objectHitBox;

    private Pane parentPane;
    private StageCreator mainClass;

    //Copy actions:
    ArrayList<String> topDataL;
    ArrayList<String> topDataR;
    ArrayList<String> midDataL;
    ArrayList<String> midDataR;
    ArrayList<String> botDataL;
    ArrayList<String> botDataR;

    //Efects
    Effect shadow;
    Robot robot;

    //Events:
    double objX;
    double objY;
    double scnX;
    double scnY;
    double strpX;
    double strpY;

    //ObjectContextMenuPointer
    ObjectContextMenu objectContextMenu;

    public GridObject(StageCreator thisClass, Pane mainPane, ObjectContextMenu thisObjectsContextMenu) {
        System.out.println("New Object Created to the " + mainPane);
        parentPane = mainPane;
        mainClass = thisClass;
        objectContextMenu = thisObjectsContextMenu;

        //ArrayData:
        topDataL = new ArrayList<String>();
        topDataR = new ArrayList<String>();
        midDataL = new ArrayList<String>();
        midDataR = new ArrayList<String>();
        botDataL = new ArrayList<String>();
        botDataR = new ArrayList<String>();

        shadow = new DropShadow();
        robot = new Robot();

        // Grid
        gridPane3Top = createNewGridObject(false);
        gridPane3Top.setId("gridEntryTop");
        gridPane3Mid = createNewGridObject(false);
        gridPane3Mid.setId("gridEntryMid");
        gridPane3Bot = createNewGridObject(false);
        gridPane3Bot.setId("gridEntryBot");

        //TOP section:
        g3TR = -1;
        setupTopGrid("Name", "Type");
        //BOT and MID section
        g3MR = -1;
        g3BR = -1;
        addToTheTable("Attribute 1", "String", true);
        addToTheTable("Attribute 2", "String", false);

        //Button to add Entry:
        Button addButton = addAddButton(gridPane3Bot, gridPane3Mid);

        //End label
        Label label3_main = setupTextUnderGridObject("Entry");

        //Add all to the VBox
        objectHitBox = createNewVBoxObject();
        objectHitBox.getChildren().addAll(gridPane3Top, gridPane3Mid, gridPane3Bot, addButton, label3_main);

        //Right Click menu
        //final ContextMenu objectContextMenu = createBasicContextMenu();

        objectHitBox.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                objectContextMenu.setAddAction(this);
                objectContextMenu.setDeleteAction(this);
                objectContextMenu.setDuplicateAction(this);
                objectContextMenu.setAddUniqAction(this);
                objectContextMenu.setCopyAction(this);
                ContextMenu tmp = objectContextMenu.getThisContextMenu();
                tmp.setY(e.getScreenY());
                tmp.setX(e.getScreenX());
                tmp.show(objectHitBox.getScene().getWindow());
            }
        });

        //Event Handlers:
        createEventHandlers();

        mainClass.addToTheListOfAllGridObjects(this);
        parentPane.getChildren().add(objectHitBox);
    }

    public void setGridFromThisArray(ArrayList<ArrayList<String>> inputArray) {
        setTopGrid(inputArray.get(0), inputArray.get(1));
        setMidGrid(inputArray.get(2), inputArray.get(3));
        setBotGrid(inputArray.get(4), inputArray.get(5));

        getScnXYFromParent();
        getObjectsVBox().relocate(scnX, scnY);
    }

    public VBox getObjectsVBox(){
        return objectHitBox;
    }
    public void getScnXYFromParent () {
        scnX = mainClass.getScnX();
        scnY = mainClass.getScnY();
    }
    public Pane getParentPane() {
        return parentPane;
    }
    public double getObjX() {
        return objX;
    }
    public double getObjY() {
        return objY;
    }


    private GridPane createNewGridObject(boolean padd) {
        GridPane temp = new GridPane();
        if (padd) temp.setPadding(new Insets(10, 10, 10, 10));
        temp.setAlignment(Pos.CENTER);
        temp.setMaxWidth(300);
        //temp.setGridLinesVisible(true);

        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(50);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(35);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(15);
        column2.setHalignment(HPos.CENTER);

        temp.getColumnConstraints().addAll(column0, column1, column2);

        return temp;
    }
    private VBox createNewVBoxObject() {
        VBox temp = new VBox();
        temp.setPadding(new Insets(10, 10, 10, 10));
        temp.setId("regularObject");
        temp.setAlignment(Pos.CENTER);
        temp.setMaxWidth(350);
        AnchorPane.setLeftAnchor(temp, 0.0);
        AnchorPane.setRightAnchor(temp, 0.0);

        return temp;
    }

    private void setupTopGrid(String leftString, String rightString) {
        g3TR++;

        if (leftString.isEmpty()) leftString = "Attribute";
        if (rightString.isEmpty()) rightString = "Type";

        Label g3_label = new Label(leftString);
        GridPane.setConstraints(g3_label, 0, g3TR);
        Label g3_label_type = new Label(rightString);
        GridPane.setConstraints(g3_label_type, 1, g3TR);
        GridPane.setMargin(g3_label, new Insets(5, 10, 5, 10));
        GridPane.setMargin(g3_label_type, new Insets(5, 10, 5, 10));

        topDataL.add(leftString);
        topDataR.add(rightString);

        gridPane3Top.getChildren().addAll(g3_label, g3_label_type);
    }

    private Label setupTextUnderGridObject(String labelText) {
        //End label
        Label label3_main = new Label(labelText);
        label3_main.setAlignment(Pos.CENTER);
        label3_main.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label3_main, 0.0);
        AnchorPane.setRightAnchor(label3_main, 0.0);
        label3_main.setPadding(new Insets(5, 0,5, 0)); //(top/right/bottom/left)

        return label3_main;
    }

    private Button addAddButton(GridPane bottomGrid, GridPane middleGrid) {
        Button btn_AddNewEntry = new Button("+");
        btn_AddNewEntry.setOnAction(e -> {
            String[] attTmp = {"Attribute", "String", "false"};
            addToTheTable(attTmp[0], attTmp[1], Boolean.parseBoolean(attTmp[2]));
        });
        Effect shadow = new DropShadow();
        btn_AddNewEntry.setOnMouseEntered(e -> btn_AddNewEntry.setEffect(shadow));
        btn_AddNewEntry.setOnMouseExited(e -> btn_AddNewEntry.setEffect(null));

        return btn_AddNewEntry;
    }

    public void addToTheTable(String attName, String attType, boolean attUniq) {
        addToTheBotTable(attName, attType);
        if (attUniq) {
            addToTheMidTable(attName, "Unique Key");
        }
    }

    public void addToTheMidTable(String attName, String typeName) {
        // at the moment there is only one type: Unique Key
        // therefore typeName is not used
        g3MR++;
        System.out.println("Adding MID Row: " + g3MR);

        TextField tmp_att_u = new TextField(attName);
        gridPane3Mid.add(tmp_att_u, 0, g3MR); // C + R
        Label tmp_type_u = new Label("Unique Key");
        gridPane3Mid.add(tmp_type_u, 1, g3MR);

        midDataL.add(attName);
        midDataR.add("Unique Key");

        //Attribute name change:
        tmp_att_u.textProperty().addListener((observable, oldValue, newValue) -> {
            int row = GridPane.getRowIndex(tmp_att_u);
            System.out.println("textfield changed from " + oldValue + " to " + newValue + row);
            midDataL.set(row, newValue);
        });
        tmp_att_u.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                tmp_att_u.setStyle("-fx-background-color: white; -fx-border-color: #b0feaa; -fx-border-width: 1pt;");
            } else if (!newValue){
                tmp_att_u.setStyle("");
            }
        });

        //Remove Button
        Button btn_RemoveLastEntry_uniq = new Button("-");
        btn_RemoveLastEntry_uniq.setOnAction(e -> {
            final int tmpID = GridPane.getRowIndex(btn_RemoveLastEntry_uniq);
            removeFromTheTable(gridPane3Mid, tmpID);
        });
        //Effect shadow = new DropShadow();
        btn_RemoveLastEntry_uniq.setOnMouseEntered(e -> btn_RemoveLastEntry_uniq.setEffect(shadow));
        btn_RemoveLastEntry_uniq.setOnMouseExited(e -> btn_RemoveLastEntry_uniq.setEffect(null));
        gridPane3Mid.add(btn_RemoveLastEntry_uniq, 2, g3MR);

        //GridPane.setMargin(tmp_att_u, new Insets(5, 10, 5, 10));
        GridPane.setMargin(tmp_type_u, new Insets(5, 10, 5, 10));
        GridPane.setMargin(btn_RemoveLastEntry_uniq, new Insets(0, 2, 0, 2));
    }

    public void addToTheBotTable(String attName, String attType) {
        g3BR++;
        System.out.println("Adding Row: " + g3BR);
        if (attName.isEmpty()) attName = "Attribute";
        if (attType.isEmpty()) attType = "String";

        TextField tmp_att = new TextField(attName);
        gridPane3Bot.add(tmp_att, 0, g3BR); // C + R
        Label tmp_type = new Label(attType);
        gridPane3Bot.add(tmp_type, 1, g3BR);

        botDataL.add(attName);
        botDataR.add(attType);

        //Attribute name change:
        tmp_att.textProperty().addListener((observable, oldValue, newValue) -> {
            int row = GridPane.getRowIndex(tmp_att);
            System.out.println("textfield changed from " + oldValue + " to " + newValue + row);
            botDataL.set(row, newValue);
        });
        tmp_att.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                tmp_att.setStyle("-fx-background-color: white; -fx-border-color: #b0feaa; -fx-border-width: 1pt;");
            } else if (!newValue){
                tmp_att.setStyle("");
            }
        });

        //Remove Button
        Button btn_RemoveLastEntry = new Button("-");
        btn_RemoveLastEntry.setOnAction(e -> {
            final int tmpID = GridPane.getRowIndex(btn_RemoveLastEntry);
            removeFromTheTable(gridPane3Bot, tmpID);
        });
        btn_RemoveLastEntry.setOnMouseEntered(e -> btn_RemoveLastEntry.setEffect(shadow));
        btn_RemoveLastEntry.setOnMouseExited(e -> btn_RemoveLastEntry.setEffect(null));
        gridPane3Bot.add(btn_RemoveLastEntry, 2, g3BR);

        //GridPane.setMargin(tmp_att, new Insets(5, 10, 5, 10));
        GridPane.setMargin(tmp_type, new Insets(5, 10, 5, 10));
        GridPane.setMargin(btn_RemoveLastEntry, new Insets(0, 2, 0, 2));
    }

    private void removeFromTheTable(GridPane removeFromThisGrid, int deleteThisRow) {
        System.out.println("Removing Row: " + deleteThisRow);
        if (deleteThisRow == 0) {
            removeFromThisGrid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == deleteThisRow || GridPane.getRowIndex(node) == null);
        } else {
            removeFromThisGrid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == deleteThisRow);
        }

        if (removeFromThisGrid == gridPane3Top) {
            topDataL.remove(deleteThisRow);
            topDataR.remove(deleteThisRow);
            System.out.println(topDataL + topDataR.toString());
            setTopGrid(topDataL, topDataR);
        } else if (removeFromThisGrid == gridPane3Mid) {
            midDataL.remove(deleteThisRow);
            midDataR.remove(deleteThisRow);
            System.out.println(midDataL + midDataR.toString());
            setMidGrid(midDataL, midDataR);
        } else if (removeFromThisGrid == gridPane3Bot) {
            botDataL.remove(deleteThisRow);
            botDataR.remove(deleteThisRow);
            System.out.println(botDataL + botDataR.toString());
            setBotGrid(botDataL, botDataR);
        }
        parentPane.requestFocus();
    }

    public void delete() {
        objectHitBox.getChildren().clear();
        parentPane.getChildren().remove(objectHitBox);
        parentPane.requestFocus();
    }

    public void setTopGrid(ArrayList<String> attributesList, ArrayList<String> typeList) {
        //in case where botData = Params
        ArrayList<String> tmpAttList = new ArrayList<String>();
        tmpAttList.addAll(attributesList);
        ArrayList<String> tmpTypList = new ArrayList<String>();
        tmpTypList.addAll(typeList);
        //Reset Data:
        gridPane3Top.getChildren().clear();
        topDataL.clear();
        topDataR.clear();
        g3TR = -1;
        for (int i = 0; i < tmpAttList.size(); i++) {
            setupTopGrid(tmpAttList.get(i), tmpTypList.get(i));
        }
    }

    public void setMidGrid(ArrayList<String> attributesList, ArrayList<String> typeList) {
        //in case where botData = Params
        ArrayList<String> tmpAttList = new ArrayList<String>();
        tmpAttList.addAll(attributesList);
        ArrayList<String> tmpTypList = new ArrayList<String>();
        tmpTypList.addAll(typeList);
        //Reset Data:
        gridPane3Mid.getChildren().clear();
        midDataL.clear();
        midDataR.clear();
        g3MR = -1;
        for (int i = 0; i < tmpAttList.size(); i++) {
            addToTheMidTable(tmpAttList.get(i), tmpTypList.get(i));
        }
    }

    public void setBotGrid(ArrayList<String> attributesList, ArrayList<String> typeList) {
        //in case where botData = Params
        ArrayList<String> tmpAttList = new ArrayList<String>();
        tmpAttList.addAll(attributesList);
        ArrayList<String> tmpTypList = new ArrayList<String>();
        tmpTypList.addAll(typeList);
        //Reset Data:
        gridPane3Bot.getChildren().clear();
        botDataL.clear();
        botDataR.clear();
        g3BR = -1;
        for (int i = 0; i < tmpAttList.size(); i++) {
            addToTheBotTable(tmpAttList.get(i), tmpTypList.get(i));
        }
    }

    public GridObject duplicate() {
        GridObject newObject = new GridObject(mainClass, parentPane, objectContextMenu);
        newObject.setTopGrid(topDataL, topDataR);
        newObject.setMidGrid(midDataL, midDataR);
        newObject.setBotGrid(botDataL, botDataR);
        //int x = MouseInfo.getPointerInfo().getLocation().x;
        //int y = MouseInfo.getPointerInfo().getLocation().y;
        //newObject.getObjectsVBox().relocate(MouseInfo.getPointerInfo().getLocation().x, robot.getMouseY());
        //newObject.getObjectsVBox().relocate(MouseInfo.getPointerInfo().getLocation().x/2, MouseInfo.getPointerInfo().getLocation().y/2);
        newObject.getObjectsVBox().relocate(scnX, scnY);
        return newObject;
    }

    public void copyToClipboard() {
        // Clipboard
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        String copyOfObject = txtCopyOfObject();
        content.putString(copyOfObject);
        clipboard.setContent(content);
        System.out.println(content);
    }

    public void startDragBoard() {
        // Dragboard
        ClipboardContent content = new ClipboardContent();
        String copyOfObject = txtCopyOfObject();
        content.putString(copyOfObject);
        Dragboard db = this.getObjectsVBox().startDragAndDrop(TransferMode.ANY);
        db.setContent(content);
    }

    private String txtCopyOfObject(){
        String copyOfObject = "";
        copyOfObject = copyOfObject + topDataL.toString() + ",,";
        copyOfObject = copyOfObject + topDataR.toString() + ",,";
        copyOfObject = copyOfObject + midDataL.toString() + ",,";
        copyOfObject = copyOfObject + midDataR.toString() + ",,";
        copyOfObject = copyOfObject + botDataL.toString() + ",,";
        copyOfObject = copyOfObject + botDataR.toString();
        return copyOfObject;
    }

    private void createEventHandlers() {

        objX = 0;
        objY = 0;
        scnX = 0;
        scnY = 0;

        objectHitBox.setOnMouseClicked(e -> {
            objectHitBox.toFront();
            if (e.getButton() == MouseButton.PRIMARY) {
                System.out.println("Mouse clicked");
                System.out.println(objX + " " + objY);
            } else if (e.getButton() == MouseButton.SECONDARY) {
                System.out.println("Right Mouse clicked");
            }
        });
        objectHitBox.setOnMousePressed(e -> {
            objectHitBox.toFront();
            objX = e.getX();
            objY = e.getY();
            scnX = e.getSceneX();
            scnY = e.getSceneY();
        });

        // DRAG START:
        //This will be called once
        objectHitBox.setOnDragDetected(e -> {
            System.out.println("setOnDragDetected started");
            if (e.getButton() == MouseButton.PRIMARY) {
                strpX = objectHitBox.getLayoutX();
                strpY = objectHitBox.getLayoutY();
                objectHitBox.setStyle("-fx-background-color: #086501; -fx-border-color: #b0feaa;");
                copyToClipboard();
                startDragBoard();
                //e.consume();
            }
        });
        //This will be called constantly - But this will only work if there is NO TARGET LISTENER
        // - Obsolete in my case - since whole Pane is target
        /*objectHitBox.setOnMouseDragged(e -> {
            System.out.println("setOnMouseDragged started");
            if (e.getButton() == MouseButton.PRIMARY) {
                objectHitBox.relocate(e.getSceneX() - objX, e.getSceneY() - objY);
            }
        });*/
        // DRAG DONE - However, this Mouse action will work
        objectHitBox.setOnMouseDragReleased(e -> {
            System.out.println("setOnMouseReleased started");
            if (e.getButton() == MouseButton.PRIMARY) {
                objectHitBox.setStyle("");
            }
        });
        objectHitBox.setOnDragEntered(e -> {
            System.out.println("setOnDragEntered/ any");
            //if (e.getGestureSource() != objectHitBox) {
            if (e.getGestureTarget() == objectHitBox || e.getGestureSource() != objectHitBox) {
                System.out.println("setOnDragEntered - another VBox");
                objectHitBox.setStyle("-fx-background-color: red; -fx-border-color: black;");
                e.consume();
            }
        });
        objectHitBox.setOnDragOver(e -> {
            //System.out.println("setOnDragOver / any");
            if (e.getGestureSource() != objectHitBox) {
                System.out.println("setOnDragOver - another VBox");
                objectHitBox.setStyle("-fx-background-color: red; -fx-border-color: black;");
                e.consume();
            }
        });
        objectHitBox.setOnDragExited(e -> {
            //System.out.println("setOnDragExited - GREEN");
            if (e.getGestureTarget() == objectHitBox || e.getGestureSource() != objectHitBox) {
                System.out.println("setOnDragExited - another VBox");
                objectHitBox.setStyle("");
                //e.consume();
            }
        });
        // DRAG DONE goes after DRAG DROPPED and is called on source
        objectHitBox.setOnDragDone(e -> {
            System.out.println("setOnDragDone Called");
            objectHitBox.setStyle("");
            if ((e.isDropCompleted() || e.getTransferMode() != null) && e.getTransferMode() == TransferMode.COPY) {
                System.out.println("Transfer is done: Original object is deleted, TransferMode was: " + e.getTransferMode());
                delete();
                e.consume();
            } else if ((e.isDropCompleted() || e.getTransferMode() != null) && e.getTransferMode() == TransferMode.MOVE) {
                System.out.println("Transfer is done: Original object is alive, TransferMode was: " + e.getTransferMode());
                // Do nothing here
                e.consume();
            } else {
                // This is error case / Transfer failed
                objectHitBox.relocate(strpX, strpY);
            }
        });
        // DROPPED is used in case: TARGET GETS DROPED ON
        //objectHitBox.setOnDragDropped(e -> {
        //    System.out.println("setOnMouseReleased started");
        //    objectHitBox.setStyle("");
        //    delete();
        //});
    }

}
