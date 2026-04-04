package com.axonivy.portal.dto.menu;

import java.util.Optional;

import com.axonivy.portal.components.enums.MenuKind;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.addon.portal.generic.menu.PortalMenuItem;
import ch.addon.portal.generic.menu.PortalMenuItem.PortalMenuBuilder;
import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.service.DashboardService;
import ch.ivy.addon.portalkit.util.UrlUtils;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DashboardMenuItemDefinition extends PortalMenuItemDefinition {

  private static final long serialVersionUID = 3638152027138711358L;
  private String dashboardId;

  @JsonIgnore
  private Dashboard dashboard;

  @Override
  public MenuKind getType() {
    return MenuKind.MAIN_DASHBOARD;
  }

  @Override
  public PortalMenuItem convertToPortalMenuItem() {
    if (!hasPermission()) {
      return null;
    }

    dashboard = DashboardService.getInstance().findById(dashboardId);

    String dashboardLink = DEFAULT_LINK;
    if (Optional.ofNullable(dashboard).map(Dashboard::getId).isPresent()) {
      dashboardLink = UrlUtils.getServerUrl() + PortalNavigator.getDashboardPageUrl(dashboard.getId());
    }

    PortalMenuBuilder builder = new PortalMenuBuilder(
        getTitles().stream().filter(name -> name.getLocale().toLanguageTag().contentEquals(getCurrentLanguage()))
            .findFirst().get().getValue(),
        getType(), false).icon(getIconClass()).url(dashboardLink);

    if (getWorkingtaskId() == null) {
      return builder.build();
    }

    return builder.isWorkingOnATask(true).workingTaskId(getWorkingtaskId()).build();
  }

  public String getDashboardId() {
    return dashboardId;
  }

  public void setDashboardId(String dashboardId) {
    this.dashboardId = dashboardId;
  }

  @JsonIgnore
  public Dashboard getDashboard() {
    return dashboard;
  }

  @JsonIgnore
  public void setDashboard(Dashboard dashboard) {
    this.dashboard = dashboard;
  }

}