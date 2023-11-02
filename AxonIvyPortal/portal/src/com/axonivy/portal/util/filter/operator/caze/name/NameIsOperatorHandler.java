package com.axonivy.portal.util.filter.operator.caze.name;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class NameIsOperatorHandler {

  private static NameIsOperatorHandler instance;

  public static NameIsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new NameIsOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildIsQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getTexts())) {
      return null;
    }
    CaseQuery query = CaseQuery.create();
    filter.getTexts().forEach(text -> {
      CaseQuery subQuery = CaseQuery.create();
      subQuery.where().name().isEqualIgnoreCase(text.toLowerCase());
      query.where().or(subQuery);
    });
    return query;
  }

  public CaseQuery buildIsNotQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getTexts())) {
      return null;
    }
    CaseQuery query = CaseQuery.create();
    filter.getTexts().forEach(text -> {
      CaseQuery subQuery = CaseQuery.create();
      subQuery.where().name().isNotEqualIgnoreCase(text.toLowerCase());
      query.where().and(subQuery);
    });
    return query;
  }
}
