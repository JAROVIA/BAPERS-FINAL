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

	Collection<Job> Job;

	private String userRole;
	private int employeeID;
	private String username;
	private String Password;
	private String employeeName;
	static String tablename = "UserAccounts";

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	static String url = "jdbc:mysql://localhost:3306/Bapers";
	static String dbUsername = "jaroviadb";
	static String dbPassword = "Jarovia123#@!";
	static Connection connection;

	static {
		try {
			connection = DriverManager.getConnection(
					url, dbUsername, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void Login() {
		// TODO - implement UserAccount.Login
		throw new UnsupportedOperationException();
	}

	public void Logout() {
		// TODO - implement UserAccount.Logout
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param UserData
	 */
	public void DestroyAccount(String UserData) {
		// TODO - implement UserAccount.DestroyAccount
		throw new UnsupportedOperationException();
	}

	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * 
	 * @param NewJobData
	 */
	public boolean CreateJob(String NewJobData) {
		// TODO - implement UserAccount.CreateJob
		throw new UnsupportedOperationException();
	}

	public String SetCustomerAccountStatus() {
		// TODO - implement UserAccount.SetCustomerAccountStatus
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param User
	 */
	public void SaveUser(UserAccount User) {
		// TODO - implement UserAccount.SaveUser
		throw new UnsupportedOperationException();
	}

	public UserAccount RetrieveUser() {
		// TODO - implement UserAccount.RetrieveUser
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param AutoTime
	 */
	public void SetDBAutoBackupTime(int AutoTime) {
		// TODO - implement UserAccount.SetDBAutoBackupTime
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param AutoTime
	 */
	public void SetDBAutoRestoreTime(Date AutoTime) {
		// TODO - implement UserAccount.SetDBAutoRestoreTime
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ReportData
	 */
	public void GenerateReport(String ReportData) {
		// TODO - implement UserAccount.GenerateReport
		throw new UnsupportedOperationException();
	}


//	public static void UserAccount(String tablename, String EmployeeName, String Username, String password, String UserRole) throws SQLException {
//
//	}

	/**
	 * Adds all user accoutns to a list
	 * @return A list of all UserAccounts in the database
	 * */

	public static ArrayList<String[]> GetUserList() throws SQLException {
//		String tablename = this.tablename;
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

//			tuple = "employeeID: " + employeeID +
//					" username: " + username +
//					" roleName: " + roleName +
//					" staffName: " + staffName;

			tuple = employeeID + ","
					+ username + ","
					+ roleName + ","
					+ staffName;

			arrayList.add(tuple.split(","));

		}
		return arrayList;
	}

	/**
	 * This constructor will write a new user account to the db with information from its parameters.
	 * It needs a userrole, username, employeename, and password to do so.
	 */
	public UserAccount(String userRole, String dbUsername, String dbPassword, String employeeName) throws SQLException {
		this.userRole = userRole;
		this.username = dbUsername;
		this.Password = dbPassword;
		this.employeeName = employeeName;

		String sql = "INSERT INTO " + tablename + "(Username, RoleName, Password, StaffName) " + "VALUES(\"" + this.employeeName + "\", \"" + username + "\", \"" + dbPassword + "\", \"" + this.userRole + "\");" ;
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);
	}

	public static void main(String[] args) throws SQLException {

		// create a new user
//		UserAccount root = new UserAccount("superuser", "root", "user", "n/a");

		// adds users to a list
		ArrayList<String[]> al = UserAccount.GetUserList();

		// test to ensure correct alist format
		for(String[] col: al){
			for (String a: col){
				System.out.println(a);

			}
		}
	}
}