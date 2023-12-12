package com.axonivy.portal.util.filter.operator.caze.category;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class CategoryInOperatorHandler {
  private static CategoryInOperatorHandler instance;

  public static CategoryInOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CategoryInOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildInQuery(DashboardFilter filter) {
    return buildQuery(filter, false);
  }

  public CaseQuery buildNotInQuery(DashboardFilter filter) {
    return buildQuery(filter, true);
  }

  public CaseQuery buildQuery(DashboardFilter filter, boolean isNot) {
    if (CollectionUtils.isEmpty(filter.getTexts())) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    IFilterQuery filterQuery = query.where();
    for (String category : filter.getTexts()) {
      if (isNot) {
        filterQuery.and().category().isNotEqual(category);
      } else {
        filterQuery.or().category().isEqual(category);
      }
    }

    return query;
  }
}