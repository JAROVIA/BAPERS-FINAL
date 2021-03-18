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

	public void OnClickButton() {
		// TODO - implement l.OnClickButton
		throw new UnsupportedOperationException();
	}

	public String RetrieveTextArea() {
		// TODO - implement l.RetrieveTextArea
		throw new UnsupportedOperationException();
	}

	public void onClickLoginButton() throws SQLException {
		if(login(usernameField.getText(), passwordField.getText())){
			adminUiController.getMain().showScreen("HomeScreen");
		}
		System.out.println("login");
		//adminUiController.getMain().showScreen("UserAccounts");
	}

	private boolean login(String username, String password) throws SQLException {
		boolean isLogin = false;
		for(String[] dbUser : UserAccount.GetUserList()){
			if(username.equals(dbUser[1])){
				adminUiController.setLoggedInUser(new UserAccount(dbUser[2], dbUser[1], "", dbUser[3]));
				System.out.println("ID : " + dbUser[0] + " username : " + adminUiController.getLoggedInUser().getUsername() + "role : " + adminUiController.getLoggedInUser().getUserRole());
				isLogin = true;
			}
		}
		return isLogin;
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