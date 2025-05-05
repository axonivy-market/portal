package com.axonivy.portal.util.statisticfilter.field.task;

import java.util.ArrayList;

import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.statisticfilter.operator.text.TextInOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskFilterFieldCanWorkOn extends FilterField{

  public TaskFilterFieldCanWorkOn() {
    super(StatisticConstants.CAN_WORK_ON);
  }

  @Override
  public String getLabel() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/canWorkOn");
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
    filter.setOperator(FilterOperator.CURRENT_USER);
    filter.setValues(new ArrayList<>());  
  }

  @Override
  public String generateTaskFilter(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case CURRENT_USER -> TextInOperatorHandler.getInstance().buildFilterWithoutValue(filter);
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
