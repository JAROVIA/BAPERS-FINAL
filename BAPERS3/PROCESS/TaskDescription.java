package PROCESS;

import java.sql.*;
import java.util.ArrayList;

public class TaskDescription {

	private static int TaskID;
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

	public static int getTaskIDStatic() {
		return TaskID;
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
		// TODO this needs to be a method on the page that autocalcs the price using Java
		throw new UnsupportedOperationException();
	}

	public int getTaskID() {
		// TODO - implement TaskDescription.getTaskID
		return TaskID;
	}

	/**
	 * 
	 * @param taskID
	 */
	public void setTaskID(int taskID) {
		TaskID = taskID;
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

	public static String[] getTask(int taskID) throws SQLException {
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM " + tablename + " where TaskID = " + taskID + " AND IsArchived = 0";
		ResultSet resultSet = statement.executeQuery(sql);

		if(resultSet.next()){
			return new String[]{
					String.valueOf(resultSet.getInt("TaskID")),
					resultSet.getString("TaskLocation"),
					String.format("%.2f",resultSet.getFloat("TaskPrice")),
					resultSet.getString("TaskDescription"),
					String.valueOf(resultSet.getInt("PredictedDuration"))
			};
		}
		return null;
	}

	/**
	 * returns an arraylist of all tasks BIPL offers
	 * */
	public static ArrayList<String[]> GetTaskList() throws SQLException {
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM " + tablename + " where IsArchived = 0";
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		// adding changes to an array list
		while (resultSet.next()){

			int TaskID = resultSet.getInt("TaskID");
			String TaskLocation = resultSet.getString("TaskLocation");
			float TaskPrice = resultSet.getFloat("TaskPrice");
			String DescriptionOfTask = resultSet.getString("TaskDescription");
			int Duration = resultSet.getInt("PredictedDuration");


//			tuple = "employeeID: " + employeeID +
//					" username: " + username +
//					" roleName: " + roleName +
//					" staffName: " + staffName;

			tuple = TaskID + "`"
					+ DescriptionOfTask + "`"
					+ TaskLocation + "`"
					+ String.format("%.2f", TaskPrice) + "`"
					+ Duration + "`"
			;

			arrayList.add(tuple.split("`"));

		}
		return arrayList;
	}

	/**
	 * Creates a TaskDescription object. Allows get/setMethods to work
	 */
	public TaskDescription(String TaskLocation, int TaskPrice, String TaskDescription, int Duration) throws SQLException {
		this.TaskLocation = TaskLocation;
		this.TaskPrice = TaskPrice;
		this.DescriptionOfTask = TaskDescription;
		this.Duration = Duration;
				
	}

	/**
	 * Creates a new BIPL task and adds it to the db
	 *
	 *
	 *
	 * */
	public static void NewTask(String TaskLocation, float TaskPrice, String TaskDescription, int Duration) throws SQLException {
		String sql =
				"INSERT INTO " + tablename + " ( TaskLocation, TaskPrice, TaskDescription, PredictedDuration) "
				+ "VALUES (" + "\"" + TaskLocation + "\", " + TaskPrice + ", \"" + TaskDescription + "\", " + Duration + ");";
		Statement statement = connection.createStatement();
		System.out.println(sql);
		statement.executeUpdate(sql);
	}

	public static TaskDescription TaskDescription(int TaskID, String TaskLocation, float TaskPrice, String DecriptionOfTask, float Duration) {
		// TODO - implement TaskDescription.TaskDescription
		throw new UnsupportedOperationException();
	}

	// todo
	public static void ArchiveTask(int TaskID) throws SQLException {
		String sql = "UPDATE Tasks SET IsArchived = 1 WHERE TaskID = " + TaskID + ";";
		Statement statement = connection.createStatement();
		System.out.println(sql);
		statement.executeUpdate(sql);
	}

	// todoTask edit
	public static void UpdateTask(String columnToEdit, int newValue, String identifierOfTableToEdit, int identifierCurrentValue) throws SQLException {

		// TaskInAJob.UpdateJob("TaskPrice", 76, "TaskID", 1);
		String sql = "UPDATE Tasks SET " + columnToEdit + " = " + newValue + " WHERE " + identifierOfTableToEdit + " = " + identifierCurrentValue + ";" ;
		System.out.println(sql);
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);

	}

	public static void UpdateTask(String columnToEdit, String newValue, String identifierOfTableToEdit, int identifierCurrentValue) throws SQLException {

		// TaskInAJob.UpdateJob("TaskPrice", 76, "TaskID", 1);
		String sql = "UPDATE Tasks SET " + columnToEdit + " = '" + newValue + "' WHERE " + identifierOfTableToEdit + " = " + identifierCurrentValue + ";" ;
		System.out.println(sql);
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);

	}




	public static void main(String[] args) throws SQLException {

//		TaskDescription.GetTaskList();

//		TaskDescription.NewTask( "D", 3, "S", 360);

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