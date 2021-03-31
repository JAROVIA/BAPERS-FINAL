package GUI;

import ADMIN.AlertUser;
import PROCESS.TaskDescription;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AddNewTaskScreen extends Window {

	@FXML
	private TextField descriptionField;
	@FXML
	private TextField locationField;
	@FXML
	private TextField durationField;
	@FXML
	private TextField priceField;
	@FXML
	private Button cancelButton;
	@FXML
	private Button confirmButton;

	public void onConfirm() throws SQLException {
		//save task
		/*
		String description = descriptionField.getText();
		String location = locationField.getText();

		boolean isdurationInt = isStringInt(durationField.getText());
		boolean isPriceInt = isStringInt(priceField.getText());
		boolean isDescriptionEmpty = description == null || description.trim().isEmpty() || description.equals("");
		boolean isLocationEmpty = location == null || location.trim().isEmpty() || location.equals("");

		if(isdurationInt && isPriceInt && !isDescriptionEmpty && !isLocationEmpty){
			int duration = Integer.parseInt(durationField.getText());
			int price = Integer.parseInt(priceField.getText());

			TaskDescription.NewTask(location, price, description, duration);

			showScreen(this, "Tasks");
		}
		else{
			String message = "The following fields have incorrect format.";
			if(isDescriptionEmpty){
				message += "\nDescription is empty";
			}
			if(isLocationEmpty){
				message += "\nLocation is empty";
			}
			if(!isPriceInt){
				message += "\nprice";
			}
			if(!isdurationInt){
				message += "\nduration";
			}
			Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.CLOSE);
			alert.show();
		}

		 */

		if(isValueNotEmpty(descriptionField, durationField, locationField, priceField)){
			if(!matchAnyNumberType(durationField.getText()) || !matchAnyNumberType(priceField.getText())) {
				String message = "Incorrect input in : \n";
				if (!matchAnyNumberType(durationField.getText())) {
					message += "duration\n";
				}
				if (!matchAnyNumberType(priceField.getText())) {
					message += "price\n";
				}
				new Alert(Alert.AlertType.ERROR, message, ButtonType.CLOSE).show();
			}
			else {
				TaskDescription.NewTask(
						locationField.getText(),
						Integer.parseInt(priceField.getText()),
						descriptionField.getText(),
						Integer.parseInt(durationField.getText())
				);
				AlertUser.showCompletion("Adding new task");
				showScreen(this, "Tasks");
			}
		}
	}

	public void onCancel() {
		super.showScreen(this, "Tasks");
	}

	public void onShow(){
		super.onShow();
		descriptionField.clear();
		durationField.clear();
		priceField.clear();
		locationField.clear();
	}

	/**
	 *
	 */
	@FXML
	public void initialize(){
		super.initialize();
		userAllowed = new String[]{ROLE_OFFICE_MANAGER};

		addFloatNumberListener(priceField);
		addIntegerNumberListener(durationField);

		cancelButton.setOnAction(actionEvent -> onCancel());
		confirmButton.setOnAction(actionEvent -> {
			try {
				onConfirm();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		});
	}
}