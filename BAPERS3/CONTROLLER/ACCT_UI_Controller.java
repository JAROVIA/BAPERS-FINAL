package CONTROLLER;

import ACCOUNT.*;
import ADMIN.UserAccount;
import CUSTOMER.Discount;
import CUSTOMER.FixedDiscountRate;
import CUSTOMER.FlexibleDiscountRate;
import CUSTOMER.VariableDiscountRate;
import GUI.*;
import PAYMENT.Payment;
import PROCESS.Job;
import PROCESS.TaskDescription;
import PROCESS.TaskInAJob;
import javafx.util.Pair;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ACCT_UI_Controller {
	private Main main;

	//gui this controller handles

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
	 * @param
	 */
	public void submitEditCustomer(int accountNumber, String status, String phone, String address, String email, String name, String contactName) throws SQLException {
		String[] columns = new String[]{
				"AccountStatus",
				"PhoneNumber",
				"Address",
				"EmailAddress",
				"CustomerName",
				"ContactName"
		};

		CustomerAccountDetails.updateCustomer(columns[0], status, "AccountNumber", accountNumber);
		CustomerAccountDetails.updateCustomer(columns[1], phone, "AccountNumber", accountNumber);
		CustomerAccountDetails.updateCustomer(columns[2], address, "AccountNumber", accountNumber);
		CustomerAccountDetails.updateCustomer(columns[3], email, "AccountNumber", accountNumber);
		CustomerAccountDetails.updateCustomer(columns[4], name, "AccountNumber", accountNumber);
		CustomerAccountDetails.updateCustomer(columns[5], contactName, "AccountNumber", accountNumber);
	}

	public ArrayList<String[]> discountToStringArray(FlexibleDiscountRate flexible){
		ArrayList<String[]> newData = new ArrayList<>();
		for(int[] data : flexible.getDiscountRate()){
			newData.add(new String[]{String.valueOf(data[0]), String.valueOf(data[1]), String.valueOf(data[2])});
		}
		return newData;
	}

	public ArrayList<String[]> discountToStringArray(VariableDiscountRate variable){
		ArrayList<String[]> newData = new ArrayList<>();
		for(Pair<Integer, Integer> data : variable.getTaskDiscountRate()){
			newData.add(new String[]{String.valueOf(data.getKey()), String.valueOf(data.getValue())});
		}
		return newData;
	}

	public static String getCustomerDiscountType(int accountNumber) throws SQLException {
		if(FlexibleDiscountRate.ifFlexDiscountExists(accountNumber)){
			return "Flexible";
		}
		if(FixedDiscountRate.ifFixDiscountExists(accountNumber)){
			return "Fixed";
		}
		if(VariableDiscountRate.ifVariableDiscountExists(accountNumber)){
			return "Variable";
		}
		return null;
	}

	public void submitDiscountData(String discountType, int accountNumber, ArrayList<String[]> discountData) throws SQLException {
		//delete previous entry if exists
		if(getCustomerDiscountType(accountNumber) != null){
			Discount.deleteDiscount(accountNumber, getCustomerDiscountType(accountNumber));
		}

		//new discount entry depending on type

		if(discountType.equals("Fixed")){
			FixedDiscountRate fixed = new FixedDiscountRate(accountNumber, Integer.parseInt(discountData.get(0)[0]));
			fixed.saveDiscount();
		}
		if(discountType.equals("Flexible")){
			ArrayList<int[]> bandsData = new ArrayList<>();
			for(String[] bands : discountData){
				bandsData.add(new int[]{
						Integer.parseInt(bands[0]),
						Integer.parseInt(bands[1]),
						Integer.parseInt(bands[2])
				});
			}
			FlexibleDiscountRate flexible = new FlexibleDiscountRate(accountNumber, bandsData);
			flexible.saveDiscount();
		}
		if(discountType.equals("Variable")){
			ArrayList<Pair<Integer, Integer>> varDiscData = new ArrayList<>();
			for(String[] data : discountData){
				varDiscData.add(new Pair<Integer, Integer>(Integer.parseInt(data[0]), Integer.parseInt(data[1])));
			}
			VariableDiscountRate variable = new VariableDiscountRate(accountNumber, varDiscData);
			variable.saveDiscount();
		}
	}

	public static float calculateFinalPrice(int accountNumber, int jobId) throws SQLException {
		String discountType = getCustomerDiscountType(accountNumber);
		String[] jobData = null;
		System.out.println("calculating");

		for(String[] data : Job.GetJobList()){
			if(Integer.parseInt(data[0]) == jobId){
				System.out.println(data[0] + " = " + jobId);
				jobData = data;
				break;
			}
		}
		if(jobData != null) {
			float price = Float.parseFloat(jobData[6]);
			System.out.println("initial price = " + price);
			if(discountType == null){
				return price;
			}
			if (discountType.equals("Fixed")) {
				FixedDiscountRate fixed = new FixedDiscountRate();
				fixed.retrieveDiscount(accountNumber);

				System.out.println("price with fixed applied = " + price * (100 - fixed.getDiscountRate()) / 100);
				return price * (100 - fixed.getDiscountRate()) / 100;
			}
			else if (discountType.equals("Flexible")) {
				FlexibleDiscountRate flex = new FlexibleDiscountRate();
				flex.retrieveDiscount(accountNumber);

				float accumulated = Payment.getMonthAccumulated(accountNumber);
				int appliedDiscount = 0;
				for(int[] bands : flex.getDiscountRate()) {
					if(bands[0] < accumulated && accumulated < bands[1]){
						appliedDiscount = bands[2];
					}
				}

				System.out.println("price with flexible applied" + price * (100 - appliedDiscount) / 100);
				return price * (100 - appliedDiscount) / 100;
			}
			else if (discountType.equals("Variable")) {
				VariableDiscountRate variable = new VariableDiscountRate();
				variable.retrieveVariableDiscount(accountNumber);

				ArrayList<String[]> taskInaJobDataList = TaskInAJob.GetTIJList(jobId);

				for(String[] taskInAJobData : taskInaJobDataList){
					for(Pair<Integer, Integer> discount : variable.getTaskDiscountRate()){
						if(Integer.parseInt(taskInAJobData[2]) == discount.getKey()){
							System.out.println("match");
							String[] taskDescriptionData = TaskDescription.getTask(Integer.parseInt(taskInAJobData[2]));
							if(taskDescriptionData != null) {
								System.out.print("\ndiscount amount = " + discount.getValue());
								float taskPrice = Float.parseFloat(taskDescriptionData[2]);
								System.out.print("\nprice deducted with variable = " + (taskPrice * (discount.getValue()) / 100 ));
								price -= (taskPrice * (discount.getValue()) / 100 );
							}
						}
					}
				}
			}
			System.out.println("calculated price = " + price);
			return price;
		}
		return -1;
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

	/**
	 * takes user to a page.
	 * use window.showScreen() to call coupled method, onLeave()
	 * @param name fxml file name of destination
	 */
	public void showScreen(Window gui, String name){
		main.showScreen(gui, name);
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

	public void setEditingCustomerNumber(int accountNumber){
		editCustomerDetailsScreen.setAccountNumber(accountNumber);
	}

	public void setEditingCustomer(String[] customerData){
		editCustomerDetailsScreen.setAccountData(customerData);
	}

    public void addNewCustomer(String status,
							   String phone,
							   String address,
							   String email,
							   String name,
							   String contactName) throws SQLException {
		new CustomerAccountDetails(status, phone, address, email, name, contactName);
    }
}