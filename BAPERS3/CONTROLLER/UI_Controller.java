package CONTROLLER;

import GUI.HomeScreen;
import GUI.RecordPaymentScreen;
import GUI.ReportsScreen;
import GUI.Window;

import java.io.IOException;

public class UI_Controller {

	private Main main;

	//gui handled by this controller
	private RecordPaymentScreen recordPaymentScreen;
	private String recordPaymentFxml = "";

	private ReportsScreen reportsScreen;
	private String reportsFxml = "";

	private HomeScreen homeScreen;
	private String homeFxml = "HomeScreen";

	/**
	 * 
	 * @param PaymentDetails
	 */
	public boolean SavePayment(String[] PaymentDetails) {
		// TODO - implement UI_Controller.SavePayment
		throw new UnsupportedOperationException();
	}

	public boolean CancelPayment() {
		// TODO - implement UI_Controller.CancelPayment
		throw new UnsupportedOperationException();
	}

	public boolean GenerateReport() {
		// TODO - implement UI_Controller.GenerateReport
		throw new UnsupportedOperationException();
	}

	public HomeScreen getHomeScreen() {
		return homeScreen;
	}

	public ReportsScreen getReportsScreen() {
		return reportsScreen;
	}

	public RecordPaymentScreen getRecordPaymentScreen() {
		return recordPaymentScreen;
	}

	public Main getMain() {
		return main;
	}

	public UI_Controller(Main main) throws IOException {
		// TODO - implement UI_Controller.UI_Controller
		this.main = main;

		homeScreen = (HomeScreen) Window.newGuiFromFxml(homeFxml);
		homeScreen.setUiController(this);
	}
}