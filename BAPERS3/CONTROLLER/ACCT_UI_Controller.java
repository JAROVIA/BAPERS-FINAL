package CONTROLLER;

import ACCOUNT.*;
import ADMIN.UserAccount;
import GUI.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ACCT_UI_Controller {
	private Main main;

	//gui this controller handles
	//TODO which screen is this
	private ActiveJobScreen activeJobScreen;
	private String activeJobFxml = "Jobs";

	private EditCustomerDetailsScreen editCustomerDetailsScreen;
	private String editCustomerFxml = "EditCustomerDetails";

	private RegisterNewCustomerScreen registerNewCustomerScreen;
	private String registerCustomerFxml = "RegisterNewCustomer";

	private CustomerAccountScreen customerAccountScreen;
	private String customerFxml = "CustomerAccounts";

	private I_CustomerAccount customerAccountHandler;

	private Map<String, Window> screens;

	/**
	 * 
	 * @param AccountNum
	 */
	public String searchCustomer(int AccountNum) {
		// TODO - implement ACCT_UI_Controller.SearchCustomer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param CustomerData
	 */
	public boolean submit(String[] CustomerData) {
		// TODO - implement ACCT_UI_Controller.Submit
		throw new UnsupportedOperationException();
	}

	public void deleteCustomer() {
		// TODO - implement ACCT_UI_Controller.DeleteCustomer
		throw new UnsupportedOperationException();
	}

	public CustomerAccountDetails[] retrieveCustomer() {
		// TODO - implement ACCT_UI_Controller.RetrieveCustomer
		throw new UnsupportedOperationException();
	}

	public CustomerAccountDetails selectCustomer() {
		// TODO - implement ACCT_UI_Controller.SelectCustomer
		throw new UnsupportedOperationException();
	}

	public void showScreen(String name){
		main.showScreen(name);
	}

	public Window getScreen(String name){
		return screens.get(name);
	}

	public UserAccount getLoggedInUser(){
		return main.getAdminUiController().getLoggedInUser();
	}

	//constructor
	public ACCT_UI_Controller(Main main) throws IOException {

		this.main = main;
		screens = new HashMap<>();

		//gui setup
		editCustomerDetailsScreen = (EditCustomerDetailsScreen) Window.newGuiFromFxml(editCustomerFxml);
		screens.put(editCustomerFxml, editCustomerDetailsScreen);
		registerNewCustomerScreen = (RegisterNewCustomerScreen) Window.newGuiFromFxml(registerCustomerFxml);
		screens.put(registerCustomerFxml, registerNewCustomerScreen);
		customerAccountScreen = (CustomerAccountScreen) Window.newGuiFromFxml(customerFxml);
		screens.put(customerFxml, customerAccountScreen);

		for(Map.Entry<String, Window> entry : screens.entrySet()){
			main.addScreen(entry.getKey(), entry.getValue().getParent(), "ACCT");
			entry.getValue().setAcctUiController(this);
		}

		//get implementation class from interface
		customerAccountHandler = new Impl_CustomerAccount();
	}

}