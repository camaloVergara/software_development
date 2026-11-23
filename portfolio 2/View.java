package org.example.portfolio2x;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.layout.*;

public class View {
    //measures
    int wiButton = 300;
    int heiButton = 30;

    //Objects for the listeners
    TextField textName = text("");
    ComboBox<String> comboAct = combo();
    Button signUp = button("Sign Up");
    TextArea regisAct = textA(350,300);

    ComboBox<String> groupAct = combo();
    Button showGroup = button("Show groups");
    TextArea viewGroup = textA(330,300);
    TextField title = text("");
    TextField supervisor = text("");
    Button createGroup = button("Create group");
    ComboBox<String> groups = combo();
    Button join = button("Join");


    GridPane v1 = new GridPane();
    GridPane v3 = new GridPane();
    //Center box
    HBox box = new HBox(
            new VBox(v1), //first vertical box inside the middle box
            new VBox(space(10, 1)), //second vertical box inside the middle box
            new VBox(v3) //third vertical box inside the middle box
    );

    BorderPane outGrid = new BorderPane();

    //-------------- methods -------------------
    Button button(String name){
        Button b = new Button(name);
        b.setPrefWidth(wiButton);
//        b.setPrefSize(wiButton, heiButton);
        return b;
    }

    TextField text(String text){
        TextField t = new TextField(text);
        t.setPrefSize(wiButton, heiButton);
        return t;
    }

    Label label(String label){
        Label l = new Label(label);
        l.setPrefHeight(heiButton);
        return l;
    }

    Label specialLabel(String label){
        return new Label(label);
    }

    Label space(int width, int length){
        Label h = new Label("");
        h.setPrefSize(width, length);
        return h;
    }

    ComboBox<String> combo(String... many){
        ComboBox<String> c = new ComboBox<String>();
        c.getItems().addAll(many);
        c.setPrefWidth(wiButton);
        return c;
    }

    TextArea textA(int w, int h){
        TextArea tA = new TextArea();
        tA.setPrefSize(w, h);
        return tA;
    }

    Alert alert(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Missing information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        return alert;
    }

    Alert confirmation(String message){
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText(null);
        confirmation.setContentText(message);
        confirmation.showAndWait();
        return confirmation;
    }

    View(){
        //1st vertical box inside the center box
        v1.add(label("Student name"),0,0);
        v1.add(textName,0,1);
        v1.add(label("Select Study Activity"),0,2);
        v1.add(comboAct,0,3);
        v1.add(space(wiButton, 20),0,4);
        v1.add(signUp,0,5);
        v1.add(label("Registered Activities"),0,6);
        v1.add(regisAct,0,7,2,1);

        //3rd vertical box inside the center box
        v3.add(label("Project groups for activities"),0,0,2,1);
        v3.add(groupAct,0,1,2,1);
        v3.add(showGroup,0,2,3,1);
        v3.add(viewGroup,0,3,4,1);
        v3.add(label("Add yourself to existing project group"),0,4,2,1);
        v3.add(groups,0,5,2,1);
        v3.add(join,0,6,3,1);
        v3.add(label("Or"),0,7,2,1);
        v3.add(label("Create new project group"),0,8,2,1);
        v3.add(specialLabel("Title"),0,9,1,1);
        v3.add(title,1,9);
        v3.add(specialLabel("Supervisor    "),0,10,1,1);
        v3.add(supervisor,1,10);
        v3.add(createGroup,0,11,3,1);

        //Outer grid
        outGrid.setTop(space(1, 10)); outGrid.setLeft(space(10, 1)); outGrid.setRight(space(10, 1));
        outGrid.setBottom(space(1, 10));
        outGrid.setCenter(box);
    }
}
