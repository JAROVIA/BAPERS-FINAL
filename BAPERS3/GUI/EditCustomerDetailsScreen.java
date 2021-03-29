package GUI;

import PROCESS.TaskDescription;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.ArrayList;

public class EditCustomerDetailsScreen extends RegisterNewCustomerScreen{

	@Override
	public void onShow(){
		super.onShow();

		System.out.println();
		gridPane.getRowConstraints().get(GridPane.getRowIndex(valuedCheckBox)).setPercentHeight(-1);
	}

	@Override
	protected void submitCustomerData(String status, String phone, String address, String email, String name, String contactName){

	}

	@FXML
	public void initialize(){
		super.initialize();

		valuedCheckBox.setStyle("visibility : visible");
		valuedCheckBox.setDisable(false);
		discountBox.setDisable(false);
	}

}