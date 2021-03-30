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
		if(isValueNotEmpty(new TextField[]{staffNameField, passwordField, usernameField}, new ComboBox[]{roleBox})) {
			saveUser(editingUser);
		}
		showScreen(this, "UserAccounts");
	}

	private void onCancel(){
		showScreen(this, "UserAccounts");
	}

	@Override
	public void onLeave(){
		adminUiController.setEditingUser(null);
		usernameField.clear();
		passwordField.clear();
		staffNameField.clear();
		roleBox.getSelectionModel().clearSelection();
	}

	public void onShow(){
		super.onShow();

		UserAccount editingUser = adminUiController.getEditingUser();
		staffNameField.setText(editingUser.getEmployeeName());
		passwordField.setText(editingUser.getPassword());
		usernameField.setText(editingUser.getUsername());
		roleBox.setValue(editingUser.getUserRole());
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
		setComboBoxPromptText(roleBox, "Select role");
	}

}