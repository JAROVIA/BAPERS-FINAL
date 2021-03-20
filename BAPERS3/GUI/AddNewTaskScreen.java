package GUI;

import PROCESS.TaskDescription;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
		String description = descriptionField.getText();
		String location = locationField.getText();
		String duration = durationField.getText();
		String price = priceField.getText();

		TaskDescription.NewTask(location,Integer.parseInt(price),description,Integer.parseInt(duration));

		procUiController.showScreen("Tasks");
	}

	public void onCancel() {
		procUiController.showScreen("Tasks");
	}

	public void onShow(){
		super.onShow();
		descriptionField.setText("");
		durationField.setText("");
		priceField.setText("");
		locationField.setText("");
	}

	/**
	 *
	 */
	@FXML
	public void initialize(){
		super.initialize();
		userAllowed = new String[]{ROLE_OFFICE_MANAGER};

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