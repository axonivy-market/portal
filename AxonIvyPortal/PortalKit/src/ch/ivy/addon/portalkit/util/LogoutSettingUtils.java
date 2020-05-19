package ch.ivy.addon.portalkit.util;

public class LogoutSettingUtils {
  
  private LogoutSettingUtils() {}
  
  public static void returnToHomepage() {
    PrimeFacesUtils.executeScript("returnHomePage()");
  }
  
  public static void showLogoutConfirmation() {
    PrimeFacesUtils.executeScript("PF('logoutConfirmation').show()");
  }
}
