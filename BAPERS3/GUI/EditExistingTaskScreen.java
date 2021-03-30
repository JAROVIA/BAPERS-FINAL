package GUI;

import ADMIN.AlertUser;
import PROCESS.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class EditExistingTaskScreen extends Window {

	@FXML
	private TextField descriptionField;
	@FXML
	private TextField locationField;
	@FXML
	private TextField priceField;
	@FXML
	private TextField durationField;
	@FXML
	private Button confirmButton;
	@FXML
	private Button cancelButton;

	public void setTaskData(String[] taskData) {
		this.taskData = taskData;
	}

	private String[] taskData;

	public TaskInAJob retrieveTask() {
		// TODO - implement UpdateExistingTaskScreen.RetrieveTask
		throw new UnsupportedOperationException();
	}

	private void onConfirm() {
		//TODO save task
		if(isValueNotEmpty(descriptionField, durationField, priceField, locationField)){
			if(isStringFloat(priceField.getText()) && isStringFloat(durationField.getText())){
				submit(new String[]{
						taskData[0],
						descriptionField.getText(),
						locationField.getText(),
						priceField.getText(),
						durationField.getText(),
			});
				showScreen(this, "Tasks");
			}
			else{
				String message = "Check fields have correct format : \n";
				if(!isStringFloat(priceField.getText())){
					message += "price\n";
				}
				if(!isStringFloat(durationField.getText())){
					message += "duration\n";
				}
				Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.CLOSE);
				alert.show();
			}
		}
	}

	private void submit(String[] taskData){
		try {
			procUiController.submitEditTask(taskData);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			AlertUser.showDBError();
		}
	}

	private void onCancel(){
		showScreen(this, "Tasks");
	}

	public void onLeave(){

	}

	public void onShow(){
		super.onShow();

		descriptionField.setText(taskData[1]);
		locationField.setText(taskData[2]);
		priceField.setText(taskData[3]);
		durationField.setText(taskData[4]);
	}

	/**
	 */
	@FXML
	public void initialize(){
		super.initialize();
		userAllowed = new String[]{ROLE_OFFICE_MANAGER};

		cancelButton.setOnAction(actionEvent -> onCancel());
		confirmButton.setOnAction(actionEvent -> onConfirm());

		addIntegerNumberListener(durationField);
		addIntegerNumberListener(priceField);
	}
}