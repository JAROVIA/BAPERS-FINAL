package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RecordPaymentScreen extends Window {

	@FXML
	private TextField priceField;
	@FXML
	private TextField cardNumberField;
	@FXML
	private TextField cardNameField;
	@FXML
	private ComboBox<String> paymentBox;
	@FXML
	private Button cancelButton;
	@FXML
	private Button confirmButton;

	public void savePayment() {
		// TODO - implement RecordPaymentScreen.SavePayment
		throw new UnsupportedOperationException();
	}

	public void cancelRecord() {
		// TODO - implement RecordPaymentScreen.CancelRecord
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