package CUSTOMER;

import PROCESS.*;

import java.sql.*;
import java.util.ArrayList;

public class FlexibleDiscountRate extends Discount {

	private int accountNumber;
	private ArrayList<int[]> bandsToRate;

	public FlexibleDiscountRate(){
		bandsToRate = new ArrayList<>();
	}

	public FlexibleDiscountRate(int accountNumber, ArrayList<int[]> bandsToRate) throws SQLException {
		this.accountNumber = accountNumber;
		this.bandsToRate = bandsToRate;
	}

	public FlexibleDiscountRate(int discountId, int accountNumber, ArrayList<int[]> bandsToRate){
		this.discountId = discountId;
		this.accountNumber = accountNumber;
		this.bandsToRate = bandsToRate;
	}

	public ArrayList<int[]> getDiscountRate() {
		return bandsToRate;
	}

	/**
	 */
	public void setDiscountRate(ArrayList<int[]> bandsToRate) {
		this.bandsToRate = bandsToRate;
	}

	public void retrieveDiscount(int accountNumber) throws SQLException {
		String sql = "SELECT * FROM FlexibleDiscount WHERE AccountNumber = " + accountNumber + " ORDER BY BandNumber ASC;";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		boolean isIdSet = false;
		while(resultSet.next()){
			if(!isIdSet){
				bandsToRate.clear();
				this.discountId = resultSet.getInt("DiscountID");
				this.accountNumber = accountNumber;
				isIdSet = true;
			}
			int lower = resultSet.getInt("VolumeLowerBound");
			int upper = resultSet.getInt("VolumeUpperBound");
			int rate = resultSet.getInt("FlexibleDiscountRate");

			bandsToRate.add(new int[]{lower, upper, rate});
		}
	}

	public void saveDiscount() throws SQLException {

		for(int i = 0; i < bandsToRate.size(); i++) {
			String sql = "INSERT INTO FlexibleDiscount (BandNumber, AccountNumber, VolumeLowerBound, VolumeUpperBound, FlexibleDiscountRate)" +
					" values(" + i + ","
					+ accountNumber + ","
					+ bandsToRate.get(i)[0] + ","
					+ bandsToRate.get(i)[1] + ","
					+ bandsToRate.get(i)[2] + ");";
			System.out.println(sql);
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
		}
	}

	public static boolean ifFlexDiscountExists(int accountNumber) throws SQLException {
		String sql = "SELECT COUNT(*) AS 'count' FROM FlexibleDiscount WHERE AccountNumber = " + accountNumber + ";";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		if(resultSet.next()){
			return resultSet.getInt("count") > 0;
		}
		return false;
	}
}