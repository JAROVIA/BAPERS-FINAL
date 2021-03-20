package PROCESS;

public interface I_PROCESS {

	/*
	TODO should the abstract methods be abstract?
	 Task implements this in the design, if this is the case some methods should not be abstract
	 or we remove the implementation
	 */
	/**
	 * 
	 * @param TaskData
	 */
	abstract boolean AddTaskToJob(String TaskData);

	/**
	 * 
	 * @param TaskID
	 */
	abstract TaskInAJob RetrieveTasks(int TaskID);

	/**
	 * 
	 * @param TaskID
	 */
	abstract boolean RemoveTask(int TaskID);

	/**
	 * 
	 * @param TaskData
	 */
	abstract boolean UpdateTask(String TaskData);

	static int getJobID() {
		return 0;
	}

	/**
	 * 
	 * @param NewUrgency
	 */
	abstract boolean SetUrgency(String NewUrgency);

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
	abstract boolean CreateJob(String NewJobData);

	/**
	 * 
	 * @param TaskDescription
	 * @param TaskLocation
	 * @param TaskPrice
	 * @param TaskDuration
	 */
	abstract boolean AddNewTask(String TaskDescription, String TaskLocation, float TaskPrice, int TaskDuration);

	abstract void CompleteTask();

	abstract void StartTask();

	abstract void GetTaskID();

	abstract void GetTaskPrice();

}