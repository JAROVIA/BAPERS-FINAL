package GUI;

import PROCESS.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UpdateExistingTaskScreen extends Window {

	//TODO - delete this class, same as edit existing task

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
		// TODO - implement UpdateExistingTaskScreen.ConfirmChanges
		throw new UnsupportedOperationException();
	}

	@Override
	public void onShow(){
		super.onShow();
	}

	@Override
	public void onLeave(){
		descriptionField.clear();
		durationField.clear();
		locationField.clear();
		priceField.clear();
	}

	/**
	 *
	 */
	@FXML
	public void initialize(){
		super.initialize();
		userAllowed = new String[]{ROLE_OFFICE_MANAGER};
	}
}