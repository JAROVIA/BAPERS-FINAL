package PROCESS;

import java.sql.Date;

public class Job implements I_PROCESS {

	private int JobID;
	private String Urgency = "Normal";
	//TODO set this type (was timestamp)
	private String JobDeadline;
	private String JobStatus = "Started";
	private int NumberOfTasks = 0;
	private Date DateOfJob;
	private String TaskProgress;
	private float JobPrice;
	private float CustomUrgencyTime = 0;

	/**
	 * 
	 * @param TaskData
	 */
	public boolean AddTaskToJob(String TaskData) {
		// TODO - implement Job.AddTaskToJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param TaskID
	 */
	public Task RetrieveTasks(int TaskID) {
		// TODO - implement Job.RetreiveTasks
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param TaskID
	 */
	public boolean RemoveTask(int TaskID) {
		// TODO - implement Job.RemoveTask
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param TaskData
	 */
	public boolean UpdateTask(String TaskData) {
		// TODO - implement Job.UpdateTask
		throw new UnsupportedOperationException();
	}

	public int getJobID() {
		// TODO - implement Job.getJobID
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param NewJobID
	 */
	public int setJobID(int NewJobID) {
		// TODO - implement Job.setJobID
		throw new UnsupportedOperationException();
	}

	public String getJobStatus() {
		// TODO - implement Job.getJobStatus
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param NewJobStatus
	 */
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

	/**
	 * 
	 * @param NewUrgency
	 */
	public boolean SetUrgency(String NewUrgency) {
		// TODO - implement Job.SetUrgency
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param JobData
	 */
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

	public int getNumberOfTasks() {
		// TODO - implement Job.getNumberOfTasks
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param NumberOfTasks
	 */
	public void setNumberOfTasks(int NumberOfTasks) {
		// TODO - implement Job.setNumberOfTasks
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param JobData
	 * @param Normal
	 * @param Deadline
	 * @param JobStatus
	 * @param NumberOfTasks
	 * @param JobPrice
	 */
	public static Job Job(int JobData, String Normal, Date Deadline, String JobStatus, int NumberOfTasks, float JobPrice) {
		// TODO - implement Job.Job
		throw new UnsupportedOperationException();
	}

}