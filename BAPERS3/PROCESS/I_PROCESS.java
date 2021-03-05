package BAPERS3.PROCESS;

public interface I_PROCESS {

	/**
	 * 
	 * @param TaskData
	 */
	abstract boolean AddTaskToJob(string TaskData);

	/**
	 * 
	 * @param TaskID
	 */
	abstract Task RetreiveTasks(int TaskID);

	/**
	 * 
	 * @param TaskID
	 */
	abstract boolean RemoveTask(int TaskID);

	/**
	 * 
	 * @param TaskData
	 */
	abstract boolean UpdateTask(string TaskData);

	abstract int getJobID();

	/**
	 * 
	 * @param NewUrgency
	 */
	abstract boolean SetUrgency(string NewUrgency);

	abstract float getJobPrice();

	/**
	 * 
	 * @param JobPrice
	 */
	abstract void setJobPrice(float JobPrice);

	/**
	 * 
	 * @param NewJobData
	 */
	abstract boolean CreateJob(string NewJobData);

	/**
	 * 
	 * @param TaskDescription
	 * @param TaskLocation
	 * @param TaskPrice
	 * @param TaskDuration
	 */
	abstract boolean AddNewTask(string TaskDescription, string TaskLocation, float TaskPrice, int TaskDuration);

	abstract void CompleteTask();

	abstract void StartTask();

	abstract void GetTaskID();

	abstract void GetTaskPrice();

}