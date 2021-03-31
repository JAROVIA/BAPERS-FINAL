package GUI;

import ACCOUNT.CustomerAccountDetails;
import ADMIN.AlertUser;
import CUSTOMER.FixedDiscountRate;
import CUSTOMER.FlexibleDiscountRate;
import CUSTOMER.VariableDiscountRate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;
import java.util.ArrayList;

public class EditCustomerDetailsScreen extends RegisterNewCustomerScreen{

	public void setAccountData(String[] accountData) {
		this.accountData = accountData;
	}

	protected String[] accountData;

	@Override
	public void onShow(){
		super.onShow();
		valuedCheckBox.setDisable(false);
		if(accountData[1].equals("valued")) {
			valuedCheckBox.setSelected(true);

			setupExistingDiscount();
		}

		phoneField.setText(accountData[2]);
		addressField.setText(accountData[3]);
		emailField.setText(accountData[4]);
		nameField.setText(accountData[5]);
		contactNameField.setText(accountData[6]);

		gridPane.getRowConstraints().get(GridPane.getRowIndex(valuedCheckBox)).setPercentHeight(-1);
	}

	@Override
	protected void onDiscountSelect() {
		super.onDiscountSelect();
	}

	private void setupExistingDiscount(){
		String discountType = "";
		try {
			discountType = acctUiController.getCustomerDiscountType(accountNumber);
			if(acctUiController.getCustomerDiscountType(accountNumber) != null) {
				discountBox.getSelectionModel().select(discountType);
			}
			onDiscountSelect();

			if(discountType.equals(DISCOUNT_FIXED)){
				FixedDiscountRate fixed = new FixedDiscountRate();
				fixed.retrieveDiscount(accountNumber);
				data.add(new String[]{""+fixed.getDiscountRate()});
			}
			if(discountType.equals(DISCOUNT_FLEX)){
				FlexibleDiscountRate flex = new FlexibleDiscountRate();
				flex.retrieveDiscount(accountNumber);

				int mostUpper = flex.getDiscountRate().get(flex.getDiscountRate().size()-1)[1];

				bandLabel.setText(""+mostUpper);
				data.addAll(acctUiController.discountToStringArray(flex));
			}
			if (discountType.equals(DISCOUNT_VAR)){
				VariableDiscountRate variable = new VariableDiscountRate();
				variable.retrieveVariableDiscount(accountNumber);
				data.addAll(acctUiController.discountToStringArray(variable));
			}

			discountTable.setItems(data);
		} catch (SQLException throwables) {
			AlertUser.showDBError();
		}
	}

	/**
	 * listens to split pane and adjusts columns
	 */
	private void setColumnsEven(){
		for(TableColumn<String[], ?> col : discountTable.getColumns()){
			col.setMinWidth(discountTable.widthProperty().get() / discountTable.getColumns().size());
		}
	}

	@Override
	protected void submitCustomerData(String phone, String address, String email, String name, String contactName){
		try {
			if (!CustomerAccountDetails.checkIfCustomerExistsOnEdit(Integer.parseInt(accountData[0]), contactName)) {
				acctUiController.submitEditCustomer(
						Integer.parseInt(accountData[0]),
						"non-valued",
						phone,
						address.trim(),
						email.trim(),
						name.trim(),
						contactName.trim());
			}
			else{
				new Alert(Alert.AlertType.ERROR, "Customer account with same contact name exists").show();
			}
		} catch (SQLException e) {
			AlertUser.showDBError();
		}
	}

	@Override
	protected void submitCustomerDataWithDiscount(
			String phone,
			String address,
			String email,
			String name,
			String contactName,
			String discountType,
			int accountNumber,
			ArrayList<String[]> discountData
	){
		try {
			if (!CustomerAccountDetails.checkIfCustomerExistsOnEdit(Integer.parseInt(accountData[0]), contactName)) {
				acctUiController.submitEditCustomer(
						Integer.parseInt(accountData[0]),
						"valued",
						phone,
						address.trim(),
						email.trim(),
						name.trim(),
						contactName.trim());
				submitDiscountData(discountType, discountData);
				AlertUser.showCompletion("Customer data submit");
				showScreen(this, "CustomerAccounts");
			}
			else{
				new Alert(Alert.AlertType.ERROR, "Customer account with same contact name exists").show();
			}
		} catch (SQLException e) {
			AlertUser.showDBError();
		}
	}

	@FXML
	public void initialize(){
		super.initialize();

		valuedCheckBox.setStyle("visibility : visible");
		valuedCheckBox.setDisable(false);
		discountBox.setDisable(false);

		discountTable.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number number, Number newSize) {
				setColumnsEven();
			}
		});
	}

}