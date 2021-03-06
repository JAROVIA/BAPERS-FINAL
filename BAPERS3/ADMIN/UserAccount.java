package ADMIN;

import PROCESS.*;

import java.util.Collection;
import java.util.Date;

public class UserAccount {

	Collection<Job> Job;
	private String UserRole;
	private int EmployeeID;
	private String Username;
	private String Password;
	private String EmployeeName;

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
	 * 
	 * @param UserData
	 * @param UserName
	 * @param EmployeeID
	 * @param EmployeeName
	 * @param UserRole
	 */
	public static UserAccount UserAccount(String UserData, String UserName, int EmployeeID, String EmployeeName, String UserRole) {
		// TODO - implement UserAccount.UserAccount
		throw new UnsupportedOperationException();
	}

}