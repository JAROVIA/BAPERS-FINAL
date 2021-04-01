package REPORT;

import ADMIN.UserAccount;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import javafx.scene.text.Font;

import java.io.File;
import java.sql.*;
import java.util.*;

import static REPORT.PDFGeneration.convertTypes;

// Jobsheet
public class CustomerReport extends Report {

    static String tablename = "UserAccounts";
    static String url = "jdbc:mysql://localhost:3306/Bapers";
    static String dbusername = "jaroviadb";
    static String dbpassword = "Jarovia123#@!";
    static Connection connection;
//	private static final String DEST = "../PLEASE.pdf";
	//private static final String DEST = "/home/javonne/IdeaProjects/BAPERS-FINAL/BAPERS3/GENERATED/REPORTS/CUSTOMERREPORT/CustomerReport" + Calendar.getInstance().getTimeInMillis() + ".pdf";

    static {
        try {
            connection = DriverManager.getConnection(
                    url, dbusername, dbpassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	private static Timer timer = new Timer();
	private static boolean isTimerStarted = false;

	public static void main(String[] args) throws Exception {
//		File file = new File(DEST);
		//CustomerReport.autoGenerateReport(1,1,"", "");
        new CustomerReport().printCustomerReport(1,"","");
	}

	public static ArrayList<String[]> CustomerReportList(int AccountNumber, String date, String year) throws SQLException {
		System.out.println("line 45");

		String sql =
				"SELECT CA.AccountNumber AS 'Account number',\n" +
						"       CA.CustomerName,\n" +
						"       CA.ContactName,\n" +
						"\n" +
						"       J.JobID AS 'Job',\n" +
						"       T.TaskPrice AS 'Price, £',\n" +
						"       T.`TaskID` AS 'Task',\n" +
						"       T.`TaskLocation` AS 'Department',\n" +
						"       `TaskStartTime` AS 'Start time/Date',\n" +
						"       `ActualDuration` AS 'Time Taken',\n" +
						"       `EmployeeCompletedBy`,\n" +
						"       JobTaskID\n" +
						"FROM TaskInAJob TIJ,\n" +
						"     Jobs J,\n" +
						"     Tasks T,\n" +
						"     CustomerAccounts CA\n" +
						"WHERE TIJ.isCompleted = 1\n" +
						"  AND CA.AccountNumber = " + AccountNumber + "\n" +
						"  AND TaskStartTime LIKE '%" + date + "%'\n" +
						"AND TIJ.TaskStartTime LIKE '%" + year + "%'\n" +
						"  AND J.`JobID` = TIJ.`JobID`\n" +
						"  AND T.TaskID = TIJ.TaskID\n" +
						"  AND J.`AccountNumber` = CA.`AccountNumber`\n" +
						";\n";
		System.out.println(sql);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		// adding changes to an array list
		while (resultSet.next()){

			int a = resultSet.getInt("Account number");
			String b = resultSet.getString("CustomerName");
			String c = resultSet.getString("ContactName");
			int d = resultSet.getInt("Job");
			int e = resultSet.getInt("Price, £");
			int f = resultSet.getInt("Task");
			String g = resultSet.getString("Department");
			String h = resultSet.getString("Start time/Date");
			int i = resultSet.getInt("Time Taken");
			String j = resultSet.getString("EmployeeCompletedBy");

			tuple = a + "`"	+ b + "`" + c + "`" + d + "`" + e + "`" + f + "`" + g + "`" + h + "`" + i + "`" + j;
			arrayList.add(tuple.split("`"));

		}
		return arrayList;
	}

	/**
	 * This code was written following the following tutorial
	 * https://github.com/itext/i7js-examples/blob/develop/src/main/java/com/itextpdf/samples/sandbox/tables/ArrayToTable.java
	 */
	public static void printCustomerReport(int accountNumber, String date, String year) throws Exception {

		String DEST = "../BAPERS-FINAL/BAPERS3/GENERATED/REPORTS/CUSTOMERREPORT/CustomerReport" + Calendar.getInstance().getTimeInMillis() + ".pdf";
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(DEST));

		Document doc = new Document(pdfDoc);
		doc.add(new Paragraph("Customer Report!"));
		// By default column width is calculated automatically for the best fit.
		// useAllAvailableWidth() method makes table use the whole page's width while placing the content.
//		Table table = new Table(UnitValue.createPercentArray(10));
		Table table = new Table(10,true);
		List<List<String>> dataset = null;
		Font yourCustomFont = new Font(1);
		// change toLoad to decide what you load
		ArrayList<String[]> toLoad = CustomerReportList(accountNumber, date, year);
		dataset = convertTypes(toLoad);
		table.addCell("Account Number");
		table.addCell("Customer Name");
		table.addCell("Contact Name");
		table.addCell("Job");
		table.addCell("Price, £");
		table.addCell("Task");
		table.addCell("Department");
		table.addCell("Start Time + Date");
		table.addCell("Time Taken");
		table.addCell("Completed By");
		for (List<String> record : dataset) {
			for (String field : record) {
				table.addCell(new Cell().add(new Paragraph(field)));
			}
		}

		doc.add(table);
		doc.close();
	}

//	public static void autoGenerateReport(int minutes, int acccountNumber, String /**dddmm*/dayOfYear, String year){
//
//		Timer timer = new Timer();
//		TimerTask task = new TimerTask() {
//			@Override
//			public void run() {
//				try {
//					new CustomerReport().printCustomerReport(acccountNumber, dayOfYear, year);
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		};
//		timer.schedule(task, 1,minutes*60*1000);
//	}

	// old

	public static ArrayList<String[]> CustomerReportList(int AccountNumber, String date) throws SQLException {
		String sql =
				"SELECT CA.AccountNumber AS 'Account number',\n" +
                        "       CA.CustomerName,\n" +
                        "       CA.ContactName,\n" +
                        "\n" +
                        "       J.JobID AS 'Job',\n" +
                        "       T.TaskPrice AS 'Price, £',\n" +
                        "       T.`TaskID` AS 'Task',\n" +
                        "       T.`TaskLocation` AS 'Department',\n" +
                        "       `TaskStartTime` AS 'Start time/Date',\n" +
                        "       `ActualDuration` AS 'Time Taken',\n" +
                        "       `EmployeeCompletedBy`,\n" +
                        "       JobTaskID\n" +
                        "FROM TaskInAJob TIJ,\n" +
                        "     Jobs J,\n" +
                        "     Tasks T,\n" +
                        "     CustomerAccounts CA\n" +
                        "WHERE TIJ.isCompleted = 1\n" +
                        "  AND CA.AccountNumber = " + AccountNumber + "\n" +
                        "  AND TaskStartTime LIKE '%" + date + "%'\n" +
                        "  AND J.`JobID` = TIJ.`JobID`\n" +
                        "  AND T.TaskID = TIJ.TaskID\n" +
                        "  AND J.`AccountNumber` = CA.`AccountNumber`\n" +
                        ";\n";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		// adding changes to an array list
		while (resultSet.next()){

			int a = resultSet.getInt("Account number");
			String b = resultSet.getString("CustomerName");
			String c = resultSet.getString("ContactName");
			int d = resultSet.getInt("Job");
			int e = resultSet.getInt("Price, £");
			int f = resultSet.getInt("Task");
			String g = resultSet.getString("Department");
			String h = resultSet.getString("Start time/Date");
			int i = resultSet.getInt("Time Taken");
			String j = resultSet.getString("EmployeeCompletedBy");

			tuple = a + "`"	+ b + "`" + c + "`" + d + "`" + e + "`" + f + "`" + g + "`" + h + "`" + i + "`" + j;
			arrayList.add(tuple.split("`"));

		}
		return arrayList;
	}

	/**
	 * This code was written following the following tutorial
	 * https://github.com/itext/i7js-examples/blob/develop/src/main/java/com/itextpdf/samples/sandbox/tables/ArrayToTable.java
	 */
	public static void printCustomerReport(int accountNumber, String date) throws Exception {
		String DEST = "../BAPERS-FINAL/BAPERS3/GENERATED/REPORTS/CUSTOMERREPORT/CustomerReport" + Calendar.getInstance().getTimeInMillis() + ".pdf";
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(DEST));
		Document doc = new Document(pdfDoc);
		doc.add(new Paragraph("Customer Report!"));
		// By default column width is calculated automatically for the best fit.
		// useAllAvailableWidth() method makes table use the whole page's width while placing the content.
		Table table = new Table(UnitValue.createPercentArray(10)).useAllAvailableWidth();
		List<List<String>> dataset = null;

		// change toLoad to decide what you load
		ArrayList<String[]> toLoad = CustomerReportList(accountNumber,date);
		dataset = convertTypes(toLoad);
		for (List<String> record : dataset) {

			for (String field : record) {
				table.addCell(new Cell().add(new Paragraph(field)));
			}
		}

		doc.add(table);
		doc.close();
	}

//	public static void autoGenerateReport(int minutes, int acccountNumber, String dayOfYear){

//    Timer timer = new Timer();
//    TimerTask task = new TimerTask() {
//        @Override
//        public void run() {
//			try {
//				new CustomerReport().printCustomerReport(acccountNumber, dayOfYear);
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//    };
//		timer.schedule(task, 1,minutes*60*1000);
//	}

	public static void stopTimer() {
		System.out.println("stopped");
		timer.cancel();
		timer.purge();
	}

	public static void autoGenerateReport(int minutes, int accountNumber) {

		if (isTimerStarted) {
			stopTimer();
		}
		timer = new Timer();
		System.out.println(isTimerStarted);
		isTimerStarted = true;
		String str  = Calendar.getInstance().getTime().toString();
		System.out.println("customer report : " + Calendar.getInstance().getTime().toString());

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {
					printCustomerReport(accountNumber, str.substring(4,8), str.substring(24,28));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		timer.schedule(task, 1, minutes * 60 * 1000);
	}

}