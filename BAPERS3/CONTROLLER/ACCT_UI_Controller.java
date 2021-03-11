package CONTROLLER;

import ACCOUNT.*;
import GUI.*;

import java.io.IOException;

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

	/**
	 * 
	 * @param AccountNum
	 */
	public String SearchCustomer(int AccountNum) {
		// TODO - implement ACCT_UI_Controller.SearchCustomer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param CustomerData
	 */
	public boolean Submit(String[] CustomerData) {
		// TODO - implement ACCT_UI_Controller.Submit
		throw new UnsupportedOperationException();
	}

	public void DeleteCustomer() {
		// TODO - implement ACCT_UI_Controller.DeleteCustomer
		throw new UnsupportedOperationException();
	}

	public CustomerAccountDetails[] RetrieveCustomer() {
		// TODO - implement ACCT_UI_Controller.RetrieveCustomer
		throw new UnsupportedOperationException();
	}

	public CustomerAccountDetails SelectCustomer() {
		// TODO - implement ACCT_UI_Controller.SelectCustomer
		throw new UnsupportedOperationException();
	}

	public RegisterNewCustomerScreen getRegisterNewCustomerScreen() {
		return registerNewCustomerScreen;
	}

	public ActiveJobScreen getActiveJobScreen() {
		return activeJobScreen;
	}

	public CustomerAccountScreen getCustomerAccountScreen() {
		return customerAccountScreen;
	}

	public EditCustomerDetailsScreen getEditCustomerDetailsScreen() {
		return editCustomerDetailsScreen;
	}

	public Main getMain() {
		return main;
	}

	//constructor
	public ACCT_UI_Controller(Main main) throws IOException {
		// TODO - implement ACCT_UI_Controller.ACCT_UI_Controller
		/*
		activeJobScreen = (ActiveJobScreen) Window.newGuiFromFxml(activeJobFxml);
		main.addScreen(activeJobFxml, activeJobScreen.getParent());
		activeJobScreen.setAcctUiController(this);

		 */

		editCustomerDetailsScreen = (EditCustomerDetailsScreen) Window.newGuiFromFxml(editCustomerFxml);
		main.addScreen(editCustomerFxml, editCustomerDetailsScreen.getParent());
		editCustomerDetailsScreen.setAcctUiController(this);

		registerNewCustomerScreen = (RegisterNewCustomerScreen) Window.newGuiFromFxml(registerCustomerFxml);
		main.addScreen(registerCustomerFxml, registerNewCustomerScreen.getParent());
		registerNewCustomerScreen.setAcctUiController(this);

		customerAccountScreen = (CustomerAccountScreen) Window.newGuiFromFxml(customerFxml);
		main.addScreen(customerFxml, customerAccountScreen.getParent());
		customerAccountScreen.setAcctUiController(this);
	}

}