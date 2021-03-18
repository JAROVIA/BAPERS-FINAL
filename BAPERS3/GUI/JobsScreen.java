package GUI;

import PROCESS.*;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

import java.util.ArrayList;

public class JobsScreen extends Window {

	@FXML
	private Button createJobOrderButton;
	@FXML
	private Button processTasksButton;
	@FXML
	private Button recordPaymentButton;
	@FXML
	private TextField SearchJob;
	@FXML
	private TableView jobsTable;
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

	public void LogOut() {
		// TODO - implement JobsScreen.LogOut
		throw new UnsupportedOperationException();
	}

	public void OnClick() {
		// TODO - implement JobsScreen.OnClick
		throw new UnsupportedOperationException();
	}

	public String RetrieveTextArea() {
		// TODO - implement JobsScreen.RetrieveTextArea
		throw new UnsupportedOperationException();
	}

	protected void toCreateJobOrder(){
		procUiController.getMain().showScreen("CreateJobOrder");
		procUiController.getMain().getProcUiController().getCreateJobScreen().onShow();
	}

	protected void toProcessTasks(){
		procUiController.getMain().showScreen("ProgressTasks");
	}

	@Override
	public void onShow(){
		ArrayList<Job> list = new ArrayList<>();
		//get list of jobs here
		list.add(new Job(1, 2, "a", 2, "a", "a", "a",2.00f));
		ObservableList<Job> data = FXCollections.observableArrayList();
		data.addAll(list);
		jobsTable.setItems(data);
	}
	/**
	 *
	 */
	@FXML
	public void initialize(){
		super.initialize();
		createJobOrderButton.setOnAction(actionEvent -> toCreateJobOrder());
		processTasksButton.setOnAction(actionEvent -> toProcessTasks());

		accountNumberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job, Number>, ObservableValue<Number>>() {
			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<Job, Number> property) {
				return new SimpleIntegerProperty((property.getValue().getAccountNumber()));
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
	}

}