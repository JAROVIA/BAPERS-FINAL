package CUSTOMER;

import java.sql.*;

public abstract class Discount {

	protected int discountId = 0;
	protected int accountNumber;

	static String url = "jdbc:mysql://localhost:3306/Bapers";
	static String username = "jaroviadb";
	static String password = "Jarovia123#@!";
	static Connection connection;

	static {
		try {
			connection = DriverManager.getConnection(
					url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteDiscount(int accountNumber, String discountTable) throws SQLException {
		String sql = "DELETE FROM " + discountTable + "Discount " + " WHERE AccountNumber = " + accountNumber + ";";
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);
	}

	public static boolean ifDiscountExists(int accountNumber) throws SQLException {
		String sql = "SELECT SUM(customer_count_table.customer_count) AS 'sum' " +
				"FROM (" +
				"SELECT COUNT(*) as customer_count FROM FixedDiscount WHERE AccountNumber = " + accountNumber + " " +
				"UNION ALL " +
				"SELECT COUNT(*) as customer_count FROM FlexibleDiscount WHERE AccountNumber = 1" + accountNumber + " " +
				"UNION ALL " +
				"SELECT COUNT(*) as customer_count FROM VariableDiscount WHERE AccountNumber = 1" + accountNumber + " " +
				")customer_count_table;";

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while(resultSet.next()){
			if(resultSet.getInt("sum") > 0){
				System.out.println(resultSet.getInt("sum"));
				return true;
			}
		}
		return false;
	}
}