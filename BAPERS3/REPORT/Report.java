package REPORT;

import java.sql.Date;

public abstract class Report {

	protected int ReportID;
	protected java.sql.Date Date;
	private String ReportData;

	/**
	 * 
	 * @param ReportID
	 * @param Date
	 * @param ReportData
	 */
	protected abstract boolean GenerateReport(int ReportID, Date Date, String ReportData);

}