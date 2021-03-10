package GUI;

import ADMIN.*;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UserAccountScreen extends Window {

	/*
	private Button RegisterNewUserButton;
	private Button LogoutButton;
	private Button EditUserButton;
	private Button DeleteUserButton;

	 */
	private TextField SearchUserTextArea;
	private TableView UserAccountTable;

	/**
	 * 
	 * @param UserAccountDetails
	 */
	public UserAccount[] SearchUser(String UserAccountDetails) {
		// TODO - implement UserAccountScreen.SearchUser
		throw new UnsupportedOperationException();
	}

	public void OnClickButton() {
		// TODO - implement UserAccountScreen.OnClickButton
		throw new UnsupportedOperationException();
	}

	public void DeleteUserAcc() {
		// TODO - implement UserAccountScreen.DeleteUserAcc
		throw new UnsupportedOperationException();
	}

	public String RetrieveTextArea() {
		// TODO - implement UserAccountScreen.RetrieveTextArea
		throw new UnsupportedOperationException();
	}

	public void toCreateUser(){
		adminUiController.getMain().showScreen(adminUiController.getRegisterNewUserScreen().getParent());
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param xSize
	 * @param ySize
	 * @param icon
	 */
	public static UserAccountScreen LogOut(int x, int y, int xSize, int ySize, String icon) {
		// TODO - implement UserAccountScreen.LogOut
		throw new UnsupportedOperationException();
	}

}