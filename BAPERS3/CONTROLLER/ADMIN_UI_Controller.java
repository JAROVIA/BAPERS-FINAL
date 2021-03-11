package CONTROLLER;

import ADMIN.*;
import GUI.*;

import java.io.IOException;
import java.sql.Date;

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

	/**
	 * 
	 * @param Username
	 */
	public UserAccount SearchUser(String Username) {
		// TODO - implement ADMIN_UI_Controller.SearchUser
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param UserDetails
	 */
	public boolean SaveUser(String[] UserDetails) {
		// TODO - implement ADMIN_UI_Controller.SaveUser
		throw new UnsupportedOperationException();
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

	private void guiSetup(Window gui, String fxml){
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