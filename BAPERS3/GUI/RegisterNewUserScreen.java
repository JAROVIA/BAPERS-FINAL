package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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
		String employeeName = staffNameField.getText();
		String userName = userNameField.getText();
		String password = pwField.getText();
		String userRole = roleBox.getValue();

		if(isValueNotEmpty(new TextField[]{staffNameField, userNameField, pwField}, new ComboBox[]{roleBox})){
			adminUiController.saveUser(userRole, employeeName, password, userName);
			showScreen(this, "UserAccounts");
		}
	}

	private void onSubmit() throws SQLException {
		saveUser();
	}

	public void onLeave(){
		staffNameField.clear();
		userNameField.clear();
		pwField.clear();
		roleBox.getSelectionModel().clearSelection();
	}

	private void onCancel(){
		showScreen(this, "UserAccounts");
	}

	@FXML
	public void initialize() {
		super.initialize();
		userAllowed = new String[]{ROLE_OFFICE_MANAGER};
		roleBox.getItems().removeAll(roleBox.getItems());
		roleBox.getItems().addAll(ROLE_OFFICE_MANAGER, ROLE_SHIFT_MANAGER, ROLE_RECEPTIONIST, ROLE_TECHNICIAN_COPY, ROLE_TECHNICIAN_DEV, ROLE_TECHNICIAN_PACK ,ROLE_TECHNICIAN_FIN);
		roleBox.setPromptText("Select role");
		setComboBoxPromptText(roleBox, "Select role");
		addNameListener(staffNameField);
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
	}
}