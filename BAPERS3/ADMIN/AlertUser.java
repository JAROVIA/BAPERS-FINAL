package ADMIN;

import CONTROLLER.ADMIN_UI_Controller;
import GUI.Window;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertUser {

	private String alertMessage;
	private boolean messageSeen;
	private String destination;
	private ADMIN_UI_Controller adminUiController;

	/**
	 * 
	 * @param AlertMessage
	 */
	public AlertUser(String AlertMessage) {
		// TODO - implement Alert.Alert
		throw new UnsupportedOperationException();
	}

	public AlertUser(ADMIN_UI_Controller adminUiController){
		this.adminUiController = adminUiController;
	}

	public void setDestination(String destination){
		this.destination = destination;
	}

	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}

	public void showAlert(Window currentGui){
		if(!messageSeen) {
			if (destination != null) {
				ButtonType goToDest = new ButtonType("Go to " + destination, ButtonBar.ButtonData.OK_DONE);
				ButtonType close = new ButtonType("close", ButtonBar.ButtonData.CANCEL_CLOSE);
				Alert alert = new Alert(Alert.AlertType.WARNING, alertMessage, goToDest, close);
				Optional<ButtonType> result = alert.showAndWait();

				if (result.isPresent() && result.get() == goToDest) {
					adminUiController.showScreen(currentGui, destination);
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.WARNING, alertMessage, ButtonType.CLOSE);
				alert.show();
			}
			setMessageSeen();
		}
	}

	public boolean getMessageSeen() {
		return messageSeen;
	}

	public static void showDBError(){
		Alert alert = new Alert(Alert.AlertType.ERROR, "Error accessing the database", ButtonType.CLOSE);
		alert.show();
	}

	/**
	 * sets message seen to true for this session
	 */
	public void setMessageSeen() {
		this.messageSeen = true;
	}

}