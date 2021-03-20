package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ReportsScreen extends Window {

	@FXML
	private Button generateCustomerReportButton;
	@FXML
	private ComboBox<String> reportGenerationBox;
	@FXML
	private Button generateStaffReportButton;
	@FXML
	private Button generateSummaryReportButton;
	@FXML
	private TextField accountNumberField;
	@FXML
	private TextField dateField;
	@FXML
	private TextField shiftField;

	public void generateReport() {
		// TODO - implement ReportsScreen.GenerateReport
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
		userAllowed = new String[]{ROLE_OFFICE_MANAGER, ROLE_SHIFT_MANAGER};

	}
}