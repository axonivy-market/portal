package com.axonivy.portal.util.filter.field;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

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
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return null;
  }
}
