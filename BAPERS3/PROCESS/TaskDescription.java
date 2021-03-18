package PROCESS;

import java.sql.*;
import java.util.ArrayList;

public class TaskDescription {

	private int TaskID;
	private String TaskLocation;
	private float TaskPrice;
	private String DescriptionOfTask;
	private int Duration;
	static String tablename = "Tasks";



	public float getTaskPrice() {
		return TaskPrice;
	}

	public String getDescriptionOfTask() {
		return DescriptionOfTask;
	}

	public int getDuration() {
		return Duration;
	}

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


	public float RetrievePrice() {
		// TODO - implement TaskDescription.RetrievePrice
		throw new UnsupportedOperationException();
	}

	public int getTaskID() {
		// TODO - implement TaskDescription.getTaskID
		return TaskID;
	}

	/**
	 * 
	 * @param TaskID
	 */
	public void setTaskID(int TaskID) {
		// TODO - implement TaskDescription.setTaskID
		this.TaskID = TaskID;
	}

	public String getTaskLocation() {
		// TODO - implement TaskDescription.getTaskLocation
		return TaskLocation;
	}

	/**
	 * 
	 * @param TaskLocation
	 */
	public void setTaskLocation(String TaskLocation) {
		// TODO - implement TaskDescription.setTaskLocation
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param TaskData
	 */
	public boolean StoreTask(String TaskData) {
		// TODO - implement TaskDescription.StoreTask
		throw new UnsupportedOperationException();
	}

	public static ArrayList<String[]> GetTaskList() throws SQLException {
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM " + tablename;
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		// adding changes to an array list
		while (resultSet.next()){

			int TaskID = resultSet.getInt("TaskID");
			String TaskLocation = resultSet.getString("TaskLocation");
			int TaskPrice = resultSet.getInt("TaskPrice");
			String DescriptionOfTask = resultSet.getString("TaskDescription");
			int Duration = resultSet.getInt("PredictedDuration");


//			tuple = "employeeID: " + employeeID +
//					" username: " + username +
//					" roleName: " + roleName +
//					" staffName: " + staffName;

			tuple = TaskID + "`"
					+ TaskLocation + "`"
					+ TaskPrice + "`"
					+ DescriptionOfTask + "`"
					+ Duration;

			arrayList.add(tuple.split("`"));

		}
		return arrayList;
	}

	public TaskDescription(String TaskLocation, int TaskPrice, String TaskDescription, int Duration) throws SQLException {
		this.TaskLocation = TaskLocation;
		this.TaskPrice = TaskPrice;
		this.DescriptionOfTask = TaskDescription;
		this.Duration = Duration;
				
	}
	
	public static void NewTask(String TaskLocation, int TaskPrice, String TaskDescription, int Duration) throws SQLException {
		String sql =
				"INSERT INTO " + tablename + " ( TaskLocation, TaskPrice, TaskDescription, PredictedDuration) "
				+ "VALUES (" + "\"" + TaskLocation + "\", " + TaskPrice + ", \"" + TaskDescription + "\", " + Duration + ");";
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);
	}

	/**
	 * 
	 * @param TaskID
	 * @param TaskLocation
	 * @param TaskPrice
	 * @param DecriptionOfTask
	 * @param Duration
	 */
	public static TaskDescription TaskDescription(int TaskID, String TaskLocation, float TaskPrice, String DecriptionOfTask, float Duration) {
		// TODO - implement TaskDescription.TaskDescription
		throw new UnsupportedOperationException();
	}

	public static void main(String[] args) throws SQLException {

		TaskDescription.NewTask( "dddddddddd", 3, "ssssssssss", 360);

		ArrayList<String[]> al = TaskDescription.GetTaskList();
		System.out.println();
		// test to ensure correct alist format
		for(String[] col: al){
			for (String a: col){
				System.out.println(a);

			}
		}
	}
}