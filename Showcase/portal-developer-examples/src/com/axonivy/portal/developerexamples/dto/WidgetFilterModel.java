package com.axonivy.portal.developerexamples.dto;

import java.io.Serializable;
import java.util.function.Predicate;

import com.axonivy.portal.developerexamples.configuration.AbstractConfiguration;
import com.axonivy.portal.developerexamples.enums.DashboardWidgetType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WidgetFilterModel extends AbstractConfiguration implements Serializable {

  private static final long serialVersionUID = 5781537661765302380L;
  private String name;
  private String widgetId;
  private String widgetName;
  private DashboardWidgetType widgetType;

  public WidgetFilterModel() {}

  public WidgetFilterModel(String name, DashboardWidgetType widgetType) {
    this.name = name;
    this.widgetType = widgetType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getWidgetId() {
    return widgetId;
  }

  public void setWidgetId(String widgetId) {
    this.widgetId = widgetId;
  }

  public DashboardWidgetType getWidgetType() {
    return widgetType;
  }

  public void setWidgetType(DashboardWidgetType widgetType) {
    this.widgetType = widgetType;
  }


  public String getWidgetName() {
    return widgetName;
  }

  public void setWidgetName(String widgetName) {
    this.widgetName = widgetName;
  }

  @JsonIgnore
  public static Predicate<? super WidgetFilterModel> isEqualFilter(WidgetFilterModel filter) {
    return selectedFilter -> selectedFilter.getId().equals(filter.getId());
  }
}
