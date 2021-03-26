package ACCOUNT;

import ADMIN.UserAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CustomerAccountDetails {

	SQL_CustomerAccountHelper sql_helper;

	private int accountNumber;
	private int discountID;
	private String accountStatus = "normal";
	private String phoneNumber;
	private String address;
	private String email;
	private String customerName;
	private String contactName;

	private float VolumePerMonth = 0;

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

	public CustomerAccountDetails(
			String accountStatus, String phoneNumber,
		  	String address, String email, String customerName, String contactName) throws SQLException {

		this.accountNumber = accountNumber;
		this.discountID = discountID;
		this.accountStatus = accountStatus;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
		this.customerName = customerName;
		this.contactName = contactName;

		String sql =
				"INSERT INTO CustomerAccounts (AccountStatus,PhoneNumber,Address,EmailAddress,CustomerName,ContactName) "
				+ "VALUES(" + "\"" + accountStatus  + "\", \"" + phoneNumber + "\", \"" + address + "\", \""
				+ email + "\", \"" + customerName + "\", \"" + contactName + "\");";
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);

	}

	public static ArrayList<String[]> getCustomerList() throws SQLException {
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM CustomerAccounts;";
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		// adding changes to an array list
		while (resultSet.next()){

			int AccountNumber = resultSet.getInt("AccountNumber");
			String AccountStatus = resultSet.getString("AccountStatus");
			String PhoneNumber = resultSet.getString("PhoneNumber");
			String Address = resultSet.getString("Address");
			String EmailAddress = resultSet.getString("EmailAddress");
			String CustomerName = resultSet.getString("CustomerName");
			String ContactName = resultSet.getString("ContactName");

			tuple = AccountNumber + "`"
					+ AccountStatus + "`"
					+ PhoneNumber + "`"
					+ Address + "`"
					+ EmailAddress + "`"
					+ CustomerName + "`"
					+ ContactName;

			arrayList.add(tuple.split("`"));

		}
		return arrayList;
	}

	public static void main(String[] args) throws SQLException {
//		ArrayList<String[]> test = new ArrayList<>();
//		test = ACCOUNT.CustomerAccountDetails.getCustomerList();
//
//		for (String[] t : test){
//			System.out.println(Arrays.toString(t));
//
//		}
		CustomerAccountDetails customerAccountDetails = new CustomerAccountDetails("as","425","sad","2141","asf","dsg");
	}

	public int getAccountNumber() {
		// TODO - implement CustomerAccountDetails.getAccountNumber
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param NewName
	 */
	public void setName(String NewName) {
		// TODO - implement CustomerAccountDetails.setName
		throw new UnsupportedOperationException();
	}

	public String getName() {
		// TODO - implement CustomerAccountDetails.getName
		throw new UnsupportedOperationException();
	}

	public String getAccountStatus() {
		// TODO - implement CustomerAccountDetails.getAccountStatus
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param AccountStatus
	 */
	public void setAccountStatus(String AccountStatus) {
		// TODO - implement CustomerAccountDetails.setAccountStatus
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param AccountNumber
	 */
	public void setAccountNumber(int AccountNumber) {
		// TODO - implement CustomerAccountDetails.setAccountNumber
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Customer
	 */
	public void SaveCustomer(CustomerAccountDetails Customer) {
		// TODO - implement CustomerAccountDetails.SaveCustomer
		throw new UnsupportedOperationException();
	}

	public int getVolumePerMonth() {
		// TODO - implement CustomerAccountDetails.getVolumePerMonth
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param VolumePerMonth
	 */
	public void setVolumePerMonth(int VolumePerMonth) {
		// TODO - implement CustomerAccountDetails.setVolumePerMonth
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param UserName
	 * @param Email
	 * @param AccountStatus
	 * @param AccountNumber
	 * @param VolumePerMonth
	 */
	public static CustomerAccountDetails CustomerAccountDetails(String UserName, String Email, String AccountStatus, int AccountNumber, float VolumePerMonth) {
		// TODO - implement CustomerAccountDetails.CustomerAccountDetails
		throw new UnsupportedOperationException();
	}

}