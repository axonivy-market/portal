package ch.ivyteam.ivy.project.portal.guitest.mobile.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
  private static final String APPIUM_ADDRESS = "appium.address";
  private static final String APPIUM_PORT = "appium.port";
  private static final String DEVICE_NAME = "device.name";
  private static final String SERVER_ADDRESS = "server.address";
  private static final String IVY_ENGINE_PORT = "ivy.engine.port";
  private static final String IVY_CONTEXT_PATH = "ivy.context.path";
  private static final String APPLICATION_NAME = "application.name";
  private static final String SERVER_MODE = "server.mode";
  private static final String CONFIG_WINDOWS_SERVER = "resources/config_windows_server.properties";
  private static final String CONFIG_DESIGNER = "resources/config_designer.properties";

  private static Properties properties;

  static {
    loadProperties();
  }

  private static void loadProperties() {
    try {
      InputStream inputStream = new FileInputStream(getPropertyFileName());
      properties = new Properties();
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
  
  public static String getAppiumAddress() {
    return properties.getProperty(APPIUM_ADDRESS);
  }
  
  public static String getAppiumPort() {
    return properties.getProperty(APPIUM_PORT);
  }
  
  public static String getDeviceName() {
    return properties.getProperty(DEVICE_NAME);
  }
  
  public static boolean getServerMode() {
    return Boolean.valueOf(properties.getProperty(SERVER_MODE));
  }
}
