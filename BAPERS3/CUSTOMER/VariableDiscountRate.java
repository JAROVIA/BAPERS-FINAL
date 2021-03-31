package CUSTOMER;

import javafx.util.Pair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VariableDiscountRate extends Discount {

	private int accountNumber;
	private ArrayList<Pair<Integer, Integer>> taskIdToRate;

	public VariableDiscountRate(){
	}

	public VariableDiscountRate(int accountNumber, ArrayList<Pair<Integer, Integer>> taskIdToRate) throws SQLException {
		this.accountNumber = accountNumber;
		this.taskIdToRate = taskIdToRate;
	}

	public VariableDiscountRate(int discountId, int accountNumber, ArrayList<Pair<Integer, Integer>> taskIdToRate){
		this.discountId = discountId;
		this.accountNumber = accountNumber;
		this.taskIdToRate = taskIdToRate;
	}

	public void saveDiscount() throws SQLException {
		for (Pair<Integer, Integer> varDiscData : taskIdToRate) {
			String sql = "INSERT INTO VariableDiscount (TaskID, AccountNumber, TaskDiscount) VALUES (" +
					varDiscData.getKey() + "," +
					accountNumber + "," +
					varDiscData.getValue() +
					")";
			System.out.println(sql);
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
		}
	}

	public void retrieveVariableDiscount(int accountNumber) throws SQLException {
		String sql = "SELECT * FROM VariableDiscount WHERE AccountNumber = " + accountNumber + " ORDER BY TaskID ASC;";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		boolean isIdSet = false;
		while(resultSet.next()){
			if(!isIdSet){
				taskIdToRate = new ArrayList<>();
				this.discountId = resultSet.getInt("DiscountID");
				this.accountNumber = accountNumber;
				isIdSet = true;
			}
			Pair<Integer, Integer> idRate = new Pair<>(resultSet.getInt("TaskID"), resultSet.getInt("TaskDiscount"));
			taskIdToRate.add(idRate);
		}
	}

	public ArrayList<Pair<Integer, Integer>> getTaskDiscountRate() {
		return taskIdToRate;
	}

	/**
	 * 
	 * @param taskDiscountRate
	 */
	public void setTaskDiscountRate(ArrayList<Pair<Integer, Integer>> taskDiscountRate) {
		this.taskIdToRate = taskDiscountRate;
	}

	public static boolean ifVariableDiscountExists(int accountNumber) throws SQLException {
		String sql = "SELECT COUNT(*) AS 'count' FROM VariableDiscount WHERE AccountNumber = " + accountNumber + ";";
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