package GUI;

import ADMIN.AlertUser;
import PROCESS.*;
import REPORT.Invoice;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.function.Predicate;

public class JobsScreen extends Window {

	@FXML
	private Button createJobOrderButton;
	@FXML
	private Button processTasksButton1;
	@FXML
	private Button processTasksButton2;
	@FXML
	private Button processTasksButton3;
	@FXML
	private ImageView recordPaymentButton1;
	@FXML
	private ImageView recordPaymentButton2;
	@FXML
	private ImageView recordPaymentButton3;
	@FXML
	private Button paymentsButton1;
	@FXML
	private Button paymentsButton2;
	@FXML
	private Button paymentsButton3;
	@FXML
	private TextField searchField1;
	@FXML
	private TextField searchField2;
	@FXML
	private TextField searchField3;
	@FXML
	private TableView<String[]> jobsTable;
	@FXML
	private TableView<String[]> lateJobsTable;
	@FXML
	private TableView<String[]> completeJobsTable;

	/**
	 * 
	 * @param AccountNumber
	 */
	public Job[] RetrieveJobs(int AccountNumber) {
		// TODO - implement JobsScreen.RetrieveJobs
		throw new UnsupportedOperationException();
	}

	/**
	 * boolean to check if input is contained in the table. uses regex to match pattern first to check
	 * search is non case sensitive, probably easier for user
	 * @param data user account data to be checked
	 */
	private boolean matchJob(String[] data, String input) {

		if(matchNumber(input)){
			return data[0].contains(input)
					|| data[1].contains(input)
					|| data[2].contains(input)
					|| data[3].contains(input)
					|| data[4].contains(input)
					|| data[7].contains(input)
					|| data[8].contains(input);
		}
		if(matchAnyNumberType(input)){
			return data[6].contains(input);
		}
		else{
			return data[3].toLowerCase().contains(input.toLowerCase())
					|| data[4].toLowerCase().contains(input.toLowerCase())
					|| data[5].toLowerCase().contains(input.toLowerCase());
		}
	}

	/**
	 * used for filtered lists which can dynamically filter data depending on predicate such as this
	 * @param input input in the search field
	 * @return predicate to inform filter list if input matches some data in the list
	 */
	private Predicate<String[]> jobDataPredicate(String input){
		return (String[] data) -> {
			if(input == null || input.trim().isEmpty()){
				return true;
			}
			else{
				return matchJob(data, input);
			}
		};
	}

	public void onLeave(){
		searchField1.clear();
		searchField2.clear();
		jobsTable.setItems(null);
		lateJobsTable.setItems(null);
	}

	private void toPayment(){
		showScreen(this, "Payments");
	}

