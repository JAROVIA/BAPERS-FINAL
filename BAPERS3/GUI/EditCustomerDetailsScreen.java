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
	protected void submitCustomerData(){
		System.out.println("edit customer");
	}

}