package GUI;

import ADMIN.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class UserAccountScreen extends Window {

	@FXML
	private TextField searchField;
	@FXML
	private TableView<String[]> userAccountTable;
	@FXML
	private Button registerNewUserButton;
	@FXML
	private Button editUserButton;
	@FXML
	private Button deleteButton;

	/**
	 * boolean to check if input is contained in the table. uses regex to match pattern first to check
	 * search is non case sensitive, probably easier for user
	 * @param data user account data to be checked
	 */
	private boolean matchUser(String[] data, String input) {
		if(matchName(input)){
			return data[1].toLowerCase().contains(input.toLowerCase())
					|| data[2].toLowerCase().contains(input.toLowerCase());
		}

		if(matchNumber(input)){
			return data[0].toLowerCase().contains(input.toLowerCase());
		}
		else{
			return data[3].toLowerCase().contains(input.toLowerCase());
		}
	}

	/**
	 * used for filtered lists which can dynamically filter data depending on predicate such as this
	 * @param input input in the search field
	 * @return predicate to inform filter list if input matches some data in the list
	 */
	private Predicate<String[]> userDataPredicate(String input){
		return (String[] data) -> {
			if(input == null || input.trim().isEmpty()){
				return true;
			}
			else{
				return matchUser(data, input);
			}
		};
	}

	public void deleteUserAcc() {
		// TODO - implement UserAccountScreen.DeleteUserAcc
		throw new UnsupportedOperationException();
	}


	/**
	 * overridden method invoked when page is shown
	 */
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
		FilteredList<String[]> filteredData = new FilteredList<>(data);
		userAccountTable.setItems(filteredData);

		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			userAccountTable.getSelectionModel().clearSelection();
			filteredData.setPredicate(userDataPredicate(newValue));
		}  );
	}

	@Override
	protected void onLeave(){
		searchField.clear();
		userAccountTable.setItems(null);
	}

	private void toRegisterUser(){
		adminUiController.showScreen("RegisterNewUser");
		onLeave();
	}

	private void toEditUser() throws SQLException {
		if(userAccountTable.getSelectionModel().getSelectedItem() != null) {
			String userId = userAccountTable.getSelectionModel().getSelectedItem()[0];
			for (String[] user : UserAccount.getUserList()) {
				if (userId.equals(user[0])) {
					adminUiController.setEditingUser(new UserAccount(Integer.parseInt(user[0]), user[1], user[2], user[3], user[4]));
					adminUiController.showScreen("EditUserDetails");
					onLeave();
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

		//to set columns to recognize String[] data
		//looped for the number of columns
		for (int i = 0; i < userAccountTable.getColumns().size(); i++) {
			TableColumn tc = userAccountTable.getColumns().get(i);
			int j = i;
			tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<String[], String> property) {
					return new SimpleStringProperty((property.getValue()[j]));
				}
			});
		}
	}
}