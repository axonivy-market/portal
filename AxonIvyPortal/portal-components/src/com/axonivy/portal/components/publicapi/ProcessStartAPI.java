package com.axonivy.portal.components.publicapi;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.service.impl.ProcessService;
import com.axonivy.portal.components.util.ProcessStartUtils;

import ch.ivyteam.ivy.workflow.IProcessStart;

/**
 * Portal API for process start
 *
 */
public final class ProcessStartAPI {

	private ProcessStartAPI() {
	}

	/**
	 * Find startable link from friendly request path
	 *
	 * @param friendlyRequestPath friendly path e.g "Start
	 *                            Processes/UserExampleGuide/userExampleGuide.ivp"
	 * @return start link which session user can start or empty string
	 */
	public static String findStartableLinkByUserFriendlyRequestPath(String friendlyRequestPath) {
		return ProcessStartUtils.findStartableLinkByUserFriendlyRequestPath(friendlyRequestPath,
				ProcessService.getInstance().findProcesses());
	}

	/**
	 * Find start link from friendly request path
	 *
	 * @param friendlyRequestPath friendly path e.g "Start
	 *                            Processes/UserExampleGuide/userExampleGuide.ivp"
	 * @return start link or empty string
	 */
	public static String findRelativeUrlByProcessStartFriendlyRequestPath(String friendlyRequestPath) {
		IProcessStart processStart = ProcessStartUtils.findProcessStartByUserFriendlyRequestPath(friendlyRequestPath);
		return processStart != null ? processStart.getLink().getRelative() : StringUtils.EMPTY;
	}

}
