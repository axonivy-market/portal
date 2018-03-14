package ch.ivy.addon.portalkit.util;

import java.util.concurrent.Callable;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.server.ServerFactory;

/**
 * Utility functions for Ivy cases
 * 
 * @author ntttai
 * 
 */
public class IvyCaseUtility {

	/**
	 * gets process start link of current case
	 * 
	 * @return String
	 * @throws Exception
	 */
	public static String getCurrentProcessStartLink() throws Exception {
		return ServerFactory.getServer().getSecurityManager()
				.executeAsSystem(new Callable<String>() {
					@Override
          public String call() throws Exception {
						return Ivy.wfCase().getProcessStart()
								.getFullRequestPath();
					}
				});
	}

	/**
	 * gets name of process model of current case
	 * 
	 * @return String
	 * @throws Exception
	 */
	public static String getCurrentProcessModelName() throws Exception {
		return ServerFactory.getServer().getSecurityManager()
				.executeAsSystem(new Callable<String>() {
					@Override
          public String call() throws Exception {
						return Ivy.wfCase().getProcessModel().getName();
					}
				});
	}

}
