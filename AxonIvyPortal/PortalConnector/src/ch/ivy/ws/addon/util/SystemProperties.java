package ch.ivy.ws.addon.util;

import ch.ivyteam.ivy.application.IApplicationConfigurationManager;
import ch.ivyteam.ivy.bpm.error.BpmError;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.system.ISystemProperty;

public final class SystemProperties {
  public static final String EXTERNAL_HOST_NAME = "WebServer.ExternalHostName";
  public static final String EXTERNAL_PORT = "WebServer.ExternalPort";
  public static final String EXTERNAL_PROTOCOL = "WebServer.ExternalProtocol";

  private SystemProperties() {}
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
    ISystemProperty systemProperty = applicationConfigurationManager.getSystemProp(propertyName);
    return systemProperty.getValue();
  }
}
