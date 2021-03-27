package CONTROLLER;

import GUI.Window;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Stage;
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
    private Map<String, String> screenToController;

    //main class extends Application and overrides start()
    @Override
    public void start(Stage stage) throws Exception{
        this.stage = stage;
        screens = new HashMap<>();
        screenToController = new HashMap<>();

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

        adminUiController = new ADMIN_UI_Controller(this);
        acctUiController = new ACCT_UI_Controller(this);
        procUiController = new PROC_UI_Controller(this);
        uiController = new UI_Controller(this);

        scene = new Scene(adminUiController.getScreen("Login").getParent());
        stage.setScene(scene);
        stage.show();
    }

    //add screen to the map
    public void addScreen(String name, Parent parent, String controllerName){
        screens.put(name, parent);
        screenToController.put(name, controllerName);
    }

    //remove screen from the map
    public void removeScreen(String name){
        screens.remove(name);
        screenToController.remove(name);
    }

    //show the screen existing in the map.
    //Instantiates the scene from the parent selected and sets it to the stage.
    public void showScreen(Window currentGui, String destinationName){
        String controller = screenToController.get(destinationName);
        String userRole = adminUiController.getLoggedInUser().getUserRole();
        Window gui = null;
        boolean isAccessible = true;

        if(destinationName.equals("Login")){
            gui = adminUiController.getScreen(destinationName);
        }
        else if(destinationName.equals("HomeScreen")){
            gui = uiController.getScreen(destinationName);
        }
        else {
            if (controller.equals("ACCT")) {
                isAccessible = acctUiController.getScreen(destinationName).checkAccess(userRole);
                gui = acctUiController.getScreen(destinationName);
            } else if (controller.equals("ADMIN")) {
                isAccessible = adminUiController.getScreen(destinationName).checkAccess(userRole);
                gui = adminUiController.getScreen(destinationName);
            } else if (controller.equals("PROC")) {
                isAccessible = procUiController.getScreen(destinationName).checkAccess(userRole);
                gui = procUiController.getScreen(destinationName);
            } else if (controller.equals("UI")) {
                isAccessible = uiController.getScreen(destinationName).checkAccess(userRole);
                gui = uiController.getScreen(destinationName);
            }
        }
        if (isAccessible) {
            scene.setRoot(screens.get(destinationName));
            gui.onShow();
            currentGui.onLeave();
        }
        else {
            StringBuilder message = new StringBuilder("Your role is : ")
                    .append(userRole)
                    .append(", access denied.\nRequired role;");
            for (String allowedRoles : gui.getUserAllowed()) {
                message.append("\n").append(allowedRoles);
            }
            Alert alert = new Alert(Alert.AlertType.NONE, message.toString(), ButtonType.CLOSE);
            alert.show();
        }
    }

    public Scene getScene(){return scene;}

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

