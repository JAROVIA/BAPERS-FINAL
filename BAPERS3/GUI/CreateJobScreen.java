package GUI;

import ACCOUNT.*;
import PROCESS.Job;
import PROCESS.TaskDescription;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.ArrayList;

import static PROCESS.TaskInAJob.CreateTIJInsertList;
import static PROCESS.TaskInAJob.EnterTasksIntoJob;

public class CreateJobScreen extends Window {

	private TextField SearchAccountTextArea;
	//Combobox = drop down in javaFx
	@FXML
	private ComboBox<String> tasksComboBox;
	@FXML
	private Button insertTaskButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button saveDetailsButton;
	@FXML
	private Button cancelButton;
	@FXML
	private Label jobPriceLabel;
	@FXML
	private TableView<TaskDescription> taskTable;
	@FXML
	private TableColumn<TaskDescription, Number> taskIdColumn;
	@FXML
	private TableColumn<TaskDescription, String> taskLocationColumn;
	@FXML
	private TableColumn<TaskDescription, Number> taskPriceColumn;
	@FXML
	private TableColumn<TaskDescription, String> taskDescriptionColumn;
	@FXML
	private TableColumn<TaskDescription, Number> taskDurationColumn;

	private ObservableList<TaskDescription> data;
	//for testing
	ArrayList<TaskDescription> list = new ArrayList<>();

	public CustomerAccountDetails searchAccount() {
		// TODO - implement CreateJobScreen.SearchAccount
		throw new UnsupportedOperationException();
	}

	public void onCancel() {
		procUiController.showScreen("Jobs");
		try {
			Job.DeleteLastJob();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	public void confirmJob() {

		try {
			EnterTasksIntoJob();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}

	public void onShow(){
		super.onShow();
		//get all tasks operable by BIPL
		try {
			for(String[] sa : TaskDescription.GetTaskList()){
				TaskDescription taskDescription = new TaskDescription(sa[1], Integer.parseInt(sa[3]), sa[2], Integer.parseInt(sa[4]));
				list.add(taskDescription);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		tasksComboBox.getItems().removeAll(tasksComboBox.getItems());
		for(TaskDescription t : list){
			tasksComboBox.getItems().add(t.getTaskID() + ", " + t.getDescriptionOfTask() + ", " + t.getTaskLocation() + ", " + String.format("%.02f",t.getTaskPrice()));
		}
		taskTable.setItems(data);
	}

	protected void onLeave(){
		tasksComboBox.getItems().clear();
		data.clear();
		list.clear();
		taskTable.getItems().clear();
		jobPriceLabel.setText("");
	}

	private void addTaskToJob(){
		String selected = tasksComboBox.getSelectionModel().getSelectedItem();
		if(selected != null) {
			int id = Integer.parseInt(selected.split(",")[0]);
			for (TaskDescription t : list) {
				if (t.getTaskID() == id) {
					data.add(t);
					break;
				}
			}
			taskTable.setItems(data);
			float total = 0;
			for(TaskDescription t : data){
				total += t.getTaskPrice();
			}
			jobPriceLabel.setText(String.format("%.2f", total));
		}

		try {
			CreateTIJInsertList(Job.getJobID(), TaskDescription.getTaskIDStatic(), Job.getAccountNumber(), Job.getUrgency());
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
	}

	// todo
	public void deleteTask(){
		int id = taskTable.getSelectionModel().getSelectedIndex();
		System.out.println(id);
		if(id >= 0) {
			data.remove(id);
		}
		taskTable.getSelectionModel().clearSelection();
		float total = 0;
		for(TaskDescription t : data){
			total += t.getTaskPrice();
		}
		jobPriceLabel.setText(String.format("%.2f", total));
	}

	/**
	 */
	@FXML
	public void initialize() {
		super.initialize();
		data = FXCollections.observableArrayList(new ArrayList<>());

		userAllowed = new String[]{ROLE_OFFICE_MANAGER, ROLE_SHIFT_MANAGER, ROLE_RECEPTIONIST};

		deleteButton.setOnAction(actionEvent -> deleteTask());
		insertTaskButton.setOnAction(actionEvent -> addTaskToJob());
		cancelButton.setOnAction(actionEvent -> onCancel());
		saveDetailsButton.setOnAction(actionEvent -> confirmJob());
		taskIdColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TaskDescription, Number>, ObservableValue<Number>>() {
			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<TaskDescription, Number> property) {
				return new SimpleIntegerProperty((property.getValue().getTaskID()));
			}
		});

		taskLocationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TaskDescription, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<TaskDescription, String> property) {
				return new SimpleStringProperty((property.getValue().getTaskLocation()));
			}
		});

		taskDescriptionColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TaskDescription, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<TaskDescription, String> property) {
				return new SimpleStringProperty((property.getValue().getDescriptionOfTask()));
			}
		});

		taskPriceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TaskDescription, Number>, ObservableValue<Number>>() {
			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<TaskDescription, Number> property) {
				return new SimpleFloatProperty((property.getValue().getTaskPrice()));
			}
		});

		taskDurationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TaskDescription, Number>, ObservableValue<Number>>() {
			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<TaskDescription, Number> property) {
				return new SimpleIntegerProperty((property.getValue().getDuration()));
			}
		});
	}
}