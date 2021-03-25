package GUI;

import PROCESS.TaskDescription;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.ArrayList;

public class EditCustomerDetailsScreen extends Window {

	@FXML
	private TextField nameField;
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
	@FXML
	private TextField emailField;
	@FXML
	private TextField contactNameField;
	@FXML
	private ComboBox<String> discountBox;
	@FXML
	private Button addButton;
	@FXML
	private Button removeButton;
	@FXML
	private TableView<String[]> discountTable;
	@FXML
	private ComboBox<String> taskBox;
	@FXML
	private TextField discountRateField;
	@FXML
	private TextField bandField;
	@FXML
	private Label bandLabel;
	@FXML
	private Label bandLabel2;
	@FXML
	private Label discountRateLabel;

	private final String DISCOUNT_FIXED = "Fixed";
	private final String DISCOUNT_FLEX = "Flexible";
	private final String DISCOUNT_VAR = "Variable";

	private ObservableList<String[]> data;

	/*
	maybe some refactor can be done with repeated code but it works
	 */

	/**
	 * method invoked on submit button click
	 * compiles all inputs and saves it in the database
	 */
	private void onSubmit() {
		//make sure inputs are correct
		//listing boolean so easier to see
		boolean isValuedCustomer = discountBox.getValue() == null;
		//check inputs are correct
		if(isValuedCustomer) {
			if (isValueNotEmpty(
					nameField,
					contactNameField,
					emailField,
					memorableWordField,
					address1Field,
					address2Field,
					phoneField,
					postcodeField
			) && discountTable.getItems().size() > 0) {

				//TODO submit as valued customer
			}
		}
		else if (isValueNotEmpty(nameField,
				contactNameField,
				emailField,
				memorableWordField,
				address1Field,
				address2Field,
				phoneField,
				postcodeField
		)){
			//TODO submit non valued customer
		}
	}

	/**
	 * long method invoked when option in combo box (select box) is clicked on
	 * makes field necessary for each discount type to show and populates table with appropriate columns
	 */
	private void onDiscountSelect(){
		if(discountBox.getValue() != null && !discountBox.getValue().equals("none")) {
			discountTable.getColumns().clear();
			data.clear();
			hideVar();
			hideFlex();

			discountRateField.setStyle("visibility : visible;");
			discountRateLabel.setStyle("visibility : visible;");
			discountRateField.setText("");
			discountTable.setStyle("visibility : visible;");
			addButton.setStyle("visibility : visible;");
			removeButton.setStyle("visibility : visible;");

			String discountType = discountBox.getValue();
			String[] columns = new String[]{};

			//hide input field if customer non valued
			if(discountType.equals("none")){
				discountRateField.setStyle("visibility : hidden");
				discountRateLabel.setStyle("visibility : hidden;");
			}

			//display all values for flex
			if (discountType.equals(DISCOUNT_FLEX)) {
				bandLabel.setStyle("visibility : visible;");
				bandLabel.setText("0");
				bandLabel2.setStyle("visibility : visible;");
				bandField.setStyle("visibility : visible");
				columns = new String[]{"Volume lower", "Volume upper", "Discount rate"};
			}
			//display all values for fixed (just one input field)
			if (discountType.equals(DISCOUNT_FIXED)) {
				columns = new String[]{"Discount rate"};
			}
			//display all values for variable discount
			//information is taken from tasks in database and added to the combo box (selection box)
			if (discountType.equals(DISCOUNT_VAR)) {
				taskBox.setStyle("visibility : visible;");
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
				tc.setMinWidth(discountTable.getWidth() / columns.length);
				discountTable.getColumns().add(tc);
			}
		}
		else{
			discountTable.setStyle("visibility : hidden;");
			addButton.setStyle("visibility : hidden;");
			removeButton.setStyle("visibility : hidden;");
		}
	}

	/**
	 * method invoked on add button click
	 * adds String[] to data table of discount type chosen
	 */
	private void onAdd(){
		String discountType = discountBox.getValue();
		String discountRate = discountRateField.getText();

		if(isAppropriateRate(discountRate)) {
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
				if(isInsertOkay) {
					data.add(new String[]{bandLabel.getText(), bandField.getText(), discountRate});
					bandLabel.setText(bandField.getText());
				}
			}
			if (discountType.equals(DISCOUNT_VAR)) {
				data.add(new String[]{taskBox.getValue().split(",")[0], discountRate});
			}
		}
		discountTable.setItems(data);
	}

	/**
	 * method to hide all fields related to variable discount
	 */
	private void hideVar(){
		taskBox.getSelectionModel().clearSelection();
		taskBox.getItems().clear();
		taskBox.setStyle("visibility : hidden;");
	}

	/**
	 * method to hide all fields related to flexible discount
	 */
	private void hideFlex(){
		bandField.setStyle("visibility : hidden;");
		bandField.setText("");
		bandLabel.setStyle("visibility : hidden;");
		bandLabel2.setStyle("visibility : hidden;");
	}

	/**
	 * method invoked on remove button click
	 * removes row/s from the table depending on user selection
	 */
	private void onRemove(){
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
	private boolean isAppropriateRate(String input){
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

	public void onShow(){
		super.onShow();

		//everything is set to empty state

		//customer detail fields
		memorableWordField.setText("");
		nameField.setText("");
		phoneField.setText("");
		postcodeField.setText("");
		address1Field.setText("");
		address2Field.setText("");

		//discount fields
		hideFlex();
		hideVar();
		discountRateField.setStyle("visibility : hidden;");
		discountRateLabel.setStyle("visibility : hidden;");
		//clear the table
		data.clear();
		discountBox.getSelectionModel().clearSelection();
		//overridden method to show prompt text again because javafx is great at this
		setComboBoxPromptText(discountBox, "Select discount type");
		discountRateField.setText("");
	}

	/**
	 *
	 */
	@FXML
	public void initialize(){
		super.initialize();
		userAllowed = new String[]{ROLE_OFFICE_MANAGER};
		data = FXCollections.observableArrayList(new ArrayList<>());
		taskBox.setStyle("visibility : hidden;");
		bandLabel.setStyle("visibility : hidden;");
		bandField.setStyle("visibility : hidden;");
		discountRateField.setStyle("visibility : hidden;");
		discountRateLabel.setStyle("visibility : hidden;");
		discountTable.setStyle("visibility : hidden;");
		addButton.setStyle("visibility : hidden;");
		removeButton.setStyle("visibility : hidden;");

		addIntegerNumberListener(discountRateField);
		addIntegerNumberListener(bandField);
		addIntegerNumberListener(phoneField, 15);
		addNameListener(nameField);
		addNameListener(contactNameField);

		discountBox.getItems().addAll("none", DISCOUNT_FIXED, DISCOUNT_FLEX, DISCOUNT_VAR);
		discountBox.setOnAction(actionEvent -> onDiscountSelect());
		addButton.setOnAction(actionEvent -> onAdd());
		removeButton.setOnAction(actionEvent -> onRemove());
		submitButton.setOnAction(actionEvent -> onSubmit());
	}
}