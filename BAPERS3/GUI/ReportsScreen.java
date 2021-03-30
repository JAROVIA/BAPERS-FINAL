package GUI;

import REPORT.CustomerReport;
import REPORT.StaffReport;
import REPORT.SummaryReport;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ReportsScreen extends Window {

	@FXML
	private Button customerReportButton;
	@FXML
	private ComboBox<String> reportGenerationBox;
	@FXML
	private Button staffReportButton;
	@FXML
	private Button summaryReportButton;
	@FXML
	private TextField accountNumberField;
	@FXML
	private TextField dateField1;
	@FXML
	private TextField dateField2;
	@FXML
	private TextField dateField3;
	@FXML
	private TextField shiftField;

	public void generateCustomerReport() {
		if(!accountNumberField.getText().trim().isEmpty() && !dateField1.getText().trim().isEmpty()) {
			try {
				new CustomerReport().printCustomerReport(Integer.parseInt(accountNumberField.getText()), dateField1.getText());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			new Alert(Alert.AlertType.ERROR, "Enter date", ButtonType.CLOSE).show();
		}
	}

	public void generateSummaryReport() {
		if (!dateField2.getText().trim().isEmpty()) {
			try {
				new StaffReport().printStaffReport(dateField3.getText());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			new Alert(Alert.AlertType.ERROR, "Enter date", ButtonType.CLOSE).show();
		}
	}

	public void generateStaffReport() {
		if(!dateField2.getText().trim().isEmpty()) {
			try {
				new SummaryReport().printSummaryReport(dateField2.getText());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			new Alert(Alert.AlertType.ERROR, "Enter date", ButtonType.CLOSE).show();
		}
	}

	public void onLeave(){
		accountNumberField.clear();
		dateField1.clear();
		dateField2.clear();
		dateField3.clear();
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
		customerReportButton.setOnAction(actionEvent -> generateCustomerReport());
		staffReportButton.setOnAction(actionEvent -> generateStaffReport());
		summaryReportButton.setOnAction(actionEvent -> generateSummaryReport());
	}
}