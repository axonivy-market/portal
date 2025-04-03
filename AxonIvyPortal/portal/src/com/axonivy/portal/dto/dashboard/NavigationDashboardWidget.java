package com.axonivy.portal.dto.dashboard;

import java.io.Serializable;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.dto.WidgetLayout;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.enums.DashboardWidgetType;
import ch.ivy.addon.portalkit.util.DashboardUtils;

public class NavigationDashboardWidget extends DashboardWidget implements Serializable {
  
  private static final long serialVersionUID = -565012312312361L;

  private String targetDashboardId;
  private String targetDashboardName;

  @JsonIgnore
  private List<Dashboard> dashboardList = new ArrayList<>();

  private String description;
  private String icon;
  
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
    this.dashboardList = DashboardUtils.getPublicDashboards();
  }
  
  public static NavigationDashboardWidget buildDefaultWidget(String widgetId) {
    var widget = new NavigationDashboardWidget();
    widget.setId(widgetId);
    widget.setLayout(new WidgetLayout());
    widget.getLayout().setWidth(3);
    widget.getLayout().setHeight(3);
    widget.getLayout().setAxisX(0);
    widget.getLayout().setAxisY(0);
    return widget;
  }
  
  public void setTargetDashboardId(String targetDashboardId) {
    setTargetDashboardName(targetDashboardId);
    this.targetDashboardId = targetDashboardId;
  }
  
  public String getTargetDashboardId() {
    return this.targetDashboardId;
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

  public String getTargetDashboardName() {
    return this.targetDashboardName;
  }
  
  public void setTargetDashboardName(String targetDashboardName) {
    this.targetDashboardName = targetDashboardName;
  }
  
  public void setDashboardList(List<Dashboard> dashboardList) {
    this.dashboardList = dashboardList;
  }
  
  public List<Dashboard> getDashboardList() {
      return this.dashboardList;
  }
  
  public String getIcon() {
    return this.icon;
  }
  
  public void setIcon(String icon) {
    this.icon = icon;
  }
  
  public String getDashboardNameById(String id) {
    Dashboard dashboard = this.dashboardList.stream().filter(item -> item.getId().equals(id)).findAny().orElse(null);
    if (dashboard != null) {
      return dashboard.getTitle();
    }
    return StringUtils.EMPTY;
  }
}
