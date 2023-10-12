package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
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
  private static FilterPeriodType[] filterPeriodTypes = FilterPeriodType.values();

  public List<FilterOperator> getOperators() {
    return operators;
  }

  public FilterPeriodType[] getFilterPeriodTypes() {
    return filterPeriodTypes;
  }

  public void onChangeOperator(DashboardFilter filter) {
    filter.setFrom(null);
    filter.setTo(null);
    filter.setPeriodType(FilterPeriodType.YEAR);
    filter.setPeriods(1L);
  }

  private String getMessagePrefix(String field, int index) {
    return String.format(MESSAGE_PREFIX_PATTERN, DashboardStandardCaseColumn.findBy(Optional.ofNullable(field).orElse("")).getLabel(), index + 1);
  }

  public String getWrongFormatMessage(String field, int index) {
    return String.join(": ", getMessagePrefix(field, index), Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/wrongDateFormat"));
  }
}
