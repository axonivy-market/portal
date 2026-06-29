package com.axonivy.portal.components.publicapi;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.service.impl.ProcessService;
import com.axonivy.portal.components.util.ProcessStartUtils;

import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

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
		return Sudo.get(() -> {
			List<IApplication> applicationsInSecurityContext = IApplicationRepository.of(ISecurityContext.current()).all();
			for (IApplication app : applicationsInSecurityContext) {
				IProcessStart processStart = findStartableProcessStartByUserFriendlyRequestPath(friendlyRequestPath,
						app);
				if (processStart != null) {
					return processStart.getLink().getRelative();
				}
			}
			return StringUtils.EMPTY;
		});
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

	private static IProcessStart findStartableProcessStartByUserFriendlyRequestPath(String requestPath,
			IApplication application) {
		return filterPMV(requestPath, application)
				.filter(processStart -> isStartableProcessStart(processStart.getLink().getRelative())).findFirst()
				.orElse(null);
	}

	private static boolean isActive(IProcessModelVersion processModelVersion) {
		return processModelVersion != null && processModelVersion.getActivityState() == ActivityState.ACTIVE;
	}

	private static boolean isActive(IProcessModel processModel) {
		return processModel.getActivityState() == ActivityState.ACTIVE;
	}

	private static IProcessStart getProcessStart(String requestPath, IProcessModelVersion processModelVersion) {
		return IWorkflowProcessModelVersion.of(processModelVersion)
				.findStartElementByUserFriendlyRequestPath(requestPath);
	}

	private static boolean isStartableProcessStart(String processRelativeLink) {
		return ProcessService.getInstance().findProcesses().stream().map(IWebStartable::getLink)
				.filter(webLink -> webLink.getRelative().equals(processRelativeLink)).findFirst().isPresent();
	}

	private static Stream<IProcessStart> filterPMV(String requestPath, IApplication application) {
		return filterActivePMVOfApp(application).map(p -> getProcessStart(requestPath, p)).filter(Objects::nonNull);
	}

	private static Stream<IProcessModelVersion> filterActivePMVOfApp(IApplication application) {
		return application.getProcessModelsSortedByName().stream().filter(pm -> isActive(pm))
				.map(IProcessModel::getReleasedProcessModelVersion).filter(pmv -> isActive(pmv));
	}

}