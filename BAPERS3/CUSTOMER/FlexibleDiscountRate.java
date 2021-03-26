package CUSTOMER;

import PROCESS.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FlexibleDiscountRate extends Discount {

	private int bandNumber;
	private int accountNumber;
	private int volumeLowerBound;
	private int volumeUpperBound;
	private int flexibleDiscountRate;

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

	public FlexibleDiscountRate(int bandNumber, int accountNumber, int volumeLowerBound,
								int volumeUpperBound, int flexibleDiscountRate) throws SQLException {

		this.bandNumber = bandNumber;
		this.accountNumber = accountNumber;
		this.volumeLowerBound = volumeLowerBound;
		this.volumeUpperBound = volumeUpperBound;
		this.flexibleDiscountRate = flexibleDiscountRate;

		String sql = "";
		System.out.println(sql);
		Statement statement = connection.createStatement();
	}

	/**
	 * 
	 * @param job
	 */
	public float CalculateDiscountRate(Job job) {
		// TODO - implement FlexibleDiscountRate.CalculateDiscountRate
		throw new UnsupportedOperationException();
	}

	public int getDiscountRate() {
		// TODO - implement FlexibleDiscountRate.getDiscountRate
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param DiscountRate
	 */
	public int setDiscountRate(int DiscountRate) {
		// TODO - implement FlexibleDiscountRate.setDiscountRate
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param JobPrice
	 * @param AccountNumber
	 * @param DiscountRate
	 */
	public float SetFinalPrice(float JobPrice, int AccountNumber, int DiscountRate) {
		// TODO - implement FlexibleDiscountRate.SetFinalPrice
		throw new UnsupportedOperationException();
	}

	public static FlexibleDiscountRate FlexibleDiscountRate() {
		// TODO - implement FlexibleDiscountRate.FlexibleDiscountRate
		throw new UnsupportedOperationException();
	}

}