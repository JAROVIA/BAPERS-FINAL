package GUI;

import ADMIN.AlertUser;
import REPORT.CustomerReport;
import REPORT.StaffReport;
import REPORT.SummaryReport;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Arrays;

public class ReportsScreen extends Window {

	@FXML
	private Button customerReportButton;
	@FXML
	private Button staffReportButton;
	@FXML
	private Button summaryReportButton;
	@FXML
	private TextField accountNumberField;
	@FXML
	private TextField staffGenerateMinutesField;
	@FXML
	private TextField customerGenerateMinutesField;
	@FXML
	private TextField summaryGenerateMinutesField;
	@FXML
	private Button confirmAutoCustomerButton;
	@FXML
	private Button confirmAutoStaffButton;
	@FXML
	private Button confirmAutoSummaryButton;
	//customer
	@FXML
	private ComboBox<String> customerWeekBox;
	@FXML
	private ComboBox<String> customerDayBox;
	@FXML
	private ComboBox<String> customerMonthBox;
	@FXML
	private ComboBox<String> customerYearBox;
	//staff
	@FXML
	private ComboBox<String> staffWeekBox;
	@FXML
	private ComboBox<String> staffDayBox;
	@FXML
	private ComboBox<String> staffMonthBox;
	@FXML
	private ComboBox<String> staffYearBox;
	//summery
	@FXML
	private ComboBox<String> summaryWeekBox;
	@FXML
	private ComboBox<String> summaryDayBox;
	@FXML
	private ComboBox<String> summaryMonthBox;
	@FXML
	private ComboBox<String> summaryYearBox;

	private static final String[] daysOfWeek = new String[]{
			"Mon",
			"Tue",
			"Wed",
			"Thu",
			"Fri",
			"Sat",
			"Sun"
	};
	private static final String[] days = new String[31];
	private static final String[] months = new String[]{
			"Jan",
			"Feb",
			"Mar",
			"Apr",
			"May",
			"Jun",
			"Jul",
			"Aug",
			"Sep",
			"Oct",
			"Nov",
			"Dec"
	};
	private static final String[] years = new String[99];

	public void generateCustomerReport() {
		if(isValueNotEmpty(new TextField[]{accountNumberField}, new ComboBox[]{customerWeekBox, customerDayBox, customerMonthBox, customerYearBox})) {
			try {
				new CustomerReport().printCustomerReport(Integer.parseInt(
						accountNumberField.getText()),
						customerWeekBox.getValue() + " " +
								customerMonthBox.getValue() + " " +
								customerDayBox.getValue(),
								customerYearBox.getValue()
				);
				AlertUser.showCompletion("Customer report generation");
			} catch (Exception e) {
				AlertUser.showDBError();
				e.printStackTrace();
			}
		}else {
			new Alert(Alert.AlertType.ERROR, "Enter date", ButtonType.CLOSE).show();
		}
	}

	private void generateSummaryReport() {
		if (isValueNotEmpty(summaryWeekBox, summaryDayBox, summaryMonthBox, summaryYearBox)) {
			try {
				new SummaryReport().printSummaryReport(
						summaryWeekBox.getValue() + " " +
								summaryMonthBox.getValue() + " " +
								summaryDayBox.getValue(),
								summaryYearBox.getValue()
				);
				AlertUser.showCompletion("Summary report generation");

			} catch (Exception e) {
				AlertUser.showDBError();
				e.printStackTrace();
			}
		} else {
			new Alert(Alert.AlertType.ERROR, "Enter date", ButtonType.CLOSE).show();
		}
	}

	private void generateStaffReport() {
		if(isValueNotEmpty(staffWeekBox, staffDayBox, staffMonthBox, staffYearBox)) {
			try {
				new StaffReport().printStaffReport(
						staffWeekBox.getValue() + " " +
								staffMonthBox.getValue() + " " +
								staffDayBox.getValue(),
								staffYearBox.getValue()
				);
				AlertUser.showCompletion("Staff report generation");
			} catch (Exception e) {
				e.printStackTrace();
				AlertUser.showDBError();
			}
		}else{
			new Alert(Alert.AlertType.ERROR, "Enter date", ButtonType.CLOSE).show();
		}
	}

	private void autoGenerateStaffReport(){
		if(isValueNotEmpty(staffGenerateMinutesField)){
			StaffReport.autoGenerateReport(Integer.parseInt(staffGenerateMinutesField.getText()));
		}
	}
	private void autoGenerateCustomerReport(){
		if(isValueNotEmpty(customerGenerateMinutesField, accountNumberField)){
			CustomerReport.autoGenerateReport(Integer.parseInt(customerGenerateMinutesField.getText()), Integer.parseInt(accountNumberField.getText()));
		}
	}
	private void autoGenerateSummaryReport(){
		if(isValueNotEmpty(summaryGenerateMinutesField)){
			SummaryReport.autoGenerateReport(Integer.parseInt(summaryGenerateMinutesField.getText()));
		}
	}

	public void onLeave(){
		accountNumberField.clear();
		customerGenerateMinutesField.clear();
		staffGenerateMinutesField.clear();
		summaryGenerateMinutesField.clear();
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

		addIntegerNumberListener(accountNumberField);
		addIntegerNumberListener(staffGenerateMinutesField);
		addIntegerNumberListener(customerGenerateMinutesField);
		addIntegerNumberListener(summaryGenerateMinutesField);

		confirmAutoStaffButton.setOnAction(actionEvent -> autoGenerateStaffReport());

		for(int i = 0; i < 31; i++){
			days[i] = String.valueOf(i+1);
		}

		for(int i = 0; i < 99; i++){
			years[i] = String.valueOf((i+20)%100);
		}

		ArrayList<ComboBox[]> boxes = new ArrayList<>(Arrays.asList(
				new ComboBox[]{customerWeekBox, customerMonthBox, customerDayBox, customerYearBox},
				new ComboBox[]{summaryWeekBox, summaryMonthBox, summaryDayBox, summaryYearBox},
				new ComboBox[]{staffWeekBox, staffMonthBox, staffDayBox, staffYearBox}
		));

		for(ComboBox<String>[] box : boxes){
			setComboBoxPromptText(box[0], "Days");
			setComboBoxPromptText(box[1], "Month");
			setComboBoxPromptText(box[2], "Day");
			setComboBoxPromptText(box[3], "Year");

			box[0].setItems(FXCollections.observableList(Arrays.asList(daysOfWeek)));
			box[1].setItems(FXCollections.observableList(Arrays.asList(months)));
			box[2].setItems(FXCollections.observableList(Arrays.asList(days)));
			box[3].setItems(FXCollections.observableList(Arrays.asList(years)));
		}
	}
}