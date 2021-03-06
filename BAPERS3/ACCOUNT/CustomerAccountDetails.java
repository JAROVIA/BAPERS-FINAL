package ACCOUNT;

import ADMIN.UserAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CustomerAccountDetails {

	SQL_CustomerAccountHelper sql_helper;

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

	public static void ArchiveCustAcc(int TaskID) throws SQLException {
		String sql = "UPDATE CustomerAccounts SET IsArchived = 1 WHERE TaskID = " + TaskID + ";";
		Statement statement = connection.createStatement();
		System.out.println(sql);
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

	public static ArrayList<String[]> getSmallCustomerList() throws SQLException {
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM CustomerAccounts;";
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		// adding changes to an array list
		while (resultSet.next()){

			int AccountNumber = resultSet.getInt("AccountNumber");
			String CustomerName = resultSet.getString("CustomerName");
			String ContactName = resultSet.getString("ContactName");

			tuple = AccountNumber + "`"
					+ CustomerName + "`"
					+ ContactName;

			arrayList.add(tuple.split("`"));

		}
		return arrayList;
	}

	public static void main(String[] args) throws SQLException {
//		new CustomerAccountDetails("normal", "078999354", "address 13", "epr@dag.com", "Felix","fexil");
	}

	public int getAccountNumber() {
		// TODO - implement CustomerAccountDetails.getAccountNumber
		throw new UnsupportedOperationException();
	}

	public static void updateCustomer(String columnToEdit, String newValue, String identifierOfTableToEdit, int identifierCurrentValue) throws SQLException {

		// TaskInAJob.UpdateJob("TaskPrice", 76, "TaskID", 1);
		String sql = "UPDATE CustomerAccounts SET " + columnToEdit + " = '" + newValue + "' WHERE " + identifierOfTableToEdit + " = " + identifierCurrentValue + ";" ;
		System.out.println(sql);
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);

	}

	/**
	 * checks customer with same contact exists, should not exist
	 * @return if customer exists - true -  or not - false -
	 */
	public static boolean checkIfCustomerExistsOnEdit(int accountNumber, String contactName) throws SQLException {
		String sql = "SELECT COUNT(*) AS 'count' " +
				"FROM CustomerAccounts " +
				"WHERE ContactName = '" + contactName.trim() +"'" +
				"AND NOT AccountNumber = " + accountNumber + ";";
		Statement statement = connection.createStatement();
		System.out.println(sql);
		ResultSet resultSet = statement.executeQuery(sql);

		if(resultSet.next()){
			return resultSet.getInt("count") > 0;
		}
		return false;
	}

	public static boolean checkIfCustomerExists(String contactName) throws SQLException {
		String sql = "SELECT COUNT(*) AS 'count' FROM CustomerAccounts WHERE ContactName = '" + contactName.trim() + "';";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		System.out.println(sql);

		if(resultSet.next()){
			return resultSet.getInt("count") > 0;
		}
		return false;
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