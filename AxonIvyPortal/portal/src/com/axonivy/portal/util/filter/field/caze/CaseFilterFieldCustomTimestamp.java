package com.axonivy.portal.util.filter.field.caze;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.enums.dashboard.filter.FilterType;
import com.axonivy.portal.util.filter.field.FilterField;

import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CaseFilterFieldCustomTimestamp extends FilterField {

  public CaseFilterFieldCustomTimestamp() {}

  public CaseFilterFieldCustomTimestamp(ICustomFieldMeta customField) {
    super(customField.name());
  }

  public String getLabel() {
    return getName();
  }

  @Override
  public void initFilter(DashboardFilter filter) {
    filter.setType(FilterType.DATE);
  }

  @Override
  public CaseQuery generateFilterQuery(DashboardFilter filter) {
    return null; // TODO z1 implement
  }
}
