package org.example.portfolio2x;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Launcher extends Application{
    @Override
    public void start(Stage stage){
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(view, model);
        Scene scene = new Scene(view.outGrid);
        stage.setTitle("Group Project Formation");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
