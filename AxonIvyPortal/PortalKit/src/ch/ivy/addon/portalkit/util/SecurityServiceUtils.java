package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.SessionAttribute;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IHttpRequest;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IProcessStart;

/**
 * Utility for security service.
 *
 */
public class SecurityServiceUtils {

  /**
   * Get all application names in an ivy server.
   * 
   * @param server server to get applications.
   * @return list of application name.
   */
  public static List<String> getAppNames(Server server) {
    if (server != null) {
      return CollectionUtils.emptyIfNull(server.getApplications())
          .stream()
          .map(Application::getName)
          .collect(Collectors.toList());
    }
    return new ArrayList<String>();
  }

  /**
   * get Process start URL base on Signature
   * 
   * @param processStartsSignature process Starts Signature
   * @return process URI
   * @throws Exception exception
   */
  public static String getProcessUri(final String processStartsSignature) throws Exception {
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(()-> {
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
   * @throws Exception
   */
  public static String findProcessByUserFriendlyRequestPath(String processStartSignature) throws Exception {
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
      ProcessStartCollector processStartCollector = new ProcessStartCollector(Ivy.wf().getApplication());
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
   * @throws Exception
   */
  public static String getDefaultPortalStartUrl() throws Exception {
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
      IApplication defaultPortalApplication =
          ServerFactory.getServer().getApplicationConfigurationManager()
              .findApplication(IApplication.PORTAL_APPLICATION_NAME);
      if (defaultPortalApplication != null) {
        ProcessStartCollector processStartCollector = new ProcessStartCollector(defaultPortalApplication);
        IProcessStart processStart =
            processStartCollector
                .findProcessStartByUserFriendlyRequestPath("Start Processes/PortalStart/PortalStart.ivp");
        if (processStart != null) {
          try {
            return String.format("/%s/pro/%s",
                RequestUriFactory.getIvyContextName(ServerFactory.getServer().getApplicationConfigurationManager()),
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

  public static Long getServerIdFromSession() {
    Object selectedServerIdAttribute = getSessionAttribute(SessionAttribute.SERVER_ID.toString());
    return selectedServerIdAttribute != null ? Long.parseLong(selectedServerIdAttribute.toString()) : null;
  }

  public static String getApplicationDisplayNameFromSession() {
    Object selectedAppDisplayNameAttribute = getSessionAttribute(SessionAttribute.SELECTED_APP_DISPLAY_NAME.toString());
    return selectedAppDisplayNameAttribute != null ? selectedAppDisplayNameAttribute.toString() : null;
    
  }
  
  public static String findFriendlyRequestPathContainsKeyword(String keyword) throws Exception{
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
      ProcessStartCollector collector = new ProcessStartCollector(Ivy.wf().getApplication());
      Object portalStartPmvId = getSessionAttribute(SessionAttribute.PORTAL_START_PMV_ID.toString());
      return collector.findFriendlyRequestPathContainsKeyword(keyword, portalStartPmvId);
    });
  }
}
