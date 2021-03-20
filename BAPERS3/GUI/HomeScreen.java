package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.SQLException;

public class HomeScreen extends Window {

	private int CreateUserButton;
	@FXML
	private Button userAccountButton;
	@FXML
	private Button customerAccountButton;
	@FXML
	private Button databaseButton;
	@FXML
	private Button jobsButton;
	@FXML
	private Button tasksButton;
	@FXML
	private Button reportsButton;

	private void toUserAccounts() throws SQLException {
		//goes to account page, only for office manage
		uiController.showScreen("UserAccounts");
	}

	private void toCustomerAccounts(){
		//goes to customer account page if anyone but technician
		uiController.showScreen("CustomerAccounts");
	}

	private void toJob(){
		//goes to jobs page
		uiController.showScreen("Jobs");
	}

	private void toDatabase(){
		//goes to database page
		uiController.showScreen("Database");
	}

	private void toReport(){
		//goes to reports page
		uiController.showScreen("Reports");
	}

	private void toTask(){
		//goes to task page
		uiController.showScreen("Tasks");
	}

	public void onShow(){
		super.onShow();
		System.out.println("username " + uiController.getLoggedInUser().getUsername() +"'s role is " + uiController.getLoggedInUser().getUserRole());
	}

	/**
	 */
	@Override
	public void initialize(){
		//set buttons their on action
		super.initialize();
		userAllowed = new String[]{ROLE_OFFICE_MANAGER, ROLE_SHIFT_MANAGER, ROLE_RECEPTIONIST, ROLE_TECHNICIAN_COPY, ROLE_TECHNICIAN_DEV, ROLE_TECHNICIAN_FIN, ROLE_TECHNICIAN_PACK};
		userAccountButton.setOnAction(actionEvent -> {
			try {
				toUserAccounts();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		});
		customerAccountButton.setOnAction(actionEvent -> toCustomerAccounts());
		databaseButton.setOnAction(actionEvent -> toDatabase());
		jobsButton.setOnAction(actionEvent -> toJob());
		tasksButton.setOnAction(actionEvent -> toTask());
		reportsButton.setOnAction(actionEvent -> toReport());
	}

}