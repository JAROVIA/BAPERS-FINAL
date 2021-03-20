package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegisterNewCustomerScreen extends Window {

	@FXML
	private TextField usernameField;
	@FXML
	private TextField memorableWordField;
	@FXML
	private Button submitButton;
	@FXML
	private TextField phoneField;
	@FXML
	private TextField address1Field;
	@FXML
	private TextField address2Field;
	@FXML
	private TextField postcodeField;

	/**
	 * 
	 * @param CustomerData
	 */
	public boolean submit(String[] CustomerData) {
		// TODO - implement RegisterNewCustomerScreen.Submit
		throw new UnsupportedOperationException();
	}

	public void onShow(){
		super.onShow();
	}

	/**
	 *
	 */
	@FXML
	public void initialize(){
		super.initialize();
		userAllowed = new String[]{ROLE_OFFICE_MANAGER, ROLE_SHIFT_MANAGER, ROLE_RECEPTIONIST};
	}
}