package GUI;

import javafx.scene.control.TextField;

public class Login extends Window {

	//private Button MyButton;
	private TextField UsernameTextArea;
	private TextField PasswordTextArea;

	public void Login() {
		// TODO - implement l.Login
		throw new UnsupportedOperationException();
	}

	public void OnClickButton() {
		// TODO - implement l.OnClickButton
		throw new UnsupportedOperationException();
	}

	public String RetrieveTextArea() {
		// TODO - implement l.RetrieveTextArea
		throw new UnsupportedOperationException();
	}

	public void onClickLoginButton(){
		//adminUiController.getMain().showScreen("home");
		adminUiController.getMain().showScreen(adminUiController.getMain().getUiController().getHomeScreen().getParent());
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