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
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.ArrayList;

public class JobsScreen extends Window {

	@FXML
	private Button createJobOrderButton;
	@FXML
	private Button processTasksButton;
	@FXML
	private Button recordPaymentButton;
	@FXML
	private TextField searchField;
	@FXML
	private TableView<String[]> jobsTable;
	@FXML
	private TableColumn<Job, Number> jobIdColumn;
	@FXML
	private TableColumn<Job, Number> accountNumberColumn;
	@FXML
	private TableColumn <Job, Number> numberOfTasksColumn;
	@FXML
	private TableColumn <Job, String> dateOfJobColumn;
	@FXML
	private TableColumn <Job, String> urgencyColumn;
	@FXML
	private TableColumn<Job, Number> priceColumn;
	@FXML
	private TableColumn <Job, String> completedByColumn;

	/**
	 * 
	 * @param AccountNumber
	 */
	public Job[] RetrieveJobs(int AccountNumber) {
		// TODO - implement JobsScreen.RetrieveJobs
		throw new UnsupportedOperationException();
	}

	public String RetrieveTextArea() {
		// TODO - implement JobsScreen.RetrieveTextArea
		throw new UnsupportedOperationException();
	}

	private void toPayment() {
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
	public void onShow(){
		super.onShow();
		ArrayList<String[]> list = new ArrayList<>();
		//get list of jobs here
		try {
			list = Job.GetJobList();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		ObservableList<String[]> data = FXCollections.observableArrayList();
		data.addAll(list);
		jobsTable.setItems(data);
	}
	/**
	 *
	 */
	@FXML
	public void initialize(){
		super.initialize();
		recordPaymentButton.setOnAction(actionEvent -> toPayment());
		createJobOrderButton.setOnAction(actionEvent -> toCreateJobSetup());
		processTasksButton.setOnAction(actionEvent -> toProcessTasks());
		userAllowed = new String[]{ROLE_OFFICE_MANAGER, ROLE_SHIFT_MANAGER, ROLE_RECEPTIONIST, ROLE_TECHNICIAN_COPY, ROLE_TECHNICIAN_DEV, ROLE_TECHNICIAN_PACK, ROLE_TECHNICIAN_FIN};

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
		/*
		accountNumberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job, Number>, ObservableValue<Number>>() {
			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<Job, Number> property) {
				return new SimpleIntegerProperty((property.getValue().get));
			}
		});

		numberOfTasksColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job, Number>, ObservableValue<Number>>() {
			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<Job, Number> property) {
				return new SimpleIntegerProperty((property.getValue().getNumberOfTasks()));
			}
		});

		jobIdColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job, Number>, ObservableValue<Number>>() {
			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<Job, Number> property) {
				return new SimpleIntegerProperty((property.getValue().getJobID()));
			}
		});

		urgencyColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Job, String> property) {
				return new SimpleStringProperty((property.getValue().getUrgency()));
			}
		});

		priceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job, Number>, ObservableValue<Number>>() {
			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<Job, Number> property) {
				return new SimpleFloatProperty((property.getValue().getJobPrice()));
			}
		});

		 */
	}

}