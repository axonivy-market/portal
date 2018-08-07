package ch.ivy.ws.addon.util;

import ch.ivyteam.ivy.application.IApplicationConfigurationManager;
import ch.ivyteam.ivy.bpm.error.BpmError;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.system.IProperty;

public final class SystemProperties {
  public static final String EXTERNAL_HOST_NAME = "WebServer.ExternalHostName";
  public static final String EXTERNAL_PORT = "WebServer.ExternalPort";
  public static final String EXTERNAL_PROTOCOL = "WebServer.ExternalProtocol";

  public static String getSystemPropertyValue(String propertyName) {
    try {
      return ServerFactory.getServer().getSecurityManager()
          .executeAsSystem(() -> getExternalHostCallable(propertyName));
    } catch (Exception e) {
      throw BpmError.create("ivy:portalconnector:error:serverservice").withCause(e).build();
    }
  }

  private static String getExternalHostCallable(String propertyName) {
    IApplicationConfigurationManager applicationConfigurationManager =
        ServerFactory.getServer().getApplicationConfigurationManager();
    IProperty systemProperty = applicationConfigurationManager.getSystemProperty(propertyName);
    return systemProperty.getValue();
  }
}
