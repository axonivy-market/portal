package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IApplicationConfigurationManager;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.request.IHttpRequest;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.workflow.IProcessStart;

/**
 * Utility for security service.
 * 
 * @author maonguyen
 *
 */
public class SecurityServiceUtils {

  /**
   * INTERNAL_CUSOM_APPLICATION
   */
  public static final String INTERNAL_CUSTOM_APPLICATION = "InternalCustomApplication(String,String)";

  /**
   * Get all application names in an ivy server.
   * 
   * @param server server to get applications.
   * @return list of application name.
   */
  public static List<String> getAppNames(Server server) {
    List<String> apps = new ArrayList<String>();
    if (server != null) {
      for (Application app : server.getApplications()) {
        apps.add(app.getName());
      }
    }
    return apps;
  }

  /**
   * get Process start URL base on Signature
   * 
   * @param processStartsSignature process Starts Signature
   * @return process URI
   * @throws Exception exception
   */
  public static String getProcessUri(final String processStartsSignature) throws Exception {
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<String>() {
      @Override
      public String call() throws Exception {
        String requestUri = "";
        if (Ivy.request() instanceof IHttpRequest) {
          Set<IProcessStart> processStarts = Ivy.wf().findProcessStartsBySignature(processStartsSignature);
          if (processStarts.iterator() != null && processStarts.iterator().hasNext()) {
            IProcessStart processStart = (IProcessStart) processStarts.iterator().next();
            String fullRequestPath = processStart.getFullRequestPath();
            requestUri = "/pro/" + fullRequestPath;
          }
        }

        return requestUri;
      }
    });
  }

  /**
   * Finds process url by request path in whole server
   * 
   * @param userFriendlyRequestPath
   * @return
   * @throws Exception 
   */
  public static String findProcessByUserFriendlyRequestPath(String userFriendlyRequestPath) throws Exception {
    return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<String>() {

      @Override
      public String call() throws Exception {
        IApplicationConfigurationManager applicationConfigurationManager =
            ServerFactory.getServer().getApplicationConfigurationManager();
        for (IApplication application : applicationConfigurationManager.getApplications()) {
          ProcessStartCollector processStartCollector = new ProcessStartCollector(application);
          IProcessStart processStart =
              processStartCollector.findProcessStartByUserFriendlyRequestPath(userFriendlyRequestPath);
          if (processStart != null) {
            try {
              return "/pro/" + processStart.getFullRequestPath();
            } catch (Exception e) {
              Ivy.log().error(e);
              return StringUtils.EMPTY;
            }
          }
        }
        return StringUtils.EMPTY;
      }
    });
  }
}
