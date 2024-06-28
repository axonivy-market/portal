package com.axonivy.portal.selenium.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
  private static final String SERVER_ADDRESS = "server.address";
  private static final String IVY_ENGINE_PORT = "ivy.engine.port";
  private static final String APPLICATION_NAME = "application.name";
  private static final String BROWSER_TYPE = "browser.type";
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

  public static String getApplicationName() {
    return properties.getProperty(APPLICATION_NAME);
  }

  public static String getBrowserType() {
    return properties.getProperty(BROWSER_TYPE);
  }

}
