package com.axonivy.portal.util.statisticfilter.field.caze;

import java.util.ArrayList;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.statisticfilter.operator.text.TextInOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CaseFilterFieldState extends FilterField {

  public CaseFilterFieldState() {
    super(DashboardStandardCaseColumn.STATE.getField());
  }

  @Override
  public String getLabel() {
    return DashboardStandardCaseColumn.STATE.getLabel();
  }

  @Override
  public void initFilter(DashboardFilter filter) {
    filter.setFilterField(this);
    filter.setFilterType(DashboardColumnType.STANDARD);
    filter.setField(getName());
  }

  @Override
  public void addNewFilter(DashboardFilter filter) {
    initFilter(filter);
    filter.setOperator(FilterOperator.IN);
    filter.setValues(new ArrayList<>());
  }

  public String generateStringFilter(DashboardFilter filter) {
    return switch (filter.getOperator()) {
    case IN -> TextInOperatorHandler.getInstance().buildFilter(filter);
    default -> null;
    };
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TaskQuery generateFilterTaskQuery(DashboardFilter filter) {
    // TODO Auto-generated method stub
    return null;
  }

}
