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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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


	public static void main(String[] args) throws Exception {
		//File file = new File(DEST);
		new SummaryReport().printSummaryReport("sun mar 28");
	}

	/**
	 * This code was written following the following tutorial
	 * https://github.com/itext/i7js-examples/blob/develop/src/main/java/com/itextpdf/samples/sandbox/tables/ArrayToTable.java
	 */

	public void printSummaryReport(String date) throws Exception {

		String DEST = "../BAPERS-FINAL/BAPERS3/GENERATED/REPORTS/STAFFREPORT/StaffReport" + Calendar.getInstance().getTimeInMillis() + ".pdf";

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