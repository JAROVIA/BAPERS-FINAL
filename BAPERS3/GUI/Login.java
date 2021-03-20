package GUI;

import ADMIN.UserAccount;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class Login extends Window {

	//private Button MyButton;
	@FXML
	private TextField usernameField;
	@FXML
	private TextField passwordField;

	public void onClickLoginButton() throws SQLException {
		if(adminUiController.login(usernameField.getText(), passwordField.getText())){
			adminUiController.showScreen("HomeScreen");
		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param xSize
	 * @param ySize
	 * @param icon
	 * @param Text
	 * @param Button
	 */
	/*
	public static Login LoginScreen(int x, int y, int xSize, int ySize, string icon, string Text, Button Button) {
		// TODO - implement l.LoginScreen
		throw new UnsupportedOperationException();
	}
	 */

}