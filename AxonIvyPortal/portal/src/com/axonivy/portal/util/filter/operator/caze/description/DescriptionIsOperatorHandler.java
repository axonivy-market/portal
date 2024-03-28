package com.axonivy.portal.util.filter.operator.caze.description;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class DescriptionIsOperatorHandler {

  private static final String EQUALS_FORMAT = "%s";

  private static DescriptionIsOperatorHandler instance;

  public static DescriptionIsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new DescriptionIsOperatorHandler();
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
      subQuery.where().description().isLikeIgnoreCase(String.format(EQUALS_FORMAT, text.toLowerCase()));
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
      subQuery.where().description().isNotLikeIgnoreCase(String.format(EQUALS_FORMAT, text.toLowerCase()));
      query.where().and(subQuery);
    });
    return query;
  }
}
