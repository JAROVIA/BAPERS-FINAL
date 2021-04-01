package GUI;

import ACCOUNT.CustomerAccountDetails;
import ADMIN.AlertUser;
import PROCESS.TaskDescription;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterNewCustomerScreen extends Window {

	@FXML
	protected TextField nameField;
	@FXML
	protected Button submitButton;
	@FXML
	protected TextField phoneField;
	@FXML
	protected TextField addressField;
	@FXML
	protected TextField emailField;
	@FXML
	protected TextField contactNameField;
	@FXML
	protected ComboBox<String> discountBox;
	@FXML
	protected Button addButton;
	@FXML
	protected Button removeButton;
	@FXML
	protected TableView<String[]> discountTable;
	@FXML
	protected ComboBox<String> taskBox;
	@FXML
	protected TextField discountRateField;
	@FXML
	protected TextField bandField;
	@FXML
	protected Label bandLabel;
	@FXML
	protected Label bandLabel2;
	@FXML
	protected Label discountRateLabel;
	@FXML
	protected GridPane gridPane;
	@FXML
	protected CheckBox valuedCheckBox;
	@FXML
	protected Button cancelButton;

	protected final String DISCOUNT_FIXED = "Fixed";
	protected final String DISCOUNT_FLEX = "Flexible";
	protected final String DISCOUNT_VAR = "Variable";

	protected ObservableList<String[]> data;

	protected int accountNumber = -1;

	/*
	maybe some refactor can be done with repeated code but it works
	 */

	/**
	 * method invoked on submit button click
	 * compiles all inputs and saves it in the database
	 */
	protected void onSubmit() {
		//make sure inputs are correct
		//listing boolean so easier to see
		boolean isValuedCustomer = valuedCheckBox.isSelected();
		//check inputs are not empty
		if (isValueNotEmpty(
					nameField,
					contactNameField,
					emailField,
					addressField,
					phoneField
			)) {
			//check email format is correct
			if (matchEmail(emailField.getText())) {
				//check if person is valued customer type
				if (isValuedCustomer) {
					//check discount info is not empty
					if (discountBox.getValue() == null || discountTable.getItems().size() <= 0 || discountTable.getItems() == null) {
						Alert alert = new Alert(Alert.AlertType.ERROR, "Enter discount settings", ButtonType.CLOSE);
						alert.show();
					}
					else {
						//check customer account is not empty
						//TODO submit as valued customer
						submitCustomerDataWithDiscount(
								phoneField.getText().trim(),
								addressField.getText().trim(),
								emailField.getText().trim(),
								nameField.getText().trim(),
								contactNameField.getText().trim(),
								discountBox.getValue(),
								accountNumber,
								new ArrayList<>(discountTable.getItems())
						);
					}
				} else {
					//TODO submit as non valued customer
					submitCustomerData(
							phoneField.getText().trim(),
							addressField.getText().trim(),
							emailField.getText().trim(),
							nameField.getText().trim(),
							contactNameField.getText().trim()
					);
				}

			}
			else{
				Alert alert = new Alert(Alert.AlertType.ERROR, "email field has incorrect format : should be like some@thing.com", ButtonType.CLOSE);
				alert.show();
			}
		}
	}

	public void setAccountNumber(int accountNumber){
		this.accountNumber = accountNumber;
	}

	protected void submitDiscountData(String discountType, ArrayList<String[]> discountData) {
		try {
			acctUiController.submitDiscountData(discountType, accountNumber, discountData);
		} catch (SQLException throwables) {
			AlertUser.showDBError();
		}
	}

	protected void submitCustomerData(String phone, String address, String email, String name, String contactName){
		try {
			if (CustomerAccountDetails.checkIfCustomerExists(contactNameField.getText())) {
				new Alert(Alert.AlertType.ERROR, "Customer with same contact name exists", ButtonType.CLOSE).show();
			} else {
				acctUiController.addNewCustomer("non-valued", phone, address, email, name, contactName);
				AlertUser.showCompletion("Customer data submit");
				showScreen(this, "CustomerAccounts");
			}
		} catch(SQLException throwables) {
			AlertUser.showDBError();
		}
	}

	protected void submitCustomerDataWithDiscount(
			String phone,
			String address,
			String email,
			String name,
			String contactName,
			String discountType,
			int accountNumber,
			ArrayList<String[]> discountData
	){
		try {
			if(CustomerAccountDetails.checkIfCustomerExists(contactNameField.getText())){
				new Alert(Alert.AlertType.ERROR, "Customer with same contact name exists", ButtonType.CLOSE).show();
			} else {
				acctUiController.addNewCustomer("valued", phone, address, email, name, contactName);
				acctUiController.submitDiscountData(discountType, accountNumber, discountData);
				AlertUser.showCompletion("Customer data submit");
				showScreen(this, "CustomerAccounts");
			}
		} catch (SQLException e) {
			AlertUser.showDBError();
		}
	}

	protected void onCancel(){
		showScreen(this, "CustomerAccounts");
	}

	/**
	 * long method invoked when option in combo box (select box) is clicked on
	 * makes field necessary for each discount type to show and populates table with appropriate columns
	 */
	protected void onDiscountSelect(){
		if(discountBox.getValue() != null) {

			discountTable.getColumns().clear();
			data.clear();

			for(int i = GridPane.getRowIndex(discountBox); i < GridPane.getRowIndex(discountTable); i++){
				gridPane.getRowConstraints().get(i).setPercentHeight(-1);
				gridPane.getRowConstraints().get(i).setMinHeight(20);
			}
			gridPane.getRowConstraints().get(GridPane.getRowIndex(discountTable)).setPercentHeight(20);

			String discountType = discountBox.getValue();
			String[] columns = new String[]{};

			//display all values for flex
			if (discountType.equals(DISCOUNT_FLEX)) {
				showFixed(false);
				showVar(false);
				showFlex(true);
				columns = new String[]{"Volume lower", "Volume upper", "Discount rate"};
			}
			//display all values for fixed (just one input field)
			if (discountType.equals(DISCOUNT_FIXED)) {
				showFlex(false);
				showVar(false);
				showFixed(true);
				columns = new String[]{"Discount rate"};
			}
			//display all values for variable discount
			//information is taken from tasks in database and added to the combo box (selection box)
			if (discountType.equals(DISCOUNT_VAR)) {
				showFixed(false);
				showFlex(false);
				showVar(true);
				try {
					for (String[] tasks : TaskDescription.GetTaskList()) {
						StringBuilder columnsConcat = new StringBuilder("");
						String connector = ", ";
						for (String taskColumns : tasks) {
							columnsConcat.append(taskColumns).append(connector);
						}
						columnsConcat.setLength(columnsConcat.length() - connector.length());
						taskBox.getItems().add(columnsConcat.toString());
					}
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				columns = new String[]{"TaskID", "Discount rate"};
			}

			for (int i = 0; i < columns.length; i++) {

				TableColumn<String[], String> tc = new TableColumn<>();
				tc.setText(columns[i]);
				int j = i;
				tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> property) {
						return new SimpleStringProperty((property.getValue()[j]));
					}
				});
				System.out.println(discountTable.getWidth());
				tc.setMinWidth(discountTable.getWidth() / columns.length);
				discountTable.getColumns().add(tc);
			}
		}
		else{
			discountRateField.setStyle("visibility : hidden;");
			discountRateLabel.setStyle("visibility : hidden;");
			discountTable.setStyle("visibility : hidden;");
			addButton.setStyle("visibility : hidden;");
			removeButton.setStyle("visibility : hidden;");

			for(int i = GridPane.getRowIndex(discountBox); i < GridPane.getRowIndex(discountTable)+1; i++){
				gridPane.getRowConstraints().get(i).setPercentHeight(0);
			}
		}
	}

	/**
	 * method invoked on add button click
	 * adds String[] to data table of discount type chosen
	 */
	protected void onAdd(){
		if(discountBox.getValue() != null) {
			String discountType = discountBox.getValue();
			String discountRate = discountRateField.getText();

			if (discountRateField.getText().trim().isEmpty()) {
				Alert alert = new Alert(Alert.AlertType.ERROR, "Enter discount rate", ButtonType.CLOSE);
				alert.show();
			} else {
				if (discountType.equals(DISCOUNT_FIXED) && data.size() == 0) {
					data.add(new String[]{discountRate});
				}
				if (discountType.equals(DISCOUNT_FLEX)) {
					boolean isInsertOkay = true;
					if (data.size() > 0) {
						int oldUpper = Integer.parseInt(data.get(data.size() - 1)[1]);
						int newUpper = Integer.parseInt(bandField.getText());
						if (oldUpper >= newUpper) {
							isInsertOkay = false;
						}
					}
					if(!bandField.getText().trim().isEmpty()) {
						if (isInsertOkay) {
							data.add(new String[]{bandLabel.getText(), bandField.getText(), discountRate});
							bandLabel.setText(bandField.getText());
						}
					}
					else {
						Alert alert = new Alert(Alert.AlertType.ERROR, "Make sure to set bands correctly", ButtonType.CLOSE);
						alert.show();
					}
				}
				if (discountType.equals(DISCOUNT_VAR)) {
					if (taskBox.getValue() != null) {
						boolean isInsertOkay = true;
						for(String[] currentData : discountTable.getItems()) {
							if (currentData[0].equals(taskBox.getValue().split(",")[0])) {
								isInsertOkay = false;
								break;
							}
						}
						if(isInsertOkay) {
							data.add(new String[]{taskBox.getValue().split(",")[0], discountRate});
						}
						else {
							Alert alert = new Alert(Alert.AlertType.ERROR, "Make sure there are no duplicates", ButtonType.CLOSE);
							alert.show();
						}
					} else {
						Alert alert = new Alert(Alert.AlertType.ERROR, "select task", ButtonType.CLOSE);
						alert.show();
					}
				}
			}
		}
		discountTable.setItems(data);
	}

	/**
	 * method invoked on remove button click
	 * removes row/s from the table depending on user selection
	 */
	protected void onRemove(){
		//only remove if there is data
		if(data.size() > 0){
			String discountType = discountBox.getValue();
			if (discountTable.getSelectionModel().getSelectedIndex() == -1
					|| discountType.equals(DISCOUNT_FIXED)) {
				//if no row is selected the last entry will be removed (if this method is even necessary)
				data.remove(data.size() - 1);
				//if the discount type is flex then the bands label needs to be updated
				if(discountType.equals(DISCOUNT_FLEX)){
					bandLabel.setText(data.get(data.size()-1)[1]);
				}
			}
			else if(discountType.equals(DISCOUNT_VAR)){
				//remove selected discount row
				data.remove(discountTable.getSelectionModel().getSelectedIndex());
			}
			else if(discountType.equals(DISCOUNT_FLEX)){
				//since flex discount bands will not make sense
				// if lower bounds do not match the previous upper,
				// if a row is selected all rows after will also be removed
				int i = discountTable.getSelectionModel().getSelectedIndex();
				int end = data.size() - i;
				for(int j = 0; j < end; j++){
					data.remove(i);
				}
				if(data.size() <= 0) {
					//if there are no entry the lower bound is 0
					bandLabel.setText("0");
				}
				else{
					//set text of lower bound to the last entry
					bandLabel.setText(data.get(data.size() - 1)[1]);
				}
			}
		}
	}

	/**
	 *
	 * @param input the string to check if the discount string is an integer, and also within appropriate range
	 * @return if input can be used as an appropriate rate or not
	 */
	protected boolean isAppropriateRate(String input){
		boolean isAppropriate = true;
		int i = 0;
		if(!input.equals("")) {
			try {
				i = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				isAppropriate = false;
			}
		}
		if(i <= 0 || i >= 100){
			isAppropriate = false;
		}
		return isAppropriate;
	}

	public void onLeave(){
		accountNumber = -1;
		//customer detail fields
		nameField.clear();
		phoneField.clear();
		emailField.clear();
		contactNameField.clear();
		addressField.clear();
		discountRateField.clear();

		//clear the table
		data.clear();
		discountBox.getSelectionModel().clearSelection();
	}

	public void onShow(){
		super.onShow();

		//everything is set to empty state
		//discount fields
		showVar(false);
		showFixed(false);
		showFlex(false);

		valuedCheckBox.setSelected(false);
		valuedCheckBox.setDisable(true);

		discountRateField.setStyle("visibility : hidden;");
		discountRateLabel.setStyle("visibility : hidden;");
		discountTable.setStyle("visibility : hidden;");
		addButton.setStyle("visibility : hidden;");
		removeButton.setStyle("visibility : hidden;");

		for(int i = GridPane.getRowIndex(valuedCheckBox); i < GridPane.getRowIndex(discountTable)+1; i++){
			gridPane.getRowConstraints().get(i).setPercentHeight(0);
			gridPane.getRowConstraints().get(i).setMinHeight(0);
		}
	}

	protected String visibilityByBoolean(boolean isVisible){
		return isVisible ? "visibility : visible;" : "visibility : hidden;";
	}

	protected void showFlex(boolean isInUse){
		bandField.setDisable(!isInUse);
		addButton.setDisable(!isInUse);
		removeButton.setDisable(!isInUse);
		discountTable.setDisable(!isInUse);
		discountRateField.setDisable(!isInUse);

		bandLabel.setStyle(visibilityByBoolean(isInUse));
		bandLabel2.setStyle(visibilityByBoolean(isInUse));
		bandField.setStyle(visibilityByBoolean(isInUse));
		addButton.setStyle(visibilityByBoolean(isInUse));
		removeButton.setStyle(visibilityByBoolean(isInUse));
		discountRateLabel.setStyle(visibilityByBoolean(isInUse));
		discountTable.setStyle(visibilityByBoolean(isInUse));
		discountRateField.setStyle(visibilityByBoolean(isInUse));

		bandLabel.setText("0");
		bandField.clear();
		discountRateField.clear();
	}

	protected void showFixed(boolean isInUse){
		addButton.setDisable(!isInUse);
		removeButton.setDisable(!isInUse);
		discountTable.setDisable(!isInUse);
		discountRateField.setDisable(!isInUse);

		addButton.setStyle(visibilityByBoolean(isInUse));
		removeButton.setStyle(visibilityByBoolean(isInUse));
		discountRateLabel.setStyle(visibilityByBoolean(isInUse));
		discountTable.setStyle(visibilityByBoolean(isInUse));
		discountRateField.setStyle(visibilityByBoolean(isInUse));

		discountRateField.clear();
	}

	protected void showVar(boolean isInUse){
		taskBox.getSelectionModel().clearSelection();
		taskBox.getItems().clear();

		addButton.setDisable(!isInUse);
		removeButton.setDisable(!isInUse);
		discountTable.setDisable(!isInUse);
		discountRateField.setDisable(!isInUse);
		taskBox.setDisable(!isInUse);

		addButton.setStyle(visibilityByBoolean(isInUse));
		removeButton.setStyle(visibilityByBoolean(isInUse));
		discountRateLabel.setStyle(visibilityByBoolean(isInUse));
		discountTable.setStyle(visibilityByBoolean(isInUse));
		discountRateField.setStyle(visibilityByBoolean(isInUse));
		taskBox.setStyle(visibilityByBoolean(isInUse));

		discountRateField.clear();
	}

	/**
	 *
	 */
	@FXML
	public void initialize(){
		super.initialize();
		userAllowed = new String[]{ROLE_OFFICE_MANAGER, ROLE_SHIFT_MANAGER, ROLE_RECEPTIONIST};
		data = FXCollections.observableArrayList(new ArrayList<>());

		//dont show extra stuff unless needed
		showFixed(false);
		showFlex(false);
		showVar(false);

		// no discounts on this page
		valuedCheckBox.setStyle("visibility : hidden");
		valuedCheckBox.setDisable(true);
		discountBox.setStyle("visibility : hidden");
		discountBox.setDisable(false);

		valuedCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean newValue) {

				discountBox.getSelectionModel().clearSelection();
				discountTable.getColumns().clear();
				data.clear();

				if(newValue) {
					gridPane.getRowConstraints().get(GridPane.getRowIndex(discountBox)).setPercentHeight(-1);
				}
				else{
					showFixed(false);
					showFlex(false);
					showVar(false);
					for (int i = GridPane.getRowIndex(discountBox); i < GridPane.getRowIndex(discountTable) + 1; i++) {
						gridPane.getRowConstraints().get(i).setPercentHeight(0);
						gridPane.getRowConstraints().get(i).setPrefHeight(0);
						gridPane.getRowConstraints().get(i).setMinHeight(0);
					}
				}
				discountBox.setStyle(visibilityByBoolean(newValue));
				discountBox.setDisable(!newValue);
			}
		});

		addIntegerNumberListener(discountRateField, 2);
		addIntegerNumberListener(bandField);
		addIntegerNumberListener(phoneField, 15);
		addNameListener(nameField);
		addNameListener(contactNameField);

		//overridden method to show prompt text again because javafx is great at this
		setComboBoxPromptText(discountBox, "Select discount type");
		setComboBoxPromptText(taskBox, "Select task");

		discountBox.getItems().addAll(DISCOUNT_FIXED, DISCOUNT_FLEX, DISCOUNT_VAR);
		discountBox.setOnAction(actionEvent -> onDiscountSelect());
		addButton.setOnAction(actionEvent -> onAdd());
		removeButton.setOnAction(actionEvent -> onRemove());
		submitButton.setOnAction(actionEvent -> onSubmit());
		cancelButton.setOnAction(actionEvent -> onCancel());
	}
}