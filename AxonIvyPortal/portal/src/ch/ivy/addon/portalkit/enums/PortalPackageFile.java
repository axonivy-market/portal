package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum PortalPackageFile {

  DASHBOARD("Portal_Dashboard.json", PortalVariable.DASHBOARD, "DashboardFileDescription"),
  CUSTOM_STATISTIC("Portal_CustomStatistic.json", PortalVariable.CUSTOM_STATISTIC, "CustomStatisticFileDescription"),
  USER_MENU("Portal_UserMenu.json", PortalVariable.USER_MENU, "UserMenuFileDescription"),
  CASE_DETAIL("Portal_CaseDetails.json", PortalVariable.CASE_DETAIL, "CaseDetailsFileDescription"),
  THIRD_PARTY_APP("Portal_ThirdPartyApplications.json", PortalVariable.THIRD_PARTY_APP, "ThirdPartyApplicationsFileDescription"),
  CUSTOM_MENU_ITEMS("Portal_CustomMenuItems.json", PortalVariable.CUSTOM_MENU_ITEMS, "CustomMenuItemsFileDescription"),
  EXTERNAL_LINK("Portal_ExternalLinks.json", PortalVariable.EXTERNAL_LINK, "ExternalLinksFileDescription"),
  MENU_ORDER("Portal_MenuOrder.json", PortalVariable.MENU_ORDER, "MenuOrderFileDescription");

  private String filename;
  private PortalVariable variable;
  private String descriptionCmsKey;

  private PortalPackageFile(String filename, PortalVariable variable, String descriptionCmsKey) {
    this.filename = filename;
    this.variable = variable;
    this.descriptionCmsKey = descriptionCmsKey;
  }

  public String getFilename() {
    return filename;
  }

  public String getVariableKey() {
    return variable.key;
  }

  public String getDescription() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/PortalPackageManagement/" + descriptionCmsKey);
  }

  public static PortalPackageFile fromFilename(String filename) {
    for (PortalPackageFile file : values()) {
      if (file.filename.equals(filename)) {
        return file;
      }
    }
    return null;
  }
}
