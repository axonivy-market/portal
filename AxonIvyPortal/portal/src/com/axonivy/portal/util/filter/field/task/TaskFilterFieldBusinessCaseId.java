package com.axonivy.portal.util.filter.field.task;

import java.util.ArrayList;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.operator.task.caseid.BusinessCaseIdContainsOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskFilterFieldBusinessCaseId extends FilterField {

  public TaskFilterFieldBusinessCaseId() {
    super(DashboardStandardTaskColumn.BUSINESS_CASE_ID.getField());
  }

  @Override
  public String getLabel() {
    if (org.apache.commons.lang3.StringUtils.isBlank(this.label)) {
      return DashboardStandardTaskColumn.BUSINESS_CASE_ID.getLabel();
    }
    return this.label;
  }

  @Override
  public void initFilter(DashboardFilter filter) {
    filter.setFilterField(this);
    filter.setFilterType(DashboardColumnType.STANDARD);
    filter.setField(getName());
    if (this.label == null) {
      setLabel(filter.getLabel());
    }
  }

  @Override
  public void addNewFilter(DashboardFilter filter) {
    initFilter(filter);
    filter.setOperator(FilterOperator.CONTAINS);
    filter.setValues(new ArrayList<>());
  }

  @Override
  public TaskQuery generateFilterTaskQuery(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case CONTAINS -> BusinessCaseIdContainsOperatorHandler.getInstance().buildContainsQuery(filter);
      default -> null;
    };
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return null;
  }
}
