package ch.ivy.addon.portalkit.publicapi;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.ExpressProcess;
import ch.ivy.addon.portalkit.configuration.ExternalLink;
import ch.ivy.addon.portalkit.configuration.UserProcess;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.service.ExpressProcessService;
import ch.ivy.addon.portalkit.service.ExternalLinkService;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.IStartElement;
import ch.ivyteam.ivy.workflow.IWorkflowProcessModelVersion;

/**
 * Portal API for process start
 *
 */
public final class ProcessStartAPI {
  private ProcessStartAPI() {}
  
  /**
   * Find startable link from friendly request path
   * @param friendlyRequestPath friendly path e.g "Start Processes/UserExampleGuide/userExampleGuide.ivp"
   * @return start link which session user can start or empty string
   */
  public static String findStartableLinkByUserFriendlyRequestPath(String friendlyRequestPath) {
    return IvyExecutor.executeAsSystem(() -> {
      List<IApplication> applicationsInSecurityContext = IApplicationRepository.instance().allOf(ISecurityContext.current());
      IProcessStart processStart = null;
      for (IApplication app : applicationsInSecurityContext) {
        processStart = findStartableProcessStartByUserFriendlyRequestPath(friendlyRequestPath, app);
        if (processStart != null) {
          return processStart.getLink().getRelative();
        }
      }
      return StringUtils.EMPTY; 
    });
  }

  /**
   * Initiates {@link UserProcess} by user friendly request path.
   * 
   * @param friendlyRequestPath friendly path e.g "Start Processes/UserExampleGuide/userExampleGuide.ivp"
   * @param displayName value for {@link UserProcess} processName field
   * @return {@link UserProcess}
   */
  public static UserProcess initUserProcessByUserFriendlyRequestPath(String friendlyRequestPath, String displayName) {
    return initUserProcess(findStartableIdByUserFriendlyRequestPath(friendlyRequestPath), ProcessType.IVY_PROCESS, displayName);
  }

  /**
   * Initiates {@link UserProcess} by {@link ExpressProcess} name.
   * 
   * @param expressProcessName Name of existing {@link ExpressProcess} in database
   * @param displayName value for {@link UserProcess} processName field
   * @return {@link UserProcess}
   */
  public static UserProcess initUserProcessByExpressProcessName(String expressProcessName, String displayName) {
    return initUserProcess(findExpressProcessIdByExpressProcessName(expressProcessName), ProcessType.EXPRESS_PROCESS, displayName);
  }

  /**
   * Initiates {@link UserProcess} by External Link name.
   * 
   * @param externalLinkName Name of existing {@link ExternalLink} in database. Note: {@link ExternalLink} name can be duplicated in database, this method will return first matched {@link ExternalLink}
   * @param displayName value for {@link UserProcess} processName field
   * @return {@link UserProcess}
   */
  public static UserProcess initUserProcessByExternalLinkName(String externalLinkName, String displayName) {
    return initUserProcess(findExternalLinkIdByExternalLinkName(externalLinkName), ProcessType.EXTERNAL_LINK, displayName);
  }

  private static UserProcess initUserProcess(String processId, ProcessType processType, String displayName) {
    UserProcess userProcess = new UserProcess();
    userProcess.setProcessId(processId);
    userProcess.setProcessType(processType);
    userProcess.setLink(StringUtils.EMPTY);
    userProcess.setProcessName(displayName);
    userProcess.setIcon("fa fa-play fa-fw");
    return userProcess;
  }

  private static String findStartableIdByUserFriendlyRequestPath(String friendlyRequestPath) {
    return IvyExecutor.executeAsSystem(() -> {
      List<IApplication> applicationsInSecurityContext = IApplicationRepository.instance().allOf(ISecurityContext.current());
      IProcessStart processStart = null;
      for (IApplication app : applicationsInSecurityContext) {
        processStart = findStartableProcessStartByUserFriendlyRequestPath(friendlyRequestPath, app);
        if (processStart != null) {
          return app.getName() + "/" + processStart.getFullUserFriendlyRequestPath();
        }
      }
      return StringUtils.EMPTY;
    });
  }

  private static String findExpressProcessIdByExpressProcessName(String expressProcessName) {
    ExpressProcess expressProcess = ExpressProcessService.getInstance().findReadyToExecuteProcessByName(expressProcessName);
    return expressProcess != null ? expressProcess.getId() : StringUtils.EMPTY;
  }

  private static String findExternalLinkIdByExternalLinkName(String externalLinkName) {
    ExternalLink externalLink = ExternalLinkService.getInstance().findExternalLinkByName(externalLinkName);
    return externalLink != null ? externalLink.getId().toString() : StringUtils.EMPTY;
  }

