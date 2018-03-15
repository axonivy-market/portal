package ch.ivy.addon.portalkit.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;

import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivy.addon.portalkit.service.exception.ApplicationNotFoundException;
import ch.ivy.addon.portalkit.service.exception.GetPortalConnectorApplicationException;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.support.UrlDetector;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IApplicationConfigurationManager;
import ch.ivyteam.ivy.application.ILibrary;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.htmldialog.request.IServerPortResolver;
import ch.ivyteam.ivy.request.RequestUriFactory;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.webserver.IWebServer;

@SuppressWarnings("restriction")
public class PortalConnectorDetector {
  private static IApplication portalConnectorApplication;
  private static final String LOCALHOST = "localhost";
  private static final String PORTAL_CONNECTOR_PROJECT_ID = "ch.ivyteam.ivy.project.system:portalConnector";
  private static final String PORTAL_CONNECTOR_URL_FORMAT =
      "${serverURL}${ivyContextPath}/ws/${applicationName}/${portalConnectorPMName}";

  /**
   * Get PortalConnector application
   * 
   * @throws GetPortalConnectorApplicationException
   * @return {@link IApplication}
   */
  public IApplication getPortalConnectorApplication() {
    if (portalConnectorApplication == null) {
      try {

        Callable<IApplication> findApplicationCallable =
            () -> {
              List<IApplication> applications =
                  ServerFactory.getServer().getApplicationConfigurationManager().getApplications();
              for (IApplication application : applications) {
                IProcessModelVersion processModelVersion = findPortalConnectorPMV(application);
                if (processModelVersion != null) {
                  return processModelVersion.getApplication();
                }
              }
              throw new ApplicationNotFoundException(
                  "Cannot find application that contains process model PortalConnector");

            };
        portalConnectorApplication = IvyAdapterService.executeCallableAsSystem(findApplicationCallable);
      } catch (Exception e) {
        Ivy.log().error("Cannot get PortalConnector application", e);
        throw new GetPortalConnectorApplicationException(e);
      }
    }
    return portalConnectorApplication;
  }

  private IProcessModelVersion findPortalConnectorPMV(IApplication application) {
    Callable<IProcessModelVersion> findPMVCallable = () -> {
      List<IProcessModel> pms = application.getProcessModels();
      for (IProcessModel pm : pms) {
        IProcessModelVersion pmv = pm.getReleasedProcessModelVersion();
        if (isPortalConnectorPMV(pmv)) {
          return pmv;
        }
      }
      return null;
    };
    return IvyAdapterService.executeCallableAsSystem(findPMVCallable);
  }

  private boolean isPortalConnectorPMV(IProcessModelVersion pmv) {
    if (pmv == null) {
      return false;
    }
    ILibrary library = pmv.getLibrary();
    if (library != null) {
      return PORTAL_CONNECTOR_PROJECT_ID.equals(library.getId());
    } else {
      return false;
    }
  }

  public String getPortalConectorLocalhostURLFromSystemProperty() {
    IApplication portalConnectorApplication = getPortalConnectorApplication();

    Callable<String> getPortalConnectorURLCallable = () -> {
      IWebServer webServer = ServerFactory.getServer().getWebServer();
      IServerPortResolver serverPortResolver = webServer.getServerPortResolver();
      String serverUrl = getServerURL(serverPortResolver);
      return getPortalConectorLocalhostURL(portalConnectorApplication, webServer, serverUrl);
    };

    return IvyAdapterService.executeCallableAsSystem(getPortalConnectorURLCallable);
  }

