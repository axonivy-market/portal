package com.axonivy.portal.dto.statistic;

import java.io.Serializable;

import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.util.statisticfilter.field.FilterField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StatisticFilter extends BaseFilter implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonIgnore
  private FilterField filterField;

  @JsonIgnore
  public boolean isCategory() {
    return DashboardStandardTaskColumn.CATEGORY.getField().equals(getField());
  }

  @JsonIgnore
  public boolean isState() {
    return DashboardStandardTaskColumn.STATE.getField().equals(getField());
  }

  public FilterField getFilterField() {
    return filterField;
  }

  public void setFilterField(FilterField filterField) {
    this.filterField = filterField;
  }
  
  @JsonIgnore
  public boolean isExpiryDateField() {
    return DashboardStandardTaskColumn.EXPIRY.getField().equals(getField());
  }
  
  @JsonIgnore
  public boolean isCanWorkOn() {
    return StatisticConstants.CAN_WORK_ON.equals(getField());
  }
  
  @JsonIgnore
  public boolean isCreator() {
    return DashboardStandardCaseColumn.CREATOR.getField().equals(getField());
  }
}
