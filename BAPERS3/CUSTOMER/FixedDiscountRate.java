package CUSTOMER;

import PROCESS.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FixedDiscountRate extends Discount {

	private int accountNumebr;
	private int fixedDiscountRate;

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

	public FixedDiscountRate(int accountNumebr, int fixedDiscountRate) throws SQLException {

		this.accountNumebr = accountNumebr;
		this.fixedDiscountRate = fixedDiscountRate;

		String sql = "";
		System.out.println(sql);
		Statement statement = connection.createStatement();

	}

	/**
	 * 
	 * @param job
	 */
	public float CalculateDiscountRate(Job job) {
		// TODO - implement FixedDiscountRate.CalculateDiscountRate
		throw new UnsupportedOperationException();
	}

	public int getDiscountRate() {
		// TODO - implement FixedDiscountRate.getDiscountRate
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param DiscountRate
	 */
	public int setDiscountRate(int DiscountRate) {
		// TODO - implement FixedDiscountRate.setDiscountRate
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param JobPrice
	 * @param AccountNumber
	 * @param DiscountRate
	 */
	public float SetFinalPrice(float JobPrice, int AccountNumber, int DiscountRate) {
		// TODO - implement FixedDiscountRate.SetFinalPrice
		throw new UnsupportedOperationException();
	}

	public static FixedDiscountRate FixedDiscountRate() {
		// TODO - implement FixedDiscountRate.FixedDiscountRate
		throw new UnsupportedOperationException();
	}

}