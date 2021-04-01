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

// summary performance report
public class SummaryReport extends Report {

	static String tablename = "UserAccounts";
	static String url = "jdbc:mysql://localhost:3306/Bapers";
	static String dbusername = "jaroviadb";
	static String dbpassword = "Jarovia123#@!";
	static Connection connection;
//	private static final String DEST = "../BAPERS-FINAL/BAPERS3/GENERATED/REPORTS/SUMMARYREPORT/SummaryReport" + Calendar.getInstance().getTimeInMillis() + ".pdf";

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
		//File file = new File(DEST);
		new SummaryReport().printSummaryReport("wed mar 31", "2021");
		//new SummaryReport().autoGenerateReport(1,"wed mar 31", "2021");
	}

	public static void printSummaryReport(String date, String year) throws Exception {

		String str = Calendar.getInstance().getTime().toString();
		String weekday = str.substring(0,3);
		String day = str.substring(4,8);
		String month = str.substring(8,11);
		String yearNow = str.substring(24,28);

		String DEST = "../BAPERS-FINAL/BAPERS3/GENERATED/REPORTS/SUMMARYREPORT/SummaryReport"
				+ weekday.trim() + day.trim() + month.trim() + yearNow.trim() + "_" + Calendar.getInstance().getTimeInMillis() + ".pdf";

		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(DEST));
		Document doc = new Document(pdfDoc);
		doc.add(new Paragraph("Summary Report"));

		Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
		List<List<String>> dataset = null;

		ArrayList<String[]> SR1 = SummaryReport1(date, year);
		dataset = convertTypes(SR1);
		table.addCell("Time in Copy Coom");
		table.addCell("Shift Completed");
		for (List<String> record : dataset) {
			for (String field : record) {
				table.addCell(new Cell().add(new Paragraph(field)));
			}
		}
		doc.add(table);
		table = new Table(2);

		//--
		doc.add(new Paragraph(""));
		ArrayList<String[]> SR2 = SummaryReport2(date, year);
		dataset = convertTypes(SR2);
		table.addCell("Time in Development");
		table.addCell("Shift Completed");
		for (List<String> record : dataset) {
			for (String field : record) {
				table.addCell(new Cell().add(new Paragraph(field)));
			}
		}
		doc.add(table);
		table = new Table(2);

		//--
		doc.add(new Paragraph(""));
		ArrayList<String[]> SR3 = SummaryReport3(date, year);
		dataset = convertTypes(SR3);
		table.addCell("Time in Packing");
		table.addCell("Shift Completed");
		for (List<String> record : dataset) {
			for (String field : record) {
				table.addCell(new Cell().add(new Paragraph(field)));
			}
		}
		doc.add(table);
		table = new Table(2);

		//--
		doc.add(new Paragraph(""));
		ArrayList<String[]> SR4 = SummaryReport4(date, year);
		dataset = convertTypes(SR4);
		table.addCell("Time in Finishing");
		table.addCell("Shift Completed");
		for (List<String> record : dataset) {
			for (String field : record) {
				table.addCell(new Cell().add(new Paragraph(field)));
			}
		}
		doc.add(table);
