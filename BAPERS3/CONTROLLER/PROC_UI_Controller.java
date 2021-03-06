package CONTROLLER;

import ACCOUNT.*;
import PROCESS.*;

public class PROC_UI_Controller {

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

	public static PROC_UI_Controller PROC_UI_Controller() {
		// TODO - implement PROC_UI_Controller.PROC_UI_Controller
		throw new UnsupportedOperationException();
	}

}