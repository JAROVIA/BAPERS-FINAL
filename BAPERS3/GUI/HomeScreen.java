package GUI;

import ADMIN.AlertUser;
import PROCESS.Job;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
		uiController.showScreen(this, "UserAccounts");
	}

	private void toCustomerAccounts(){
		//goes to customer account page if anyone but technician
		uiController.showScreen(this, "CustomerAccounts");
	}

	private void toJob(){
		//goes to jobs page
		uiController.showScreen(this, "Jobs");
	}

	private void toDatabase(){
		//goes to database page
		uiController.showScreen(this, "Database");
	}

	private void toReport(){
		//goes to reports page
		uiController.showScreen(this, "Reports");
	}

	private void toTask(){
		//goes to task page
		uiController.showScreen(this, "Tasks");
	}

	public void onShow(){
		super.onShow();

		if(uiController.getLoggedInUser().getAlert() != null && uiController.getLoggedInUser().getAlert().size() > 0){
			for(AlertUser alert : uiController.getLoggedInUser().getAlert()){
				System.out.println("showing alert");
				alert.showAlert(this);
			}
		}
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