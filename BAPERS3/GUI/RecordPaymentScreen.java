package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RecordPaymentScreen extends Window {

	@FXML
	private TextField priceField;
	@FXML
	private TextField cardNumberField;
	@FXML
	private TextField cardHolderNameField;
	@FXML
	private TextField monthField;
	@FXML
	private TextField yearField;
	@FXML
	private ComboBox<String> paymentMethodBox;
	@FXML
	private Button cancelButton;
	@FXML
	private Button saveButton;

	public void savePayment() {

		if(isValueNotEmpty(new TextField[]{cardHolderNameField, cardNumberField, priceField, monthField, yearField}, new ComboBox[]{paymentMethodBox})){
			if(isStringFloat(priceField.getText())){
				//TODO save
			}
			else{
				Alert alert = new Alert(Alert.AlertType.ERROR, "Price format is incorrect, check if value is appropriate", ButtonType.CLOSE);
				alert.show();
			}
		}
	}

	protected void onLeave(){
		priceField.clear();
		cardNumberField.clear();
		cardHolderNameField.clear();
	}

	public void onCancel() {
		uiController.showScreen("Jobs");
		onLeave();
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
		cancelButton.setOnAction(actionEvent -> onCancel());
		saveButton.setOnAction(actionEvent -> savePayment());
		addFloatNumberListener(priceField);
		addIntegerNumberListener(monthField, 2);
		addIntegerNumberListener(yearField, 2);

		paymentMethodBox.setPromptText("Select payment method");
		setComboBoxPromptText(paymentMethodBox, "Select payment method");
		paymentMethodBox.getItems().addAll("Card", "Cash");
	}
}