  /**
   * Find start link from friendly request path
   * @param friendlyRequestPath friendly path e.g "Start Processes/UserExampleGuide/userExampleGuide.ivp"
   * @return start link or empty string
   */
  public static String findRelativeUrlByProcessStartFriendlyRequestPath(String friendlyRequestPath) {
    IProcessStart processStart = findProcessStartByUserFriendlyRequestPath(friendlyRequestPath);
    return processStart != null ? processStart.getLink().getRelative() : StringUtils.EMPTY;
  }

  /**
   * Find start element from friendly request path
   * @param friendlyRequestPath friendly path e.g "Start Processes/UserExampleGuide/userExampleGuide.ivp"
   * @return start element or empty string
   */
  public static IStartElement findStartElementByProcessStartFriendlyRequestPath(String friendlyRequestPath) {
    return IvyExecutor.executeAsSystem(() -> {
      IStartElement startElement = getStartElement(friendlyRequestPath, Ivy.request().getProcessModelVersion());
      if (startElement != null) {
        return startElement;
      }

      List<IApplication> applicationsInSecurityContext = IApplicationRepository.instance().allOf(ISecurityContext.current());
      for (IApplication app : applicationsInSecurityContext) {
        IStartElement findStartElement = filterPMVForStartElement(friendlyRequestPath, app).findFirst().orElse(null);
        if (findStartElement != null) {
          return findStartElement;
        }
      }
      return null;
    });
  }
  
  private static IProcessStart findStartableProcessStartByUserFriendlyRequestPath(String requestPath, IApplication application) {
    return filterPMV(requestPath, application)
      .filter(processStart -> isStartableProcessStart(processStart.getFullUserFriendlyRequestPath()))
      .findFirst().orElse(null);
  }

  
  private static boolean isActive(IProcessModelVersion processModelVersion) {
    return processModelVersion != null && processModelVersion.getActivityState() == ActivityState.ACTIVE;
  }

  private static boolean isActive(IProcessModel processModel) {
    return processModel.getActivityState() == ActivityState.ACTIVE;
  }
  
  private static IProcessStart getProcessStart(String requestPath, IProcessModelVersion processModelVersion) {
    return IWorkflowProcessModelVersion.of(processModelVersion).findStartElementByUserFriendlyRequestPath(requestPath);
  }
  
  private static IStartElement getStartElement(String requestPath, IProcessModelVersion processModelVersion) {
    return IWorkflowProcessModelVersion.of(processModelVersion).findStartElementByUserFriendlyRequestPath(requestPath);
  }
  
  private static boolean isStartableProcessStart(String fullUserFriendlyRequestPath) {
    return Ivy.session().getStartableProcessStarts()
        .stream()
        .map(IProcessStart::getFullUserFriendlyRequestPath)
        .filter(startablePorcessRequestPath -> startablePorcessRequestPath.equals(fullUserFriendlyRequestPath))
        .findFirst().isPresent();
  }
  
  private static IProcessStart findProcessStartByUserFriendlyRequestPath(String requestPath) {
    return IvyExecutor.executeAsSystem(() -> {
      IProcessStart processStart = getProcessStart(requestPath, Ivy.request().getProcessModelVersion());
      if (processStart != null) {
        return processStart;
      }
      List<IApplication> applicationsInSecurityContext = IApplicationRepository.instance().allOf(ISecurityContext.current());
      
      for (IApplication app : applicationsInSecurityContext) {
        IProcessStart findProcessStart = filterPMV(requestPath, app).findFirst().orElse(null);
        if (findProcessStart != null) {
          return findProcessStart;
        }
      }
      return null;
    });
  }

  private static Stream<IProcessStart> filterPMV(String requestPath, IApplication application) {
    return application.getProcessModelsSortedByName()
      .stream()
      .filter(pm -> isActive(pm))
      .map(IProcessModel::getReleasedProcessModelVersion)
      .filter(pmv -> isActive(pmv))
      .map(p -> getProcessStart(requestPath, p))
      .filter(Objects::nonNull);
  }

  private static Stream<IStartElement> filterPMVForStartElement(String requestPath, IApplication application) {
    return application.getProcessModelsSortedByName()
      .stream()
      .filter(pm -> isActive(pm))
      .map(IProcessModel::getReleasedProcessModelVersion)
      .filter(pmv -> isActive(pmv))
      .map(p -> getStartElement(requestPath, p))
      .filter(Objects::nonNull);
  }
}
