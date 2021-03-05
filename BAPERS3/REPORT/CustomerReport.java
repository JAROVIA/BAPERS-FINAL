package BAPERS3.REPORT;

public class CustomerReport extends Report {

	private int InvoiceNumber;
	private int AccountNumber;
	private int JobID;
	private string CustomerName;
	private string ContactName;
	private string Address;
	private int PhoneNumber;
	private string DescriptionOfWork;
	private boolean JobCompletion;
	private string DiscountType;
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
	public boolean GenerateReport(int ReportID, Date Date, string ReportData) {
		// TODO - implement CustomerReport.GenerateReport
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ReportID
	 * @param Date
	 * @param ReportData
	 */
	public static CustomerReport CustomerReport(int ReportID, Date Date, string ReportData) {
		// TODO - implement CustomerReport.CustomerReport
		throw new UnsupportedOperationException();
	}

}