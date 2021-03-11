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

public class RegisterNewUserScreen extends Window implements Initializable {

	@FXML
	private TextField staffNameField;

	@FXML
	private TextField userNameField;

	@FXML
	private PasswordField pwField;

	@FXML
	private ComboBox roleBox;

	public void OnClick() {
		// TODO - implement RegisterNewUserScreen.OnClick
		throw new UnsupportedOperationException();
	}

	public String RetrieveTextArea() {
		// TODO - implement RegisterNewUserScreen.RetrieveTextArea
		throw new UnsupportedOperationException();
	}

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

		//userAccount(a,b,c,d);
		new UserAccount(a, b, c, d);
	}

	public void GoHome() {
		adminUiController.getMain().showScreen("HomeScreen");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		roleBox.getItems().removeAll(roleBox.getItems());
		roleBox.getItems().addAll("Office Manager", "Shift Manager", "Recptionist", "Technician");
		roleBox.getSelectionModel().select("Select role");
	}
}