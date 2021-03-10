package CONTROLLER;

import GUI.Window;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    private Stage stage;
    private Scene scene;

    private ACCT_UI_Controller acctUiController;
    private ADMIN_UI_Controller adminUiController;
    private PROC_UI_Controller procUiController;
    private UI_Controller uiController;

    //map to store all screens as 'parent' class
    private Map<String, Parent> screens;

    //main class extends Application and overrides start()
    @Override
    public void start(Stage stage) throws Exception{
        this.stage = stage;
        screens = new HashMap<>();

        //setup the window
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
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/login.fxml"));
        //Parent parent = loader.load();

        //create a scene (page)
        //Scene scene = new Scene(parent);

        //set the scene to the window
        //stage.setScene(scene);

        adminUiController = new ADMIN_UI_Controller(this);
        acctUiController = new ACCT_UI_Controller(this);
        procUiController = new PROC_UI_Controller(this);
        uiController = new UI_Controller(this);

        scene = new Scene(adminUiController.getLoginScreen().getParent());
        stage.setScene(scene);
        stage.show();
        //showScreen("login");

        //show the page
        //stage.show();
    }

    //add screen to the map
    public void addScreen(String name, Parent parent){
        screens.put(name, parent);
    }

    //remove screen from the map
    public void removeScreen(String name){
        screens.remove(name);
    }

    //show the screen existing in the map.
    //Instantiates the scene from the parent selected and sets it to the stage.
    public void showScreen(String name){
        scene.setRoot(screens.get(name));
        stage.show();
    }

    //show screen when parent object is passed
    public void showScreen(Parent parent){
        scene.setRoot(parent);
        stage.show();
    }

    public Window newGuiFromFxml(String name, String fxml) throws IOException {
        //load fxml
        String url = "/gui/fxml/" + fxml + ".fxml";
        System.out.println(url);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        //get the parent element in fxml
        //parent is used to construct the scene, which is assigned to the stage(window) to show
        Parent parent = loader.load();
        //add the screen to the collection so it is accessible
        addScreen(name, parent);
        //get the gui class
        Window gui = loader.getController();
        gui.setParentElement(parent);
        return gui;
    }

    public UI_Controller getUiController() {
        return uiController;
    }

    public PROC_UI_Controller getProcUiController() {
        return procUiController;
    }

    public ADMIN_UI_Controller getAdminUiController() {
        return adminUiController;
    }

    public ACCT_UI_Controller getAcctUiController() {
        return acctUiController;
    }

    //to start the app
    public static void main(String[] args) { launch(args); }
}

