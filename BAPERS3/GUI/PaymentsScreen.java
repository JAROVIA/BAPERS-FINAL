package GUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

    private ObservableList<String[]> paymentData;
    private ObservableList<String[]> jobData;

    private final String[] paymentCol = new String[]{
            "JobId",
            "AccountNumber",
            "Final Price",
            "Date",
            "Payment type",
            "Card holder",
            "Card type",
            "Last 4 digits",
            "CVC",
            "Expiry Date"
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

    private void onBack(){
        jobData.clear();
        paymentData.clear();
    }

    public void onShow(){
        super.onShow();
    }

    @FXML
    public void initialize(){
        super.initialize();
        userAllowed = new String[]{ROLE_OFFICE_MANAGER, ROLE_SHIFT_MANAGER};
        backButton1.setOnAction(actionEvent -> onBack());
        backButton2.setOnAction(actionEvent -> onBack());

        jobData = FXCollections.observableArrayList(new ArrayList<>());
        paymentData = FXCollections.observableArrayList(new ArrayList<>());

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
