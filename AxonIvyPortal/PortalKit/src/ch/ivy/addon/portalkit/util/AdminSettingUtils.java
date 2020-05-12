package ch.ivy.addon.portalkit.util;

public class AdminSettingUtils {
  private AdminSettingUtils() {}
  
  public static void clearFiltersForSettingTable() {
    PrimeFacesUtils.executeScript("PF('settingTable').clearFilters()");
  }
  
  public static void filterForSettingTable() {
    PrimeFacesUtils.executeScript("PF('settingTable').filter()");
  }
}