//		table = new Table(2);

		doc.close();
	}

	public static ArrayList<String[]> SummaryReport1(String date, String year) throws SQLException {
		String sql =
				"SELECT SUM(TIJ.ActualDuration) AS 'Time in Copy Room', ANY_VALUE(TIJ.ShiftCompleted) AS 'During Shift'\n" +
						"FROM TaskInAJob TIJ,\n" +
						"     Tasks T\n" +
						"WHERE TIJ.TaskStartTime LIKE '%" + date + "%'\n" +
						"AND TIJ.TaskStartTime LIKE '%" + year + "%'\n" +
						"  AND T.TaskLocation LIKE '%copy room%'\n" +
						"GROUP BY TIJ.ShiftCompleted\n" +
						";\n";
		System.out.println(sql);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		// adding changes to an array list
		while (resultSet.next()){

			int a = resultSet.getInt("Time in Copy Room");
			int b = resultSet.getInt("During Shift");

			tuple = a + "`"	+ b;
			arrayList.add(tuple.split("`"));

		}

		return arrayList;
	}

	public static ArrayList<String[]> SummaryReport2(String date, String year) throws SQLException {
		String sql =
				"SELECT SUM(TIJ.ActualDuration) AS 'Time in Development', ANY_VALUE(TIJ.ShiftCompleted) AS 'During Shift'\n" +
						"FROM TaskInAJob TIJ,\n" +
						"     Tasks T\n" +
						"WHERE TIJ.TaskStartTime LIKE '%" + date + "%'\n" +
						"AND TIJ.TaskStartTime LIKE '%" + year + "%'\n" +
						"  AND T.TaskLocation LIKE '%Development area%'\n" +
						"GROUP BY TIJ.ShiftCompleted\n" +
						";";
		System.out.println(sql);

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		// adding changes to an array list
		while (resultSet.next()){

			int a = resultSet.getInt("Time in Development");
			int b = resultSet.getInt("During Shift");

			tuple = a + "`"	+ b;
			arrayList.add(tuple.split("`"));

		}

		return arrayList;
	}

	public static ArrayList<String[]> SummaryReport3(String date, String year) throws SQLException {
		String sql =
				"SELECT SUM(TIJ.ActualDuration) AS 'Time in Packing', ANY_VALUE(TIJ.ShiftCompleted) AS 'During Shift'\n" +
						"FROM TaskInAJob TIJ,\n" +
						"     Tasks T\n" +
						"WHERE TIJ.TaskStartTime LIKE '%" + date + "%'\n" +
						"AND TIJ.TaskStartTime LIKE '%" + year + "%'\n" +
						"  AND T.TaskLocation LIKE '%Packing Departments%'\n" +
						"GROUP BY TIJ.ShiftCompleted\n" +
						";\n";
		System.out.println(sql);

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		// adding changes to an array list
		while (resultSet.next()){

			int a = resultSet.getInt("Time in Packing");
			int b = resultSet.getInt("During Shift");

			tuple = a + "`"	+ b;
			arrayList.add(tuple.split("`"));

		}

		return arrayList;
	}

	public static ArrayList<String[]> SummaryReport4(String date, String year) throws SQLException {
		String sql =
				"SELECT SUM(TIJ.ActualDuration) AS 'Time in Finishing', ANY_VALUE(TIJ.ShiftCompleted) AS 'During Shift'\n" +
						"FROM TaskInAJob TIJ,\n" +
						"     Tasks T\n" +
						"WHERE TIJ.TaskStartTime LIKE '%" + date + "%'\n" +
						"AND TIJ.TaskStartTime LIKE '%" + year + "%'\n" +
						"  AND T.TaskLocation LIKE '%Finishing Room%'\n" +
						"GROUP BY TIJ.ShiftCompleted\n" +
						";";
		System.out.println(sql);

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		// adding changes to an array list
		while (resultSet.next()){

			int a = resultSet.getInt("Time in Finishing");
			int b = resultSet.getInt("During Shift");

			tuple = a + "`"	+ b;
			arrayList.add(tuple.split("`"));

		}

		return arrayList;
	}

