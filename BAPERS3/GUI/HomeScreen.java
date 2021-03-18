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

	private void toUserAccounts() throws SQLException {
		/*
		System.out.println("user's role is " + uiController.getMain().getAdminUiController().getLoggedInUser().getUserRole());
		 if(uiController.getMain().getAdminUiController().getLoggedInUser().getUserRole().equals("Office Manager")){
		 	//goes to user account page
			uiController.getMain().showScreen("UserAccounts");
			 uiController.getMain().getAdminUiController().getUserAccountScreen().showContent();
		}

		 */
	}

	private void toCustomerAccounts(){
		//goes to customer account page
		uiController.getMain().showScreen("CustomerAccounts");
		uiController.getMain().getAcctUiController().getCustomerAccountScreen().onShow();
	}

	private void toJob(){
		//goes to jobs page
		uiController.getMain().showScreen("Jobs");
		uiController.getMain().getProcUiController().getJobsScreen().onShow();
	}

	private void toDatabase(){
		//goes to database page
		uiController.getMain().showScreen("Database");
	}

	private void toReport(){
		//goes to reports page
		uiController.getMain().showScreen("Reports");
	}

	private void toTask(){
		//goes to task page
		uiController.getMain().showScreen("Tasks");
	}

	/**
	 */
	@Override
	public void initialize(){
		//set buttons their on action
		super.initialize();
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