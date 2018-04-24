package ch.ivy.ws.addon;

import java.util.Objects;

import ch.ivy.ws.addon.service.AbsenceServiceImpl;
import ch.ivy.ws.addon.service.ApplicationServiceImpl;
import ch.ivy.ws.addon.service.CaseServiceImpl;
import ch.ivy.ws.addon.service.IAbsenceService;
import ch.ivy.ws.addon.service.IApplicationService;
import ch.ivy.ws.addon.service.ICaseService;
import ch.ivy.ws.addon.service.IIsAliveService;
import ch.ivy.ws.addon.service.ILanguagesSettingsService;
import ch.ivy.ws.addon.service.ILibraryService;
import ch.ivy.ws.addon.service.IProcessStartService;
import ch.ivy.ws.addon.service.ISecurityService;
import ch.ivy.ws.addon.service.IServerService;
import ch.ivy.ws.addon.service.ISideStepService;
import ch.ivy.ws.addon.service.ITaskService;
import ch.ivy.ws.addon.service.IUserSettingService;
import ch.ivy.ws.addon.service.IWebStartableService;
import ch.ivy.ws.addon.service.IsAliveServiceImpl;
import ch.ivy.ws.addon.service.LanguagesSettingsServiceImpl;
import ch.ivy.ws.addon.service.LibraryServiceImpl;
import ch.ivy.ws.addon.service.ProcessStartServiceImpl;
import ch.ivy.ws.addon.service.SecurityServiceImpl;
import ch.ivy.ws.addon.service.ServerServiceImpl;
import ch.ivy.ws.addon.service.SideStepServiceImpl;
import ch.ivy.ws.addon.service.TaskServiceImpl;
import ch.ivy.ws.addon.service.UserSettingServiceImpl;
import ch.ivy.ws.addon.service.WebStartableServiceImpl;


/**
 * Service Factory for all services that are provided as web service
 */
@SuppressWarnings("deprecation")
public class WsServiceFactory {

  private static ICaseService caseService;

  private static ISecurityService securityService;

  private static ITaskService taskService;

  private static IProcessStartService processStartService;

  private static IUserSettingService userSettingService;

  private static IApplicationService applicationService;
  
  private static IServerService serverService;

  private static ISideStepService sideStepService;
  
  @Deprecated
  private static IIsAliveService isAliveService;

  private static IAbsenceService absenceService;

  private static ILanguagesSettingsService languagesSettingsService;
  
  private static IWebStartableService webStartableService;
  
  private static ILibraryService libraryService;

  public static ILanguagesSettingsService getLanguagesSettingsService() {
    if (languagesSettingsService == null) {
      languagesSettingsService = new LanguagesSettingsServiceImpl();
    }
    return languagesSettingsService;
  }

  public static ICaseService getCaseService() {
    if (caseService == null)
      caseService = new CaseServiceImpl();

    return caseService;
  }

  public static IProcessStartService getProcessStartService() {
    if (processStartService == null)
      processStartService = new ProcessStartServiceImpl();

    return processStartService;
  }

  public static ITaskService getTaskService() {
    if (taskService == null)
      taskService = new TaskServiceImpl();

    return taskService;
  }

  public static ISecurityService getSecurityService() {
    if (securityService == null)
      securityService = new SecurityServiceImpl();

    return securityService;
  }

  public static IUserSettingService getUserSettingService() {
    if (userSettingService == null)
      userSettingService = new UserSettingServiceImpl();

    return userSettingService;
  }

  @Deprecated
  public static IIsAliveService getIsAliveService() {
    if (isAliveService == null)
      isAliveService = new IsAliveServiceImpl();

    return isAliveService;
  }

  public static IApplicationService getApplicationService() {
    if (Objects.isNull(applicationService)) {
      applicationService = new ApplicationServiceImpl();
    }
    return applicationService;
  }

  public static IAbsenceService getAbsenceService() {
    if (absenceService == null) {
      absenceService = new AbsenceServiceImpl();
    }
    return absenceService;
  }
  
  public static IServerService getServerService() {
    if(serverService == null) {
      serverService = new ServerServiceImpl();
    }
    return serverService;
  }

  public static ISideStepService getSideStepService() {
    if (sideStepService == null) {
      sideStepService = new SideStepServiceImpl();
    }
    return sideStepService;
  }
  
  public static IWebStartableService getWebStartableService() {
    if (webStartableService == null) {
      webStartableService = new WebStartableServiceImpl();
    }
    return webStartableService;
  }
  
  public static ILibraryService getLibraryService() {
    if (libraryService == null) {
      libraryService = new LibraryServiceImpl();
    }
    return libraryService;
  }
}
