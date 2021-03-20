package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;

public class ProcessTasksScreen extends Window {

	@FXML
	private Button startButton;
	@FXML
	private Button completeButton;
	@FXML
	private TableView<String[]> JobProgressTable;
	@FXML
	private ProgressBar progressBar;

	public void onShow(){
		super.onShow();
	}
	/**
	 *
	 */
	@FXML
	public void initialize(){
		super.initialize();
		userAllowed = new String[]{ROLE_OFFICE_MANAGER, ROLE_SHIFT_MANAGER, ROLE_TECHNICIAN_COPY, ROLE_TECHNICIAN_DEV, ROLE_TECHNICIAN_PACK, ROLE_TECHNICIAN_FIN};
	}
}