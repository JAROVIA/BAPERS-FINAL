package GUI;

import CONTROLLER.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.io.IOException;

public abstract class Window{

	protected ACCT_UI_Controller acctUiController = null;
	protected ADMIN_UI_Controller adminUiController = null;
	protected PROC_UI_Controller procUiController = null;
	protected UI_Controller uiController = null;
	protected Parent parent;

	protected String[] userAllowed;
	protected final String ROLE_OFFICE_MANAGER = "Office Manager";
	protected final String ROLE_SHIFT_MANAGER = "Shift Manager";
	protected final String ROLE_RECEPTIONIST = "Receptionist";
	protected final String ROLE_TECHNICIAN_DEV = "Technician - Development";
	protected final String ROLE_TECHNICIAN_COPY = "Technician - Copy room";
	protected final String ROLE_TECHNICIAN_PACK = "Technician - Packing room";
	protected final String ROLE_TECHNICIAN_FIN = "Technician - Finishing room";

	/**
	 * Button / image which returns user to home screen
	 */
	@FXML
	private ImageView homeButton;

	/**
	 * Button for logout
	 */
	@FXML
	private Button logoutButton;

	/**
	 * Label to welcome user with their username
	 */
	@FXML
	private Label welcomeLabel;

	/**
	 * access each controller so each gui knows which controller to report back to
	 */
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

	/**
	 *
	 * @param fxml the name of the fxml file to get the gui class from (path is adjusted in the method)
	 * @return The window object set to controller in the fxml file
	 * @throws IOException
	 */
	public static Window newGuiFromFxml(String fxml) throws IOException {
		//load fxml
		String url = "fxml/" + fxml + ".fxml";
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
		if(getProcUiController() != null){
			getProcUiController().showScreen("Login");
		}
		if(getAcctUiController() != null){
			getAcctUiController().showScreen("Login");
		}
		if(getUiController() != null){
			getUiController().showScreen("Login");
		}
		if(getAdminUiController() != null){
			getAdminUiController().showScreen("Login");
		}
	}

	public void toMain(){
		if(getProcUiController() != null){
			getProcUiController().showScreen("HomeScreen");
		}
		if(getAcctUiController() != null){
			getAcctUiController().showScreen("HomeScreen");
		}
		if(getUiController() != null){
			getUiController().showScreen("HomeScreen");
		}
		if(getAdminUiController() != null){
			getAdminUiController().showScreen("HomeScreen");
		}
	}

	public boolean checkAccess(String userRole){
		boolean isAllowed = false;
		for(String user : userAllowed){
			System.out.println("role: " + userRole + " = " + user + ", access: " + isAllowed);
			if(user.equals(userRole)){
				isAllowed = true;
				break;
			}
		}
		return  isAllowed;
	}

	public void onShow(){
		//do what the screen needs to do on show
		if(welcomeLabel != null){
			String userText ="Welcome! user: ";
			if(adminUiController != null){
				userText += adminUiController.getLoggedInUser().getUsername();
				System.out.println("username: " + adminUiController.getLoggedInUser().getUsername());
			}
			if(uiController != null){
				userText += uiController.getLoggedInUser().getUsername();
				System.out.println("username: " + uiController.getLoggedInUser().getUsername());
			}
			if(acctUiController != null){
				userText += acctUiController.getLoggedInUser().getUsername();
				System.out.println("username: " + acctUiController.getLoggedInUser().getUsername());
			}
			if(procUiController != null){
				userText += procUiController.getLoggedInUser().getUsername();
				System.out.println("username: " + procUiController.getLoggedInUser().getUsername());
			}
			welcomeLabel.setText(userText);
		}
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