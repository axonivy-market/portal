package com.axonivy.portal.bean.dashboard;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.enums.dashboard.filter.FilterPeriodType;
import com.axonivy.portal.util.filter.field.FilterFieldFactory;
import com.axonivy.portal.util.filter.field.TaskFilterFieldFactory;

import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class WidgetDateFilterBean implements Serializable {

  private static final String MESSAGE_PREFIX_PATTERN = "%s(%d)";

  private static final long serialVersionUID = 4356531402161360729L;

  private static List<FilterOperator> operators = FilterOperator.DATE_OPERATORS.stream().toList();
  private static List<FilterOperator> createdDateOperators = FilterOperator.CREATED_DATE_OPERATORS.stream().toList();

  private static FilterPeriodType[] filterPeriodTypes = FilterPeriodType.values();
  private static List<FilterPeriodType> currentFilterPeriodTypes = FilterPeriodType.PERIOD_TYPES_FOR_CURRENT_OPERATOR
      .stream().toList();

  private static SimpleDateFormat formatter = new SimpleDateFormat(DashboardFilter.DATE_FORMAT);

  public List<FilterOperator> getOperators() {
    return operators;
  }

  public FilterPeriodType[] getFilterPeriodTypes() {
    return filterPeriodTypes;
  }

  public void onChangeOperator(BaseFilter filter) {
    filter.setFromDate(null);
    filter.setToDate(null);
    filter.setFrom(null);
    filter.setTo(null);
    filter.setPeriodType(null);
    filter.setPeriods(null);
  }

  private String getCaseMessagePrefix(String field, int index) {
    return String.format(MESSAGE_PREFIX_PATTERN,
        FilterFieldFactory.findBy(Optional.ofNullable(field).orElse("")).getLabel(), index + 1);
  }

  private String getTaskMessagePrefix(String field, int index) {
    return String.format(MESSAGE_PREFIX_PATTERN,
        TaskFilterFieldFactory.findBy(Optional.ofNullable(field).orElse("")).getLabel(), index + 1);
  }

  private String getMessagePrefix(String field, int index, String widgetType) {
    if ("task" == StringUtils.lowerCase(widgetType)) {
      return getTaskMessagePrefix(field, index);
    }

    return getCaseMessagePrefix(field, index);
  }

  public String getWrongFormatMessage(String field, int index, String widgetType) {
    return String.join(": ", getMessagePrefix(field, index, widgetType),
        Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/wrongDateFormat"));
  }

  public List<FilterPeriodType> getCurrentFilterPeriodTypes() {
    return currentFilterPeriodTypes;
  }

  public void updateToDate(BaseFilter filter) {
    if (Optional.ofNullable(filter).map(BaseFilter::getToDate).isPresent()) {
      filter.setTo(formatter.format(filter.getToDate()));
    }
  }

  public void updateFromDate(BaseFilter filter) {
    if (Optional.ofNullable(filter).map(BaseFilter::getFromDate).isPresent()) {
      filter.setFrom(formatter.format(filter.getFromDate()));
    }
  }

  public List<FilterOperator> getCreatedDateOperators() {
    return createdDateOperators;
  }
}
