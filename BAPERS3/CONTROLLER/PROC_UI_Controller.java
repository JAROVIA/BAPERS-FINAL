package CONTROLLER;

import ACCOUNT.*;
import ADMIN.UserAccount;
import GUI.*;
import PROCESS.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PROC_UI_Controller {

	private Main main;

	//gui handled by this controller
	private JobsScreen jobsScreen;
	private String jobFxml = "Jobs";

	private AddNewTaskScreen addNewTaskScreen;
	private String newTaskFxml = "AddNewTask";

	private TasksScreen tasksScreen;
	private String taskFxml = "Tasks";

	private CreateJobScreen createJobScreen;
	private String createJobFxml = "CreateJobOrder";

	private CreateJobSetupScreen createJobSetupScreen;
	private String createJobSetupFxml = "CreateJobSetup";

	private EditExistingTaskScreen editExistingTaskScreen;
	private String editExistingTasksFxml = "EditExistingTasks";

	private ProcessTasksScreen processTasksScreen;
	private String processTasksFxml = "ProgressTasks";

	private PaymentsScreen paymentsScreen;
	private String paymentFxml = "Payments";

	private Map<String, Window> screens;

	private I_PROCESS job;

	private TaskDescription editingTaskDescription;

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

	public TaskDescription getEditingTaskDescription() {
		return editingTaskDescription;
	}

	public void setEditingTaskDescription(TaskDescription editingTaskDescription) {
		this.editingTaskDescription = editingTaskDescription;
	}

	public void setJobBeingPaid(String[] jobData){
		main.getUiController().setJobBeingPaid(jobData);
	}

	public void setEditingTask(String[] taskData){
		editExistingTaskScreen.setTaskData(taskData);
	}

	public void submitEditTask(String[] taskData) throws SQLException {
		String[] columns = new String[]{
				"TaskDescription",
				"TaskLocation",
				"TaskPrice",
				"PredictedDuration"
		};

		for(int i =  0; i < columns.length; i++){
			TaskDescription.UpdateTask(columns[i], taskData[i+1], "TaskID", Integer.parseInt(taskData[0]));
		}
	}

	public boolean checkIfJobPaid(int jobId) throws SQLException {
		return main.getUiController().checkIfPaid(jobId);
	}

	public UserAccount getLoggedInUser(){
		return main.getAdminUiController().getLoggedInUser();
	}

	public void showScreen(Window gui, String name){
		main.showScreen(gui, name);
	}

	public Window getScreen(String name){
		return screens.get(name);
	}

	public PROC_UI_Controller(Main main) throws IOException {

		this.main = main;
		//map to match string names to respective gui class for easier lookup
		screens = new HashMap<>();

		jobsScreen = (JobsScreen) Window.newGuiFromFxml(jobFxml);
		screens.put(jobFxml, jobsScreen);

		tasksScreen = (TasksScreen) Window.newGuiFromFxml(taskFxml);
		screens.put(taskFxml, tasksScreen);

		addNewTaskScreen = (AddNewTaskScreen) Window.newGuiFromFxml(newTaskFxml);
		screens.put(newTaskFxml, addNewTaskScreen);

		createJobScreen = (CreateJobScreen) Window.newGuiFromFxml(createJobFxml);
		screens.put(createJobFxml, createJobScreen);

		createJobSetupScreen = (CreateJobSetupScreen) Window.newGuiFromFxml(createJobSetupFxml);
		screens.put(createJobSetupFxml, createJobSetupScreen);

		editExistingTaskScreen = (EditExistingTaskScreen) Window.newGuiFromFxml(editExistingTasksFxml);
		screens.put(editExistingTasksFxml, editExistingTaskScreen);

		processTasksScreen = (ProcessTasksScreen) Window.newGuiFromFxml(processTasksFxml);
		screens.put(processTasksFxml, processTasksScreen);

		paymentsScreen = (PaymentsScreen) Window.newGuiFromFxml(paymentFxml);
		screens.put(paymentFxml,paymentsScreen);

		for(Map.Entry<String, Window> entry : screens.entrySet()){
			main.addScreen(entry.getKey(), entry.getValue().getParent(), "PROC");
			entry.getValue().setProcUiController(this);
		}
	}

}