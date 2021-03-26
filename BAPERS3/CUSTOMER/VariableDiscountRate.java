package CUSTOMER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class VariableDiscountRate extends Discount {

	private int taskID;
	private int accountNumebr;
	private int taskDiscountRate;

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

	public VariableDiscountRate(int taskID, int accountNumebr, int taskDiscountRate) throws SQLException {

		this.taskID = taskID;
		this.accountNumebr = accountNumebr;
		this.taskDiscountRate = taskDiscountRate;

		String sql = "";
		System.out.println(sql);
		Statement statement = connection.createStatement();

	}

	@Override
	public int setDiscountRate(int DiscountRate) {
		return 0;
	}

	/**
	 * 
	 * @param JobPrice
	 * @param AccountNumber
	 * @param DiscountRate
	 */
	public float SetFinalPrice(float JobPrice, int AccountNumber, int DiscountRate) {
		// TODO - implement VariableDiscountRate.SetFinalPrice
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param AccountNumber
	 * @param TaskID
	 * @param DiscountRate
	 */
	public int CalculateTaskDiscountRate(int AccountNumber, int TaskID, int DiscountRate) {
		// TODO - implement VariableDiscountRate.CalculateTaskDiscountRate
		throw new UnsupportedOperationException();
	}

	public int[] getTaskDiscountRate() {
		// TODO - implement VariableDiscountRate.getTaskDiscountRate
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param TaskDiscountRate
	 */
	public void setTaskDiscountRate(int[] TaskDiscountRate) {
		// TODO - implement VariableDiscountRate.setTaskDiscountRate
		throw new UnsupportedOperationException();
	}

	public static VariableDiscountRate VariableDiscountRate() {
		// TODO - implement VariableDiscountRate.VariableDiscountRate
		throw new UnsupportedOperationException();
	}

	public int getVariableDiscountRate() {
		// TODO - implement VariableDiscountRate.getVariableDiscountRate
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param VariableDiscountRate
	 */
	public void setVariableDiscountRate(int VariableDiscountRate) {
		// TODO - implement VariableDiscountRate.setVariableDiscountRate
		throw new UnsupportedOperationException();
	}

}