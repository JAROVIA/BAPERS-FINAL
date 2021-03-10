package CONTROLLER;

import ACCOUNT.*;
import GUI.ActiveJobScreen;
import GUI.CustomerAccountScreen;
import GUI.EditCustomerDetailsScreen;
import GUI.RegisterNewCustomerScreen;

public class ACCT_UI_Controller {
	private Main main;

	//gui this controller handles
	private ActiveJobScreen activeJobScreen;
	private String activeJobFxml = "";

	private EditCustomerDetailsScreen editCustomerDetailsScreen;
	private String editCustomerFxml = "";

	private RegisterNewCustomerScreen registerNewCustomerScreen;
	private String registerCustomerFxml = "";

	private CustomerAccountScreen customerAccountScreen;
	private String customerFxml = "";

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
	public ACCT_UI_Controller(Main main) {
		// TODO - implement ACCT_UI_Controller.ACCT_UI_Controller
	}

}