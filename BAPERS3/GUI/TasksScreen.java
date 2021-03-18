package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class TasksScreen extends Window {

	@FXML
	private Button deleteTaskButton;
	@FXML
	private Button editTaskButton;
	@FXML
	private Button addNewTaskButton;

	private TableView TasksTable;

	//only for OM

	public void RemoveTask() {
		// TODO - implement TasksScreen.RemoveTask
		throw new UnsupportedOperationException();
	}

	public void toAddTask() {
		procUiController.getMain().showScreen("AddNewTask");
	}

	public void toEditTask() {
		procUiController.getMain().showScreen("EditExistingTasks");
	}

	/**
	 */
	@FXML
	public void initialize(){
		super.initialize();
		editTaskButton.setOnAction(actionEvent -> toEditTask());
		addNewTaskButton.setOnAction(actionEvent -> toAddTask());
	}

}