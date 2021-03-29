package GUI;

import ACCOUNT.CustomerAccountDetails;
import PROCESS.Job;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.function.Predicate;

public class CreateJobSetupScreen  extends Window{
    @FXML
    private Button nextButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private TableView<String[]> customerAccountTable;
    @FXML
    private ComboBox<String> urgencyBox;

    private String[] columns = new String[]{
            "Account number",
            "Customer name",
            "Contact name"
    };

    /**
     * boolean to check if input is contained in the table. uses regex to match pattern first to check
     * search is non case sensitive, probably easier for user
     * @param data user account data to be checked
     */
    private boolean matchCustomer(String[] data, String input) {
        if(matchName(input)){
            return data[1].toLowerCase().contains(input.toLowerCase())
                    || data[2].toLowerCase().contains(input.toLowerCase());
        }
        if(matchNumber(input)){
            return data[0].contains(input);
        }
        else{
            return data[1].toLowerCase().contains(input.toLowerCase())
                    || data[2].toLowerCase().contains(input.toLowerCase());
        }
    }

    /**
     * used for filtered lists which can dynamically filter data depending on predicate such as this
     * @param input input in the search field
     * @return predicate to inform filter list if input matches some data in the list
     */
    private Predicate<String[]> customerDataPredicate(String input){
        return (String[] data) -> {
            //if the input is empty, show all data
            if(input == null || input.trim().isEmpty()){
                System.out.println("empty input");
                return true;
            }
            else{
                System.out.println("attempt match" + matchCustomer(data, input));
                return matchCustomer(data, input);
            }
        };
    }

    private void onCancel(){
        super.showScreen(this, "Jobs");
    }

    @Override
    public void onLeave(){
        searchField.setText("");
        urgencyBox.getSelectionModel().clearSelection();
        customerAccountTable.setItems(null);
    }

    private void onNext(){
        if(customerAccountTable.getSelectionModel().getSelectedItem() == null || urgencyBox.getValue() == null){
            String message = "Select : \n";
            if(customerAccountTable.getSelectionModel().getSelectedItem() == null){
                message += "customer from the table\n";
            }
            if(urgencyBox.getValue() == null){
                message += "urgency from the selection box\n";
            }
            Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.CLOSE);
            alert.show();
        }
        else{
            String[] customerData = customerAccountTable.getSelectionModel().getSelectedItem();
            try {
                Job job = new Job(Integer.parseInt(customerData[0]), urgencyBox.getValue());
                Job.EnterJob();
                Job.setJobIDFromDB();
                showScreen(this, "CreateJobOrder");
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    public void onShow(){
        super.onShow();

        ObservableList<String[]> data = FXCollections.observableArrayList();
        try {
            for (String[] customerData : CustomerAccountDetails.getCustomerList()) {
                data.add(new String[]{customerData[0], customerData[5], customerData[6]});
            }
        }catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error accessing database", ButtonType.CLOSE);
            alert.show();
        }
        FilteredList<String[]> filteredData = new FilteredList<>(data);
        customerAccountTable.setItems(filteredData);
        searchField.textProperty().addListener((observable, oldText, newText)-> {
            customerAccountTable.getSelectionModel().clearSelection();
            filteredData.setPredicate(customerDataPredicate(newText));
        });
    }

    @FXML
    public void initialize(){
        super.initialize();
        userAllowed = new String[]{ROLE_OFFICE_MANAGER, ROLE_SHIFT_MANAGER, ROLE_RECEPTIONIST};
        urgencyBox.getItems().addAll("vurgent", "urgent", "normal");
        setComboBoxPromptText(urgencyBox, "Select job urgency");

        nextButton.setOnAction(actionEvent -> onNext());
        cancelButton.setOnAction(actionEvent -> onCancel());

        for (int i = 0; i < columns.length; i++) {
            TableColumn<String[], String> tc = new TableColumn<>(columns[i]);
            int j = i;
            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> property) {
                    return new SimpleStringProperty((property.getValue()[j]));
                }
            });
            customerAccountTable.getColumns().add(tc);
        }
    }
}
