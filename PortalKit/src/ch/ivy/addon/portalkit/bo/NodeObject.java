package ch.ivy.addon.portalkit.bo;

import java.io.Serializable;

import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivyteam.ivy.workflow.ITask;

/**
 * A wrapper class for ITask or Application.
 * 
 * @author ptanh
 *
 */
public class NodeObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ITask task;
	private Application application;
	
	/**
	 * Constructor
	 *
	 * @param obj application to add to node.
	 */
	public NodeObject(Object obj){
		if(obj instanceof ITask){
			task = (ITask)obj;
		}else if(obj instanceof Application){
			application = (Application)obj;
		}
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
	 * Gets the application
	 *
	 * @return Returns the application
	 */
	public Application getApplication() {
		return application;
	}

	/**
	 * Sets the application
	 *
	 * @param application The application to set
	 */
	public void setApplication(Application application) {
		this.application = application;
	}

	
}
