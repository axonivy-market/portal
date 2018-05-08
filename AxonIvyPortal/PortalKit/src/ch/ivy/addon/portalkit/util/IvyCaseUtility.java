package ch.ivy.addon.portalkit.util;

import ch.ivyteam.ivy.environment.Ivy;

/**
 * Utility functions for Ivy cases
 * 
 */
public class IvyCaseUtility {

  private IvyCaseUtility() {}

	/**
	 * gets process start link of current case
	 * 
	 * @return String
	 */
	public static String getCurrentProcessStartLink() {
		return IvyExecutor.executeAsSystem(() -> Ivy.wfCase().getProcessStart().getFullRequestPath());
	}

	/**
	 * gets name of process model of current case
	 * 
	 * @return String
	 */
	public static String getCurrentProcessModelName() {
	  return IvyExecutor.executeAsSystem(() ->Ivy.wfCase().getProcessModel().getName());
	}

}
