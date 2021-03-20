package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class DatabaseScreen extends Window {

	/*
	private Button LogOutButton;
	private Button DBBackupButton;
	private Button DBRestoreButton;
	 */
	private ComboBox BackUpTimeMenu;
	private ComboBox DBRestoreTimeMenu;

	public Void selectTime() {
		// TODO - implement DatabaseScreen.SelectTime
		throw new UnsupportedOperationException();
	}

	public void onShow(){
		super.onShow();
	}

	/**
	 */
	@FXML
	public void initialize(){
		super.initialize();
		System.out.println("db");
		userAllowed = new String[]{ROLE_OFFICE_MANAGER};
	}
}