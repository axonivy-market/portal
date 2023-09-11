package com.axonivy.portal.components.service.impl;

import static com.axonivy.portal.components.constant.CustomFields.IS_DASHBOARD_PROCESS;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.IvyProcessResultDTO;
import com.axonivy.portal.components.service.IProcessService;
import com.axonivy.portal.components.util.IvyExecutor;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.server.restricted.EngineMode;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

public class ProcessService implements IProcessService {

  private static final String PORTAL_IN_TEAMS_REQUEST_PATH = "InTeams.ivp";
  private static final String PORTAL_START_REQUEST_PATH = "/DefaultApplicationHomePage.ivp";
  private static ProcessService instance;
  private static IvyProcessResultDTO ivyProcessResultDTO;
  private static List<IWebStartable> customDashboardProcesses;
  private static String sessionUserId;

  public ProcessService() { }

  /**
   * @deprecated instead use {@link #getInstance()}
   * @return create a new instance for ProcessService
   */
  @Deprecated
  public static ProcessService newInstance() {
    return new ProcessService();
  }

  public static ProcessService getInstance() {
    if (instance == null) {
      instance = new ProcessService();
      ivyProcessResultDTO = new IvyProcessResultDTO();
      customDashboardProcesses = new ArrayList<>();
    }
    return instance;
  }

  @Override
  public IvyProcessResultDTO findProcesses() {
    if (isInSession() && isNotEmpty(ivyProcessResultDTO.getProcesses())) {
      return ivyProcessResultDTO;
    }
    sessionUserId = Ivy.session().getSessionUser().getSecurityMemberId();
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

  private boolean isInSession() {
    return EngineMode.isNot(EngineMode.DESIGNER_EMBEDDED)
        && StringUtils.equals(Ivy.session().getSessionUser().getSecurityMemberId(), sessionUserId);
  }

  public List<IWebStartable> findCustomDashboardProcesses() {
    if (isInSession() && isNotEmpty(customDashboardProcesses)) {
      return customDashboardProcesses;
    }
    sessionUserId = Ivy.session().getSessionUser().getSecurityMemberId();
    customDashboardProcesses = new ArrayList<>(
        Ivy.session().getAllStartables().filter(filterByCustomDashboardProcess()).collect(Collectors.toList()));
    return customDashboardProcesses;
  }

  private Predicate<? super IWebStartable> filterByCustomDashboardProcess() {
    return start -> BooleanUtils.toBoolean(start.customFields().value(IS_DASHBOARD_PROCESS));
  }

  public IWebStartable findWebStartableInSecurityContextById(String processId){
    Predicate<? super IWebStartable> predicate = startable -> StringUtils.endsWith(startable.getId(), processId) && isNotPortalHomeAndMSTeamsProcess(startable);
    return findStartable(predicate);
  }

  public IWebStartable findWebStartableInSecurityContextByRelativeLink(String processRelativeLink){
    Predicate<? super IWebStartable> predicate = startable -> StringUtils.equals(processRelativeLink, startable.getLink().getRelative()) && isNotPortalHomeAndMSTeamsProcess(startable);
    return findStartable(predicate);
  }
  
  public IWebStartable findCustomDashboardProcessInSecurityContextByProcessId(String processId) {
    Predicate<? super IWebStartable> predicate = startable -> StringUtils.endsWith(startable.getId(),processId) && 
        BooleanUtils.toBoolean(startable.customFields().value(IS_DASHBOARD_PROCESS));
    return findStartable(predicate);
  }
  
  public IWebStartable findCustomDashboardProcessInSecurityContextByRelativePath(String processRelativeLink) {
    Predicate<? super IWebStartable> predicate = startable -> StringUtils.equals(processRelativeLink, startable.getLink().getRelative()) && 
        BooleanUtils.toBoolean(startable.customFields().value(IS_DASHBOARD_PROCESS));
    return findStartable(predicate);
  }
  
  
  private IWebStartable findStartable(Predicate<? super IWebStartable> predicate) {
    List<IApplication> applicationsInSecurityContext = IApplicationRepository.instance().allOf(ISecurityContext.current());
    IWebStartable foundStartable = null;
    for (IApplication app : applicationsInSecurityContext) {
      List<IProcessModelVersion> pmvs = app.getProcessModels()
                                            .stream()
                                            .map(IProcessModel::getProcessModelVersions)
                                            .flatMap(List::stream)
                                            .collect(Collectors.toList());
      for (IProcessModelVersion pmv : pmvs) {
        foundStartable = IWorkflowProcessModelVersion.of(pmv)
            .getAllStartables()
            .filter(predicate)
            .findFirst()
            .orElse(null);
        if (foundStartable != null) {
          return foundStartable;
        }
      }
    }
    return foundStartable;
  }
  
}
