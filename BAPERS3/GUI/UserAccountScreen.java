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

import java.sql.SQLException;
import java.util.ArrayList;

public class UserAccountScreen extends Window {

	/*
	private Button DeleteUserButton;

	 */
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
	private Button registerNewUserButton;
	@FXML
	private Button editUserButton;

	/**
	 * 
	 * @param UserAccountDetails
	 */
	public UserAccount[] searchUser(String UserAccountDetails) {
		// TODO - implement UserAccountScreen.SearchUser
		throw new UnsupportedOperationException();
	}

	public void deleteUserAcc() {
		// TODO - implement UserAccountScreen.DeleteUserAcc
		throw new UnsupportedOperationException();
	}


	public void onShow() {
		//TODO get data here
		ArrayList<String[]> list = new ArrayList<>();
		try {
			 list = UserAccount.getUserList();
		}
		catch (SQLException e){
			e.printStackTrace();
		}

		ObservableList<String[]> data = FXCollections.observableArrayList();
		for(int i = 0; i < list.size(); i++){
			String[] newData = new String[]{list.get(i)[0], list.get(i)[1], list.get(i)[2], list.get(i)[4]};
			data.add(newData);
		}

		for (int i = 0; i < data.get(0).length; i++) {
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

	private void toRegisterUser(){
		adminUiController.showScreen("RegisterNewUser");
	}

	private void toEditUser() throws SQLException {
		String userId = userAccountTable.getSelectionModel().getSelectedItem()[0];
		if(userId != null) {
			for(String[] userData : UserAccount.getUserList()){
				if(userId.equals(userData[0])){
					adminUiController.setEditingUser(new UserAccount(Integer.parseInt(userData[0]),userData[1],userData[2],userData[3],userData[4]));
					adminUiController.showScreen("EditUserDetails");
					break;
				}
			}
		}
	}

	/**
	 *
	 */
	@FXML
	public void initialize(){
		super.initialize();
		userAllowed = new String[]{ROLE_OFFICE_MANAGER};
		registerNewUserButton.setOnAction(actionEvent -> toRegisterUser());
		editUserButton.setOnAction(actionEvent -> {
			try {
				toEditUser();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		});
	}
}