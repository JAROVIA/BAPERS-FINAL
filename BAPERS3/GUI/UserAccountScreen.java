package GUI;

import ADMIN.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserAccountScreen extends Window {

	private TextField searchUserTextArea;
	@FXML
	private TableView<String[]> userAccountTable;
	@FXML
	private TableColumn<String[], String> userIdColumn;
	@FXML
	private TableColumn<String[], String> staffNameColumn;
	@FXML
	private TableColumn<String[], String> usernameColumn;
	@FXML
	private TableColumn<String[], String> roleColumn;
	@FXML
	private Button editUserButton;
	@FXML
	private Button registerNewUserButton;

	/**
	 * 
	 * @param UserAccountDetails
	 */
	public UserAccount[] SearchUser(String UserAccountDetails) {
		// TODO - implement UserAccountScreen.SearchUser
		throw new UnsupportedOperationException();
	}

	public void OnClickButton() {
		// TODO - implement UserAccountScreen.OnClickButton
		throw new UnsupportedOperationException();
	}

	public void DeleteUserAcc() {
		// TODO - implement UserAccountScreen.DeleteUserAcc
		throw new UnsupportedOperationException();
	}

	public String RetrieveTextArea() {
		// TODO - implement UserAccountScreen.RetrieveTextArea
		throw new UnsupportedOperationException();
	}

	public void showContent() throws SQLException {
		//TODO get data here
		//below is just for test

		ArrayList<String[]> list = UserAccount.GetUserList();

		ObservableList<String[]> data = FXCollections.observableArrayList();
		data.addAll(list);

		for (int i = 0; i < list.get(0).length; i++) {
			TableColumn tc = userAccountTable.getColumns().get(i);
			int j = i;
			tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<String[], String> property) {
					return new SimpleStringProperty((property.getValue()[j]));
				}
			});
		}

		userAccountTable.setItems(data);
	}

	public void toRegisterUser(){
		adminUiController.getMain().showScreen("RegisterNewUser");
		adminUiController.getRegisterNewUserScreen().onShow();
	}

	private void toEditUser(){
		adminUiController.getMain().showScreen("EditUserDetails");
	}


	/**
	 */
	@FXML
	public void initialize(){
		super.initialize();
		editUserButton.setOnAction(actionEvent -> toEditUser());
		registerNewUserButton.setOnAction(actionEvent -> toRegisterUser());
	}

}