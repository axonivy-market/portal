package com.axonivy.portal.dto.dashboard;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.util.DashboardUtils;

public class NavigationDashboardWidget extends DashboardWidget implements Serializable {
  
  private static final long serialVersionUID = -565012312312361L;
  
  @JsonIgnore
  private Dashboard targetDashboard;

  @JsonIgnore
  private List<Dashboard> dashboardList = new ArrayList<>();

  private String title;
  private String description;
  private String buttonName;
  
  @JsonIgnore
  private Boolean isDisable;
  
  @Override
  public void resetWidgetFilters() {}
  @Override
  public void cancelUserFilter() {}
  @Override
  public DashboardWidgetType getType() {
    return DashboardWidgetType.NAVIGATION_DASHBOARD;
  }
  
  public NavigationDashboardWidget() {
  }
  
  public static NavigationDashboardWidget buildDefaultWidget(String widgetId) {
    var widget = new NavigationDashboardWidget();
    widget.setId(widgetId);
    widget.setLayout(new WidgetLayout());
    widget.getLayout().setWidth(2);
    widget.getLayout().setHeight(2);
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
  
  public void setDashboardList(List<Dashboard> dashboardList) {
    this.dashboardList = dashboardList;
  }
  
  public void setIsDisable(Boolean isDisable) {
    this.isDisable = isDisable;
  }
  
  public Boolean getIsDisable() {
    return this.isDisable;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public String getButtonName() {
    return this.buttonName;
  }
  
  public void setButtonName(String buttonName) {
    this.buttonName = buttonName;
  }
  
  public String getTitle() {
    return this.title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public List<Dashboard> getDashboardList() {
    List<Dashboard> list = DashboardUtils.getPublicDashboards().stream().filter(dashboard -> !dashboard.getIsTopMenu()).collect(Collectors.toList());
    if (list.isEmpty()) {
      return this.dashboardList;
    }
    return list;
  }
  
  public String getDashboardNameById(String id) {
    Dashboard dashboard = this.dashboardList.stream().filter(item -> item.getId().equals(id)).findAny().orElse(null);
    if (dashboard != null) {
      return dashboard.getTitle();
    }
    return "NO dashboard";
  }
}
