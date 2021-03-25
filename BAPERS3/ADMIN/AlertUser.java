package ADMIN;

import CONTROLLER.ADMIN_UI_Controller;
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

	public void showAlert(){
		if(!messageSeen) {
			if (destination != null) {
				ButtonType goToDest = new ButtonType("Go to " + destination, ButtonBar.ButtonData.OK_DONE);
				ButtonType close = new ButtonType("close", ButtonBar.ButtonData.CANCEL_CLOSE);
				Alert alert = new Alert(Alert.AlertType.WARNING, alertMessage, goToDest, close);
				Optional<ButtonType> result = alert.showAndWait();

				if (result.isPresent() && result.get() == goToDest) {
					adminUiController.showScreen(destination);
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.WARNING, alertMessage, ButtonType.CLOSE);
				alert.show();
			}
			setMessageSeen();
		}
	}

	public boolean getMessageSeen() {
		// TODO - implement Alert.getMessageSeen
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param MessageSeen
	 */
	public void setMessageSeen() {
		this.messageSeen = true;
	}

}