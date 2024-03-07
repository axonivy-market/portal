package com.axonivy.portal.util.filter.field;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class FilterFieldDefault extends FilterField {

  public FilterFieldDefault() {}

  public FilterFieldDefault(String name) {
    super(name);
  }

  public String getLabel() {
    return getName();
  }

  @Override
  public void initFilter(DashboardFilter filter) {}

  @Override
  public void addNewFilter(DashboardFilter filter) {}

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return null;
  }

  @Override
  public TaskQuery generateFilterTaskQuery(DashboardFilter filter) {
    return null;
  }
}
