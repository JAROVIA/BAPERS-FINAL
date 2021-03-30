package GUI;

import ADMIN.AlertUser;
import ADMIN.DBConfiguration;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseScreen extends Window {

	/*
	private Button LogOutButton;
	private Button DBBackupButton;
	private Button DBRestoreButton;
	 */
	@FXML
	private ComboBox<String> backupBox;
	@FXML
	private ComboBox<String> restoreBox;
	@FXML
	private Button backupButton;
	@FXML
	private Button restoreButton;

	public Void selectTime() {
		// TODO - implement DatabaseScreen.SelectTime
		throw new UnsupportedOperationException();
	}

	private void setFilesToBox(){
		restoreBox.getItems().clear();
		ArrayList<String> files = DBConfiguration.GetListOfFiles();
		restoreBox.getItems().addAll(files);
	}

	private void onBackup(){
		try {
			DBConfiguration.MakeBackup();
			onShow();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void onRestore(){
		try {
			DBConfiguration.RestoreListedBackup(restoreBox.getValue());
		} catch (IOException | SQLException e) {
			AlertUser.showDBError();
			e.printStackTrace();
		}
	}

	public void onShow(){
		super.onShow();

		backupBox.getSelectionModel().clearSelection();
		restoreBox.getSelectionModel().clearSelection();
		setFilesToBox();
	}

	/**
	 */
	@FXML
	public void initialize(){
		super.initialize();
		userAllowed = new String[]{ROLE_OFFICE_MANAGER};

		setComboBoxPromptText(restoreBox, "Select file to restore from");
		restoreBox.setPromptText("Select file to restore from");
		backupButton.setOnAction(actionEvent -> onBackup());
		restoreButton.setOnAction(actionEvent -> onRestore());
	}
}