package CUSTOMER;

import PROCESS.*;

import java.sql.*;

public class FixedDiscountRate extends Discount {

	private int fixedDiscountRate;

	public FixedDiscountRate() {
	}

	public FixedDiscountRate(int accountNumber, int fixedDiscountRate) {
		this.accountNumber = accountNumber;
		this.fixedDiscountRate = fixedDiscountRate;
	}

	public FixedDiscountRate(int discountId, int accountNumber, int fixedDiscountRate) {
		this.discountId = discountId;
		this.accountNumber = accountNumber;
		this.fixedDiscountRate = fixedDiscountRate;
	}

	public int getDiscountRate() {
		return fixedDiscountRate;
	}

	public void setDiscountRate(int discountRate) {
		this.fixedDiscountRate = discountRate;
	}

	public void retrieveDiscount(int accountNumber) throws SQLException {
		String sql = "SELECT * FROM FixedDiscount WHERE AccountNumber = " + accountNumber + ";";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		if(resultSet.next()){
			this.discountId = resultSet.getInt("DiscountID");
			this.fixedDiscountRate = resultSet.getInt("FlatDiscountRate");
			this.accountNumber = accountNumber;
		}
	}

	public void saveDiscount() throws SQLException {
		String sql = "INSERT INTO FixedDiscount (AccountNumber, FlatDiscountRate)values(" + accountNumber + "," + fixedDiscountRate + ");";
		System.out.println(sql);
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);
	}

	public static boolean ifFixDiscountExists(int accountNumber) throws SQLException {
		String sql = "SELECT COUNT(*) AS 'count' FROM FixedDiscount WHERE AccountNumber = " + accountNumber + ";";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		System.out.println(sql);

		if(resultSet.next()){
			System.out.println(resultSet.getInt("count"));
			return resultSet.getInt("count") > 0;
		}
		return false;
	}
}