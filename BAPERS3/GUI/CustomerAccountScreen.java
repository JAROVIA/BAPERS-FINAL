package GUI;

import ACCOUNT.*;
import ADMIN.AlertUser;
import CUSTOMER.Discount;
import CUSTOMER.FixedDiscountRate;
import CUSTOMER.FlexibleDiscountRate;
import CUSTOMER.VariableDiscountRate;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class CustomerAccountScreen extends Window {

	@FXML
	private TextField searchField;
	@FXML
	private TableView<String[]> customerAccountTable;
	@FXML
	private Button deleteCustomerButton;
	@FXML
	private Button registerNewCustomerButton;
	@FXML
	private Button editCustomerButton;
	@FXML
	private Button processTasksButton;
	@FXML
	private GridPane customerDetailGridPane;
	@FXML
	private SplitPane splitPane;
	@FXML
	private Label accountNumberLabel;
	@FXML
	private Label phoneLabel;
	@FXML
	private Label nameLabel;
	@FXML
	private Label contactNameLabel;
	@FXML
	private Label addressLabel;
	@FXML
	private Label emailLabel;
	@FXML
	private Label valuedLabel;
	@FXML
	private TableView<String[]> discountTable;
	@FXML
	private Button closeButton;

	private int selectedCustomerId = -1;

	private void onSelectCustomer() {

		splitPane.setDividerPositions(.6);
		customerDetailGridPane.setStyle("visibility : visible");
		if(customerAccountTable.getSelectionModel().getSelectedItem() != null) {
			String[] customerData = customerAccountTable.getSelectionModel().getSelectedItem();
			selectedCustomerId = Integer.parseInt(customerData[0]);

			accountNumberLabel.setText(customerData[0]);
			nameLabel.setText(customerData[5]);
			contactNameLabel.setText(customerData[6]);
			valuedLabel.setText(customerData[1]);
			phoneLabel.setText(customerData[2]);
			addressLabel.setText(customerData[3]);
			emailLabel.setText(customerData[4]);

			try {
				setupDiscountTable(Integer.parseInt(customerData[0]));
			} catch (SQLException throwables) {
				throwables.printStackTrace();
				AlertUser.showDBError();
			}
		}
	}

	private void setupDiscountTable(int accountNumber) throws SQLException {
		String discountType = "";
		if(discountTable.getItems() != null){
			discountTable.getItems().clear();
		}
		discountTable.getColumns().clear();
		try {
			discountType = acctUiController.getCustomerDiscountType(accountNumber);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			AlertUser.showDBError();
		}
		if(discountType != null){
			String[] columns = new String[]{};
			ArrayList<String[]> discountData = new ArrayList<>();

			if(discountType.equals("FixedDiscount")){
				columns = new String[]{"Discount rate"};

				FixedDiscountRate fixed = new FixedDiscountRate();
				fixed.retrieveDiscount(accountNumber);
				discountData.add(new String[]{String.valueOf(fixed.getDiscountRate())});
			}
			if(discountType.equals("FlexibleDiscount")){
				columns = new String[]{"Volume lower", "Volume upper", "Discount rate"};

				FlexibleDiscountRate flex = new FlexibleDiscountRate();
				flex.retrieveDiscount(accountNumber);
				discountData = acctUiController.discountToStringArray(flex);
			}
			if(discountType.equals("VariableDiscount")){
				columns = new String[]{"TaskID", "Discount rate"};

				VariableDiscountRate variable = new VariableDiscountRate();
				variable.retrieveVariableDiscount(accountNumber);
				discountData = acctUiController.discountToStringArray(variable);
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
			ObservableList<String[]> data = FXCollections.observableArrayList(discountData);
			discountTable.setItems(data);
		}
	}

	public void deleteCustomer() {
		// TODO - implement CustomerAccountScreen.DeleteCustomer
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 */
	/*
	cant use this
	public CustomerAccountDetails searchCustomerAcc(String CustomerAccDetails) {
		// TODO - implement CustomerAccountScreen.SearchCustomerAcc
		throw new UnsupportedOperationException();
	}
	 */

	public String[] searchCustomerAcc(String customerData) {
		// TODO - implement CustomerAccountScreen.SearchCustomerAcc
		throw new UnsupportedOperationException();
	}

	public CustomerAccountDetails retrieveCustomer() {
		// TODO - implement CustomerAccountScreen.RetrieveCustomer
		throw new UnsupportedOperationException();
	}

	private void toEditCustomer(){
		if(selectedCustomerId >= 0) {
			//TODO assign id somewhere
			showScreen(this, "EditCustomerDetails");
			acctUiController.setEditingCustomerNumber(selectedCustomerId);
		}
	}

	private void toRegisterNewCustomer(){
		showScreen(this, "RegisterNewCustomer");
	}

	private void toProcessTasks(){
		if(selectedCustomerId >= 0) {
			//TODO assign id somewhere
			showScreen(this, "ProgressTasks");
		}
	}

	private void resetCustomerDetail(){
		emailLabel.setText("");
		addressLabel.setText("");
		phoneLabel.setText("");
		valuedLabel.setText("");
		contactNameLabel.setText("");
		accountNumberLabel.setText("");
		nameLabel.setText("");
	}

	/**
	 * boolean to check if input is contained in the table. uses regex to match pattern first to check
	 * search is non case sensitive, probably easier for user
	 * @param data user account data to be checked
	 */
	private boolean matchCustomer(String[] data, String input) {
		if(matchName(input)){
			return data[1].toLowerCase().contains(input.toLowerCase())
					|| data[3].toLowerCase().contains(input.toLowerCase())
					|| data[4].toLowerCase().contains(input.toLowerCase())
					|| data[5].toLowerCase().contains(input.toLowerCase())
					|| data[6].toLowerCase().contains(input.toLowerCase());
		}
		if(matchNumber(input)){
			return data[0].contains(input)
					|| data[2].contains(input)
					|| data[3].toLowerCase().contains(input.toLowerCase());
		}
		else{
			return data[1].toLowerCase().contains(input.toLowerCase())
					|| data[3].toLowerCase().contains(input.toLowerCase())
					|| data[4].toLowerCase().contains(input.toLowerCase());
		}
	}

	/**
	 * used for filtered lists which can dynamically filter data depending on predicate such as this
	 * @param input input in the search field
	 * @return predicate to inform filter list if input matches some data in the list
	 */
	private Predicate<String[]> customerDataPredicate(String input){
		return (String[] data) -> {
			//if the input is empty, show all data
			if(input == null || input.trim().isEmpty()){
				System.out.println("empty input");
				return true;
			}
			else{
				System.out.println("attempt match" + matchCustomer(data, input));
				return matchCustomer(data, input);
			}
		};
	}

	private void onClose(){
		splitPane.setDividerPositions(1);
		resetCustomerDetail();
		if(discountTable.getItems() != null) {
			discountTable.getItems().clear();
		}
		customerDetailGridPane.setStyle("visibility : hidden");
	}

	public void onLeave(){
		customerAccountTable.setItems(null);
		discountTable.getItems().clear();
	}

	@Override
	public void onShow(){
		super.onShow();
		//TODO assign the customer account details list here
		selectedCustomerId = -1;
		ObservableList<String[]> data = FXCollections.observableArrayList();
		//add customer info here
		try {
			data.addAll(CustomerAccountDetails.getCustomerList());
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		FilteredList<String[]> filteredData = new FilteredList<>(data);
		customerAccountTable.setItems(filteredData);
		searchField.textProperty().addListener((observable, oldText, newText)->{
			customerAccountTable.getSelectionModel().clearSelection();
			onClose();
			filteredData.setPredicate(customerDataPredicate(newText));
		});

		splitPane.setDividerPositions(1);
		customerDetailGridPane.setStyle("visibility : hidden");

		resetCustomerDetail();
	}
	/**
	 *
	 */
	@FXML
	public void initialize(){
		super.initialize();
		userAllowed = new String[]{ROLE_OFFICE_MANAGER, ROLE_SHIFT_MANAGER, ROLE_RECEPTIONIST};
		editCustomerButton.setOnAction(actionEvent -> toEditCustomer());
		processTasksButton.setOnAction(actionEvent -> toProcessTasks());
		registerNewCustomerButton.setOnAction(actionEvent -> toRegisterNewCustomer());
		closeButton.setOnAction(actionEvent -> onClose());

		discountTable.setPlaceholder(new Label("No discounts are applied"));

		customerAccountTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (customerAccountTable.getSelectionModel().getSelectedItem() != null){
					onSelectCustomer();
				}
			}
		});

		for (int i = 0; i < customerAccountTable.getColumns().size(); i++) {
			TableColumn tc = customerAccountTable.getColumns().get(i);
			int j = i;
			tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> property) {
					return new SimpleStringProperty((property.getValue()[j]));
				}
			});
		}

	}

}