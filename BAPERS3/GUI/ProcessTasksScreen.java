package GUI;

import PROCESS.TaskInAJob;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProcessTasksScreen extends Window {

	@FXML
	private Button startButton;
	@FXML
	private Button completeButton;
	@FXML
	private Button cancelButton;
	@FXML
	private TableView<String[]> tasksInJobTable;
	@FXML
	private ProgressBar progressBar;

	private static int jobID;

	public static void setJobID(int JobID) {
		jobID = JobID;
	}

	public void onShow(){
		super.onShow();
		ArrayList<String[]> list = new ArrayList<>();
		try {
			list = TaskInAJob.GetTIJList(jobID);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		ObservableList<String[]> data = FXCollections.observableArrayList(list);
		tasksInJobTable.setItems(data);

		int completed = 0;
		for(String[] taskData : data){
			if(taskData[9].equals("1")){
				completed += 1;
			}
		}
		showProgress(completed, data.size());
	}

	private void onTaskStart(){
		//TODO do stuff on start button click

		if(tasksInJobTable.getSelectionModel().getSelectedItem() != null) {
			String[] taskInAJobData = tasksInJobTable.getSelectionModel().getSelectedItem();
			if (taskInAJobData[9].equals("1")) {
				Alert alert = new Alert(Alert.AlertType.ERROR, "Task already complete", ButtonType.CLOSE);
				alert.show();
			}
			else if(!taskInAJobData[4].equals("UNSPECIFIED")){
				Alert alert = new Alert(Alert.AlertType.ERROR, "Task already started", ButtonType.CLOSE);
				alert.show();
			}
			else{
				try {
					TaskInAJob.StartTask(Integer.parseInt(taskInAJobData[0]));
					onShow();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		}
		else{
			Alert alert = new Alert(Alert.AlertType.ERROR, "Select task to process", ButtonType.CLOSE);
			alert.show();
		}
	}

	private void showProgress(int completed, int total){
		progressBar.setProgress((double)completed / (double)total);
		System.out.println("progress = " + (double)completed / (double)total);
	}

	private void onTaskComplete(){
		//TODO do stuff on complete click
		if(tasksInJobTable.getSelectionModel().getSelectedItem() != null) {
			String[] taskInAJobData = tasksInJobTable.getSelectionModel().getSelectedItem();
			if (taskInAJobData[9].equals("1")) {
				Alert alert = new Alert(Alert.AlertType.ERROR, "Task already complete", ButtonType.CLOSE);
				alert.show();
			}
			else if(taskInAJobData[4].equals("")){
				Alert alert = new Alert(Alert.AlertType.ERROR, "Task not yet started", ButtonType.CLOSE);
				alert.show();
			}
			else{
				try {
					TaskInAJob.CompleteTask(Integer.parseInt(taskInAJobData[0]));
					onShow();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		}
		else{
			Alert alert = new Alert(Alert.AlertType.ERROR, "Select task to process", ButtonType.CLOSE);
			alert.show();
		}
	}

	private void onCancel(){
		procUiController.showScreen(this, "Jobs");
	}

	/**
	 *
	 */
	@FXML
	public void initialize(){
		super.initialize();
		userAllowed = new String[]{ROLE_OFFICE_MANAGER, ROLE_SHIFT_MANAGER, ROLE_TECHNICIAN_COPY, ROLE_TECHNICIAN_DEV, ROLE_TECHNICIAN_PACK, ROLE_TECHNICIAN_FIN};
		cancelButton.setOnAction(actionEvent -> onCancel());
		completeButton.setOnAction(actionEvent -> onTaskComplete());
		startButton.setOnAction(actionEvent -> onTaskStart());

		for (int i = 0; i < tasksInJobTable.getColumns().size(); i++) {
			TableColumn tc = tasksInJobTable.getColumns().get(i);
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