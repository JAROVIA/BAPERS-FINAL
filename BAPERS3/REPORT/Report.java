package BAPERS3.REPORT;

public abstract class Report {

	protected int ReportID;
	protected date Date;
	private string ReportData;

	/**
	 * 
	 * @param ReportID
	 * @param Date
	 * @param ReportData
	 */
	protected abstract boolean GenerateReport(int ReportID, Date Date, string ReportData);

}