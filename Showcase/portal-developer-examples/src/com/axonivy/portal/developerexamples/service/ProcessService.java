package com.axonivy.portal.developerexamples.service;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.developerexamples.enums.SessionAttribute;
import com.axonivy.portal.developerexamples.util.UserUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.server.restricted.EngineMode;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class ProcessService {
  private static final String PORTAL_IN_TEAMS_REQUEST_PATH = "InTeams.ivp";
  private static final String PORTAL_START_REQUEST_PATH = "/DefaultApplicationHomePage.ivp";
  private static ProcessService instance;
  private static List<IWebStartable> processes;
  private static String sessionUserId;
  private static String userLanguage;

  public ProcessService() {}

  public static ProcessService getInstance() {
    if (instance == null) {
      instance = new ProcessService();
      processes = new ArrayList<IWebStartable>();
    }
    return instance;
  }

  public List<IWebStartable> findProcesses() {
    if (isInSession() && isNotEmpty(processes)) {
      return processes;
    }
    updateUserSessionAtributes();
    processes = new ArrayList<>();
    return Sudo.get(() -> {
      return findStartablesWithoutPortalHomeAndMSTeamsProcess(Ivy.session());
    });
  }

  private void updateUserSessionAtributes() {
    sessionUserId = Ivy.session().getAttribute(SessionAttribute.SESSION_IDENTIFIER.toString()).toString();
    userLanguage = UserUtils.getUserLanguage();
  }

  private List<IWebStartable> findStartablesWithoutPortalHomeAndMSTeamsProcess(IWorkflowSession session) {
    return session.getStartables().stream().filter(process -> isNotPortalHomeAndMSTeamsProcess(process))
        .collect(Collectors.toList());
  }

  private boolean isNotPortalHomeAndMSTeamsProcess(IWebStartable process) {
    String relativeEncoded = process.getLink().getRelativeEncoded();
    return !StringUtils.endsWithAny(relativeEncoded, PORTAL_START_REQUEST_PATH, PORTAL_IN_TEAMS_REQUEST_PATH);
  }

  private boolean isInSession() {
    String currentUserLanguage = UserUtils.getUserLanguage();
    String sessionIdAttribute = SessionAttribute.SESSION_IDENTIFIER.toString();
    if (Ivy.session().getAttribute(sessionIdAttribute) == null) {
      Ivy.session().setAttribute(sessionIdAttribute, UUID.randomUUID().toString());
    }
    return EngineMode.isNot(EngineMode.DESIGNER_EMBEDDED) && currentUserLanguage.equals(userLanguage)
        && Ivy.session().getAttribute(sessionIdAttribute).toString().equals(sessionUserId);
  }
}
