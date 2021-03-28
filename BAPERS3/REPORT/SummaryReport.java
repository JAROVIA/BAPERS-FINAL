package REPORT;

import java.sql.Date;

// summary performance report
public class SummaryReport extends Report {

	private int ShiftID;
	private String Department;
	private float TimeInCopyRoom;
	private float TimeInDevelopment;
	private float TimeInFinishing;
	private float TimeInPacking;

	/**
	 * 
	 * @param ReportID
	 * @param Date
	 * @param ReportData
	 */
	public boolean GenerateReport(int ReportID, Date Date, String ReportData) {
		// TODO - implement SummaryReport.GenerateReport
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ReportID
	 * @param Date
	 * @param ReportData
	 */
	public static SummaryReport SummaryReport(int ReportID, Date Date, String ReportData) {
		// TODO - implement SummaryReport.SummaryReport
		throw new UnsupportedOperationException();
	}

}