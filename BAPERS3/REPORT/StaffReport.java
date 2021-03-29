package REPORT;

import java.sql.Date;

// individual performance report
public class StaffReport extends Report {

	public static void main(String[] args) {
		// create query
		// create first result set
		// create second result set
		// create second third set
		//
	}

	public static void GR(){

		String sql = "SELECT `EmployeeCompletedBy` AS 'Name', T.`TaskID` AS 'Tasks IDs', `TaskLocation` AS 'Department', `TaskStartTime` AS 'Date/Start time', `ActualDuration` AS 'Time taken'\n" +
				"FROM TaskInAJob TIJ,\n" +
				"     Tasks T\n" +
				"WHERE TIJ.TaskID = T.TaskID\n" +
				"  AND TIJ.TaskStartTime LIKE '%'\n" +
				"  AND TIJ.isCompleted = 1\n" +
				"ORDER BY EmployeeCompletedBy;\n";








	}
	
	/**
	 * This code was written following the following tutorial
	 * https://github.com/itext/i7js-examples/blob/develop/src/main/java/com/itextpdf/samples/sandbox/tables/ArrayToTable.java
	 */

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