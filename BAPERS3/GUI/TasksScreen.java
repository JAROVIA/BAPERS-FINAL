package GUI;

import PROCESS.TaskDescription;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class TasksScreen extends Window {

	@FXML
	private Button deleteTaskButton;
	@FXML
	private Button editTaskButton;
	@FXML
	private Button addNewTaskButton;
	@FXML
	private TableView<String[]> tasksTable;
	@FXML
	private TextField searchField;

	/**
	 * boolean to check if input is contained in the table. uses regex to match pattern first to check
	 * search is non case sensitive, probably easier for user
	 * @param data user account data to be checked
	 */
	private boolean matchTask(String[] data, String input) {
		if(matchName(input)){
			return data[1].toLowerCase().contains(input.toLowerCase())
					|| data[2].toLowerCase().contains(input.toLowerCase());
		}
		if(matchAnyNumberType(input)){
			return data[0].contains(input)
					|| data[3].contains(input)
					|| data[4].contains(input);
		}
		if(matchNumber(input)){
			return data[0].contains(input)
					|| data[3].contains(input)
					|| data[4].contains(input);
		}
		return false;
	}

	/**
	 * used for filtered lists which can dynamically filter data depending on predicate such as this
	 * @param input input in the search field
	 * @return predicate to inform filter list if input matches some data in the list
	 */
	private Predicate<String[]> taskDataPredicate(String input){
		return (String[] data) -> {
			if(input == null || input.trim().isEmpty()){
				return true;
			}
			else{
				return matchTask(data, input);
			}
		};
	}

	public void removeTask() {

		if(tasksTable.getSelectionModel().getSelectedItem() != null) {
			String[] taskData = tasksTable.getSelectionModel().getSelectedItem();
			try {
				System.out.println(taskData[0]);
				TaskDescription.ArchiveTask(Integer.parseInt(taskData[0]));
				onShow();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		else{
			Alert alert = new Alert(Alert.AlertType.ERROR, "Select task to edit", ButtonType.CLOSE);
			alert.show();
		}
	}

	public void toAddTask() {
		showScreen(this, "AddNewTask");
	}

	public void toEditTask() {
		if(tasksTable.getSelectionModel().getSelectedItem() != null) {
			String[] taskData = tasksTable.getSelectionModel().getSelectedItem();
			procUiController.setEditingTask(taskData);
			showScreen(this, "EditExistingTasks");
		}
		else{
			Alert alert = new Alert(Alert.AlertType.ERROR, "Select task to edit", ButtonType.CLOSE);
			alert.show();
		}
	}

	public void onLeave(){
		searchField.clear();
		tasksTable.setItems(null);
	}

	public void onShow(){
		super.onShow();
		ArrayList<String[]> list = new ArrayList<>();
		try {
			list = TaskDescription.GetTaskList();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		ObservableList<String[]> data = FXCollections.observableArrayList(list);

		FilteredList<String[]> filteredData = new FilteredList<>(data);
		tasksTable.setItems(filteredData);

		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			tasksTable.getSelectionModel().clearSelection();
			filteredData.setPredicate(taskDataPredicate(newValue));
		});
	}
	/**
	 */
	@FXML
	public void initialize(){
		super.initialize();
		userAllowed = new String[]{ROLE_OFFICE_MANAGER};
		editTaskButton.setOnAction(actionEvent -> toEditTask());
		addNewTaskButton.setOnAction(actionEvent -> toAddTask());
		deleteTaskButton.setOnAction(actionEvent -> removeTask());

		for (int i = 0; i < tasksTable.getColumns().size(); i++) {
			TableColumn tc = tasksTable.getColumns().get(i);
			int j = i;
			tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> property) {
					return new SimpleStringProperty((property.getValue()[j]));
				}
			});
		}
	}

}