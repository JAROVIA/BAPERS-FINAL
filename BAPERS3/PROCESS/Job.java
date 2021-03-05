package BAPERS3.PROCESS;

import BAPERS3.PROCESS.I_PROCESS.*;

public class Job implements SetUrgency {

	private int JobID;
	private string Urgency = Normal;
	private TimeStamp JobDeadline;
	private string JobStatus = Started;
	private int NumberOfTasks = 0;
	private date DateOfJob;
	private string TaskProgress;
	private float JobPrice;
	private float CustomUrgencyTime = 0;

	/**
	 * 
	 * @param TaskData
	 */
	public boolean AddTaskToJob(string TaskData) {
		// TODO - implement Job.AddTaskToJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param TaskID
	 */
	public Task RetreiveTasks(int TaskID) {
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
	public boolean UpdateTask(string TaskData) {
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

	public string getJobStatus() {
		// TODO - implement Job.getJobStatus
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param NewJobStatus
	 */
	public string setJobStatus(string NewJobStatus) {
		// TODO - implement Job.setJobStatus
		throw new UnsupportedOperationException();
	}

	public TimeStamp CalculateDeadline() {
		// TODO - implement Job.CalculateDeadline
		throw new UnsupportedOperationException();
	}

	public string ViewTaskProgress() {
		// TODO - implement Job.ViewTaskProgress
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param NewUrgency
	 */
	public boolean SetUrgency(string NewUrgency) {
		// TODO - implement Job.SetUrgency
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param JobData
	 */
	public boolean StoreJob(string JobData) {
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
	public static Job Job(int JobData, string Normal, Date Deadline, string JobStatus, int NumberOfTasks, float JobPrice) {
		// TODO - implement Job.Job
		throw new UnsupportedOperationException();
	}

}