package GUI;

import ADMIN.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import java.util.ArrayList;

public class UserAccountScreen extends Window {

	/*
	private Button RegisterNewUserButton;
	private Button LogoutButton;
	private Button EditUserButton;
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

	public void showContent(){
		//TODO get data here
		//below is just for test
		ArrayList<String[]> list = new ArrayList<>();
		list.add(new String[]{"a", "b", "c", "d"});
		list.add(new String[]{"e", "f", "g", "h"});

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

		/*
		TableColumn<String[], String> userIdColumn = new TableColumn<>();
		userIdColumn.setText("userID");
		TableColumn<String[], String> staffNameColumn = new TableColumn<>();
		staffNameColumn.setText("staffName");
		TableColumn<String[], String> usernameColumn = new TableColumn<>();
		usernameColumn.setText("username");
		TableColumn<String[], String> roleColumn = new TableColumn<>();
		roleColumn.setText("Role");
		 */

		userAccountTable.setItems(data);
	}

	public void toRegisterUser(){
		adminUiController.getMain().showScreen("RegisterNewUser");
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param xSize
	 * @param ySize
	 * @param icon
	 */
	public static UserAccountScreen LogOut(int x, int y, int xSize, int ySize, String icon) {
		// TODO - implement UserAccountScreen.LogOut
		throw new UnsupportedOperationException();
	}

}