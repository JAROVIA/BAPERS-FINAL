package GUI;

import PROCESS.Job;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateJobSetupScreen  extends Window{
    @FXML
    private Button nextButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField accountNumberField;
    @FXML
    private Button searchButton;
    @FXML
    private Label accountLabel;
    @FXML
    private ComboBox<String> urgencyBox;

    private void onSearch(){
        String accNumber = accountNumberField.getText();
        //search by account number
    }

    private void onCancel(){
        procUiController.showScreen("Jobs");
    }

    private void onNext(){
        procUiController.showScreen("CreateJobOrder");

        try{
            Job job = new Job(Integer.parseInt(accountNumberField.getText()), urgencyBox.getValue());
            Job.EnterJob();
            Job.setJobIDFromDB();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void onShow(){
        accountLabel.setText("");
        urgencyBox.setPromptText("Select urgency");
    }

    @FXML
    public void initialize(){
        super.initialize();
        userAllowed = new String[]{ROLE_OFFICE_MANAGER, ROLE_SHIFT_MANAGER, ROLE_RECEPTIONIST};
        urgencyBox.getItems().addAll("vurgent", "urgent", "normal");
        searchButton.setOnAction(actionEvent -> onSearch());
        nextButton.setOnAction(actionEvent -> onNext());
        cancelButton.setOnAction(actionEvent -> onCancel());
    }
}
