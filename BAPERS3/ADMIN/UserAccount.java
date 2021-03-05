package BAPERS3.ADMIN;

import java.util.*;
import BAPERS3.PROCESS.*;

public class UserAccount {

	Collection<Job> Job;
	private string UserRole;
	private int EmployeeID;
	private string Username;
	private string Password;
	private string EmployeeName;

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
	public void DestroyAccount(string UserData) {
		// TODO - implement UserAccount.DestroyAccount
		throw new UnsupportedOperationException();
	}

	public string getEmployeeName() {
		// TODO - implement UserAccount.getEmployeeName
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param NewJobData
	 */
	public boolean CreateJob(string NewJobData) {
		// TODO - implement UserAccount.CreateJob
		throw new UnsupportedOperationException();
	}

	public string SetCustomerAccountStatus() {
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
	public void GenerateReport(string ReportData) {
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
	public static UserAccount UserAccount(string UserData, string UserName, int EmployeeID, string EmployeeName, string UserRole) {
		// TODO - implement UserAccount.UserAccount
		throw new UnsupportedOperationException();
	}

}