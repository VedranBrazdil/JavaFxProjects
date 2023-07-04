package sample;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.util.concurrent.atomic.AtomicReference;

public class ConfirmAttribute {

    public static String[] display () {
        Stage window = new Stage();
        AtomicReference<String> name = new AtomicReference<>("");
        AtomicReference<String> type = new AtomicReference<>("");
        AtomicReference<String> uniq = new AtomicReference<>("");

        //Block
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("New Attribute");
        window.setMinWidth(220);

        TextField at_name = new TextField();
        at_name.setPromptText("Attribute Name");
        at_name.setMaxWidth(150);

        ComboBox at_type = new ComboBox();
        at_type.getItems().addAll(
                "String",
                "Integer",
                "Boolean"
        );
        at_type.setMaxSize(100, 100);
        at_type.setPadding(new Insets(10, 10, 10, 10));
        at_type.setValue("String");

        CheckBox at_uniq = new CheckBox("Unique Key");
        at_uniq.setPadding(new Insets(10, 10, 10, 10));
        at_uniq.setSelected(false);

        Button btn_done = new Button("Add");
        btn_done.setOnAction(e -> {
            name.set(at_name.getText());
            type.set(at_type.getValue().toString());
            window.close();
        });

        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);
        HBox botLine = new HBox();
        botLine.setAlignment(Pos.CENTER);
        botLine.getChildren().addAll(at_type, at_uniq);
        mainLayout.getChildren().addAll(at_name, botLine, btn_done);
        Scene scene = new Scene(mainLayout);
        window.setScene(scene);
        window.showAndWait();

        String boolAsString;
        if (at_uniq.isSelected()) {
            boolAsString = "true";
        } else {
            boolAsString = "false";
        }

        String[] returnList = {name.get(), type.get(), boolAsString};

        return returnList;
    }

}
