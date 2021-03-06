package GUI;

import ADMIN.AlertUser;
import PAYMENT.Payment;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class RecordPaymentScreen extends Window {

	@FXML
	private TextField priceField;
	@FXML
	private TextField cardTypeField;
	@FXML
	private TextField cardNumberField;
	@FXML
	private TextField cardHolderNameField;
	@FXML
	private TextField monthField;
	@FXML
	private TextField yearField;
	@FXML
	private TextField cvcField;
	@FXML
	private ComboBox<String> paymentMethodBox;
	@FXML
	private Button cancelButton;
	@FXML
	private Button saveButton;

	private String[] jobData;

	private final String PAYMENT_TYPE_CARD = "Card";
	private final String PAYMENT_TYPE_CASH = "Cash";

	public void savePayment() {
		if (paymentMethodBox.getValue() != null) {
			if (paymentMethodBox.getValue().equals(PAYMENT_TYPE_CARD)) {
				if (isValueNotEmpty(cardHolderNameField, cardNumberField, priceField, monthField, yearField, cardTypeField)) {
					if (isStringFloat(priceField.getText())) {

						String[] paymentData = new String[]{
								jobData[0],
								jobData[1],
								priceField.getText().trim(),
								"date",
								paymentMethodBox.getValue().trim(),
								monthField.getText().trim() + yearField.getText().trim(),
								cardHolderNameField.getText().trim(),
								cardTypeField.getText().trim(),
								cardNumberField.getText().trim(),
								cvcField.getText().trim()
						};
						uiController.saveCardPayment(paymentData);
						AlertUser.showCompletion("Payment");
						showScreen(this, "Jobs");
					} else {
						Alert alert = new Alert(Alert.AlertType.ERROR, "Price format is incorrect, check if value is appropriate", ButtonType.CLOSE);
						alert.show();
					}
				}
			}
			if (paymentMethodBox.getValue().equals(PAYMENT_TYPE_CASH)) {
				if (isValueNotEmpty(priceField)) {
					if (isStringFloat(priceField.getText())) {

						String[] paymentData = new String[]{
								jobData[0],
								jobData[1],
								priceField.getText().trim(),
								"date",
								paymentMethodBox.getValue().trim()
						};
						uiController.saveCashPayment(paymentData);
						AlertUser.showCompletion("Payment");
						showScreen(this, "Jobs");
					} else {
						Alert alert = new Alert(Alert.AlertType.ERROR, "Price format is incorrect, check if value is appropriate", ButtonType.CLOSE);
						alert.show();
					}
				}
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Select payment type", ButtonType.CLOSE);
			alert.show();
		}
	}

	public void onLeave(){
		priceField.clear();
		cardNumberField.clear();
		cardHolderNameField.clear();
		cardTypeField.clear();
		cvcField.clear();
		monthField.clear();
		yearField.clear();

		jobData = null;
	}

	public void onCancel() {
		showScreen(this, "Jobs");
	}

	public void onShow(){
		super.onShow();
		try {
			uiController.calculateFinalPrice(Integer.parseInt(jobData[1]), Integer.parseInt(jobData[0]));
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			AlertUser.showDBError();
		}
	}

	public void setJobData(String[] jobData){
		this.jobData = jobData;
	}

	/**
	 *
	 */
	@FXML
	public void initialize(){
		super.initialize();
		userAllowed = new String[]{ROLE_OFFICE_MANAGER, ROLE_SHIFT_MANAGER, ROLE_RECEPTIONIST};
		cancelButton.setOnAction(actionEvent -> onCancel());
		saveButton.setOnAction(actionEvent -> savePayment());
		addNameListener(cardHolderNameField);
		addPriceNumberListener(priceField);
		addIntegerNumberListener(cvcField, 3);
		addIntegerNumberListener(cardNumberField, 4);
		addIntegerNumberListener(monthField, 2);
		addIntegerNumberListener(yearField, 2);

		paymentMethodBox.setPromptText("Select payment method");
		setComboBoxPromptText(paymentMethodBox, "Select payment method");
		paymentMethodBox.getItems().addAll(PAYMENT_TYPE_CASH, PAYMENT_TYPE_CARD);
	}
}