package GUI;

import PAYMENT.Payment;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class PaymentsScreen extends Window{

    @FXML
    private Button backButton1;
    @FXML
    private Button backButton2;
    @FXML
    private Button makePaymentButton;
    @FXML
    private TableView<String[]> paymentsTable;
    @FXML
    private TableView<String[]> latePaymentJobTable;

    private final String[] paymentCol = new String[]{
            "JobId",
            "AccountNumber",
            "Price",
            "Discounted price",
            "Date",
            "Payment type",
            "Expiry Date",
            "Card holder",
            "Card type",
            "Last 4 digits",
            "CVC"
    };
    private final String[] latePaymentJobCol = new String[]{
            "jobId",
            "Account number",
            "No. of Tasks",
            "Date job start",
            "Job deadline",
            "Urgency",
            "Tasks completed",
            "completed"
    };

    private void onMakePayment(){
        if(latePaymentJobTable.getSelectionModel().getSelectedItem() != null){
            String[] latPayJobData = latePaymentJobTable.getSelectionModel().getSelectedItem();
            //TODO - use data to process payment
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Select job to process payment", ButtonType.CLOSE);
            alert.show();
        }
    }

    private void onBack(){
        showScreen(this, "Jobs");
    }

    public void onLeave(){
        latePaymentJobTable.getItems().clear();
        paymentsTable.setItems(null);
    }

    public void onShow(){
        super.onShow();

        ObservableList<String[]> paymentData = FXCollections.observableArrayList(new ArrayList<>());
        FilteredList<String[]> filteredPayment = new FilteredList<>(paymentData);
        paymentsTable.setItems(filteredPayment);
        try {
            paymentData.addAll(Payment.GetPaymentList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load database for payments", ButtonType.CLOSE);
            alert.show();
        }

        ObservableList<String[]> jobData = FXCollections.observableArrayList(new ArrayList<>());
    }

    /**
     * boolean to check if input is contained in the table. uses regex to match pattern first to check
     * search is non case sensitive, probably easier for user
     * @param data user account data to be checked
     */
    private boolean matchPayment(String[] data, String input) {
        if(matchName(input)){
            return data[5].toLowerCase().contains(input.toLowerCase())
                    || data[7].toLowerCase().contains(input.toLowerCase())
                    || data[8].toLowerCase().contains(input.toLowerCase());
        }
        if(matchAnyNumberType(input)){
            return data[2].contains(input)
                    || data[3].contains(input);
        }

        if(matchNumber(input)){
            return data[0].toLowerCase().contains(input.toLowerCase())
                    || data[1].toLowerCase().contains(input.toLowerCase())
                    || data[4].contains(input)
                    || data[6].contains(input)
                    || data[9].contains(input)
                    || data[10].contains(input);
        }
        else{
            return data[2].toLowerCase().contains(input.toLowerCase())
                    || data[4].contains(input)
                    || data[6].contains(input);
        }
    }

    /**
     * used for filtered lists which can dynamically filter data depending on predicate such as this
     * @param input input in the search field
     * @return predicate to inform filter list if input matches some data in the list
     */
    private Predicate<String[]> userDataPredicate(String input){
        return (String[] data) -> {
            if(input == null || input.trim().isEmpty()){
                return true;
            }
            else{
                return matchPayment(data, input);
            }
        };
    }

    @FXML
    public void initialize(){
        super.initialize();
        userAllowed = new String[]{ROLE_OFFICE_MANAGER, ROLE_SHIFT_MANAGER};
        backButton1.setOnAction(actionEvent -> onBack());
        backButton2.setOnAction(actionEvent -> onBack());



        //populate table with right columns
        //for payment table
        for (int i = 0; i < paymentCol.length; i++) {

            TableColumn<String[], String> tc = new TableColumn<>();
            tc.setText(paymentCol[i]);
            int j = i;
            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> property) {
                    return new SimpleStringProperty((property.getValue()[j]));
                }
            });
            tc.setMinWidth(paymentsTable.getWidth() / paymentCol.length);
            paymentsTable.getColumns().add(tc);
        }

        //for jobs table
        for (int i = 0; i < latePaymentJobCol.length; i++) {

            TableColumn<String[], String> tc = new TableColumn<>();
            tc.setText(latePaymentJobCol[i]);
            int j = i;
            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> property) {
                    return new SimpleStringProperty((property.getValue()[j]));
                }
            });
            tc.setMinWidth(latePaymentJobTable.getWidth() / latePaymentJobCol.length);
            latePaymentJobTable.getColumns().add(tc);
        }
    }

}
