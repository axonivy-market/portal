package com.axonivy.portal.util.filter.operator.caze.customfield;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomStringIsOperatorHandler {

  private static CustomStringIsOperatorHandler instance;

  public static CustomStringIsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomStringIsOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildIsQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    CaseQuery query = CaseQuery.create();
    filter.getValues().forEach(text -> {
      CaseQuery subQuery = CaseQuery.create();
      subQuery.where().customField().stringField(filter.getField()).isEqualIgnoreCase(text.toLowerCase());
      query.where().or(subQuery);
    });
    return query;
  }

  public CaseQuery buildIsNotQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    CaseQuery query = CaseQuery.create();
    filter.getValues().forEach(text -> {
      CaseQuery subQuery = CaseQuery.create();
      subQuery.where().customField().stringField(filter.getField()).isNotEqualIgnoreCase(text.toLowerCase());
      query.where().and(subQuery);
    });
    return query;
  }
}
