package PROCESS;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class Job implements I_PROCESS {

	private static int JobID;
	private static int AccountNumber;
	private static String Urgency = "normal";
	//TODO set this type (was timestamp)
	private String JobDeadline;
	private String JobStatus = "Ordered";
	private static int NumberOfTasks = 0;
	private int TasksCompleted = 0;
	private String DateOfJob;
	private int TaskProgress = 0;
	private float JobPrice = 0;
	private int IsCompleted = 0;

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


	/**
	 * Creates a Job object and adds it to the db.
	 *
	 * Should run when the confirm button on the page after create job order page is pressed.
	 *
	 * Needs the account number and urgency from the page after the create job button is pressed.
	 * */
	public Job(int AccountNumber, String Urgency) throws SQLException{
		this.AccountNumber = AccountNumber;
		this.Urgency = Urgency;
		this.JobDeadline = JobDeadline;
		this.JobStatus = JobStatus;
		this.DateOfJob = DateOfJob;

		java.util.Date date = new java.util.Date();
		DateOfJob = date.toString();





		/**
		 * calendar code built up from https://stackoverflow.com/questions/18733582/calculating-a-future-date#18733637
		 * */
		//24h
		if (Urgency == "normal"){
			Calendar c = Calendar.getInstance(); // create calendar object
			c.setTime(new java.util.Date()); //
			c.add(Calendar.DATE, 1);
			java.util.Date JobDeadlineDate = c.getTime();
			JobDeadline = JobDeadlineDate.toString();
		}
		//6h
		else if (Urgency == "urgent"){
			Calendar c = Calendar.getInstance();
			c.setTime(new java.util.Date());
			c.add(Calendar.HOUR, 6);
			java.util.Date JobDeadlineDate = c.getTime();
			JobDeadline = JobDeadlineDate.toString();
		}
		//3h
		else if (Urgency == "vurgent"){
			Calendar c = Calendar.getInstance();
			c.setTime(new java.util.Date());
			c.add(Calendar.HOUR, 3);
			java.util.Date JobDeadlineDate = c.getTime();
			JobDeadline = JobDeadlineDate.toString();
		}



		String sql =
				"INSERT INTO Jobs (AccountNumber, NumberOfTasks, DateOfJob, JobDeadline, JobUrgency, Price) Values("
						+ AccountNumber + ", " + NumberOfTasks + ", \"" + DateOfJob + "\", \"" + JobDeadline + "\", \"" + Urgency + "\", " + JobPrice + ");";
		System.out.println(sql);
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);

		//need to set job id equal to the actual job id
		String sqlSelect = "SELECT * FROM Jobs;"; /* SELECT * FROM Jobs WHERE IsArchived = 0; */
		ResultSet resultSetForJobID = statement.executeQuery(sqlSelect);
		while (resultSetForJobID.next()){
			setJobID(resultSetForJobID.getInt("JobID"));
		}


	}

	/**
	 * Pulls a list of Jobs from the db
	 * */
	public static ArrayList<String[]> GetJobList() throws SQLException {
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM Jobs;"; /* SELECT * FROM Jobs WHERE IsArchived = 0; */
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		// adding changes to an array list
		while (resultSet.next()){

			int JobID = resultSet.getInt("JobID");
			int AccountNumber = resultSet.getInt("AccountNumber");
			int NumberOfTasks = resultSet.getInt("NumberOfTasks");
			String DateOfJob = resultSet.getString("DateOfJob");
			String JobDeadline = resultSet.getString("JobDeadline");
			String JobUrgency = resultSet.getString("JobUrgency");
			int TasksCompleted = resultSet.getInt("TasksCompleted");
			int IsCompleted = resultSet.getInt("IsCompleted");

			tuple = JobID + "`"
					+ AccountNumber + "`"
					+ NumberOfTasks + "`"
					+ DateOfJob + "`"
					+ JobDeadline + "`"
					+ JobUrgency + "`"
					+ TasksCompleted + "`"
					+ IsCompleted;

			arrayList.add(tuple.split("`"));

		}
		return arrayList;
	}

	// any jobs within 2 hours of deadline
	public static ArrayList<String[]> GetLateJobList() throws SQLException {
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM Jobs;"; /* SELECT * FROM Jobs WHERE IsArchived = 0; */
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		// adding changes to an array list
		while (resultSet.next()){

			int JobID = resultSet.getInt("JobID");
			int AccountNumber = resultSet.getInt("AccountNumber");
			int NumberOfTasks = resultSet.getInt("NumberOfTasks");
			String DateOfJob = resultSet.getString("DateOfJob"); // date of job order
			String JobDeadline = resultSet.getString("JobDeadline"); // dependant on urgency
			String JobUrgency = resultSet.getString("JobUrgency");
			int TasksCompleted = resultSet.getInt("TasksCompleted");
			int IsCompleted = resultSet.getInt("IsCompleted");


			// im trying to check the current hour a job is due. vs the current system hour.

			String FullDateNow = Calendar.getInstance().getTime().toString().substring(0,11);
			String FullDateDue = JobDeadline.substring(0,11);

			String hourNow = Calendar.getInstance().getTime().toString().substring(11,13);
			String hourDue = JobDeadline.substring(11,13);
			int hourDueInt = Integer.parseInt(hourDue);
			// 1h before due time
			int hourAlmostDueInt;

			if (hourDueInt == 00){
				hourAlmostDueInt = 24;
			}
			else {
				hourAlmostDueInt = hourDueInt - 1;
			}

			String hourAlmostDue = String.valueOf(hourAlmostDueInt);

			// can add year to make sure no duplication same time next year
			if (FullDateNow.equals(FullDateDue) && ( hourNow.equals(hourDue) || hourNow.equals(hourAlmostDue) )){

			tuple =
				JobID + "`"
				+ AccountNumber + "`"
				+ NumberOfTasks + "`"
				+ DateOfJob + "`"
				+ JobDeadline + "`"
				+ JobUrgency + "`"
				+ TasksCompleted + "`"
				+ IsCompleted;

				arrayList.add(tuple.split("`"));

			}

		}
		return arrayList;
	}

	public static boolean AreLateJobs() throws SQLException {
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM Jobs;"; /* SELECT * FROM Jobs WHERE IsArchived = 0; */
		ResultSet resultSet = statement.executeQuery(sql);

		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		String tuple;
		boolean bool;
		// adding changes to an array list

		while (resultSet.next()){

			int JobID = resultSet.getInt("JobID");
			int AccountNumber = resultSet.getInt("AccountNumber");
			int NumberOfTasks = resultSet.getInt("NumberOfTasks");
			String DateOfJob = resultSet.getString("DateOfJob"); // date of job order
			String JobDeadline = resultSet.getString("JobDeadline"); // dependant on urgency
			String JobUrgency = resultSet.getString("JobUrgency");
			int TasksCompleted = resultSet.getInt("TasksCompleted");
			int IsCompleted = resultSet.getInt("IsCompleted");


			// im trying to check the current hour a job is due. vs the current system hour.

			String FullDateNow = Calendar.getInstance().getTime().toString().substring(0,11);
			String FullDateDue = JobDeadline.substring(0,11);

			String hourNow = Calendar.getInstance().getTime().toString().substring(11,13);
			String hourDue = JobDeadline.substring(11,13);
			int hourDueInt = Integer.parseInt(hourDue);
			// 1h before due time
			int hourAlmostDueInt;

			if (hourDueInt == 00){
				hourAlmostDueInt = 24;
			}
			else {
				hourAlmostDueInt = hourDueInt - 1;
			}

			String hourAlmostDue = String.valueOf(hourAlmostDueInt);

			// can add year to make sure no duplication same time next year
			if (FullDateNow.equals(FullDateDue) && ( hourNow.equals(hourDue) || hourNow.equals(hourAlmostDue) )){

				tuple =
						JobID + "`"
								+ AccountNumber + "`"
								+ NumberOfTasks + "`"
								+ DateOfJob + "`"
								+ JobDeadline + "`"
								+ JobUrgency + "`"
								+ TasksCompleted + "`"
								+ IsCompleted;

				arrayList.add(tuple.split("`"));

			}

		}


		bool = !arrayList.isEmpty();
//		boolean bool = tuple.isEmpty();
		return bool;
	}

	public static void main(String[] args) throws SQLException {

//		ArrayList<String[]> test = Job.GetLateJobList();
//		for (String[] iter : test){
//			System.out.println(Arrays.toString(iter));
//
//		}

		System.out.println(Job.AreLateJobs());

//		Job job = new Job(7, "normal");
//		ArrayList<String[]> al = PROCESS.Job.GetJobList();

		// test to ensure correct alist format
//		for(String[] col: al){
//			for (String a: col){
//				System.out.println(a);
//
//			}
//		}

	}

	public boolean AddTaskToJob(String TaskData) {
		// TODO - implement Job.AddTaskToJob
		throw new UnsupportedOperationException();
	}

	public TaskInAJob RetrieveTasks(int TaskID) {
		// TODO - implement Job.RetreiveTasks
		throw new UnsupportedOperationException();
	}

	public boolean RemoveTask(int TaskID) {
		// TODO - implement Job.RemoveTask
		throw new UnsupportedOperationException();
	}

	public boolean UpdateTask(String TaskData) {
		// TODO - implement Job.UpdateTask
		throw new UnsupportedOperationException();
	}

	public static int getJobID() {
		return JobID;
	}

	public static void setJobID(int jobID) {
		JobID = jobID;
	}

	public String getJobStatus() {
		// TODO - implement Job.getJobStatus
		throw new UnsupportedOperationException();
	}


	public void setJobStatus(String NewJobStatus) {
		// TODO - implement Job.setJobStatus
		throw new UnsupportedOperationException();
	}

	// TODO set this type (was timestamp)
	public String CalculateDeadline() {
		// TODO - implement Job.CalculateDeadline
		throw new UnsupportedOperationException();
	}

	public String ViewTaskProgress() {
		// TODO - implement Job.ViewTaskProgress
		throw new UnsupportedOperationException();
	}

	public boolean SetUrgency(String NewUrgency) {
		// TODO - implement Job.SetUrgency
		throw new UnsupportedOperationException();
	}

	public boolean StoreJob(String JobData) {
		// TODO - implement Job.StoreJob
		throw new UnsupportedOperationException();
	}

	public float getJobPrice() {
		// TODO - implement Job.getJobPrice
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param JobPrice
	 */
	public void setJobPrice(float JobPrice) {
		// TODO - implement Job.setJobPrice
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean CreateJob(String NewJobData) {
		return false;
	}

	@Override
	public boolean AddNewTask(String TaskDescription, String TaskLocation, float TaskPrice, int TaskDuration) {
		return false;
	}

	@Override
	public void CompleteTask() {

	}

	@Override
	public void StartTask() {

	}

	@Override
	public void GetTaskID() {

	}

	@Override
	public void GetTaskPrice() {

	}

	public static int getNumberOfTasks() {
		return NumberOfTasks;
	}

	public void setNumberOfTasks(int NumberOfTasks) {
		// TODO - implement Job.setNumberOfTasks
		throw new UnsupportedOperationException();
	}


	public static int getAccountNumber() {
		return AccountNumber;
	}

	public static String getUrgency() {
		return Urgency;
	}

	public void setAccountNumber(int accountNumber) {
		AccountNumber = accountNumber;
	}

	public void setUrgency(String urgency) {
		Urgency = urgency;
	}



	public static Job Job(int JobData, String Normal, Date Deadline, String JobStatus, int NumberOfTasks, float JobPrice) {
		// TODO - implement Job.Job
		throw new UnsupportedOperationException();
	}



}