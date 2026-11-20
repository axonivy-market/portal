package com.axonivy.portal.util.filter.field.task;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.operator.task.technicalstate.TechnicalStateInOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskFilterFieldTechnicalState extends FilterField {

  public TaskFilterFieldTechnicalState() {
    super(BaseFilter.TECHNICAL_STATE);
  }

  @Override
  public boolean isColumnDependent() {
    return false;
  }

  @Override
  public String getLabel() {
    return StringUtils.isBlank(label) ? Ivy.cms().co("/Labels/TechnicalState") : label;
  }

  @Override
  public void initFilter(DashboardFilter filter) {
    filter.setFilterField(this);
    filter.setFilterType(DashboardColumnType.STANDARD);
    filter.setField(getName());
    filter.setFilterFormat(null);
    if (label == null) {
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
      case IN -> TechnicalStateInOperatorHandler.getInstance().buildStateInQuery(filter);
      default -> null;
    };
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return null;
  }
}
