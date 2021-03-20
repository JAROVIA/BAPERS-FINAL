package GUI;

import PROCESS.TaskDescription;
import PROCESS.TaskInAJob;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

	public void onShow(){
		super.onShow();
		ArrayList<String[]> list = new ArrayList<>();
		try {
			list = TaskInAJob.GetTIJList();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		ObservableList<String[]> data = FXCollections.observableArrayList(list);
		tasksInJobTable.setItems(data);

		int completed = 0;
		for(String[] taskData : data){
			if(taskData[8].equals("1")){
				completed += 1;
			}
		}
		showProgress(completed, data.size());
	}

	private void onStart(){
		//TODO do stuff on start button click
		String[] taskData = tasksInJobTable.getSelectionModel().getSelectedItem();
		if(taskData != null && taskData[8].equals("0")){
			// start
		}
	}

	private void showProgress(int completed, int total){
		progressBar.setProgress((double)completed / (double)total);
		System.out.println("progress = " + (double)completed / (double)total);
	}

	private void onComplete(){
		//TODO do stuff on complete click
		String[] taskData = tasksInJobTable.getSelectionModel().getSelectedItem();
		if(taskData != null && (taskData[3].equals(null)||taskData[3].equals(""))&&taskData[8].equals("0")){
			//complete
		}
	}

	private void onCancel(){
		procUiController.showScreen("Jobs");
	}

	/**
	 *
	 */
	@FXML
	public void initialize(){
		super.initialize();
		userAllowed = new String[]{ROLE_OFFICE_MANAGER, ROLE_SHIFT_MANAGER, ROLE_TECHNICIAN_COPY, ROLE_TECHNICIAN_DEV, ROLE_TECHNICIAN_PACK, ROLE_TECHNICIAN_FIN};
		cancelButton.setOnAction(actionEvent -> onCancel());
		completeButton.setOnAction(actionEvent -> onComplete());
		startButton.setOnAction(actionEvent -> onStart());

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