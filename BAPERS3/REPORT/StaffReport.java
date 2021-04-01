package REPORT;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import java.io.File;
import java.sql.*;
import java.util.*;

public class StaffReport extends Report {

	static String tablename = "UserAccounts";
	static String url = "jdbc:mysql://localhost:3306/Bapers";
	static String dbusername = "jaroviadb";
	static String dbpassword = "Jarovia123#@!";
	static Connection connection;
	private static final String DEST = "../BAPERS-FINAL/BAPERS3/GENERATED/REPORTS/STAFFREPORT/StaffReport" + Calendar.getInstance().getTimeInMillis() + ".pdf";

	private static Timer timer = new Timer();
	private static boolean isTimerStarted = false;

	static {
		try {
			connection = DriverManager.getConnection(
					url, dbusername, dbpassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		//File file = new File(DEST);
//		new StaffReport().printStaffReport("sun mar 28", "2021");
		new StaffReport().printStaffReport("wed dec 23", "2020");
//		ArrayList<String> t = StaffReport.GetTechnicianNames();
//		for (String st : t){
//			System.out.println(st);
//		}
	}

	public static void stopTimer(){
		System.out.println("stopped");
		timer.cancel();
		timer.purge();
	}

	// todo
	public static ArrayList<String> GetTechnicianNames() throws SQLException {
		String sql =
				"SELECT StaffName\n" +
						"FROM UserAccounts\n" +
						"WHERE RoleName LIKE '%%';";

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		ArrayList<String> technicianNames = new ArrayList<>();

		while (resultSet.next()){
			String name = resultSet.getString("StaffName");
			technicianNames.add(name);
		}

		return technicianNames;
	}




	public static void autoGenerateReport(int minutes){

		if(isTimerStarted) {
			stopTimer();
		}
		timer = new Timer();
		System.out.println(isTimerStarted);
		isTimerStarted = true;

		String str  = Calendar.getInstance().getTime().toString();
		System.out.println("staff report"+Calendar.getInstance().getTime().toString());

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {
					printStaffReport(str.substring(4,8), str.substring(24,28));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		timer.schedule(task, 1,minutes*60*1000);
	}

	public static void printStaffReport(String date, String year) throws Exception {

		String DEST = "../BAPERS-FINAL/BAPERS3/GENERATED/REPORTS/STAFFREPORT/StaffReport" + Calendar.getInstance().getTimeInMillis() + ".pdf";

		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(DEST));
		Document doc = new Document(pdfDoc);
		doc.add(new Paragraph("Staff report"));

		Table table = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();
		List<List<String>> dataset = null;

		ArrayList<String[]> SR1 = StaffReport1(date, year);
		dataset = convertTypes(SR1);
		table.addCell("Name");
		table.addCell("Tasks IDs");
		table.addCell("Department");
		table.addCell("Date/Start time");
		table.addCell("Time taken");
		for (List<String> record : dataset) {
			for (String field : record) {
				table.addCell(new Cell().add(new Paragraph(field)));
			}
		}

		//-----
		ArrayList<ArrayList<String[]>> SR2 = StaffReport2(date, year);
		for (ArrayList<String[]> individualSR2 : SR2){

//			doc.add(table);
//			table = new Table(2);

			dataset = convertTypes(individualSR2);
			for (List<String> record : dataset) {
				doc.add(new Paragraph(""));
				doc.add(table);
				table = new Table(2);

				table.addCell("Time spent working");
				table.addCell("Staff name");
				for (String field : record) {
					table.addCell(new Cell().add(new Paragraph(field)));
				}
			}

		}


		// ----------
		doc.add(new Paragraph(""));
		table = new Table(1);
		table.addCell("Time spent working on day (all staff)");
		ArrayList<String[]> SR3 = StaffReport3(date, year);
		dataset = convertTypes(SR3);
		for (List<String> record : dataset) {
			for (String field : record) {
				table.addCell(new Cell().add(new Paragraph(field)));
			}
		}
		doc.add(table);
		doc.close();
	}

	public static ArrayList<String[]> StaffReport1(String date, String year) throws SQLException {
		String sql =
				"SELECT `EmployeeCompletedBy` AS 'Name', T.`TaskID` AS 'Tasks IDs', `TaskLocation` AS 'Department', `TaskStartTime` AS 'Date/Start time', `ActualDuration` AS 'Time taken'\n" +
						"FROM TaskInAJob TIJ,\n" +
						"     Tasks T\n" +
						"WHERE TIJ.TaskID = T.TaskID\n" +
						"  AND TIJ.TaskStartTime LIKE '%" + date + "%'\n" +
						"AND TIJ.TaskStartTime LIKE '%" + year + "%'\n" +
						"  AND TIJ.isCompleted = 1\n" +
						"ORDER BY EmployeeCompletedBy;";

		System.out.println(sql);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		// adding changes to an array list
		while (resultSet.next()){

			String a = resultSet.getString("Name");
			int b = resultSet.getInt("Tasks IDs");
			String c = resultSet.getString("Department");
			String d = resultSet.getString("Date/Start time");
			int e = resultSet.getInt("Time taken");

			tuple = a + "`"	+ b + "`" + c + "`" + d + "`" + e;
			arrayList.add(tuple.split("`"));

		}

		return arrayList;
	}

	public static ArrayList<ArrayList<String[]>> StaffReport2(String date, String year) throws SQLException {
		ArrayList<ArrayList<String[]>> EmployeesWorkDone = new ArrayList<>();
		ArrayList<String> technicians = GetTechnicianNames();
		for (String technician : technicians) {

			String sql =
					"SELECT SUM(`ActualDuration`) AS TimeSpent, `EmployeeCompletedBy`\n" +
							"FROM TaskInAJob TIJ,\n" +
							"     Tasks T\n" +
							"WHERE TIJ.TaskID = T.TaskID\n" +
							"  AND TIJ.TaskStartTime LIKE '%" + date + "%'\n" +
							"AND TIJ.TaskStartTime LIKE '%" + year + "%'\n" +
							"  AND TIJ.isCompleted = 1\n" +
							"  AND TIJ.EmployeeCompletedBy LIKE '%" + technician + "%'\n" +
							"GROUP BY TIJ.EmployeeCompletedBy\n" +
							"ORDER BY EmployeeCompletedBy;";

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			ArrayList<String[]> arrayList = new ArrayList<String[]>();
			String tuple;

			while (resultSet.next()) {

				int a = resultSet.getInt("TimeSpent");
				String b = resultSet.getString("EmployeeCompletedBy");
				tuple = a + "`" + b;
				arrayList.add(tuple.split("`"));
			}
			EmployeesWorkDone.add(arrayList);
		}
		return EmployeesWorkDone;
	}

	public static ArrayList<String[]> StaffReport3(String date, String year) throws SQLException {
		String sql =
				"SELECT SUM(TIJ.ActualDuration) AS 'Total time on chosen date'\n" +
						"FROM TaskInAJob TIJ,\n" +
						"     Tasks T\n" +
						"WHERE TIJ.TaskStartTime LIKE '%" + date + "%'\n" +
						"AND TIJ.TaskStartTime LIKE '%" + year + "%'\n" +
						"  AND TIJ.isCompleted = 1" +
						"  AND TIJ.TaskID = T.TaskID;";

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		while(resultSet.next()){
			int a = resultSet.getInt("Total time on chosen date");
			tuple = a + "" ;
			arrayList.add(tuple.split("`"));


		}

		return arrayList;
	}


	// old

//	public static void autoGenerateReport(int minutes, int acccountNumber, String /**dddmm*/dayOfYear){
//
//		Timer timer = new Timer();
//		TimerTask task = new TimerTask() {
//			@Override
//			public void run() {
//				try {
//					new StaffReport().printStaffReport(dayOfYear);
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		};
//		timer.schedule(task, 1,minutes*60*1000);
//	}

	/**
	 * This code was written following the following tutorial
	 * https://github.com/itext/i7js-examples/blob/develop/src/main/java/com/itextpdf/samples/sandbox/tables/ArrayToTable.java
	 */
	public void printStaffReport(String date) throws Exception {

		String DEST = "../BAPERS-FINAL/BAPERS3/GENERATED/REPORTS/STAFFREPORT/StaffReport" + Calendar.getInstance().getTimeInMillis() + ".pdf";

		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(DEST));
		Document doc = new Document(pdfDoc);
		doc.add(new Paragraph("Title1"));

		Table table = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();
		List<List<String>> dataset = null;

		ArrayList<String[]> SR1 = StaffReport1(date);
		dataset = convertTypes(SR1);

		for (List<String> record : dataset) {
			for (String field : record) {
				table.addCell(new Cell().add(new Paragraph(field)));
			}
		}

		//-----
		ArrayList<ArrayList<String[]>> SR2 = StaffReport2(date);
		for (ArrayList<String[]> individualSR2 : SR2){

			doc.add(table);
			table = new Table(2);
			dataset = convertTypes(individualSR2);
			for (List<String> record : dataset) {
				doc.add(new Paragraph("Title2"));
				for (String field : record) {
					table.addCell(new Cell().add(new Paragraph(field)));
				}
			}

		}

		// ----------
		doc.add(table);
		table = new Table(1);
		doc.add(new Paragraph("Title3"));
		ArrayList<String[]> SR3 = StaffReport3(date);
		System.out.println("295");
		dataset = convertTypes(SR3);
		System.out.println("295");

		for (List<String> record : dataset) {
			for (String field : record) {
				table.addCell(new Cell().add(new Paragraph(field)));
			}
		}
		doc.add(table);
		doc.close();
	}

