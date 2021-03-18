package GUI;

import CONTROLLER.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import java.io.IOException;

public abstract class Window{

	protected ACCT_UI_Controller acctUiController = null;
	protected ADMIN_UI_Controller adminUiController = null;
	protected PROC_UI_Controller procUiController = null;
	protected UI_Controller uiController = null;

	protected Parent parent;

	@FXML
	private ImageView homeButton;

	@FXML
	private Button logoutButton;

	//access each controller so each gui knows which controller to report back to
	//getter setter for account ui
	public ACCT_UI_Controller getAcctUiController() {
		return acctUiController;
	}

	public void setAcctUiController(ACCT_UI_Controller acctUiController) {
		this.acctUiController = acctUiController;
	}

	//getter setter for admin ui
	public ADMIN_UI_Controller getAdminUiController() {
		return adminUiController;
	}

	public void setAdminUiController(ADMIN_UI_Controller adminUiController){
		this.adminUiController = adminUiController;
	}

	//getter setter for process ui
	public PROC_UI_Controller getProcUiController() {
		return procUiController;
	}

	public void setProcUiController(PROC_UI_Controller procUiController) {
		this.procUiController = procUiController;
	}

	//getter setter for ui ui
	public UI_Controller getUiController() {
		return uiController;
	}

	public void setUiController(UI_Controller uiController) {
		this.uiController = uiController;
	}

	public void setParentElement(Parent parent){
		this.parent = parent;
	}

	public Parent getParent(){
		return parent;
	}

	public static Window newGuiFromFxml(String fxml) throws IOException {
		//load fxml
		String url = "fxml/" + fxml + ".fxml";
		System.out.println(url);
		FXMLLoader loader = new FXMLLoader(Window.class.getResource(url));
		//get the parent element in fxml
		//parent is used to construct the scene, which is assigned to the stage(window) to show
		Parent parent = loader.load();
		//get the gui class
		Window gui = loader.getController();
		gui.setParentElement(parent);

		return gui;
	}

	public void logout(){
		System.out.println("out");
		if(getProcUiController() != null){
			getProcUiController().getMain().showScreen("Login");
		}
		if(getAcctUiController() != null){
			getAcctUiController().getMain().showScreen("Login");
		}
		if(getUiController() != null){
			getUiController().getMain().showScreen("Login");
		}
		if(getAdminUiController() != null){
			getAdminUiController().getMain().showScreen("Login");
		}
	}

	public void toMain(){
		System.out.println("main");
		if(getProcUiController() != null){
			getProcUiController().getMain().showScreen("HomeScreen");
		}
		if(getAcctUiController() != null){
			getAcctUiController().getMain().showScreen("HomeScreen");
		}
		if(getUiController() != null){
			getUiController().getMain().showScreen("HomeScreen");
		}
		if(getAdminUiController() != null){
			getAdminUiController().getMain().showScreen("HomeScreen");
		}
	}

	protected void onShow(){
		//do what the screen needs to do on show
	}

	@FXML
	public void initialize() {
		// TODO - implement Window.Window

		if(logoutButton != null) {
			logoutButton.setOnAction(actionEvent -> logout());
		}
		if(homeButton != null) {
			homeButton.setOnMouseClicked(mouseEvent -> toMain());
		}
	}

}