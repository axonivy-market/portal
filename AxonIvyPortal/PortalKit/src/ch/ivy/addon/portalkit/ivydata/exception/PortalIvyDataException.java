package ch.ivy.addon.portalkit.ivydata.exception;

public class PortalIvyDataException extends Exception {

  private static final long serialVersionUID = -8558165268719107465L;
  
  private String appName;
  private String userText;
  
  public PortalIvyDataException(String appName, String userText) {
    this.appName = appName;
    this.userText = userText;
  }
  
  public String getAppName() {
    return appName;
  }
  
  public String getUserText() {
    return userText;
  }
  
  @Override
  public String toString() {
    return String.format("{Application name: %s, Message: %s}", appName, userText);
  }
}
