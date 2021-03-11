package GUI;

import java.sql.SQLException;

public class HomeScreen extends Window {

	private int CreateUserButton;
	//private Button LogoutButton;
	//private Menu MyMenu;

	public void OnClickButton() {
		// TODO - implement HomeScreen.OnClickButton
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param MenuOption
	 */
	public void OnSelectMenuOption(int MenuOption) {
		// TODO - implement HomeScreen.OnSelectMenuOption
		throw new UnsupportedOperationException();
	}

	public void toUserAccounts() throws SQLException {
		uiController.getMain().showScreen("UserAccounts");
		uiController.getMain().getAdminUiController().getUserAccountScreen().showContent();
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
	 * @param MyMenu
	 */
	/*
	public static HomeScreen HomeScreen(int x, int y, int xSize, int ySize, string icon, string Text, Button Button, Menu MyMenu) {
		// TODO - implement HomeScreen.HomeScreen
		throw new UnsupportedOperationException();
	}

	 */

}