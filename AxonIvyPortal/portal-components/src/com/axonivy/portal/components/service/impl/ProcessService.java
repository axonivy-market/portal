package com.axonivy.portal.components.service.impl;

import static com.axonivy.portal.components.constant.CustomFields.IS_DASHBOARD_PROCESS;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class ProcessService {

  private static final String PORTAL_IN_TEAMS_REQUEST_PATH = "InTeams.ivp";
  private static final String PORTAL_START_REQUEST_PATH = "/DefaultApplicationHomePage.ivp";
  private static ProcessService instance;

  public ProcessService() { }

  public static ProcessService getInstance() {
    if (instance == null) {
      instance = new ProcessService();
    }
    return instance;
  }

  public List<IWebStartable> findProcesses() {
    return Sudo.get(() -> {
      return findStartablesWithoutPortalHomeAndMSTeamsProcess(Ivy.session());
    });
  }

  public List<IWebStartable> findAllProcesses() {
    return Sudo.get(() -> {
      return Ivy.session().getAllStartables()
          .filter(process -> isNotPortalHomeAndMSTeamsProcess(process))
          .collect(Collectors.toList());
    });
  }

  private List<IWebStartable> findStartablesWithoutPortalHomeAndMSTeamsProcess(IWorkflowSession session) {
    return session.getStartables().stream().filter(process -> isNotPortalHomeAndMSTeamsProcess(process))
        .collect(Collectors.toList());
  }

  private boolean isNotPortalHomeAndMSTeamsProcess(IWebStartable process) {
    String relativeEncoded = process.getLink().getRelativeEncoded();
    return !StringUtils.endsWithAny(relativeEncoded, PORTAL_START_REQUEST_PATH, PORTAL_IN_TEAMS_REQUEST_PATH);
  }

  public List<IWebStartable> findCustomDashboardProcesses() {
    return new ArrayList<>(
        Ivy.session().getAllStartables().filter(filterByCustomDashboardProcess()).collect(Collectors.toList()));
  }

  private Predicate<? super IWebStartable> filterByCustomDashboardProcess() {
    return start -> BooleanUtils.toBoolean(start.customFields().value(IS_DASHBOARD_PROCESS));
  }
}