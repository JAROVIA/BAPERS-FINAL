package REPORT;

import java.sql.Date;
// Jobsheet
public class CustomerReport extends Report {

	private int InvoiceNumber;
	private int AccountNumber;
	private int JobID;
	private String CustomerName;
	private String ContactName;
	private String Address;
	private int PhoneNumber;
	private String DescriptionOfWork;
	private boolean JobCompletion;
	private String DiscountType;
	private int VariableDiscountRate;
	private int NumberOfCompletedTasks;
	private float TaskPrice;
	private float TotalPrice;

	/**
	 * 
	 * @param ReportID
	 * @param Date
	 * @param ReportData
	 */
	public boolean GenerateReport(int ReportID, Date Date, String ReportData) {
		// TODO - implement CustomerReport.GenerateReport
		throw new UnsupportedOperationException();
	}



	/**
	 * 
	 * @param ReportID
	 * @param Date
	 * @param ReportData
	 */
	public static CustomerReport CustomerReport(int ReportID, Date Date, String ReportData) {
		// TODO - implement CustomerReport.CustomerReport
		throw new UnsupportedOperationException();
	}

}