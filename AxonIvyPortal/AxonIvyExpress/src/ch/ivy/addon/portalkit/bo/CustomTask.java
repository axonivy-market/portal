package ch.ivy.addon.portalkit.bo;


import java.io.Serializable;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IProcessModelVersionRequest;
import ch.ivyteam.ivy.security.ISecurityDescriptor;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.TaskState;

/**
 * This bean is extended from Itask which contains some new properties for disable actions
 * 
 */
public class CustomTask implements Serializable{
	
	private static final long serialVersionUID = 1L;

	ITask task;

	boolean disabledPark;

	boolean disabledReset;

	boolean disabledAll;
	
	boolean visibleExcute;

	/**
	 * Method description
	 *
	 * @return boolean value if disabled park and disabled reset.
	 */
	public boolean isDisabledAll() {
		return disabledPark && disabledReset;
	}

	
	
	/**
	 * Gets the task
	 *
	 * @return Returns the task
	 */
	public ITask getTask() {
		return task;
	}



	/**
	 * Sets the task
	 *
	 * @param task The task to set
	 */
	public void setTask(ITask task) {
		this.task = task;
	}



	/**
	 * Gets the disabledPark
	 *
	 * @return Returns the disabledPark
	 */
	public boolean isDisabledPark() {
		return disabledPark;
	}



	/**
	 * Sets the disabledPark
	 *
	 * @param disabledPark The disabledPark to set
	 */
	public void setDisabledPark(boolean disabledPark) {
		this.disabledPark = disabledPark;
	}



	/**
	 * Gets the disabledReset
	 *
	 * @return Returns the disabledReset
	 */
	public boolean isDisabledReset() {
		return disabledReset;
	}



	/**
	 * Sets the disabledReset
	 *
	 * @param disabledReset The disabledReset to set
	 */
	public void setDisabledReset(boolean disabledReset) {
		this.disabledReset = disabledReset;
	}



	/**
	 * Gets the visibleExcute
	 *
	 * @return Returns the visibleExcute
	 */
	public boolean isVisibleExcute() {
		return visibleExcute;
	}



	/**
	 * Sets the visibleExcute
	 *
	 * @param visibleExcute The visibleExcute to set
	 */
	public void setVisibleExcute(boolean visibleExcute) {
		this.visibleExcute = visibleExcute;
	}



	/**
	 * Sets the disabledAll
	 *
	 * @param disabledAll The disabledAll to set
	 */
	public void setDisabledAll(boolean disabledAll) {
		this.disabledAll = disabledAll;
	}



	/**
	 * Set disabled for actions by state and permission
	 * 
	 * @param state state of task
	 */
	public void setDisabledForActionsByState(TaskState state){
		
		IWorkflowSession ivySession = Ivy.session();
		ISecurityDescriptor securityDescriptorReset = Ivy.request().getApplication().getSecurityDescriptor();		
		
		IProcessModelVersionRequest ivyRequest = (IProcessModelVersionRequest) Ivy.html().getObject("request");
		ISecurityDescriptor securityDescriptorPark = ivyRequest.getApplication().getSecurityDescriptor();
		
		boolean hasResetPermission = ivySession
				.hasPermission(
						securityDescriptorReset,
						ch.ivyteam.ivy.security.IPermission.TASK_RESET_OWN_WORKING_TASK);
		
		boolean hasParkPermission = ivySession.hasPermission(
				securityDescriptorPark,
				ch.ivyteam.ivy.security.IPermission.TASK_PARK_OWN_WORKING_TASK);

		if ((state.equals(TaskState.RESUMED) || state.equals(TaskState.CREATED)
				|| state.equals(TaskState.PARKED)
				|| state.equals(TaskState.READY_FOR_JOIN)
				|| state.equals(TaskState.FAILED)) && hasResetPermission) {
			this.disabledReset = false;
		} else {
			this.disabledReset = true;
		}

		if ((state.equals(TaskState.RESUMED) || state.equals(TaskState.CREATED))
				&& hasParkPermission) {
			this.disabledPark = false;
		} else {
			this.disabledPark = true;
		}
		
		if (state.equals(TaskState.SUSPENDED) || state.equals(TaskState.RESUMED) || state.equals(TaskState.PARKED)) {
			this.visibleExcute = true;
		} else {
			this.visibleExcute = false;
		}
	}

}
