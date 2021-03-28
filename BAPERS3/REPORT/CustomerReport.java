package REPORT;

import ADMIN.UserAccount;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

// Jobsheet
public class CustomerReport extends Report {

//	private int InvoiceNumber;
//	private int AccountNumber;
//	private int JobID;
//	private String CustomerName;
//	private String ContactName;
//	private String Address;
//	private int PhoneNumber;
//	private String DescriptionOfWork;
//	private boolean JobCompletion;
//	private String DiscountType;
//	private int VariableDiscountRate;
//	private int NumberOfCompletedTasks;
//	private float TaskPrice;
//	private float TotalPrice;

    static String tablename = "UserAccounts";
    static String url = "jdbc:mysql://localhost:3306/Bapers";
    static String dbusername = "jaroviadb";
    static String dbpassword = "Jarovia123#@!";
    static Connection connection;
	private static final String DEST = "../PLEASE.pdf";
//	private static final String DEST = "/home/javonne/IdeaProjects/BAPERS-FINAL/BAPERS3/GENERATED/REPORTS/CUSTOMERREPORT/CustomerReport" + Calendar.getInstance().getTimeInMillis() + ".pdf";

    static {
        try {
            connection = DriverManager.getConnection(
                    url, dbusername, dbpassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public static void main(String[] args) throws Exception {
		File file = new File(DEST);
        new CustomerReport().printCustomerReport(1,"");
	}

	public ArrayList<String[]> CustomerReportList(int AccountNumber, String date) throws SQLException {
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

	public void printCustomerReport(int accountNumber, String date) throws Exception {
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

	public static List<List<String>> convertTypes(ArrayList<String[]> oldList){
		List<List<String>> list = new ArrayList<>();
		for (String[] strings : oldList){
			list.add(Arrays.asList(strings));
		}
		return list;
	}

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