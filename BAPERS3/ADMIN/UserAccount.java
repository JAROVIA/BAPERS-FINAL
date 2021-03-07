package ADMIN;

import PROCESS.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.sql.*;

public class UserAccount {

	Collection<Job> Job;
	private String UserRole;
	private int EmployeeID;
	private String Username;
	private String Password;
	private String EmployeeName;
	String tablename = "UserAccounts";

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
		// TODO - implement UserAccount.getEmployeeName
		throw new UnsupportedOperationException();
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

	/**
	 * AUTHOR: Javonne
	 * Description: This constructor will write a new user account to the db with information from this table+
//	 * @param UserData
//	 * @param UserName
//	 * @param EmployeeID
	 * @param EmployeeName
	 * @param UserRole
	 */
	public static void UserAccount(String tablename, String EmployeeName, String Username, String password, String UserRole) throws SQLException {

	}

	public UserAccount(String userRole, String username, String password, String employeeName) throws SQLException {
		this.UserRole = userRole;
		this.Username = username;
		this.Password = password;
		this.EmployeeName = employeeName;

		String sql = "INSERT INTO " + tablename + "(Username, RoleName, Password, StaffName) " + "VALUES(\"" + EmployeeName + "\", \"" + Username + "\", \"" + password + "\", \"" + UserRole + "\");" ;
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);
	}

	public static void main(String[] args) throws SQLException {

		UserAccount root = new UserAccount("superuser", "root", "user", "n/a");

	}


}