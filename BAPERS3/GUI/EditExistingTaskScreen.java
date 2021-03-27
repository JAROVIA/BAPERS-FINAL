package GUI;

import PROCESS.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

	public TaskInAJob retrieveTask() {
		// TODO - implement UpdateExistingTaskScreen.RetrieveTask
		throw new UnsupportedOperationException();
	}

	private void onConfirm() {
		//TODO save task
		showScreen(this, "Tasks");
	}

	private void onCancel(){
		showScreen(this, "Tasks");
	}

	public void onLeave(){

	}

	public void onShow(){
		super.onShow();
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