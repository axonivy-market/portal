package portal.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
  private static final String SERVER_ADDRESS = "server.address";
  private static final String IVY_ENGINE_PORT = "ivy.engine.port";
  private static final String IVY_CONTEXT_PATH = "ivy.context.path";
  private static final String APPLICATION_NAME = "application.name";
  private static final String CONFIG_WINDOWS_SERVER = "/config_windows_server.properties";
  private static final String CONFIG_DESIGNER = "/config_designer.properties";

  private static Properties properties;

  static {
    loadProperties();
  }

  private static void loadProperties() {
    InputStream inputStream = PropertyLoader.class.getResourceAsStream(getPropertyFileName());
    properties = new Properties();
    try {
      properties.load(inputStream);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static String getPropertyFileName() {
    if (SystemProperties.isInServerMode()) {
      return CONFIG_WINDOWS_SERVER;
    }
    return CONFIG_DESIGNER;
  }

  public static String getIvyEnginePort() {
    return properties.getProperty(IVY_ENGINE_PORT);
  }

  public static String getServerAddress() {
    return properties.getProperty(SERVER_ADDRESS);
  }

  public static String getIvyContextPath() {
    return properties.getProperty(IVY_CONTEXT_PATH);
  }

  public static String getApplicationName() {
    return properties.getProperty(APPLICATION_NAME);
  }

}
