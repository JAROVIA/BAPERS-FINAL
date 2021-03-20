package CONTROLLER;

import ADMIN.UserAccount;
import GUI.HomeScreen;
import GUI.RecordPaymentScreen;
import GUI.ReportsScreen;
import GUI.Window;

import java.io.IOException;
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

	public UserAccount getLoggedInUser(){
		return main.getAdminUiController().getLoggedInUser();
	}

	public void showScreen(String name){
		main.showScreen(name);
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