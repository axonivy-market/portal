package com.axonivy.portal.util.statisticfilter.field.task;

import java.util.ArrayList;

import com.axonivy.portal.dto.statistic.StatisticFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.statisticfilter.field.FilterField;
import com.axonivy.portal.util.statisticfilter.operator.TextInOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;

public class TaskFilterFieldCategory extends FilterField {

  public TaskFilterFieldCategory() {
    super(DashboardStandardTaskColumn.CATEGORY.getField());
  }

  @Override
  public String getLabel() {
    return DashboardStandardTaskColumn.CATEGORY.getLabel();
  }

  // @Override
  // public TaskQuery generateFilterTaskQuery(DashboardFilter filter) {
  // return switch (filter.getOperator()) {
  // case IN -> CategoryInOperatorHandler.getInstance().buildInQuery(filter);
  // case NOT_IN -> CategoryInOperatorHandler.getInstance().buildNotInQuery(filter);
  // case CONTAINS -> CategoryContainsOperatorHandler.getInstance().buildContainsQuery(filter);
  // case NOT_CONTAINS -> CategoryContainsOperatorHandler.getInstance().buildNotContainsQuery(filter);
  // case NO_CATEGORY -> CategoryNoCategoryOperatorHandler.getInstance().buildQuery();
  // default -> null;
  // };
  // }
  //
  // @Override
  // public CaseQuery generateFilterQuery(DashboardFilter filter) {
  // return null;
  // }

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
      case IN -> TextInOperatorHandler.getInstance().buildStringFilter(filter);
      default -> null;
    };
  }
}