	public static ArrayList<String[]> StaffReport1(String date) throws SQLException {
		String sql =
				"SELECT `EmployeeCompletedBy` AS 'Name', T.`TaskID` AS 'Tasks IDs', `TaskLocation` AS 'Department', `TaskStartTime` AS 'Date/Start time', `ActualDuration` AS 'Time taken'\n" +
				"FROM TaskInAJob TIJ,\n" +
				"     Tasks T\n" +
				"WHERE TIJ.TaskID = T.TaskID\n" +
				"  AND TIJ.TaskStartTime LIKE '%" + date + "%'\n" +
				"  AND TIJ.isCompleted = 1\n" +
				"ORDER BY EmployeeCompletedBy;";

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		// adding changes to an array list
		while (resultSet.next()){

			String a = resultSet.getString("Name");
			int b = resultSet.getInt("Tasks IDs");
			String c = resultSet.getString("Department");
			String d = resultSet.getString("Date/Start time");
			int e = resultSet.getInt("Time taken");

			tuple = a + "`"	+ b + "`" + c + "`" + d + "`" + e;
			arrayList.add(tuple.split("`"));

		}

		return arrayList;
	}

	public static ArrayList<ArrayList<String[]>> StaffReport2(String date) throws SQLException {
		ArrayList<ArrayList<String[]>> EmployeesWorkDone = new ArrayList<>();
		ArrayList<String> technicians = GetTechnicianNames();
		for (String technician : technicians) {

			String sql =
					"SELECT SUM(`ActualDuration`) AS TimeSpent, `EmployeeCompletedBy`\n" +
							"FROM TaskInAJob TIJ,\n" +
							"     Tasks T\n" +
							"WHERE TIJ.TaskID = T.TaskID\n" +
							"  AND TIJ.TaskStartTime LIKE '%" + date + "%'\n" +
							"  AND TIJ.isCompleted = 1\n" +
							"  AND TIJ.EmployeeCompletedBy LIKE '%" + technician + "%'\n" +
							"GROUP BY TIJ.EmployeeCompletedBy\n" +
							"ORDER BY EmployeeCompletedBy;";

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			ArrayList<String[]> arrayList = new ArrayList<String[]>();
			String tuple;

			while (resultSet.next()) {

				int a = resultSet.getInt("TimeSpent");
				String b = resultSet.getString("EmployeeCompletedBy");
				tuple = a + "`" + b;
				arrayList.add(tuple.split("`"));
			}
			EmployeesWorkDone.add(arrayList);
		}
		return EmployeesWorkDone;
	}

	public static ArrayList<String[]> StaffReport3(String date) throws SQLException {
		System.out.println("line 271");
		String sql =
				"SELECT SUM(TIJ.ActualDuration) AS 'Total time on chosen date'\n" +
				"FROM TaskInAJob TIJ,\n" +
				"     Tasks T\n" +
				"WHERE TIJ.TaskStartTime LIKE '%" + date + "%'\n" +
				"  AND TIJ.isCompleted = 1" +
				"  AND TIJ.TaskID = T.TaskID;";
		System.out.println(sql);

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		while(resultSet.next()){
			int a = resultSet.getInt("Total time on chosen date");
			tuple = a + "" ;
			arrayList.add(tuple.split("`"));


		}

		return arrayList;
	}
	


}