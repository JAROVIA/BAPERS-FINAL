package CONTROLLER;

import ACCOUNT.*;
import GUI.*;
import PROCESS.*;

import java.io.IOException;

public class PROC_UI_Controller {

	private Main main;

	//gui handled by this controller
	private JobsScreen jobsScreen;
	private String jobFxml = "Jobs";

	private AddNewTaskScreen addNewTaskScreen;
	private String newTaskFxml = "AddnewTask";

	private TasksScreen tasksScreen;
	private String taskFxml = "Tasks";

	private CreateJobScreen createJobScreen;
	private String createJobFxml = "CreateJobOrder";

	private UpdateExistingTaskScreen updateExistingTaskScreen;
	private String updateTasksFxml = "UpdateTasks";

	//TODO which screen is this
	private JobProgressScreen jobProgressScreen;
	private String jobProgressFxml = "";

	/**
	 * 
	 * @param JobID
	 * @param urgency
	 */
	public boolean SetUrgency(int JobID, String urgency) {
		// TODO - implement PROC_UI_Controller.SetUrgency
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param JobID
	 */
	public String retrieveJobStatus(int JobID) {
		// TODO - implement PROC_UI_Controller.retrieveJobStatus
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param JobID
	 * @param JobData
	 */
	public boolean saveJob(int JobID, String JobData) {
		// TODO - implement PROC_UI_Controller.saveJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param JobID
	 * @param TaskData
	 */
	public boolean removeTask(int JobID, String TaskData) {
		// TODO - implement PROC_UI_Controller.removeTask
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jobID
	 * @param TaskData
	 */
	public boolean addTask(int jobID, String TaskData) {
		// TODO - implement PROC_UI_Controller.addTask
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param AccountNumber
	 */
	public CustomerAccountDetails SearchCustomer(int AccountNumber) {
		// TODO - implement PROC_UI_Controller.SearchCustomer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param NumberTasks
	 * @param Urgency
	 * @param TypeOfDiscount
	 */
	public boolean ConfirmJob(int NumberTasks, String Urgency, String TypeOfDiscount) {
		// TODO - implement PROC_UI_Controller.ConfirmJob
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param NewTask
	 */
	public boolean ConfirmNewTask(TaskDescription NewTask) {
		// TODO - implement PROC_UI_Controller.ConfirmNewTask
		throw new UnsupportedOperationException();
	}

	public void CancelNewTask() {
		// TODO - implement PROC_UI_Controller.CancelNewTask
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param TaskID
	 */
	public void RetrieveTask(int TaskID) {
		// TODO - implement PROC_UI_Controller.RetrieveTask
		throw new UnsupportedOperationException();
	}

	public boolean RetrieveJobs() {
		// TODO - implement PROC_UI_Controller.RetrieveJobs
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param TaskID
	 */
	public float CalculateJobPrice(int[] TaskID) {
		// TODO - implement PROC_UI_Controller.CalculateJobPrice
		throw new UnsupportedOperationException();
	}

	public JobsScreen getJobsScreen() {
		return jobsScreen;
	}

	public TasksScreen getTasksScreen() {
		return tasksScreen;
	}

	public CreateJobScreen getCreateJobScreen() {
		return createJobScreen;
	}

	public JobProgressScreen getJobProgressScreen() {
		return jobProgressScreen;
	}

	public AddNewTaskScreen getAddNewTaskScreen() {
		return addNewTaskScreen;
	}

	public UpdateExistingTaskScreen getUpdateExistingTaskScreen() {
		return updateExistingTaskScreen;
	}

	public Main getMain() {
		return main;
	}

	public PROC_UI_Controller(Main main) throws IOException {
		// TODO - implement PROC_UI_Controller.PROC_UI_Controller
		// TODO instantiate the gui

		jobsScreen = (JobsScreen) Window.newGuiFromFxml(jobFxml);
		main.addScreen(jobFxml, jobsScreen.getParent());
		jobsScreen.setProcUiController(this);

		tasksScreen = (TasksScreen) Window.newGuiFromFxml(taskFxml);
		main.addScreen(taskFxml, tasksScreen.getParent());
		tasksScreen.setProcUiController(this);

		addNewTaskScreen = (AddNewTaskScreen) Window.newGuiFromFxml(newTaskFxml);
		main.addScreen(newTaskFxml, jobsScreen.getParent());
		addNewTaskScreen.setProcUiController(this);

		createJobScreen = (CreateJobScreen) Window.newGuiFromFxml(createJobFxml);
		main.addScreen(createJobFxml, createJobScreen.getParent());
		createJobScreen.setProcUiController(this);

		updateExistingTaskScreen = (UpdateExistingTaskScreen) Window.newGuiFromFxml(updateTasksFxml);
		main.addScreen(updateTasksFxml, updateExistingTaskScreen.getParent());
		updateExistingTaskScreen.setProcUiController(this);
	}

}