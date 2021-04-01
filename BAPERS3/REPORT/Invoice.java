package REPORT;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Invoice extends Report{

    static String tablename = "UserAccounts";
    static String url = "jdbc:mysql://localhost:3306/Bapers";
    static String dbusername = "jaroviadb";
    static String dbpassword = "Jarovia123#@!";
    static Connection connection;
    private static final String DEST = "../BAPERS-FINAL/BAPERS3/GENERATED/REPORTS/STAFFREPORT/StaffReport" + Calendar.getInstance().getTimeInMillis() + ".pdf";

    static {
        try {
            connection = DriverManager.getConnection(
                    url, dbusername, dbpassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new Invoice().printInvoice(7);
    }

    public void printInvoice(int JobID) throws Exception {
        String DEST = "../BAPERS-FINAL/BAPERS3/GENERATED/REPORTS/INVOICE/Invoice" + Calendar.getInstance().getTimeInMillis() + ".pdf";
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(DEST));
        Document doc = new Document(pdfDoc);
        doc.add(new Paragraph("Customer Report!"));
        // By default column width is calculated automatically for the best fit.
        // useAllAvailableWidth() method makes table use the whole page's width while placing the content.
        Table table = new Table(UnitValue.createPercentArray(9),true);
        List<List<String>> dataset = null;

        // change toLoad to decide what you load
        ArrayList<String[]> toLoad = GetInvoice(JobID);
        dataset = convertTypes(toLoad);
        table.addCell("JobID");
        table.addCell("TaskID");
        table.addCell("Account Number");
        table.addCell("Account Status");
        table.addCell("Phone Number");
        table.addCell("Contact Name");
        table.addCell("Customer Name");
        table.addCell("Price");
        table.addCell("Discounted Price");
        for (List<String> record : dataset) {
            for (String field : record) {
                table.addCell(new Cell().add(new Paragraph(field)));
            }
        }

        doc.add(table);
        doc.close();
    }


    public static ArrayList<String[]> GetInvoice(int JobID) throws SQLException {

        String sql =
        "SELECT TIJ.JobID,\n" +
        "       TIJ.TaskID,\n" +
        "       CA.AccountNumber,\n" +
        "       CA.AccountStatus,\n" +
        "       CA.PhoneNumber,\n" +
        "       CA.ContactName,\n" +
        "       CA.CustomerName,\n" +
        "       P.Price,\n" +
        "       P.DiscountedPrice\n" +
        "\n" +
        "       -- get current time via java module\n" +
        "FROM TaskInAJob TIJ,\n" +
        "     Payments P,\n" +
        "     CustomerAccounts CA\n" +
        "WHERE TIJ.AccountNumber = CA.AccountNumber\n" +
        "  AND P.AccountNumber = CA.AccountNumber\n" +
        "  AND P.JobID = TIJ.JobID\n" +
        "  AND TIJ.JobID = " + JobID + "\n" +
        ";";
        System.out.println(sql);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<String[]> arrayList = new ArrayList<String[]>();
        String tuple;

        while (resultSet.next()) {

            int a = resultSet.getInt("JobID");
            int b = resultSet.getInt("TaskID");
            int c = resultSet.getInt("AccountNumber");
            String d = resultSet.getString("AccountStatus");
            int e = resultSet.getInt("PhoneNumber");
            String f = resultSet.getString("ContactName");
            String g = resultSet.getString("CustomerName");
            int h = resultSet.getInt("Price");
            int i = resultSet.getInt("DiscountedPrice");

            tuple = a + "`" + b + "`" + c + "`" + d + "`" + e + "`" + f + "`" + g + "`" + h + "`" + i;
            arrayList.add(tuple.split("`"));
        }
        return arrayList;
    }
}
