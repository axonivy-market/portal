package com.axonivy.portal.util.filter.field.caze;

import java.util.ArrayList;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterOperator;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.operator.caze.creator.CreatorCurrentUserOperatorHandler;
import com.axonivy.portal.util.filter.operator.caze.creator.CreatorInOperatorHandler;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CaseFilterFieldCreator extends FilterField {

  public CaseFilterFieldCreator() {
    super(DashboardStandardCaseColumn.CREATOR.getField());
  }

  @Override
  public String getLabel() {
    if (org.apache.commons.lang3.StringUtils.isBlank(this.label)) {
      return DashboardStandardCaseColumn.CREATOR.getLabel();
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
    filter.setOperator(FilterOperator.CURRENT_USER);
    filter.setValues(new ArrayList<>());
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return switch (filter.getOperator()) {
      case IN -> CreatorInOperatorHandler.getInstance().buildInQuery(filter);
      case NOT_IN -> CreatorInOperatorHandler.getInstance().buildNotInQuery(filter);
      case CURRENT_USER -> CreatorCurrentUserOperatorHandler.getInstance().buildQuery();
      default -> null;
    };
  }
  
  @Override
  public TaskQuery generateFilterTaskQuery(DashboardFilter filter) {
    return null;
  }

}