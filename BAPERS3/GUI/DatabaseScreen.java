package GUI;

import ADMIN.AlertUser;
import ADMIN.DBConfiguration;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseScreen extends Window {

	@FXML
	private ComboBox<String> restoreBox;
	@FXML
	private Button backupButton;
	@FXML
	private TextField backupMinutesField;
	@FXML
	private Button confirmButton;
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
			AlertUser.showCompletion("Database backup");
			onShow();
		} catch (IOException e) {
			AlertUser.showDBError();
		}
	}

	private void onRestore(){
		try {
			DBConfiguration.RestoreListedBackup(restoreBox.getValue());
			AlertUser.showCompletion("Database restore");
		} catch (IOException | SQLException e) {
			AlertUser.showDBError();
		}
	}

	public void onShow(){
		super.onShow();

		backupMinutesField.clear();
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
		backupButton.setOnAction(actionEvent -> onBackup());
		restoreButton.setOnAction(actionEvent -> onRestore());
	}
}