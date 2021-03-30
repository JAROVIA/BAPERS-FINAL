package CONTROLLER;

import ADMIN.AlertUser;
import ADMIN.UserAccount;
import GUI.HomeScreen;
import GUI.RecordPaymentScreen;
import GUI.ReportsScreen;
import GUI.Window;
import PAYMENT.Payment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UI_Controller {

	private Main main;

	//gui handled by this controller
	private RecordPaymentScreen recordPaymentScreen;
	private String recordPaymentFxml = "RecordPayment";

	private ReportsScreen reportsScreen;
	private String reportsFxml = "Reports";

	private HomeScreen homeScreen;
	private String homeFxml = "HomeScreen";

	private Map<String, Window> screens;

	/**
	 * 
	 * @param paymentDetails
	 */
	public void saveCardPayment(String[] paymentDetails) {
		try {
			new Payment(
					Integer.parseInt(paymentDetails[0]),
					Integer.parseInt(paymentDetails[1]),
					Float.parseFloat(paymentDetails[2]),
					paymentDetails[3],
					paymentDetails[4],
					paymentDetails[5],
					paymentDetails[6],
					paymentDetails[7],
					Integer.parseInt(paymentDetails[8]),
					Integer.parseInt(paymentDetails[9])
			);
		} catch (SQLException throwables) {
			AlertUser.showDBError();
			throwables.printStackTrace();
		}
	}

	public void saveCashPayment(String[] paymentDetails){
//		int jobID, int accountNumber, float price, String dateOfPayment,
//				String paymentType
		try {
			new Payment(
					Integer.parseInt(paymentDetails[0]),
					Integer.parseInt(paymentDetails[1]),
					Float.parseFloat(paymentDetails[2]),
					paymentDetails[3],
					paymentDetails[4]
			);
		} catch (SQLException throwables) {
			AlertUser.showDBError();
			throwables.printStackTrace();
		}
	}

	public boolean CancelPayment() {
		// TODO - implement UI_Controller.CancelPayment
		throw new UnsupportedOperationException();
	}

	public boolean GenerateReport() {
		// TODO - implement UI_Controller.GenerateReport
		throw new UnsupportedOperationException();
	}

	public void setJobBeingPaid(String[] jobData){
		recordPaymentScreen.setJobData(jobData);
	}

	public UserAccount getLoggedInUser(){
		return main.getAdminUiController().getLoggedInUser();
	}

	public void showScreen(Window gui, String name){
		main.showScreen(gui, name);
	}

	public Window getScreen(String name){
		return screens.get(name);
	}

	public UI_Controller(Main main) throws IOException {
		// TODO - implement UI_Controller.UI_Controller
		this.main = main;
		//map to match string name to respective gui class for easier lookup
		screens = new HashMap<>();

		homeScreen = (HomeScreen) Window.newGuiFromFxml(homeFxml);
		screens.put(homeFxml, homeScreen);

		recordPaymentScreen = (RecordPaymentScreen) Window.newGuiFromFxml(recordPaymentFxml);
		screens.put(recordPaymentFxml, recordPaymentScreen);

		reportsScreen = (ReportsScreen) Window.newGuiFromFxml(reportsFxml);
		screens.put(reportsFxml, reportsScreen);

		for(Map.Entry<String, Window> entry : screens.entrySet()){
			main.addScreen(entry.getKey(), entry.getValue().getParent(), "UI");
			entry.getValue().setUiController(this);
		}
	}
}