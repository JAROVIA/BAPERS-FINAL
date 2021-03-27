package PAYMENT;

import PROCESS.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class Payment implements I_Payment {

	SQL_PaymentHelper sql_helper;
	private int jobID;
	private int accountNumber;
	private float price;
	private float discountedPrice;
	private String dateOfPayment;
	private int discountID;
	private String paymentType;

	private String expiryDate;
	private String cardHolderName;
	private int cardLast4Digits;
	private int cvvc;

	static String url = "jdbc:mysql://localhost:3306/Bapers";
	static String username = "jaroviadb";
	static String password = "Jarovia123#@!";
	static Connection connection;


	static {
		try {
			connection = DriverManager.getConnection(
					url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//apply discount method is needed asap. It should be called inside (update) of the payment constructor

	public Payment(
			int jobID, int accountNumber, float price, String dateOfPayment,
			String paymentType) throws SQLException {

		this.jobID = jobID;
		this.accountNumber = accountNumber;
		this.price = price;
		this.discountedPrice = discountedPrice;
		this.dateOfPayment = dateOfPayment;
		this.paymentType = paymentType;

		java.util.Date date = new java.util.Date();
		dateOfPayment = date.toString();

		float discountedPrice = 0;
		// need to figure out how to make these insert statements work.
		// look at GUI first
		String sql =
		"INSERT INTO Payment VALUES (" + jobID + ", " + accountNumber + ", " + price + ", " +
		discountedPrice + ", " + dateOfPayment + ", \"" + paymentType + "\");";
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);


	}

	public Payment(
			int jobID, int accountNumber, float price, String dateOfPayment,
			String paymentType, String expiryDate, String cardHolderName, int cardLast4Digits, int cvvc) throws SQLException {

		this.jobID = jobID;
		this.accountNumber = accountNumber;
		this.price = price;
		this.discountedPrice = discountedPrice;
		this.dateOfPayment = dateOfPayment;
		this.paymentType = paymentType;
		this.expiryDate = expiryDate;
		this.cardHolderName = cardHolderName;
		this.cardLast4Digits = cardLast4Digits;
		this.cvvc = cvvc;

		java.util.Date date = new java.util.Date();
		dateOfPayment = date.toString();

		float discountedPrice = 0;

		String sql =
		"INSERT INTO Payment VALUES (" + jobID + ", " + accountNumber + ", " + price + ", " +
		discountedPrice + ", \"" + dateOfPayment + "\", \"" + paymentType + "\", \"" +
		expiryDate + "\", \"" + cardHolderName + "\", \"" + cardLast4Digits + "\", " + cvvc + ");";
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);

	}

	public static void main(String[] args) throws SQLException {
		ArrayList<String[]> list = Payment.GetLatePayments();
		for(String[] l: list){
			Arrays.toString(l);

		}
	}

	public static ArrayList<String[]> GetPaymentList() throws SQLException {
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM Jobs;"; /* SELECT * FROM Jobs WHERE IsArchived = 0; */
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		// adding changes to an array list
		while (resultSet.next()){

			int jobID = resultSet.getInt("JobID");
			int accountNumber = resultSet.getInt("AccountNumber");
			float price = resultSet.getFloat("Price");
			float discountedPrice = resultSet.getFloat("DiscountedPrice");
			String dateOfPayment = resultSet.getString("DateOfPayment");
			String dueDate = resultSet.getString("DueDatePayment");
			String paymentType = resultSet.getString("PaymentType");
			int expiryDate = resultSet.getInt("ExpiryDate");
			String cardHolderName = resultSet.getString("CardholderName");
			String cardType = resultSet.getString("CardType");
			int cardLast4Digits = resultSet.getInt("Last4Digits");
			int cvvc = resultSet.getInt("CVVC");


			tuple =
				jobID + "`"
				+ accountNumber + "`"
				+ price + "`"
				+ discountedPrice + "`"
				+ dateOfPayment + "`"
				+ dueDate + "`"
				+ paymentType + "`"
				+ expiryDate + "`"
				+ cardHolderName + "`"
				+ cardType + "`"
				+ cardLast4Digits + "`"
				+ cvvc;

			arrayList.add(tuple.split("`"));
		}
		return arrayList;
	}

	// incomplete. Extra feature.
	public static ArrayList<String[]> GetLatePayments() throws SQLException {
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM Payments;"; /* SELECT * FROM Jobs WHERE IsArchived = 0; */
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		// adding changes to an array list
		while (resultSet.next()){
// change this
			int jobID = resultSet.getInt("JobID");
			int accountNumber = resultSet.getInt("JobID");
			float price = resultSet.getFloat("Price");
			float discountedPrice = resultSet.getFloat("DiscountedPrice");
			String dateOfPayment = resultSet.getString("JobUrgency");
			String dueDate = resultSet.getString("JobUrgency");

			String paymentType = resultSet.getString("JobUrgency");
			int expiryDate = resultSet.getInt("JobID");
			String cardHolderName = resultSet.getString("JobUrgency");
			String cardType = resultSet.getString("JobUrgency");
			int cardLast4Digits = resultSet.getInt("JobID");
			int cvvc = resultSet.getInt("JobID");

			// find current date
			Calendar currentTime = Calendar.getInstance();

			String fullDateString = currentTime.getTime().toString();
			String[] fullDateArray = fullDateString.split(" ");
			String currentDate = fullDateArray[2];
			System.out.println(currentDate);

			// find due date of payment
			String FullDueDate = resultSet.getString("PaymentDueDate");
			String[] fullDueDateArray = FullDueDate.split(" ");
//			String dueDate = fullDueDateArray[2];
			System.out.println(dueDate);

			if (currentDate == dueDate){

				tuple =	jobID + "`"
					+ accountNumber + "`"
					+ price + "`"
					+ discountedPrice + "`"
					+ dateOfPayment + "`"
					+ dueDate + "`"
					+ paymentType + "`"
					+ expiryDate + "`"
					+ cardHolderName + "`"
					+ cardType + "`"
					+ cardLast4Digits + "`"
					+ cvvc;

					arrayList.add(tuple.split("`"));
			}
		}
		return arrayList;
	}


	public float getdiscountedPrice() {
		// TODO - implement Payment.getdiscountedPrice
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param FinalAmount
	 */
	public void setdiscountedPrice(float FinalAmount) {
		// TODO - implement Payment.setdiscountedPrice
		throw new UnsupportedOperationException();
	}

	@Override
	public float getJobPrice() {
		return 0;
	}

	public int getPaymentID() {
		// TODO - implement Payment.getPaymentID
		throw new UnsupportedOperationException();
	}

	public String getPaymentType() {
		// TODO - implement Payment.getPaymentType
		throw new UnsupportedOperationException();
	}

	public Date getDateOfPayment() {
		// TODO - implement Payment.getDateOfPayment
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param DateOfPayment
	 */
	public void setDateOfPayment(Date DateOfPayment) {
		// TODO - implement Payment.setDateOfPayment
		throw new UnsupportedOperationException();
	}

	public void StorePaymentInDatabase() {
		// TODO - implement Payment.StorePaymentInDatabase
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param PaymentType
	 */
	public void setPaymentType(String PaymentType) {
		// TODO - implement Payment.setPaymentType
		throw new UnsupportedOperationException();
	}

	@Override
	public float getFinalPrice() {
		return 0;
	}

	@Override
	public void setFinalPrice(float FinalAmount) {

	}

	/**
	 * 
	 * @param price
	 * @param cvc
	 * @param PaymentType
	 */
	public Payment MakePayment(float price, int cvc, String PaymentType) {
		// TODO - implement Payment.MakePayment
		throw new UnsupportedOperationException();
	}

	public Payment GetPayment() {
		// TODO - implement Payment.GetPayment
		throw new UnsupportedOperationException();
	}

	public float getprice() {
		// TODO - implement Payment.getprice
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param discountedPrice
	 * @param PaymentType
	 * @param DateOfPayment
	 * @param PaymentID
	 */
	public static Payment Payment(float discountedPrice, String PaymentType, Date DateOfPayment, int PaymentID) {
		// TODO - implement Payment.Payment
		throw new UnsupportedOperationException();
	}

}