//	public void autoGenerateReport(int minutes, String /**dddmm*/dayOfYear, String year){
//
//		Timer timer = new Timer();
//		TimerTask task = new TimerTask() {
//			@Override
//			public void run() {
//				try {
//					new SummaryReport().printSummaryReport(dayOfYear, year);
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		};
//		timer.schedule(task, 1,minutes*60*1000);
//	}

	public static void stopTimer(){
		System.out.println("stopped");
		timer.cancel();
		timer.purge();
	}

	public static void autoGenerateReport(int minutes){

		if(isTimerStarted) {
			stopTimer();
		}
		timer = new Timer();
		System.out.println(isTimerStarted);
		isTimerStarted = true;

		String str  = Calendar.getInstance().getTime().toString();
		System.out.println("summary report"+Calendar.getInstance().getTime().toString());

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {
					printSummaryReport(str.substring(4,8).trim(), str.substring(24,28).trim());

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		timer.schedule(task, 1,minutes*60*1000);
	}









	// old

	/**
	 * This code was written following the following tutorial
	 * https://github.com/itext/i7js-examples/blob/develop/src/main/java/com/itextpdf/samples/sandbox/tables/ArrayToTable.java
	 */
	public void printSummaryReport(String date) throws Exception {

		String DEST = "../BAPERS-FINAL/BAPERS3/GENERATED/REPORTS/SUMMARYREPORT/SummaryReport" + Calendar.getInstance().getTimeInMillis() + ".pdf";

		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(DEST));
		Document doc = new Document(pdfDoc);
		doc.add(new Paragraph("Title1"));

		Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
		List<List<String>> dataset = null;

		ArrayList<String[]> SR1 = SummaryReport1(date);
		dataset = convertTypes(SR1);
		for (List<String> record : dataset) {
			for (String field : record) {
				table.addCell(new Cell().add(new Paragraph(field)));
			}
		}
		doc.add(table);
		table = new Table(2);

		//--
		doc.add(new Paragraph("Title2"));
		ArrayList<String[]> SR2 = SummaryReport2(date);
		dataset = convertTypes(SR2);
		for (List<String> record : dataset) {
			for (String field : record) {
				table.addCell(new Cell().add(new Paragraph(field)));
			}
		}
		doc.add(table);
		table = new Table(2);

		//--
		doc.add(new Paragraph("Title3"));
		ArrayList<String[]> SR3 = SummaryReport3(date);
		dataset = convertTypes(SR3);
		for (List<String> record : dataset) {
			for (String field : record) {
				table.addCell(new Cell().add(new Paragraph(field)));
			}
		}
		doc.add(table);
		table = new Table(2);

		//--
		doc.add(new Paragraph("Title4"));
		ArrayList<String[]> SR4 = SummaryReport4(date);
		dataset = convertTypes(SR4);
		for (List<String> record : dataset) {
			for (String field : record) {
				table.addCell(new Cell().add(new Paragraph(field)));
			}
		}
		doc.add(table);
//		table = new Table(2);

		doc.close();
	}

	public static ArrayList<String[]> SummaryReport1(String date) throws SQLException {
		String sql =
				"SELECT SUM(TIJ.ActualDuration) AS 'Time in Copy Room', ANY_VALUE(TIJ.ShiftCompleted) AS 'During Shift'\n" +
						"FROM TaskInAJob TIJ,\n" +
						"     Tasks T\n" +
						"WHERE TIJ.TaskStartTime LIKE '%" + date + "%'\n" +
						"  AND T.TaskLocation LIKE '%copy room%'\n" +
						"GROUP BY TIJ.ShiftCompleted\n" +
						";\n";
		System.out.println(sql);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		// adding changes to an array list
		while (resultSet.next()){

			int a = resultSet.getInt("Time in Copy Room");
			int b = resultSet.getInt("During Shift");

			tuple = a + "`"	+ b;
			arrayList.add(tuple.split("`"));

		}

		return arrayList;
	}

	public static ArrayList<String[]> SummaryReport2(String date) throws SQLException {
		String sql =
				"SELECT SUM(TIJ.ActualDuration) AS 'Time in Development', ANY_VALUE(TIJ.ShiftCompleted) AS 'During Shift'\n" +
						"FROM TaskInAJob TIJ,\n" +
						"     Tasks T\n" +
						"WHERE TIJ.TaskStartTime LIKE '%" + date + "%'\n" +
						"  AND T.TaskLocation LIKE '%Development area%'\n" +
						"GROUP BY TIJ.ShiftCompleted\n" +
						";";
		System.out.println(sql);

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		// adding changes to an array list
		while (resultSet.next()){

			int a = resultSet.getInt("Time in Development");
			int b = resultSet.getInt("During Shift");

			tuple = a + "`"	+ b;
			arrayList.add(tuple.split("`"));

		}

		return arrayList;
	}

	public static ArrayList<String[]> SummaryReport3(String date) throws SQLException {
		String sql =
				"SELECT SUM(TIJ.ActualDuration) AS 'Time in Packing', ANY_VALUE(TIJ.ShiftCompleted) AS 'During Shift'\n" +
						"FROM TaskInAJob TIJ,\n" +
						"     Tasks T\n" +
						"WHERE TIJ.TaskStartTime LIKE '%" + date + "%'\n" +
						"  AND T.TaskLocation LIKE '%Packing Departments%'\n" +
						"GROUP BY TIJ.ShiftCompleted\n" +
						";\n";
		System.out.println(sql);

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		// adding changes to an array list
		while (resultSet.next()){

			int a = resultSet.getInt("Time in Packing");
			int b = resultSet.getInt("During Shift");

			tuple = a + "`"	+ b;
			arrayList.add(tuple.split("`"));

		}

		return arrayList;
	}

	public static ArrayList<String[]> SummaryReport4(String date) throws SQLException {
		String sql =
				"SELECT SUM(TIJ.ActualDuration) AS 'Time in Finishing', ANY_VALUE(TIJ.ShiftCompleted) AS 'During Shift'\n" +
						"FROM TaskInAJob TIJ,\n" +
						"     Tasks T\n" +
						"WHERE TIJ.TaskStartTime LIKE '%" + date + "%'\n" +
						"  AND T.TaskLocation LIKE '%Finishing Room%'\n" +
						"GROUP BY TIJ.ShiftCompleted\n" +
						";";
		System.out.println(sql);

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		// adding changes to an array list
		while (resultSet.next()){

			int a = resultSet.getInt("Time in Finishing");
			int b = resultSet.getInt("During Shift");

			tuple = a + "`"	+ b;
			arrayList.add(tuple.split("`"));

		}

		return arrayList;
	}
}