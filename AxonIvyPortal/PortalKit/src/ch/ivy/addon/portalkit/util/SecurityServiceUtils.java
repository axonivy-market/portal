package ch.ivy.addon.portalkit.util;

import static ch.ivyteam.ivy.server.ServerFactory.getServer;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IHttpRequest;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.server.restricted.EngineMode;
import ch.ivyteam.ivy.workflow.IProcessStart;

/**
 * Utility for security service.
 */
@SuppressWarnings("restriction")
public class SecurityServiceUtils {

  private SecurityServiceUtils() {}

  /**
   * get Process start URL base on Signature
   * 
   * @param processStartsSignature process Starts Signature
   * @return process URI
   */
  public static String getProcessUri(final String processStartsSignature) {
    return IvyExecutor.executeAsSystem(() -> {
      String requestUri = "";
      if (Ivy.request() instanceof IHttpRequest) {
        Set<IProcessStart> processStarts = Ivy.wf().findProcessStartsBySignature(processStartsSignature);
        if (processStarts.iterator() != null && processStarts.iterator().hasNext()) {
          IProcessStart processStart = processStarts.iterator().next();
          String fullRequestPath = processStart.getFullRequestPath();
          requestUri = "/pro/" + fullRequestPath;
        }
      }
      return requestUri;
    });
  }

  /**
   * Finds process url by request path in whole server
   * 
   * @param processStartSignature
   * @return request path
   */
  public static String findProcessByUserFriendlyRequestPath(String processStartSignature) {
    return IvyExecutor.executeAsSystem(() -> {
      ProcessStartCollector processStartCollector = new ProcessStartCollector(IApplication.current());
      IProcessStart processStart =
          processStartCollector.findProcessStartByUserFriendlyRequestPath(processStartSignature);
      if (processStart != null) {
        try {
          return "/pro/" + processStart.getFullRequestPath();
        } catch (Exception e) {
          Ivy.log().error(e);
          return StringUtils.EMPTY;
        }
      }
      return StringUtils.EMPTY;
    });
  }

  /**
   * Finds portal home page of the default portal application
   * 
   * @return string
   */
  public static String getDefaultPortalStartUrl() {
    return IvyExecutor.executeAsSystem(() -> {
      IApplication defaultPortalApplication = getDefaultPortalApplication();
      if (defaultPortalApplication != null) {
        ProcessStartCollector processStartCollector = new ProcessStartCollector(defaultPortalApplication);
        IProcessStart processStart =
            processStartCollector
                .findProcessStartByUserFriendlyRequestPath("Start Processes/PortalStart/DefaultApplicationHomePage.ivp");
        if (processStart != null) {
          try {
            return String.format("/%s/pro/%s", RequestUriFactory.getIvyContextName(),
                processStart.getFullRequestPath());
          } catch (Exception e) {
            Ivy.log().error(e);
            return StringUtils.EMPTY;
          }
        }
      }
      return StringUtils.EMPTY;
    });
  }

  private static IApplication getDefaultPortalApplication() {
    return getServer().getApplicationConfigurationManager().findApplication(PortalConstants.PORTAL_APPLICATION_NAME);
  }

  public static Object getSessionAttribute(String name) {
    return Ivy.session().getAttribute(name);
  }

  public static void setSessionAttribute(String name, Object value) {
    Ivy.session().setAttribute(name, value);
  }

  public static void removeSessionAttribute(String name) {
    Ivy.session().removeAttribute(name);
  }

  public static String getApplicationNameFromSession() {
    Object selectedAppAttribute = getSessionAttribute(SessionAttribute.SELECTED_APP.toString());
    return selectedAppAttribute != null ? selectedAppAttribute.toString() : null;
  }

  public static String getApplicationDisplayNameFromSession() {
    Object selectedAppDisplayNameAttribute = getSessionAttribute(SessionAttribute.SELECTED_APP_DISPLAY_NAME.toString());
    return selectedAppDisplayNameAttribute != null ? selectedAppDisplayNameAttribute.toString() : null;
  }
  
  public static String findFriendlyRequestPathContainsKeyword(String keyword){
    return IvyExecutor.executeAsSystem(() -> {
      ProcessStartCollector collector = new ProcessStartCollector(IApplication.current());
      Object portalStartPmvId = getSessionAttribute(SessionAttribute.PORTAL_START_PMV_ID.toString());
      return collector.findFriendlyRequestPathContainsKeyword(keyword, portalStartPmvId);
    });
  }

  /**
   * Check current engine is designer or not.
   * 
   * @return check result
   */
  public static boolean isDesigner() {
    return EngineMode.isEmbeddedInDesigner();
  }
}
