package GUI;

import ADMIN.UserAccount;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class EditUserScreen extends Window {

	@FXML
	private TextField staffNameField;
	@FXML
	private TextField usernameField;
	@FXML
	private TextField passwordField;
	@FXML
	private ComboBox<String> roleBox;
	@FXML
	private Button saveButton;
	@FXML
	private Button cancelButton;

	public void saveUser(UserAccount editingUser) throws SQLException {
		UserAccount.editUser(editingUser.getEmployeeID(), editingUser.getUsername(), editingUser.getUserRole(), editingUser.getPassword(), editingUser.getEmployeeName());
	}

	private void onSave() throws SQLException {
		UserAccount editingUser = adminUiController.getEditingUser();
		if(roleBox.getValue() != null) {
			editingUser.setUserRole(roleBox.getValue());
		}
		if(usernameField.getText() != null && !usernameField.getText().equals("")) {
			editingUser.setUsername(usernameField.getText());
		}
		if(passwordField.getText() != null && !passwordField.getText().equals("")) {
			editingUser.setPassword(passwordField.getText());
		}
		if(staffNameField.getText() != null && !staffNameField.getText().equals("")) {
			editingUser.setEmployeeName(staffNameField.getText());
		}
		saveUser(editingUser);
		adminUiController.showScreen("UserAccounts");
	}

	private void onCancel(){
		adminUiController.setEditingUser(null);
		adminUiController.showScreen("UserAccounts");
	}

	public void onShow(){
		super.onShow();
		staffNameField.setText("");
		passwordField.setText("");
		usernameField.setText("");
		roleBox.getSelectionModel().clearSelection();
		roleBox.setPromptText("Select role");
	}

	/**
	 *
	 */
	@FXML
	public void initialize(){
		super.initialize();
		userAllowed = new String[]{ROLE_OFFICE_MANAGER};
		saveButton.setOnAction(actionEvent -> {
			try {
				onSave();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		});
		cancelButton.setOnAction(actionEvent -> onCancel());
		roleBox.getItems().setAll(ROLE_OFFICE_MANAGER, ROLE_SHIFT_MANAGER, ROLE_RECEPTIONIST, ROLE_TECHNICIAN_COPY, ROLE_TECHNICIAN_DEV, ROLE_TECHNICIAN_PACK, ROLE_TECHNICIAN_FIN);
	}

}