	private void toMakePayment() {

		if (jobsTable.getSelectionModel().getSelectedItem() != null
				|| completeJobsTable.getSelectionModel().getSelectedItem() != null
				|| lateJobsTable.getSelectionModel().getSelectedItem() != null) {
			String[] jobData;
			if(jobsTable.getSelectionModel().getSelectedItem() != null) {
				jobData = jobsTable.getSelectionModel().getSelectedItem();
			}
			else if (lateJobsTable.getSelectionModel().getSelectedItem() != null){
				jobData = lateJobsTable.getSelectionModel().getSelectedItem();
			}else {
				jobData = completeJobsTable.getSelectionModel().getSelectedItem();
			}
			//TODO assign job id
			try {
				if(!procUiController.checkIfJobPaid(Integer.parseInt(jobData[0]))){
					procUiController.setJobBeingPaid(jobData);
					try {
						Invoice.printInvoice(Integer.parseInt(jobData[0]));
					} catch (Exception e) {
						AlertUser.showDBError();
						e.printStackTrace();
					}
					showScreen(this, "RecordPayment");
				}
				else{
					Alert alert = new Alert(Alert.AlertType.WARNING, "This job is paid already", ButtonType.CLOSE);
					alert.show();
				}
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		else{
			Alert alert = new Alert(Alert.AlertType.ERROR, "Select job to process payment", ButtonType.CLOSE);
			alert.show();
		}
	}

	protected void toCreateJobSetup(){
		showScreen(this, "CreateJobSetup");
	}

	protected void toProcessTasks(){
		if(jobsTable.getSelectionModel().getSelectedItem() != null) {
			String[] jobData = jobsTable.getSelectionModel().getSelectedItem();
			if (/*jobData[8].equals("0")*/true) {
				ProcessTasksScreen.setJobID(Integer.parseInt(jobData[0]));
				showScreen(this, "ProgressTasks");
			}
			else{
				new Alert(Alert.AlertType.ERROR, "Job is complete", ButtonType.CLOSE).show();
			}
		}
		else{
			Alert alert = new Alert(Alert.AlertType.ERROR, "Select job to process", ButtonType.CLOSE);
			alert.show();
		}
	}

	protected void toProcessLateTasks(){
		if(lateJobsTable.getSelectionModel().getSelectedItem() != null) {
			String[] jobData = lateJobsTable.getSelectionModel().getSelectedItem();
			if (/*jobData[8].equals("0")*/true) {
				ProcessTasksScreen.setJobID(Integer.parseInt(jobData[0]));
				showScreen(this, "ProgressTasks");
			}
		}
		else{
			Alert alert = new Alert(Alert.AlertType.ERROR, "Select job to process", ButtonType.CLOSE);
			alert.show();
		}
	}

	protected void toProcessCompleteTasks(){
		if(completeJobsTable.getSelectionModel().getSelectedItem() != null) {
			String[] jobData = completeJobsTable.getSelectionModel().getSelectedItem();
			if (/*jobData[8].equals("0")*/true) {
				ProcessTasksScreen.setJobID(Integer.parseInt(jobData[0]));
				showScreen(this, "ProgressTasks");
			}
		}
		else{
			Alert alert = new Alert(Alert.AlertType.ERROR, "Select job to process", ButtonType.CLOSE);
			alert.show();
		}
	}

	@Override
	public void onShow(){
		super.onShow();
		//get list of jobs here
		ObservableList<String[]> jobData = FXCollections.observableArrayList();
		ObservableList<String[]> lateJobData = FXCollections.observableArrayList();
		ObservableList<String[]> completeJobData = FXCollections.observableArrayList();
		try {
			jobData.addAll(Job.GetJobList());
			Job.SetLateJobList();
			lateJobData.addAll(Job.GetLateJobList());
			completeJobData.addAll(Job.GetCompleteJobList());
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		FilteredList<String[]> jobFilteredList = new FilteredList<>(jobData);
		FilteredList<String[]> lateJobFilteredList = new FilteredList<>(lateJobData);
		FilteredList<String[]> completeJobFilteredList = new FilteredList<>(completeJobData);

		searchField1.textProperty().addListener(((observableValue, old, newValue) -> {
			jobsTable.getSelectionModel().clearSelection();
			jobFilteredList.setPredicate(jobDataPredicate(newValue));
		}));
		searchField2.textProperty().addListener(((observableValue, old, newValue) -> {
			lateJobsTable.getSelectionModel().clearSelection();
			lateJobFilteredList.setPredicate(jobDataPredicate(newValue));
		}));
		searchField3.textProperty().addListener(((observableValue, old, newValue) -> {
			lateJobsTable.getSelectionModel().clearSelection();
			completeJobFilteredList.setPredicate(jobDataPredicate(newValue));
		}));

		jobsTable.setItems(jobFilteredList);
		lateJobsTable.setItems(lateJobFilteredList);
		completeJobsTable.setItems(completeJobFilteredList);
	}
	/**
	 *
	 */
	@FXML
	public void initialize(){
		super.initialize();

		createJobOrderButton.setOnAction(actionEvent -> toCreateJobSetup());
		processTasksButton1.setOnAction(actionEvent -> toProcessTasks());
		processTasksButton2.setOnAction(actionEvent -> toProcessLateTasks());
		processTasksButton3.setOnAction(actionEvent -> toProcessCompleteTasks());
		paymentsButton1.setOnAction(actionEvent -> toPayment());
		paymentsButton2.setOnAction(actionEvent -> toPayment());
		paymentsButton3.setOnAction(actionEvent -> toPayment());
		recordPaymentButton1.setOnMouseClicked(mouseEvent -> toMakePayment());
		recordPaymentButton2.setOnMouseClicked(mouseEvent -> toMakePayment());
		recordPaymentButton3.setOnMouseClicked(mouseEvent -> toMakePayment());
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

		for (int i = 0; i < completeJobsTable.getColumns().size(); i++) {
			TableColumn tc = completeJobsTable.getColumns().get(i);
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