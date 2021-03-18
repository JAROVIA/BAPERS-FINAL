package GUI;

import ACCOUNT.*;
import PROCESS.Job;
import PROCESS.Task;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.util.ArrayList;

public class CreateJobScreen extends Window {

	/*
	private Button LogOutButton;
	private Button CreateCustomerAccButton;
	private Button CancelJobButton;
	private Button ConfirmJobButton;

	 */
	private TextField SearchAccountTextArea;
	//Comboboox = drop down in javaFx
	@FXML
	private ComboBox<String> tasksComboBox;
	@FXML
	private ComboBox<String> urgencyComboBox;
	@FXML
	private Button insertTaskButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Label jobPriceLabel;
	@FXML
	private TableView<Task> taskTable;
	@FXML
	private TableColumn<Task, Number> taskIdColumn;
	@FXML
	private TableColumn<Task, String> taskLocationColumn;
	@FXML
	private TableColumn<Task, Number> taskPriceColumn;
	@FXML
	private TableColumn<Task, String> taskDescriptionColumn;
	@FXML
	private TableColumn<Task, Number> taskDurationColumn;

	private ObservableList<Task> data;
	//for testing
	ArrayList<Task> list = new ArrayList<>();

	public CustomerAccountDetails searchAccount() {
		// TODO - implement CreateJobScreen.SearchAccount
		throw new UnsupportedOperationException();
	}

	public void cancel() {
		// TODO - implement CreateJobScreen.Cancel
		throw new UnsupportedOperationException();
	}

	public void confirmJob() {
		// TODO - implement CreateJobScreen.ConfirmJob
		throw new UnsupportedOperationException();
	}

	public void onShow(){
		//get all tasks operable by BIPL
		list.add(new Task(1, "copying a big photo", "in the basement", .99f, 100));
		list.add(new Task(2, "copying a bigger photo", "in the other basement", .99f, 200));
		list.add(new Task(7, "copying biggest photo", "in the another basement", .99f, 300));
		tasksComboBox.getItems().removeAll(tasksComboBox.getItems());
		for(Task t : list){
			tasksComboBox.getItems().add(t.getTaskID() + ", " + t.getTaskDescription() + ", " + t.getTaskLocation() + ", " + String.format("%.02f",t.getTaskPrice()));
		}
		data.clear();
		taskTable.setItems(data);
	}

	public void addTaskToJob(){
		String selected = tasksComboBox.getSelectionModel().getSelectedItem();
		if(selected != null) {
			int id = Integer.parseInt(selected.split(",")[0]);
			for (Task t : list) {
				if (t.getTaskID() == id) {
					data.add(t);
					break;
				}
			}
			taskTable.setItems(data);
			float total = 0;
			for(Task t : data){
				total += t.getTaskPrice();
			}
			jobPriceLabel.setText(String.format("%.2f", total));
		}
	}

	public void deleteTask(){
		int id = taskTable.getSelectionModel().getSelectedIndex();
		System.out.println(id);
		if(id >= 0) {
			data.remove(id);
		}
		taskTable.getSelectionModel().clearSelection();
		float total = 0;
		for(Task t : data){
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

		urgencyComboBox.getItems().removeAll();
		urgencyComboBox.getItems().addAll("3 hours", "6 hours", "24 hours");
		deleteButton.setOnAction(actionEvent -> deleteTask());
		insertTaskButton.setOnAction(actionEvent -> addTaskToJob());

		taskIdColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Task, Number>, ObservableValue<Number>>() {
			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<Task, Number> property) {
				return new SimpleIntegerProperty((property.getValue().getTaskID()));
			}
		});

		taskLocationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Task, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Task, String> property) {
				return new SimpleStringProperty((property.getValue().getTaskLocation()));
			}
		});

		taskDescriptionColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Task, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Task, String> property) {
				return new SimpleStringProperty((property.getValue().getTaskDescription()));
			}
		});

		taskPriceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Task, Number>, ObservableValue<Number>>() {
			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<Task, Number> property) {
				return new SimpleFloatProperty((property.getValue().getTaskPrice()));
			}
		});

		taskDurationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Task, Number>, ObservableValue<Number>>() {
			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<Task, Number> property) {
				return new SimpleIntegerProperty((property.getValue().getDuration()));
			}
		});
	}
}