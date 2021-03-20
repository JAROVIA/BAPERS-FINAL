package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EditCustomerDetailsScreen extends Window {

	@FXML
	private TextField usernameField;
	@FXML
	private TextField addressField;
	@FXML
	private ComboBox<String> accountStatusBox;
	@FXML
	private Button saveButton;
	@FXML
	private Button deleteButton;
	@FXML
	private TextField phoneField;
	@FXML
	private TextField memorableWordField;

	public void deleteCustomer() {
		// TODO - implement EditCustomerDetailsScreen.DeleteCustomer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param CustomerDetails
	 */
	public void SaveCustomer(String[] CustomerDetails) {
		// TODO - implement EditCustomerDetailsScreen.SaveCustomer
		throw new UnsupportedOperationException();
	}

	/**
	 */
	@FXML
	public void initialize(){
		super.initialize();
		userAllowed = new String[]{ROLE_OFFICE_MANAGER};
	}
}