package REPORT;

import java.sql.Date;

// individual performance report
public class StaffReport extends Report {

	private String EmployeeName;
	private int TaskID;
	private String Department;
	private double StartTime;
	private int TimeTakenPerTask;
	private int TotalTimeTaken;

	/**
	 * 
	 * @param ReportID
	 * @param Date
	 * @param ReportData
	 */
	public boolean GenerateReport(int ReportID, Date Date, String ReportData) {
		// TODO - implement StaffReport.GenerateReport
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ReportID
	 * @param Date
	 * @param ReportData
	 */
	public static StaffReport StaffReport(int ReportID, Date Date, String ReportData) {
		// TODO - implement StaffReport.StaffReport
		throw new UnsupportedOperationException();
	}

}