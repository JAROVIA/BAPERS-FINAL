package GUI;

import ACCOUNT.*;
import ADMIN.AlertUser;
import PROCESS.Job;
import PROCESS.TaskDescription;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

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
	private TableView<String[]> taskTable;

	private ObservableList<String[]> data;
	private ArrayList<String[]> list = new ArrayList<>();

	static String url = "jdbc:mysql://localhost:3306/Bapers";
	static String username = "jaroviadb";
	static String password = "Jarovia123#@!";
	static Connection connection;

	static {
		try {
			connection = DriverManager.getConnection(
					url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public CustomerAccountDetails searchAccount() {
		// TODO - implement CreateJobScreen.SearchAccount
		throw new UnsupportedOperationException();
	}

	public void onCancel() {
		try {
			Job.DeleteLastJob();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		super.showScreen(this, "Jobs");
	}

	public void confirmJob() {
		try {
			EnterTasksIntoJob();
			String sql = "UPDATE Jobs SET Price = " + Job.getJobPrice() + " WHERE JobID = " + Job.getJobID() + ";";
			System.out.println(sql);
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);

			AlertUser.showCompletion("Job data submit");
			super.showScreen(this, "Jobs");

		} catch (SQLException throwables) {
			AlertUser.showDBError();
		}
	}

	@Override
	public void onShow(){
		super.onShow();
		//get all tasks operable by BIPL
		try {
			for(String[] sa : TaskDescription.GetTaskList()){
				//fix one of these
				//from static method
				//TaskID DescriptionOfTask TaskLocation TaskPrice Duration
				//columns
				//id location price description duration
				list.add(new String[]{sa[0], sa[2], sa[3], sa[1], sa[4]});
			}
		} catch(SQLException e){
			e.printStackTrace();
		}

		for(String[] taskData : list){
			tasksComboBox.getItems().add(taskData[0] + ", " + taskData[1] + ", " + taskData[2] + ", " + taskData[3] + ", " + taskData[4]);
		}
		taskTable.setItems(data);
		jobPriceLabel.setText("0");
	}

	@Override
	public void onLeave(){
		tasksComboBox.getItems().clear();
		data.clear();
		list.clear();
		taskTable.getItems().clear();
		jobPriceLabel.setText("");
	}

	private void addTaskToJob(){
		if(tasksComboBox.getSelectionModel().getSelectedItem() != null) {
			String[] taskData = tasksComboBox.getSelectionModel().getSelectedItem().split(",");
			data.add(taskData);

			float total = Float.parseFloat(jobPriceLabel.getText());
			total += Float.parseFloat(taskData[2]);
			jobPriceLabel.setText(String.format("%.2f", total));
			Job.setJobPrice(total);

			try {
				CreateTIJInsertList(Job.getJobID(), Integer.parseInt(taskData[0]), Job.getAccountNumber(), Job.getUrgency());
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}

	// todo
	public void deleteTask(){
		if(taskTable.getSelectionModel().getSelectedItem() != null) {
			String[] taskData = taskTable.getSelectionModel().getSelectedItem();
			float total = Float.parseFloat(jobPriceLabel.getText());
			total -= Float.parseFloat(taskData[2]);

			jobPriceLabel.setText(String.format("%.2f", total));
			Job.setJobPrice(total);

			data.remove(taskTable.getSelectionModel().getSelectedItem());
			taskTable.getSelectionModel().clearSelection();
		}
		else{
			Alert alert = new Alert(Alert.AlertType.ERROR, "Select a task", ButtonType.CLOSE);
			alert.show();
		}
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
		setComboBoxPromptText(tasksComboBox, "Select task for job");
		tasksComboBox.setPromptText("Select task for job");

		//to set columns to recognize String[] data
		//looped for the number of columns
		for (int i = 0; i < taskTable.getColumns().size(); i++) {
			TableColumn tc = taskTable.getColumns().get(i);
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