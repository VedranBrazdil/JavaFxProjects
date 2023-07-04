package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private int g3TR, g3MR, g3BR;
    private GridPane gridPane3Top, gridPane3Mid, gridPane3Bot;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Task 1");

        // **************1. object : Class
        GridPane gridPane1 = new GridPane();
        gridPane1.setPadding(new Insets(10, 10, 10, 10));
        gridPane1.setId("gridClass");
        //gridPane1.getStyleClass().add("gridClass");
        gridPane1.setAlignment(Pos.CENTER);
        //gridPane1.setMaxWidth(Double.MAX_VALUE);
        gridPane1.setMaxWidth(300);
        AnchorPane.setLeftAnchor(gridPane1, 0.0);
        AnchorPane.setRightAnchor(gridPane1, 0.0);

        StackPane backPane1 = createTemporaryStackPane();
        backPane1.getChildren().add(gridPane1);

        int g10r = 0;

        Label g1_label = new Label("NewClass");
        g1_label.setCenterShape(true);
        GridPane.setConstraints(g1_label, 0, g10r, 2, 1, HPos.CENTER, VPos.CENTER);
        gridPane1.getChildren().add(g1_label);
        //gridPane1.setRowSpan(g1_label, 2);
        g10r++;

        //Separator:
        int g11r = 0;
        Separator g1_sep1 = new Separator();
        g1_sep1.setValignment(VPos.CENTER);
        GridPane.setConstraints(g1_sep1, 0, g10r + g11r);
        GridPane.setColumnSpan(g1_sep1, 2);
        gridPane1.getChildren().add(g1_sep1);

        g11r++;
        TextField g1_att1 = new TextField("Attribute 1");
        gridPane1.add(g1_att1, 0, g10r + g11r); // C + R
        Label g1_type1 = new Label("String");
        gridPane1.add(g1_type1, 1, g10r + g11r);

        g11r++;
        TextField g1_att2 = new TextField("Attribute 2");
        gridPane1.add(g1_att2, 0, g10r + g11r + 1);
        Label g1_type2 = new Label("String");
        gridPane1.add(g1_type2, 1, g10r + g11r + 1);

        Label label1_main = new Label("Class");
        label1_main.setLabelFor(gridPane1);
        label1_main.setAlignment(Pos.CENTER);
        label1_main.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label1_main, 0.0);
        AnchorPane.setRightAnchor(label1_main, 0.0);
        label1_main.setPadding(new Insets(0, 0,20, 0));

        // **************2. object : Record
        int g21r = 0;
        GridPane gridPane2 = new GridPane();
        gridPane2.setPadding(new Insets(10, 10, 10, 10));
        gridPane2.setId("gridRecord");
        gridPane2.setAlignment(Pos.CENTER);
        gridPane2.setMaxWidth(300);
        AnchorPane.setLeftAnchor(gridPane2, 0.0);
        AnchorPane.setRightAnchor(gridPane2, 0.0);

        StackPane backPane2 = createTemporaryStackPane();
        backPane2.getChildren().add(gridPane2);

        Label g2_label = new Label("record");
        g2_label.setCenterShape(true);
        GridPane.setConstraints(g2_label, 0, g21r);
        gridPane2.getChildren().add(g2_label);
        Label g2_label_type = new Label("Record");
        g2_label_type.setCenterShape(true);
        GridPane.setConstraints(g2_label_type, 1, g21r);
        gridPane2.getChildren().add(g2_label_type);

        g21r++;

        //Separator:
        int g22r = 0;
        Separator g2_sep1 = new Separator();
        g2_sep1.setValignment(VPos.CENTER);
        GridPane.setConstraints(g2_sep1, 0, g21r + g22r);
        GridPane.setColumnSpan(g2_sep1, 2);
        gridPane2.getChildren().add(g2_sep1);

        g22r++;
        TextField g2_att1 = new TextField("Attribute 1");
        gridPane2.add(g2_att1, 0, g21r + g22r); // C + R
        Label g2_type1 = new Label("String");
        gridPane2.add(g2_type1, 1, g21r + g22r);

        g22r++;
        TextField g2_att2 = new TextField("Attribute 2");
        gridPane2.add(g2_att2, 0, g21r + g22r);
        Label g2_type2 = new Label("String");
        gridPane2.add(g2_type2, 1, g21r + g22r);

        Label label2_main = new Label("Record");
        label2_main.setAlignment(Pos.CENTER);
        label2_main.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label2_main, 0.0);
        AnchorPane.setRightAnchor(label2_main, 0.0);
        label2_main.setPadding(new Insets(0, 0,20, 0));


        // **************3. object : Entry
        g3TR = 0;
        gridPane3Top = createNewGridObject(false);
        gridPane3Top.setId("gridEntryTop");
        gridPane3Mid = createNewGridObject(false);
        gridPane3Mid.setId("gridEntryMid");
        gridPane3Bot = createNewGridObject(false);
        gridPane3Bot.setId("gridEntryBot");

        StackPane backPane3 = createTemporaryStackPane();
        VBox backVBox3 = createNewVBoxObject();
        backVBox3.getChildren().addAll(gridPane3Top, gridPane3Mid, gridPane3Bot);
        backPane3.getChildren().add(backVBox3);

        //TOP section:

        Label g3_label = new Label("Name");
        GridPane.setConstraints(g3_label, 0, g3TR);
        Label g3_label_type = new Label("Type");
        GridPane.setConstraints(g3_label_type, 1, g3TR);
        gridPane3Top.getChildren().addAll(g3_label, g3_label_type);
        GridPane.setMargin(g3_label, new Insets(5, 10, 5, 10));
        GridPane.setMargin(g3_label_type, new Insets(5, 10, 5, 10));

        g3TR++;

        //MID section:
        /*
        g3MR = 0;
        TextField g3_att1 = new TextField("Attribute 1");
        gridPane3Mid.add(g2_att1, 0, g3MR); // C + R
        Label g3_type1 = new Label("String");
        gridPane3Mid.add(g2_type1, 1, g3MR);

        g3MR++;
        TextField g3_att2 = new TextField("Attribute 2");
        gridPane3Mid.add(g3_att2, 0, g3MR);
        Label g3_type2 = new Label("String");
        gridPane3Mid.add(g3_type2, 1, g3MR);
         */

        //BOT and MID section
        g3MR = -1;
        g3BR = -1;
        addToTheTable(gridPane3Bot, "Attribute 1", "String", true, gridPane3Mid);
        addToTheTable(gridPane3Bot, "Attribute 2", "String", false, gridPane3Mid);

        //Button to add Entry:
        Button btn_AddNewEntry = new Button("+");
        btn_AddNewEntry.setOnAction(e -> {
            String[] attTmp = ConfirmAttribute.display();
            addToTheTable(gridPane3Bot, attTmp[0], attTmp[1], Boolean.parseBoolean(attTmp[2]), gridPane3Mid);
        });
        Effect shadow = new DropShadow();
        btn_AddNewEntry.setOnMouseEntered(e -> btn_AddNewEntry.setEffect(shadow));
        btn_AddNewEntry.setOnMouseExited(e -> btn_AddNewEntry.setEffect(null));
        /*//Button to remove Entry:
        Button btn_RemoveLastEntry = new Button("-");
        btn_RemoveLastEntry.setOnAction(e -> {
            removeFromTheTable(gridPane3Bot);
        });
        btn_RemoveLastEntry.setOnMouseEntered(e -> btn_RemoveLastEntry.setEffect(shadow));
        btn_RemoveLastEntry.setOnMouseExited(e -> btn_RemoveLastEntry.setEffect(null));
        */
        //HBox btn_Hbox = new HBox();
        //btn_Hbox.setAlignment(Pos.CENTER);
        //btn_Hbox.getChildren().addAll(btn_AddNewEntry, btn_RemoveLastEntry);
        //backVBox3.getChildren().add(btn_Hbox);
        backVBox3.getChildren().add(btn_AddNewEntry);


        //End label
        Label label3_main = new Label("Entry");
        label3_main.setAlignment(Pos.CENTER);
        label3_main.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(label3_main, 0.0);
        AnchorPane.setRightAnchor(label3_main, 0.0);
        label3_main.setPadding(new Insets(0, 0,20, 0));


        //Layout
        VBox vbox = new VBox(8);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(backPane1, label1_main, backPane2, label2_main, backPane3, label3_main);
        //Scene
        Scene mainScene = new Scene(vbox, 350, 700);
        mainScene.getStylesheets().add("sample/defaultStyle.css");

        primaryStage.setScene(mainScene); //W + H
        primaryStage.show();
    }

    private StackPane createTemporaryStackPane() {
        StackPane temp = new StackPane();

        temp.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(temp, 0.0);
        AnchorPane.setRightAnchor(temp, 0.0);

        return temp;
    }

    private GridPane createNewGridObject(boolean padd) {
        GridPane temp = new GridPane();
        if (padd) temp.setPadding(new Insets(10, 10, 10, 10));
        temp.setAlignment(Pos.CENTER);
        temp.setMaxWidth(300);
        //temp.setGridLinesVisible(true);
        //AnchorPane.setLeftAnchor(temp, 0.0);
        //AnchorPane.setRightAnchor(temp, 0.0);

        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(50);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(35);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(15);
        column2.setHalignment(HPos.CENTER);
        //ColumnConstraints column0 = new ColumnConstraints(120);
        //ColumnConstraints column1 = new ColumnConstraints(120);
        //ColumnConstraints column2 = new ColumnConstraints(60);

        temp.getColumnConstraints().addAll(column0, column1, column2);

        return temp;
    }
    private VBox createNewVBoxObject() {
        VBox temp = new VBox();
        temp.setPadding(new Insets(10, 10, 10, 10));
        temp.setAlignment(Pos.CENTER);
        temp.setMaxWidth(350);
        AnchorPane.setLeftAnchor(temp, 0.0);
        AnchorPane.setRightAnchor(temp, 0.0);

        return temp;
    }

    private void addToTheTable(GridPane addToThisGrid, String attName, String attType, boolean attUniq, GridPane addToThisGridUniq) {

        g3BR++;
        System.out.println("Adding Row: " + g3BR);
        if (attName.isEmpty()) attName = "Attribute";
        if (attType.isEmpty()) attType = "String";

        TextField tmp_att = new TextField(attName);
        addToThisGrid.add(tmp_att, 0, g3BR); // C + R
        Label tmp_type = new Label(attType);
        addToThisGrid.add(tmp_type, 1, g3BR);

        //Remove Button
        Button btn_RemoveLastEntry = new Button("-");
        btn_RemoveLastEntry.setOnAction(e -> {
            final int tmpID = GridPane.getRowIndex(btn_RemoveLastEntry);
            removeFromTheTable(addToThisGrid, tmpID);
        });
        Effect shadow = new DropShadow();
        btn_RemoveLastEntry.setOnMouseEntered(e -> btn_RemoveLastEntry.setEffect(shadow));
        btn_RemoveLastEntry.setOnMouseExited(e -> btn_RemoveLastEntry.setEffect(null));
        addToThisGrid.add(btn_RemoveLastEntry, 2, g3BR);

        //GridPane.setMargin(tmp_att, new Insets(5, 10, 5, 10));
        GridPane.setMargin(tmp_type, new Insets(5, 10, 5, 10));
        GridPane.setMargin(btn_RemoveLastEntry, new Insets(0, 2, 0, 2));

        if (attUniq) {
            g3MR++;
            System.out.println("Adding MID Row: " + g3MR);

            TextField tmp_att_u = new TextField(attName);
            addToThisGridUniq.add(tmp_att_u, 0, g3MR); // C + R
            Label tmp_type_u = new Label("Unique Key");
            addToThisGridUniq.add(tmp_type_u, 1, g3MR);

            //Remove Button
            Button btn_RemoveLastEntry_uniq = new Button("-");
            btn_RemoveLastEntry_uniq.setOnAction(e -> {
                final int tmpID = GridPane.getRowIndex(btn_RemoveLastEntry_uniq);
                removeFromTheTable(addToThisGridUniq, tmpID);
            });
            //Effect shadow = new DropShadow();
            btn_RemoveLastEntry_uniq.setOnMouseEntered(e -> btn_RemoveLastEntry_uniq.setEffect(shadow));
            btn_RemoveLastEntry_uniq.setOnMouseExited(e -> btn_RemoveLastEntry_uniq.setEffect(null));
            addToThisGridUniq.add(btn_RemoveLastEntry_uniq, 2, g3MR);

            //GridPane.setMargin(tmp_att_u, new Insets(5, 10, 5, 10));
            GridPane.setMargin(tmp_type_u, new Insets(5, 10, 5, 10));
            GridPane.setMargin(btn_RemoveLastEntry_uniq, new Insets(0, 2, 0, 2));
        }
    }

    private void removeFromTheTable(GridPane removeFromThisGrid, int deleteThisRow) {
        System.out.println("Removing Row: " + deleteThisRow);
        if (deleteThisRow == 0) {
            removeFromThisGrid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == deleteThisRow || GridPane.getRowIndex(node) == null);
        } else {
            removeFromThisGrid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == deleteThisRow);
        }
    }

}