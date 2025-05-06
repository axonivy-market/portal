package com.axonivy.portal.util.statisticfilter.field.task;

import java.util.ArrayList;

import com.axonivy.portal.dto.statistic.StatisticFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.statisticfilter.field.FilterField;
import com.axonivy.portal.util.statisticfilter.operator.text.TextInOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;

public class TaskFilterFieldResponsible extends FilterField {

  public TaskFilterFieldResponsible() {
    super(DashboardStandardTaskColumn.RESPONSIBLE.getField());
  }

  @Override
  public String getLabel() {
    return DashboardStandardTaskColumn.RESPONSIBLE.getLabel();
  }

  @Override
  public void initFilter(StatisticFilter filter) {
    filter.setFilterField(this);
    filter.setFilterType(DashboardColumnType.STANDARD);
    filter.setField(getName());
    
  }

  @Override
  public void addNewFilter(StatisticFilter filter) {
    initFilter(filter);
    filter.setOperator(FilterOperator.IN);
    filter.setValues(new ArrayList<>());
  }

  @Override
  public String generateStringFilter(StatisticFilter filter) {
    return switch (filter.getOperator()) {
      case IN -> TextInOperatorHandler.getInstance().buildFilter(filter);
      default -> null;
    };
  }

}
