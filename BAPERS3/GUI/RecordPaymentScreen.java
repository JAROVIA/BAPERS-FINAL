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
								jobData[6],
								"date",
								paymentMethodBox.getValue(),
								monthField.getText() + yearField.getText(),
								cardHolderNameField.getText(),
								cardTypeField.getText(),
								cardNumberField.getText(),
								cvcField.getText()
						};
						uiController.saveCardPayment(paymentData);
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
								jobData[6],
								"date",
								paymentMethodBox.getValue()
						};
						uiController.saveCashPayment(paymentData);
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
		jobData = null;
	}

	public void onCancel() {
		showScreen(this, "Jobs");
	}

	public void onShow(){
		super.onShow();
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