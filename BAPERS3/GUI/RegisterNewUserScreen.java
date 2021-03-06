package GUI;

import ADMIN.AlertUser;
import ADMIN.UserAccount;
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
		String employeeName = staffNameField.getText().trim();
		String userName = userNameField.getText().trim();
		String password = pwField.getText().trim();
		String userRole = roleBox.getValue().trim();

		if(isValueNotEmpty(new TextField[]{staffNameField, userNameField, pwField}, new ComboBox[]{roleBox})){
			if(!UserAccount.checkUserExists(userName)) {
				adminUiController.saveUser(userRole, employeeName, password, userName);
				AlertUser.showCompletion("User data submit");
				showScreen(this, "UserAccounts");
			}
			else{
				new Alert(Alert.AlertType.ERROR, "User with same username exists", ButtonType.CLOSE).show();
			}
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
		addPasswordListener(pwField);
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