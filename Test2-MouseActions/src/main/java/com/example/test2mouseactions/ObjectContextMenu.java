package com.example.test2mouseactions;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class ObjectContextMenu {

    public ContextMenu objectMenu;
    public MenuItem menuItem1;
    public MenuItem menuItem2;
    public MenuItem menuItem3;
    public MenuItem menuItem4;
    public MenuItem menuItem5;

    public ObjectContextMenu() {
        // create a menu
        objectMenu = new ContextMenu();
        // create menu items
        menuItem1 = new MenuItem("Add Attribute");
        menuItem2 = new MenuItem("Add Unique Key");
        SeparatorMenuItem sep = new SeparatorMenuItem();
        menuItem3 = new MenuItem("Duplicate Object");
        menuItem4 = new MenuItem("Copy Object");
        menuItem5 = new MenuItem("Delete Object");

        //Add them to the Context Box
        objectMenu.getItems().addAll(menuItem1, menuItem2, sep, menuItem3, menuItem4, menuItem5);
    }

    //Getters:
    public boolean isShowing(){
        return objectMenu.isShowing();
    }

    public ContextMenu getThisContextMenu(){
        return objectMenu;
    }

    //Set Actions:
    public void setAddAction(GridObject thisGridObject) {
        menuItem1.setOnAction(event -> {
            String[] attTmp = {"Attribute", "String", "false"};
            thisGridObject.addToTheTable(attTmp[0], attTmp[1], Boolean.parseBoolean(attTmp[2]));
        });
    }
    public void setAddUniqAction(GridObject thisGridObject) {
        menuItem2.setOnAction(event -> {
            String[] attTmp = {"Attribute", "String", "true"};
            thisGridObject.addToTheMidTable(attTmp[0], "Unique Key");;
        });
    }
    public void setDuplicateAction(GridObject thisGridObject) {
        menuItem3.setOnAction(event -> {
            thisGridObject.duplicate();
        });
    }
    public void setCopyAction(GridObject thisGridObject) {
        menuItem4.setOnAction(event -> {
            thisGridObject.copyToClipboard();
        });
    }
    public void setDeleteAction(GridObject thisGridObject) {
        menuItem5.setOnAction(event -> {
            thisGridObject.delete();
        });
    }
}
