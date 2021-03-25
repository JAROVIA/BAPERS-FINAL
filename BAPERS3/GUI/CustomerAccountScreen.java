package GUI;

import ACCOUNT.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.util.ArrayList;

public class CustomerAccountScreen extends Window {

	@FXML
	private TextField SearchCustomerTextArea;
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

	private int selectedCustomerId = -1;

	public void onSelectCustomer() {
		customerDetailGridPane.setStyle("visibility : visible");
		String[] customerData = customerAccountTable.getSelectionModel().getSelectedItem();
		selectedCustomerId = Integer.parseInt(customerData[0]);

		accountNumberLabel.setText(customerData[0]);
		nameLabel.setText(customerData[1]);
		contactNameLabel.setText(customerData[2]);
		valuedLabel.setText(customerData[3]);
		phoneLabel.setText(customerData[4]);
		addressLabel.setText(customerData[5]);
		emailLabel.setText(customerData[6]);
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
			//assign id somewhere
			acctUiController.showScreen("EditCustomerDetails");
			customerAccountTable.getItems().clear();
			discountTable.getItems().clear();
		}
	}

	private void toRegisterNewCustomer(){
		acctUiController.showScreen("RegisterNewCustomer");
		customerAccountTable.getItems().clear();
		discountTable.getItems().clear();
	}

	private void toProcessTasks(){
		if(selectedCustomerId >= 0) {
			//assign id somewhere
			acctUiController.showScreen("ProgressTasks");
			customerAccountTable.getItems().clear();
			discountTable.getItems().clear();
		}
	}

	@Override
	public void onShow(){
		super.onShow();
		//TODO assign the customer account details list here
		selectedCustomerId = -1;
		ObservableList<String[]> data = FXCollections.observableArrayList();
		//add cusomter info here
		data.addAll(new String[]{"1","bob","bobby","yes","02345678","city, london, uk","bob@city.ac.uk"});

		customerAccountTable.setItems(data);

		customerDetailGridPane.setStyle("visibility : hidden");
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