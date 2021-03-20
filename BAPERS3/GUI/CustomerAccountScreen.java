package GUI;

import ACCOUNT.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.util.ArrayList;

public class CustomerAccountScreen extends Window {

	@FXML
	private TextField SearchCustomerTextArea;
	@FXML
	private TableView<CustomerAccountDetails> customerAccountTable;
	@FXML
	private TableColumn<CustomerAccountDetails, Number> accountNumberColumn;
	@FXML
	private TableColumn<CustomerAccountDetails, String> userNameColumn;
	@FXML
	private TableColumn<CustomerAccountDetails, String> accountStatusColumn;
	@FXML
	private TableColumn<CustomerAccountDetails, String> addressColumn;
	@FXML
	private TableColumn<CustomerAccountDetails, Number> phoneColumn;
	@FXML
	private TableColumn<CustomerAccountDetails, String> agreedDiscountColumn;
	@FXML
	private TableColumn<CustomerAccountDetails, Integer> discountPercentColumn;
	@FXML
	private Button deleteCustomerButton;
	@FXML
	private Button registerNewCustomerButton;
	@FXML
	private Button editCustomerButton;
	@FXML
	private Button processTasksButton;

	public CustomerAccountDetails selectCustomer() {
		// TODO - implement CustomerAccountScreen.selectCustomer
		throw new UnsupportedOperationException();
	}

	public void deleteCustomer() {
		// TODO - implement CustomerAccountScreen.DeleteCustomer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param CustomerAccDetails
	 */
	public CustomerAccountDetails searchCustomerAcc(String CustomerAccDetails) {
		// TODO - implement CustomerAccountScreen.SearchCustomerAcc
		throw new UnsupportedOperationException();
	}

	public CustomerAccountDetails retrieveCustomer() {
		// TODO - implement CustomerAccountScreen.RetrieveCustomer
		throw new UnsupportedOperationException();
	}

	private void toEditCustomer(){
		acctUiController.showScreen("EditCustomerDetails");
	}

	private void toRegisterNewCustomer(){
		acctUiController.showScreen("RegisterNewCustomer");
	}

	private void toProcessTasks(){
		acctUiController.showScreen("ProgressTasks");
	}

	@Override
	public void onShow(){
		super.onShow();
		ArrayList<CustomerAccountDetails> list = new ArrayList<>();
		//TODO assign the customer account details list here

		ObservableList<CustomerAccountDetails> data = FXCollections.observableArrayList();
		data.addAll(list);

		customerAccountTable.setItems(data);
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

		/*
		accountNumberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CustomerAccountDetails, Number>, ObservableValue<Number>>() {
			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<CustomerAccountDetails, Number> property) {
				return new SimpleIntegerProperty((property.getValue().getAccountNumber()));
			}
		});

		discountPercentColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CustomerAccountDetails, Integer>, ObservableValue<Integer>>() {
			@Override
			public ObservableValue<Integer> call(TableColumn.CellDataFeatures<CustomerAccountDetails, Integer> property) {
				return new SimpleIntegerProperty((property.getValue().getAccountNumber())).asObject();
			}
		});

		phoneColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CustomerAccountDetails, Number>, ObservableValue<Number>>() {
			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<CustomerAccountDetails, Number> property) {
				return new SimpleLongProperty((property.getValue()));
			}
		});

		userNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CustomerAccountDetails, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<CustomerAccountDetails, String> property) {
				return new SimpleStringProperty((property.getValue().getUserName()));
			}
		});

		accountStatusColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CustomerAccountDetails, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<CustomerAccountDetails, String> property) {
				return new SimpleStringProperty((property.getValue().getAccountStatus()));
			}
		});

		addressColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CustomerAccountDetails, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<CustomerAccountDetails, String> property) {
				return new SimpleStringProperty((property.getValue().getUserName()));
			}
		});

		agreedDiscountColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CustomerAccountDetails, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<CustomerAccountDetails, String> property) {
				return new SimpleStringProperty((property.getValue().getUserName()));
			}
		});

		 */
	}

}