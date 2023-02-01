package com.axonivy.portal.components.service.impl;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.IvyProcessResultDTO;
import com.axonivy.portal.components.service.IProcessService;
import com.axonivy.portal.components.util.IvyExecutor;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.server.restricted.EngineMode;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class ProcessService implements IProcessService {

  private static final String PORTAL_IN_TEAMS_REQUEST_PATH = "InTeams.ivp";
  private static final String PORTAL_START_REQUEST_PATH = "/DefaultApplicationHomePage.ivp";
  private static ProcessService instance;
  private static IvyProcessResultDTO ivyProcessResultDTO;
  private static String sessionUserId;

  public ProcessService() { }

  /**
   * @deprecated instead use {@link #getInstance()}
   */
  @Deprecated
  public static ProcessService newInstance() {
    return new ProcessService();
  }

  public static ProcessService getInstance() {
    if (instance == null) {
      instance = new ProcessService();
      ivyProcessResultDTO = new IvyProcessResultDTO();
    }
    return instance;
  }

  @Override
  public IvyProcessResultDTO findProcesses() {
    var currentSessionMemberId = Ivy.session().getSessionUser().getSecurityMemberId();
    if (EngineMode.isNot(EngineMode.DESIGNER_EMBEDDED)
        && isNotEmpty(ivyProcessResultDTO.getProcesses())
        && StringUtils.equals(currentSessionMemberId, sessionUserId)) {
      return ivyProcessResultDTO;
    }
    sessionUserId = currentSessionMemberId;
    ivyProcessResultDTO = new IvyProcessResultDTO();
    return IvyExecutor.executeAsSystem(() -> {
      ivyProcessResultDTO.setProcesses(findStartablesWithoutPortalHomeAndMSTeamsProcess(Ivy.session()));
      return ivyProcessResultDTO;
    });
  }

  private List<IWebStartable> findStartablesWithoutPortalHomeAndMSTeamsProcess(IWorkflowSession session) {
    return session.getStartables().stream().filter(process -> isNotPortalHomeAndMSTeamsProcess(process))
        .collect(Collectors.toList());
  }

  private boolean isNotPortalHomeAndMSTeamsProcess(IWebStartable process) {
    String relativeEncoded = process.getLink().getRelativeEncoded();
    return !relativeEncoded.endsWith(PORTAL_START_REQUEST_PATH)
        && !relativeEncoded.endsWith(PORTAL_IN_TEAMS_REQUEST_PATH);
  }
}
