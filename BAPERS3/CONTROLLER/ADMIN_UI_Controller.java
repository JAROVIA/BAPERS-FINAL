package CONTROLLER;

import ADMIN.*;
import GUI.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class ADMIN_UI_Controller {

	private Main main;

	//gui handled by this controller
	private Login loginScreen;
	private String loginFxml = "Login";

	private UserAccountScreen userAccountScreen;
	private String userAccFxml = "UserAccounts";

	private EditUserScreen editUserScreen;
	private String editUserFxml = "EditUserDetails";

	private RegisterNewUserScreen registerNewUserScreen;
	private String registerUserFxml = "RegisterNewUser";

	private DatabaseScreen databaseScreen;
	private String databaseFxml = "Database";

	private UserAccount loggedInUser = null;
	private UserAccount editingUser = null;

	/**
	 * 
	 * @param Username
	 */
	public UserAccount searchUser(String Username) {
		// TODO - implement ADMIN_UI_Controller.SearchUser
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param role
	 * @param name
	 * @param password
	 * @param username
	 *
	 */
	public boolean saveUser(String role, String name, String password, String username) throws SQLException {
		new UserAccount(role, username, password, name);
		// TODO - return appropriate boolean
		return true;
	}

	/**
	 * 
	 * @param Username
	 */
	public UserAccount RetrieveUser(String Username) {
		// TODO - implement ADMIN_UI_Controller.RetrieveUser
		throw new UnsupportedOperationException();
	}

	public boolean DeleteUser() {
		// TODO - implement ADMIN_UI_Controller.DeleteUser
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param AutoTime
	 */
	public boolean SetDBAutoBackupTime(Date AutoTime) {
		// TODO - implement ADMIN_UI_Controller.SetDBAutoBackupTime
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param AutoTime
	 */
	public boolean SetDBAutoRestoreTime(Date AutoTime) {
		// TODO - implement ADMIN_UI_Controller.SetDBAutoRestoreTime
		throw new UnsupportedOperationException();
	}

	public static ADMIN_UI_Controller ADMIN_UI_Controller() {
		// TODO - implement ADMIN_UI_Controller.ADMIN_UI_Controller
		throw new UnsupportedOperationException();
	}

	public Login getLoginScreen() {
		return loginScreen;
	}

	public DatabaseScreen getDatabaseScreen() {
		return databaseScreen;
	}

	public UserAccountScreen getUserAccountScreen() {
		return userAccountScreen;
	}

	public EditUserScreen editUserScreen() {
		return editUserScreen;
	}

	public RegisterNewUserScreen getRegisterNewUserScreen() {
		return registerNewUserScreen;
	}

	public Main getMain() {
		return main;
	}

	public UserAccount getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(UserAccount loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public UserAccount getEditingUser() {
		return editingUser;
	}

	public void setEditingUser(UserAccount editingUser) {
		this.editingUser = editingUser;
	}

	public ADMIN_UI_Controller(Main main) throws IOException {
		//get main class
		this.main = main;

		//the method in main initialises gui classes with fxml, the returned screen is assigned to the class variables
		loginScreen = (Login) Window.newGuiFromFxml(loginFxml);
		main.addScreen(loginFxml, loginScreen.getParent());
		loginScreen.setAdminUiController(this);

		userAccountScreen = (UserAccountScreen) Window.newGuiFromFxml(userAccFxml);
		main.addScreen(userAccFxml, userAccountScreen.getParent());
		userAccountScreen.setAdminUiController(this);

		registerNewUserScreen = (RegisterNewUserScreen) Window.newGuiFromFxml(registerUserFxml);
		main.addScreen(registerUserFxml, registerNewUserScreen.getParent());
		registerNewUserScreen.setAdminUiController(this);

		editUserScreen = (EditUserScreen) Window.newGuiFromFxml(editUserFxml);
		main.addScreen(editUserFxml, editUserScreen.getParent());
		editUserScreen.setAdminUiController(this);

		databaseScreen = (DatabaseScreen) Window.newGuiFromFxml(databaseFxml);
		main.addScreen(databaseFxml, databaseScreen.getParent());
		databaseScreen.setAdminUiController(this);
	}
}