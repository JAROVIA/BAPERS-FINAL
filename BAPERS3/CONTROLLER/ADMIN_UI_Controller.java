package CONTROLLER;

import ADMIN.*;
import GUI.*;
import PROCESS.Job;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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

	private Map<String, Window> screens;

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

	public boolean login(String username, String password) throws SQLException {
		boolean loggedin = false;
		for(String[] userData : UserAccount.getUserList()){
			if(username.equals(userData[1]) && password.equals(userData[3])){
				loggedInUser = new UserAccount(Integer.parseInt(userData[0]), userData[1], userData[2], userData[3], userData[4]);
				loggedin = true;

				if(loggedInUser.getUserRole().equals("Office Manager")){
					AlertUser jobAlert = new AlertUser(this);
					if(Job.AreLateJobs()) {
						String message = "Alert\n" + "There are " + Job.GetLateJobList().size() + " late jobs.";

						jobAlert.setAlertMessage(message);
						jobAlert.setDestination("Jobs");

						loggedInUser.setAlert(jobAlert);
						loggedInUser.setAlert(jobAlert);
					}
					// if payment is late
				}
				if(loggedInUser.getUserRole().equals("Shift Manager")){
					AlertUser jobAlert = new AlertUser(this);
					if(Job.AreLateJobs()) {
						String message = "Alert\n" + "There are " + Job.GetLateJobList().size() + " late jobs.";

						jobAlert.setAlertMessage(message);
						jobAlert.setDestination("Jobs");

						loggedInUser.setAlert(jobAlert);
					}
				}
				break;
			}
		}
		return loggedin;
	}

	public void logout() {
		this.loggedInUser = null;
	}

	/**
	 * 
	 * @param userRole role of user
	 * @param employeeName name of user
	 * @param password password of user
	 * @param username username of user
	 *
	 */
	public void saveUser(String userRole, String employeeName, String password, String username) throws SQLException {
		UserAccount.saveUser(username, userRole, password, employeeName);
	}

	/**
	 * 
	 * @param Username
	 */
	public UserAccount retrieveUser(String Username) {
		// TODO - implement ADMIN_UI_Controller.RetrieveUser
		throw new UnsupportedOperationException();
	}

	public boolean deleteUser() {
		// TODO - implement ADMIN_UI_Controller.DeleteUser
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param AutoTime
	 */
	public boolean setDBAutoBackupTime(Date AutoTime) {
		// TODO - implement ADMIN_UI_Controller.SetDBAutoBackupTime
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param AutoTime
	 */
	public boolean setDBAutoRestoreTime(Date AutoTime) {
		// TODO - implement ADMIN_UI_Controller.SetDBAutoRestoreTime
		throw new UnsupportedOperationException();
	}

	public void showScreen(String name){
		main.showScreen(name);
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

	public Window getScreen(String name){
		return screens.get(name);
	}

	public ADMIN_UI_Controller(Main main) throws IOException {
		//get main class
		this.main = main;

		//map to match name to window to gain easier access
		screens = new HashMap<>();

		//the method in main initialises gui classes with fxml, the returned screen is assigned to the class variables
		loginScreen = (Login) Window.newGuiFromFxml(loginFxml);
		screens.put(loginFxml, loginScreen);

		userAccountScreen = (UserAccountScreen) Window.newGuiFromFxml(userAccFxml);
		screens.put(userAccFxml, userAccountScreen);

		registerNewUserScreen = (RegisterNewUserScreen) Window.newGuiFromFxml(registerUserFxml);
		screens.put(registerUserFxml, registerNewUserScreen);

		editUserScreen = (EditUserScreen) Window.newGuiFromFxml(editUserFxml);
		screens.put(editUserFxml, editUserScreen);

		databaseScreen = (DatabaseScreen) Window.newGuiFromFxml(databaseFxml);
		screens.put(databaseFxml, databaseScreen);

		for(Map.Entry<String, Window> entry : screens.entrySet()){
			main.addScreen(entry.getKey(), entry.getValue().getParent(), "ADMIN");
			entry.getValue().setAdminUiController(this);
		}
	}
}