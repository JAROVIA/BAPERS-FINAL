package GUI;

import CONTROLLER.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class Window {

	private int backgroundColour = 0;
	private String foregroundColour = "rgb(192,192,192)";
	private int X = 0;
	private int Y = 0;
	private int xSize = 1920;
	private int ySize = 1080;
	private int Icon;

	protected ACCT_UI_Controller acctUiController;
	protected ADMIN_UI_Controller adminUiController;
	protected PROC_UI_Controller procUiController;
	protected UI_Controller uiController;

	protected Parent parent;

	public void show() {
		// TODO - implement Window.show
		throw new UnsupportedOperationException();
	}

	public void hide() {
		// TODO - implement Window.hide
		throw new UnsupportedOperationException();
	}

	public void resize() {
		// TODO - implement Window.resize
		throw new UnsupportedOperationException();
	}

	public void close() {
		// TODO - implement Window.close
		throw new UnsupportedOperationException();
	}

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
//		String url = "/gui/fxml/" + fxml + ".fxml";
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

	/**
	 * 
	 * @param x
	 * @param y
	 * @param xSize
	 * @param ySize
	 * @param icon
	 */
	public Window Window(int x, int y, int xSize, int ySize, String icon) {
		// TODO - implement Window.Window
		throw new UnsupportedOperationException();
	}

}