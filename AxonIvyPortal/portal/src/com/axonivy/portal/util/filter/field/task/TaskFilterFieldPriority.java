package com.axonivy.portal.util.filter.field.task;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.operator.task.priority.PriorityInOperatorHandler;
import com.axonivy.portal.util.statisticfilter.operator.text.TextInOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskFilterFieldPriority extends FilterField{
  
  public TaskFilterFieldPriority() {
    super(DashboardStandardTaskColumn.PRIORITY.getField());
  }

  @Override
  public String getLabel() {
    if (StringUtils.isBlank(this.label)) {
      return DashboardStandardTaskColumn.PRIORITY.getLabel();
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
    case IN -> PriorityInOperatorHandler.getInstance().buildPriorityInQuery(filter);
    default -> null;
    };
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return null;
  }
  
  @Override
  public String generateTaskFilter(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case IN -> TextInOperatorHandler.getInstance().buildFilter(filter);
      default -> null;
    };
  }
}