  private String getPortalConectorLocalhostURL(IApplication portalConnectorApplication, IWebServer webServer,
      String serverUrl) {
    String applicationName = portalConnectorApplication.getName();
    IProcessModelVersion portalConnectorPMV = findPortalConnectorPMV(portalConnectorApplication);
    String portalConnectorPMName = portalConnectorPMV.getProcessModel().getName();
    String path = webServer.getServletContext().getContextPath();
    Map<String, String> portalConnectorURLFormatParams = new HashMap<String, String>();
    portalConnectorURLFormatParams.put("serverURL", serverUrl);
    portalConnectorURLFormatParams.put("ivyContextPath", path);
    portalConnectorURLFormatParams.put("applicationName", applicationName);
    portalConnectorURLFormatParams.put("portalConnectorPMName", portalConnectorPMName);

    String portalConnectorURL = formatString(PORTAL_CONNECTOR_URL_FORMAT, portalConnectorURLFormatParams);
    return portalConnectorURL;
  }

  public String getPortalConectorLocalhostURLFromRequestURL() {
    IApplication portalConnectorApplication = getPortalConnectorApplication();

    Callable<String> getPortalConnectorURLCallable = () -> {
      IWebServer webServer = ServerFactory.getServer().getWebServer();
      UrlDetector detector = new UrlDetector();
      String serverUrl = detector.getHost(detector.getBaseURL(FacesContext.getCurrentInstance()));
      return getPortalConectorLocalhostURL(portalConnectorApplication, webServer, serverUrl);
    };

    return IvyAdapterService.executeCallableAsSystem(getPortalConnectorURLCallable);
  }

  private String getServerURL(IServerPortResolver serverPortResolver) throws UnknownHostException,
      MalformedURLException {
    IApplicationConfigurationManager appConfig = ServerFactory.getServer().getApplicationConfigurationManager();
    String serverAddress = getServerAddress();
    String serverProtocol;
    int serverPort;
    if (appConfig.getSystemProp("WebServer.HTTP.Enabled").getBooleanValue()) {
      serverProtocol = IServerPortResolver.HTTP_PROTOCOL;
      serverPort = serverPortResolver.getServerPort(serverAddress, serverProtocol);
    } else if (appConfig.getSystemProp("WebServer.HTTPS.Enabled").getBooleanValue()) {
      serverProtocol = IServerPortResolver.HTTPS_PROTOCOL;
      serverPort = serverPortResolver.getServerPort(serverAddress, serverProtocol);
    } else {
      throw new UnknownHostException();
    }
    return new URL(serverProtocol, serverAddress, serverPort, "").toString();
  }

  private String getServerAddress() {
    String serverAddress =
        ServerFactory.getServer().getApplicationConfigurationManager().getSystemProp("WebServer.HTTP.Address")
            .getValue();
    return (StringUtils.isEmpty(serverAddress) ? LOCALHOST : serverAddress);
  }

  public String getPortalConnectorURLOf(Server server) throws MalformedURLException {
    if (server.getId() > -1L) {
      return server.getPath();
    }
    URL originUrl = new URL(server.getPath());
    int port = getServerPort(originUrl.getProtocol());
    URL newUrl = new URL(originUrl.getProtocol(), originUrl.getHost(), port, originUrl.getFile());
    return newUrl.toString();
  }

  private int getServerPort(String protocol) {
    IApplicationConfigurationManager appConfig = ServerFactory.getServer().getApplicationConfigurationManager();
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<Integer>() {
        @Override
        public Integer call() throws Exception {
          int port;
          if (RequestUriFactory.HTTPS_PROTOCOL.equalsIgnoreCase(protocol)) {
            port = appConfig.getSystemProp("WebServer.HTTPS.Port").getIntValue();
          } else {
            port = appConfig.getSystemProp("WebServer.HTTP.Port").getIntValue();
          }
          return new UrlDetector().getPortForConstructingUrl(protocol, port);
        }
      });
    } catch (Exception e) {
      Ivy.log().error("Cannot get server port", e);
      throw new PortalException(e);
    }
  }

  private String formatString(String format, Map<String, String> parameter) {
    StrSubstitutor strSubstitutor = new StrSubstitutor(parameter);
    return strSubstitutor.replace(format);
  }

}
