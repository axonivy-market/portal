package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum DashboardWidgetType {
  TASK(true, true, true),
  CASE(true, true, true),
  PROCESS(true, true, true),
  NEW(false, false, false),
  CUSTOM(false, false, true),
  PROCESS_VIEWER(false, false, true),
  WELCOME(false, false, false),
  NEWS(false, false, true),
  NOTIFICATION(false, false, true),
  STATISTIC(false, false, true),
  NAVIGATION_DASHBOARD(false, false, true);

  private boolean canEnableQuickSearch;
  private boolean canShowWidgetInfoOption;
  private boolean canShowFullscreenModeOption;

  private DashboardWidgetType(boolean canEnableQuickSearch, boolean canShowWidgetInfoOption,
      boolean canShowFullscreenModeOption) {
    this.canEnableQuickSearch = canEnableQuickSearch;
    this.canShowWidgetInfoOption = canShowWidgetInfoOption;
    this.canShowFullscreenModeOption = canShowFullscreenModeOption;
  }

  public static DashboardWidgetType typeOf(String typeName) {
    for (DashboardWidgetType type : DashboardWidgetType.values()) {
      if (type.name().equalsIgnoreCase(typeName)) {
        return type;
      }
    }
    return null;
  }

  public String getLabel() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/Enums/DashboardWidgetType/" + name());
  }

  public boolean canEnableQuickSearch() {
    return canEnableQuickSearch;
  }
  
  public boolean canShowWidgetInfoOption() {
    return canShowWidgetInfoOption;
  }

  public boolean canShowFullscreenModeOption() {
    return canShowFullscreenModeOption;
  }
}
