package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.SQLException;

public class RegisterNewUserScreen extends Window{

	@FXML
	private TextField staffNameField;

	@FXML
	private TextField userNameField;

	@FXML
	private PasswordField pwField;

	@FXML
	private ComboBox<String> roleBox;

	@FXML
	private Button submitButton;

	@FXML
	private Button cancelButton;

	/**
	 * 
	 * @param
	 */
	private void saveUser() throws SQLException {
		// TODO - implement RegisterNewUserScreen.SaveUser
		String employeeName = staffNameField.getText();
		String userName = userNameField.getText();
		String password = pwField.getText();
		String userRole = roleBox.getValue();

		adminUiController.saveUser(userRole, employeeName, password, userName);
	}

	private void onSubmit() throws SQLException {
		saveUser();
		adminUiController.showScreen("UserAccounts");
	}

	private void onCancel(){
		adminUiController.showScreen("UserAccounts");
	}

	@FXML
	public void initialize() {
		super.initialize();
		userAllowed = new String[]{ROLE_OFFICE_MANAGER};
		roleBox.getItems().removeAll(roleBox.getItems());
		roleBox.getItems().addAll(ROLE_OFFICE_MANAGER, ROLE_SHIFT_MANAGER, ROLE_RECEPTIONIST, ROLE_TECHNICIAN_COPY, ROLE_TECHNICIAN_DEV, ROLE_TECHNICIAN_PACK ,ROLE_TECHNICIAN_FIN);
		roleBox.setPromptText("Select role");
		cancelButton.setOnAction(actionEvent -> onCancel());
		submitButton.setOnAction(actionEvent -> {
			try {
				onSubmit();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		});
	}

	@Override
	public void onShow(){
		super.onShow();
		staffNameField.setText("");
		userNameField.setText("");
		pwField.setText("");
		roleBox.getSelectionModel().clearSelection();
		roleBox.setPromptText("Select role");
	}
}