package com.axonivy.portal.util.filter.field.task;

import java.util.ArrayList;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.operator.task.state.StateInOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskFilterFieldState extends FilterField {

  public TaskFilterFieldState() {
    super(DashboardStandardTaskColumn.STATE.getField());
  }

  @Override
  public String getLabel() {
    if (org.apache.commons.lang3.StringUtils.isBlank(this.label)) {
      return DashboardStandardTaskColumn.STATE.getLabel();
    }
    return this.label;
  }

  @Override
  public void initFilter(DashboardFilter filter) {
    filter.setFilterField(this);
    filter.setFilterType(DashboardColumnType.STANDARD);
    filter.setField(getName());
    filter.setFilterFormat(null);
    if (this.label == null) {
      setLabel(filter.getLabel());
    }
  }

  @Override
  public void addNewFilter(DashboardFilter filter) {
    initFilter(filter);
    filter.setOperator(FilterOperator.IN);
    filter.setValues(new ArrayList<>());
  }

  @Override
  public TaskQuery generateFilterTaskQuery(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case IN -> StateInOperatorHandler.getInstance().buildStateInQuery(filter);
      default -> null;
    };
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return null;
  }
}