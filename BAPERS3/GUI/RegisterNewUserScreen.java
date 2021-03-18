package GUI;

import ADMIN.UserAccount;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterNewUserScreen extends Window{

	@FXML
	private TextField staffNameField;

	@FXML
	private TextField userNameField;

	@FXML
	private PasswordField pwField;

	@FXML
	private ComboBox roleBox;

	/**
	 * 
	 * @param
	 */
	public void SaveUser() throws SQLException {
		// TODO - implement RegisterNewUserScreen.SaveUser
		String a = staffNameField.getText();
		String b = userNameField.getText();
		String c = pwField.getText();
		String d = (String) roleBox.getValue();

		adminUiController.saveUser(a,b,c,d);
	}

	@FXML
	public void initialize() {
		super.initialize();
		roleBox.getItems().removeAll(roleBox.getItems());
		roleBox.getItems().addAll("Office Manager", "Shift Manager", "Receptionist", "Technician");
		roleBox.setPromptText("Select role");
	}

	@Override
	public void onShow(){
		staffNameField.setText("");
		userNameField.setText("");
		pwField.setText("");
		roleBox.getSelectionModel().clearSelection();
		roleBox.setPromptText("Select role");
	}
}