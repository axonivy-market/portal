package com.axonivy.portal.dto.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.UrlUtils;

public class NavigationDashboardWidget extends DashboardWidget implements Serializable {
  
  private static final long serialVersionUID = -5650954020648136966L;
  @JsonIgnore
  private List<String> dashboardLinkList;
  private Dashboard targetDashboard;
  private String targetDashboardLink;
  
  @JsonIgnore
  private List<Dashboard> dashboardList = new ArrayList<>();
  
  @Override
  public void resetWidgetFilters() {}
  @Override
  public void cancelUserFilter() {}
  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.NAVIGATION_DASHBOARD;
  }
  
  private String getDashboardUrlById(String dashboardId) {
    return UrlUtils.getServerUrl() + PortalNavigator.getDashboardPageUrl(dashboardId);
  }
  
  public List<String> getDashboardLinkList() {
    List<String> dashboardIdLink = DashboardUtils.getAllVisibleDashboardsOfSessionUser().stream().map(dashboard -> dashboard.getId()).collect(Collectors.toList());
    List<String> dashboardLinkList = dashboardIdLink.stream().map(id -> getDashboardUrlById(id)).collect(Collectors.toList());
    return dashboardLinkList;
  }
  
  private void buildDashboardList() {
    setDashboardList(DashboardUtils.getPublicDashboards());
  }
  
  public NavigationDashboardWidget() {
    buildDashboardList();
  }
  
  public static NavigationDashboardWidget buildDefaultWidget(String widgetId, String widgetName) {
    var widget = new NavigationDashboardWidget();
    widget.setId(widgetId);
    widget.setName(widgetName);
    widget.setLayout(new WidgetLayout());
    widget.getLayout().setWidth(2);
    widget.getLayout().setHeight(4);
    widget.getLayout().setAxisX(0);
    widget.getLayout().setAxisY(0);
    return widget;
  }
  
  public void setTargetDashboard(Dashboard targetDashboard) {
    this.targetDashboard = targetDashboard;
  }
  
  public Dashboard getTargetDashboard() {
    return this.targetDashboard;
  }
  
  public void setTargetDashboardLink(String targetDashboardLink) {
    this.targetDashboardLink = targetDashboardLink;
  }
  
  public String getTargetDashboardLink() {
    return this.targetDashboardLink;
  }
  
  public void setDashboardList(List<Dashboard> dashboardList) {
    this.dashboardList = dashboardList;
  }
  
  public List<Dashboard> getDashboardList() {
    return this.dashboardList;
  }
  
}
