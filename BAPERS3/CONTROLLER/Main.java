package CONTROLLER;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    //main class extends Application and overrides start()
    @Override
    public void start(Stage stage) throws Exception{
        //setting the title
        stage.setTitle("BAPERS");

        //maximize screen
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        //the path to the fxml file for the initial page, and load
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/login.fxml"));
        Parent parent = loader.load();

        //create a scene (page)
        Scene scene = new Scene(parent);

        //set the scene to the window
        stage.setScene(scene);

        //set stage so controller knows

        stage.show();
    }

    //to start the app
    public static void main(String[] args) { launch(args); }
}

