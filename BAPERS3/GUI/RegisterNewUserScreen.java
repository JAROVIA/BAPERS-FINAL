package GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
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
	public void SaveUser() {
		// TODO - implement RegisterNewUserScreen.SaveUser
		String a = staffNameField.getText();
		String b = userNameField.getText();
		String c = pwField.getText();
		String d = (String) roleBox.getValue();

		//userAccount(a,b,c,d);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		roleBox.getItems().removeAll(roleBox.getItems());
		roleBox.getItems().addAll("Option A", "Option B", "Option C");
		roleBox.getSelectionModel().select("Option B");
	}
}