package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.enums.dashboard.filter.FilterPeriodType;

import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class WidgetDateFilterBean implements Serializable {
  
  private static final String MESSAGE_PREFIX_PATTERN = "%s(%d)";

  private static final long serialVersionUID = 4356531402161360729L;

  private static List<FilterOperator> operators = FilterOperator.DATE_OPERATORS.stream().toList();
  private static List<FilterOperator> createdDateOperators = FilterOperator.CREATED_DATE_OPERATORS.stream().toList();
  
  private static FilterPeriodType[] filterPeriodTypes = FilterPeriodType.values();
  private static List<FilterPeriodType> currentFilterPeriodTypes = FilterPeriodType.PERIOD_TYPES_FOR_CURRENT_OPERATOR.stream().toList();

  private static SimpleDateFormat formatter = new SimpleDateFormat(DashboardFilter.DATE_FORMAT);

  public List<FilterOperator> getOperators() {
    return operators;
  }

  public FilterPeriodType[] getFilterPeriodTypes() {
    return filterPeriodTypes;
  }

  public void onChangeOperator(DashboardFilter filter) {
    filter.setFromDate(null);
    filter.setToDate(null);
    filter.setFrom(null);
    filter.setTo(null);
    filter.setPeriodType(null);
    filter.setPeriods(null);
  }

  private String getMessagePrefix(String field, int index) {
    return String.format(MESSAGE_PREFIX_PATTERN, DashboardStandardCaseColumn.findBy(Optional.ofNullable(field).orElse("")).getLabel(), index + 1);
  }

  public String getWrongFormatMessage(String field, int index) {
    return String.join(": ", getMessagePrefix(field, index), Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/wrongDateFormat"));
  }

  public List<FilterPeriodType> getCurrentFilterPeriodTypes() {
    return currentFilterPeriodTypes;
  }

  public void updateToDate(DashboardFilter filter) {
    filter.setTo(formatter.format(filter.getToDate()));
  }

  public void updateFromDate(DashboardFilter filter) {
    filter.setFrom(formatter.format(filter.getFromDate()));
  }

  public List<FilterOperator> getCreatedDateOperators() {
    return createdDateOperators;
  }
}
