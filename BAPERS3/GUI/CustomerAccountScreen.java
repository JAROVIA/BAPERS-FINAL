package GUI;

import ACCOUNT.*;
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

	public void onSelectCustomer() {

		splitPane.setDividerPositions(.6);
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
			onLeave();
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
					|| data[2].toLowerCase().contains(input.toLowerCase());
		}
		if(matchNumber(input)){
			return data[0].contains(input)
					|| data[4].contains(input);
		}
		if(matchEmail(input)){
			return data[6].toLowerCase().contains(input.toLowerCase());
		}
		else{
			return data[3].toLowerCase().contains(input.toLowerCase())
					|| data[5].toLowerCase().contains(input.toLowerCase());
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
		customerDetailGridPane.setStyle("visibility : hidden");
	}

	protected void onLeave(){
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
//		data.addAll(new String[]{"1","bob","bobby","yes","02345678","city, london, uk","bob@city.ac.uk"});

		try {
			data.addAll(CustomerAccountDetails.getCustomerList());
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		customerAccountTable.setItems(data);

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