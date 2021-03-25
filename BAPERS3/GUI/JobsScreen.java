package GUI;

import PROCESS.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.ArrayList;

public class JobsScreen extends Window {

	@FXML
	private Button createJobOrderButton;
	@FXML
	private Button processTasksButton1;
	@FXML
	private Button processTasksButton2;
	@FXML
	private ImageView recordPaymentButton1;
	@FXML
	private ImageView recordPaymentButton2;
	@FXML
	private Button paymentsButton1;
	@FXML
	private Button paymentsButton2;
	@FXML
	private TextField searchField1;
	@FXML
	private TextField searchField2;
	@FXML
	private TableView<String[]> jobsTable;
	@FXML
	private TableView<String[]> lateJobsTable;

	/**
	 * 
	 * @param AccountNumber
	 */
	public Job[] RetrieveJobs(int AccountNumber) {
		// TODO - implement JobsScreen.RetrieveJobs
		throw new UnsupportedOperationException();
	}

	private void toPayment(){
		procUiController.showScreen("Payments");
	}

	private void toMakePayment() {
		String[] jobData = jobsTable.getSelectionModel().getSelectedItem();
		if (jobData != null) {
			procUiController.showScreen("RecordPayment");
		}
	}

	protected void toCreateJobSetup(){
		procUiController.showScreen("CreateJobSetup");
	}

	protected void toProcessTasks(){
		String[] jobData = jobsTable.getSelectionModel().getSelectedItem();
		if(jobData != null && jobData[7].equals("0")) {
			procUiController.showScreen("ProgressTasks");
		}
	}
	@Override
	public void toMain(){
		super.toMain();
		onBack();
	}

	private void onBack(){
		lateJobsTable.getItems().clear();
		jobsTable.getItems().clear();
	}

	@Override
	public void onShow(){
		super.onShow();
		//get list of jobs here
		ObservableList<String[]> jobData = FXCollections.observableArrayList();
		ObservableList<String[]> lateJobData = FXCollections.observableArrayList();
		try {
			jobData.addAll(Job.GetJobList());
			lateJobData.addAll(Job.GetLateJobList());
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		jobsTable.setItems(jobData);
		lateJobsTable.setItems(lateJobData);
	}
	/**
	 *
	 */
	@FXML
	public void initialize(){
		super.initialize();

		createJobOrderButton.setOnAction(actionEvent -> toCreateJobSetup());
		processTasksButton1.setOnAction(actionEvent -> toProcessTasks());
		processTasksButton2.setOnAction(actionEvent -> toProcessTasks());
		paymentsButton1.setOnAction(actionEvent -> toPayment());
		paymentsButton2.setOnAction(actionEvent -> toPayment());
		recordPaymentButton1.setOnMouseClicked(mouseEvent -> toMakePayment());
		recordPaymentButton2.setOnMouseClicked(mouseEvent -> toMakePayment());
		userAllowed = new String[]{ROLE_OFFICE_MANAGER, ROLE_SHIFT_MANAGER, ROLE_RECEPTIONIST, ROLE_TECHNICIAN_COPY, ROLE_TECHNICIAN_DEV, ROLE_TECHNICIAN_PACK, ROLE_TECHNICIAN_FIN};

		//populate columns for job table
		for (int i = 0; i < jobsTable.getColumns().size(); i++) {
			TableColumn tc = jobsTable.getColumns().get(i);
			int j = i;
			tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> property) {
					return new SimpleStringProperty((property.getValue()[j]));
				}
			});
		}

		//populate columns for late job
		for (int i = 0; i < lateJobsTable.getColumns().size(); i++) {
			TableColumn tc = lateJobsTable.getColumns().get(i);
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