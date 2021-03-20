package GUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TasksScreen extends Window {

	@FXML
	private Button deleteTaskButton;
	@FXML
	private Button editTaskButton;
	@FXML
	private Button addNewTaskButton;
	@FXML
	private TableView<String[]> tasksTable;

	//only for OM

	public void removeTask() {
		// TODO - implement TasksScreen.RemoveTask
		throw new UnsupportedOperationException();
	}

	public void toAddTask() {
		procUiController.showScreen("AddNewTask");
	}

	public void toEditTask() {
		String id = tasksTable.getSelectionModel().getSelectedItem()[0];
		if(id != null) {
			//TODO match id
			//assign to pro_ui
			procUiController.showScreen("EditExistingTasks");
		}
	}

	public void onShow(){
		super.onShow();
		ArrayList<String[]> test = new ArrayList<>();
		test.add(new String[]{"a", "b", "c", "d", "e"});
		test.add(new String[]{"e", "f", "g", "h", "f"});
		ObservableList<String[]> data = FXCollections.observableArrayList();

		data.addAll(test);
		tasksTable.setItems(data);
	}
	/**
	 */
	@FXML
	public void initialize(){
		super.initialize();
		userAllowed = new String[]{ROLE_OFFICE_MANAGER};
		editTaskButton.setOnAction(actionEvent -> toEditTask());
		addNewTaskButton.setOnAction(actionEvent -> toAddTask());

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