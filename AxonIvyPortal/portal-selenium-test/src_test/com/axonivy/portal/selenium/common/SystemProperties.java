package com.axonivy.portal.selenium.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SystemProperties {

  private static String osName = System.getProperty("os.name").toLowerCase();

  public static boolean isInServerMode() {
    return osName.contains("server");
  }

  public static String getServerName() {
    String serverName = "";
    try {
      InetAddress myHost = InetAddress.getLocalHost();
      serverName = myHost.getHostName();
    } catch (UnknownHostException ex) {
      ex.printStackTrace();
    }
    return serverName;
  }

}
