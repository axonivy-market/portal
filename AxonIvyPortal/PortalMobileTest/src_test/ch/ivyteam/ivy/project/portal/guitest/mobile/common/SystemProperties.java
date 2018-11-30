package ch.ivyteam.ivy.project.portal.guitest.mobile.common;

public class SystemProperties {
  
  private static String osName = System.getProperty("os.name").toLowerCase();
  
  public static boolean isInServerMode(){
    return osName.contains("server");
  }
  
}