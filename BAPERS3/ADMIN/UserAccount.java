package ADMIN;

import PROCESS.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.sql.*;

public class UserAccount {

	Collection<Job> job;

	private String userRole;
	private int employeeID;
	private String username;
	private String password;
	private String employeeName;
	private ArrayList<AlertUser> alert;

	static String tablename = "UserAccounts";
	static String url = "jdbc:mysql://localhost:3306/Bapers";
	static String dbusername = "jaroviadb";
	static String dbpassword = "Jarovia123#@!";
	static Connection connection;

	static {
		try {
			connection = DriverManager.getConnection(
					url, dbusername, dbpassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public ArrayList<AlertUser> getAlert() {
		return alert;
	}

	public void setAlert(AlertUser alert) {
		this.alert.add(alert);
	}

	/**
	 * 
	 * @param UserData
	 */
	public void destroyAccount(String UserData) {
		// TODO - implement UserAccount.DestroyAccount
		throw new UnsupportedOperationException();
	}

	public String getEmployeeName() {
		// TODO - implement UserAccount.getEmployeeName
		return employeeName;
	}

	/**
	 * 
	 * @param NewJobData
	 */
	public boolean createJob(String NewJobData) {
		// TODO - implement UserAccount.CreateJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param username
	 * @param userRole
	 * @param password
	 * @param employeeName
	 */
	public static void saveUser(String username, String userRole, String password, String employeeName) throws SQLException {
		String sql = "INSERT INTO " + tablename + "(Username, RoleName, Password, StaffName) " + "VALUES(\"" + username + "\", \"" + userRole + "\", \"" + password + "\", \"" + employeeName + "\");" ;
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);
	}

	public static void editUser(int employeeID, String username, String userRole, String password, String employeeName) throws SQLException {
		String sql = "UPDATE " + tablename + " SET Username = '"+ username +"', RoleName = '"+ userRole +"', Password = '"+ password +"', StaffName = '"+ employeeName +"' WHERE EmployeeID = "+ employeeID +";";
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);
	}

	public UserAccount retrieveUser() {
		// TODO - implement UserAccount.RetrieveUser
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param AutoTime
	 */
	public void setDBAutoBackupTime(int AutoTime) {
		// TODO - implement UserAccount.SetDBAutoBackupTime
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param AutoTime
	 */
	public void setDBAutoRestoreTime(Date AutoTime) {
		// TODO - implement UserAccount.SetDBAutoRestoreTime
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ReportData
	 */
	public void generateReport(String ReportData) {
		// TODO - implement UserAccount.GenerateReport
		throw new UnsupportedOperationException();
	}

	/**
	 * Adds all user accounts to a list
	 * @return A list of all UserAccounts in the database
	 * */

	public static ArrayList<String[]> getUserList() throws SQLException {
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM " + tablename;
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		// adding changes to an array list
		while (resultSet.next()){

			int employeeID = resultSet.getInt("EmployeeID");
			String username = resultSet.getString("Username");
			String roleName = resultSet.getString("RoleName");
			String password = resultSet.getString("Password");
			String staffName = resultSet.getString("StaffName");

			tuple = employeeID + "`"
					+ username + "`"
					+ roleName + "`"
					+ password + "`"
					+ staffName;

			arrayList.add(tuple.split("`"));

		}
		return arrayList;
	}

	/**
	 * This constructor will write a new user account to the db with information from its parameters.
	 * It needs a userrole, username, employeename, and password to do so.
	 */
	public UserAccount(int employeeID,  String username, String userRole, String password, String employeeName) {
		this.employeeID = employeeID;
		this.userRole = userRole;
		this.username = username;
		this.password = password;
		this.employeeName = employeeName;

		if(userRole.equals("Office Manager") || userRole.equals("Shift Manager")){
			alert = new ArrayList<>();
		}
	}
}