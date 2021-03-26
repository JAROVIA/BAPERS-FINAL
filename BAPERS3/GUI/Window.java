package GUI;

import CONTROLLER.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Window{

	protected ACCT_UI_Controller acctUiController = null;
	protected ADMIN_UI_Controller adminUiController = null;
	protected PROC_UI_Controller procUiController = null;
	protected UI_Controller uiController = null;
	protected Parent parent;

	public String[] getUserAllowed() {
		return userAllowed;
	}

	protected String[] userAllowed;
	protected final String ROLE_OFFICE_MANAGER = "Office Manager";
	protected final String ROLE_SHIFT_MANAGER = "Shift Manager";
	protected final String ROLE_RECEPTIONIST = "Receptionist";
	protected final String ROLE_TECHNICIAN_DEV = "Technician - Development";
	protected final String ROLE_TECHNICIAN_COPY = "Technician - Copy room";
	protected final String ROLE_TECHNICIAN_PACK = "Technician - Packing room";
	protected final String ROLE_TECHNICIAN_FIN = "Technician - Finishing room";

	/**
	 * Button / image which returns user to home screen
	 */
	@FXML
	private ImageView homeButton;

	/**
	 * Button for logout
	 */
	@FXML
	private Button logoutButton;

	/**
	 * Label to welcome user with their username
	 */
	@FXML
	private Label welcomeLabel;

	/**
	 * access each controller so each gui knows which controller to report back to
	 */
	public ACCT_UI_Controller getAcctUiController() {
		return acctUiController;
	}

	public void setAcctUiController(ACCT_UI_Controller acctUiController) {
		this.acctUiController = acctUiController;
	}

	//getter setter for admin ui
	public ADMIN_UI_Controller getAdminUiController() {
		return adminUiController;
	}

	public void setAdminUiController(ADMIN_UI_Controller adminUiController){
		this.adminUiController = adminUiController;
	}

	//getter setter for process ui
	public PROC_UI_Controller getProcUiController() {
		return procUiController;
	}

	public void setProcUiController(PROC_UI_Controller procUiController) {
		this.procUiController = procUiController;
	}

	//getter setter for ui ui
	public UI_Controller getUiController() {
		return uiController;
	}

	public void setUiController(UI_Controller uiController) {
		this.uiController = uiController;
	}

	public void setParentElement(Parent parent){
		this.parent = parent;
	}

	public Parent getParent(){
		return parent;
	}

	/**
	 * used for text fields for names, removes special characters
	 * @param tf text field to assign this listener
	 */
	protected void addNameListener(TextField tf){
		tf.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldText, String newText) {
				if(newText.matches(".*[0-9!@£$%^&*():=+,;/{}<>\"?`~\\\\\\-\\[\\]].*")){
					tf.setText(oldText);
				}
			}
		});
	}

	/**
	 * used for text fields with numeric input, only allows integer numeric characters
	 * @param tf text field to assign this listener
	 */
	protected void addIntegerNumberListener(TextField tf){
		tf.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldText, String newText) {
				if(!newText.matches("[0-9]*") || newText.length() > 10 ){
					tf.setText(oldText);
				}
			}
		});
	}

	protected void addIntegerNumberListener(TextField tf, int lengthConstraint){
		tf.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldText, String newText) {
				if(!newText.matches("[0-9]*") || newText.length() > lengthConstraint ){
					tf.setText(oldText);
				}
			}
		});
	}

	/**
	 * Tests if a string input can be parsed as an integer
	 * Uses exception therefore should not be abused
	 * @param stringToParse string to be converted to an integer
	 * @return returns if string can be parsed as an integer
	 */
	protected boolean isStringInt(String stringToParse){
		try{
			Integer.parseInt(stringToParse);
			return true;
		}
		catch (NumberFormatException e){
			return false;
		}
	}

	/**
	 * used for text fields with numeric input, only allows numeric characters
	 * text field to assign this listener
	 */
	protected void addFloatNumberListener(TextField tf){
		tf.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldText, String newText) {
				if(!newText.matches("[0-9]*\\.?([0-9]*)") || newText.length() > 10){
					tf.setText(oldText);
				}
			}
		});
	}

	/**
	 * Tests if a string input can be parsed as a float
	 * Uses exception therefore should not be abused
	 * @param stringToParse string to be converted to a float
	 * @return returns if string can be parsed as a float
	 */
	protected boolean isStringFloat(String stringToParse){
		try{
			Float.parseFloat(stringToParse);
			return true;
		}
		catch (NumberFormatException e){
			return false;
		}
	}

	/**
	 *
	 * @param fxml the name of the fxml file to get the gui class from (path is adjusted in the method)
	 * @return The window object set to controller in the fxml file
	 * @throws IOException
	 */
	public static Window newGuiFromFxml(String fxml) throws IOException {
		//load fxml
		String url = "fxml/" + fxml + ".fxml";
		FXMLLoader loader = new FXMLLoader(Window.class.getResource(url));
		//get the parent element in fxml
		//parent is used to construct the scene, which is assigned to the stage(window) to show
		Parent parent = loader.load();
		//get the gui class
		Window gui = loader.getController();
		gui.setParentElement(parent);

		return gui;
	}

	public void logout(){
		if(getProcUiController() != null){
			getProcUiController().showScreen("Login");
		}
		if(getAcctUiController() != null){
			getAcctUiController().showScreen("Login");
		}
		if(getUiController() != null){
			getUiController().showScreen("Login");
		}
		if(getAdminUiController() != null){
			getAdminUiController().showScreen("Login");
		}
		onLeave();
	}

	public void toMain(){
		if(getProcUiController() != null){
			getProcUiController().showScreen("HomeScreen");
		}
		if(getAcctUiController() != null){
			getAcctUiController().showScreen("HomeScreen");
		}
		if(getUiController() != null){
			getUiController().showScreen("HomeScreen");
		}
		if(getAdminUiController() != null){
			getAdminUiController().showScreen("HomeScreen");
		}
		onLeave();
	}

	public boolean checkAccess(String userRole){
		for(String user : userAllowed){
			if(user.equals(userRole)){
				return true;
			}
		}
		return false;
	}

	public void onShow(){
		//do what the screen needs to do on show
		if(welcomeLabel != null){
			String userText ="Welcome! user: ";
			if(adminUiController != null){
				userText += adminUiController.getLoggedInUser().getUsername();
				System.out.println("username: " + adminUiController.getLoggedInUser().getUsername());
			}
			if(uiController != null){
				userText += uiController.getLoggedInUser().getUsername();
				System.out.println("username: " + uiController.getLoggedInUser().getUsername());
			}
			if(acctUiController != null){
				userText += acctUiController.getLoggedInUser().getUsername();
				System.out.println("username: " + acctUiController.getLoggedInUser().getUsername());
			}
			if(procUiController != null){
				userText += procUiController.getLoggedInUser().getUsername();
				System.out.println("username: " + procUiController.getLoggedInUser().getUsername());
			}
			welcomeLabel.setText(userText);
		}
	}

	/**
	 * for search to match positive numerics (system never records negatives)
	 * @return
	 */
	protected boolean matchNumber(String toCheck){
		return toCheck.matches("\\d+");
	}

	/**
	 * for search to match positive numerics (system never records negatives)
	 * @return
	 */
	protected boolean matchAnyNumberType(String toCheck){
		return toCheck.matches("\\d+[.]?\\d*");
	}

	/**
	 * for search to match only string
	 * @return
	 */
	protected boolean matchName(String toCheck){
		return toCheck.matches("[^0-9!@£$%^&*():=+,-]+");
	}

	/**
	 * for search to match '@'
	 * @param toCheck
	 * @return
	 */
	protected boolean matchEmail(String toCheck){
		return toCheck.matches(".*@.*");
	}

	/**
	 * used if any of the fields are missing / empty.
	 * takes multiple fields, boxes to test if they are empty
	 * @param textFields to be tested
	 * @param boxes to be tested
	 * @return boolean if the inputs were correct or not, while displaying a warning if there were errors
	 */
	protected boolean isValueNotEmpty(TextField[] textFields, ComboBox<String>[] boxes){
		boolean pass = true;
		StringBuilder message = new StringBuilder("Empty field at : ");
		for(TextField textField : textFields){
			if(textField.getText().trim().isEmpty() || textField.getText() == null){
				pass = false;
				message.append("\n").append(textField.getPromptText());
			}
		}
		for(ComboBox<String> box : boxes){
			if(box.getValue() == null){
				pass = false;
				message.append("\n").append(box.getPromptText());
			}
		}
		if(!pass){
			Alert alert = new Alert(Alert.AlertType.ERROR, message.toString(), ButtonType.CLOSE);
			alert.show();
		}
		return pass;
	}

	protected boolean isValueNotEmpty(TextField... textFields){
		boolean pass = true;
		StringBuilder message = new StringBuilder("Empty field at : ");
		for(TextField textField : textFields){
			if(textField.getText().trim().isEmpty() || textField.getText() == null){
				pass = false;
				message.append("\n").append(textField.getPromptText());
			}
		}
		if(!pass){
			Alert alert = new Alert(Alert.AlertType.ERROR, message.toString(), ButtonType.CLOSE);
			alert.show();
		}
		return pass;
	}

	protected void setComboBoxPromptText(ComboBox<String> box, String promptText){
		box.setButtonCell(new ListCell<String>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty) ;
				if (empty || item == null) {
					setText(promptText);
				} else {
					setText(item);
				}
			}
		});
	}

	/**
	 * for child classes to use when leaving page
	 */
	protected void onLeave(){

	}

	@FXML
	public void initialize() {
		// TODO - implement Window.Window

		if(logoutButton != null) {
			logoutButton.setOnAction(actionEvent -> logout());
		}
		if(homeButton != null) {
			homeButton.setOnMouseClicked(mouseEvent -> toMain());
		}
	}
}