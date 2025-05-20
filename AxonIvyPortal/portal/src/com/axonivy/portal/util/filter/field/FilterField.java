package com.axonivy.portal.util.filter.field;

import java.util.Objects;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public abstract class FilterField {

  protected String name;

  public FilterField() {
    super();
  }

  public FilterField(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public abstract String getLabel();

  public abstract void initFilter(DashboardFilter filter);

  public abstract void addNewFilter(DashboardFilter filter);

  public abstract CaseQuery generateFilterQuery(DashboardFilter filter);

  public abstract TaskQuery generateFilterTaskQuery(DashboardFilter filter);

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    FilterField other = (FilterField) obj;
    return Objects.equals(name, other.name);
  }
  
  public String generateTaskFilter(DashboardFilter filter) {
    return filter.getValue();
  }
  
  public String generateCaseFilter(DashboardFilter filter) {
    return filter.getValue();
  }

}
