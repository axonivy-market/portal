package com.axonivy.portal.util.filter.field;

import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class FilterFieldDefault extends FilterField {

  public FilterFieldDefault() {
    super(BaseFilter.DEFAULT);
  }

  @Override
  public String getLabel() {
    return Ivy.cms()
        .co("/ch.ivy.addon.portalkit.ui.jsf/adminSettings/pleaseSelect");
  }

  @Override
  public void initFilter(DashboardFilter filter) {
    filter.setFilterField(null);
    filter.setField(null);
  }

  @Override
  public void addNewFilter(DashboardFilter filter) {
    initFilter(filter);
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return null;
  }

  @Override
  public TaskQuery generateFilterTaskQuery(DashboardFilter filter) {
    return null;
  }
}
