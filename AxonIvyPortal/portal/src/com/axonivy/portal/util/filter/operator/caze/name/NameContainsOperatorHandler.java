package com.axonivy.portal.util.filter.operator.caze.name;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class NameContainsOperatorHandler {

  private static final String LIKE_FORMAT = "%%%s%%";

  private static NameContainsOperatorHandler instance;

  public static NameContainsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new NameContainsOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildContainsQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getTexts())) {
      return null;
    }
    CaseQuery query = CaseQuery.create();
    filter.getTexts().forEach(text -> {
      CaseQuery subQuery = CaseQuery.create();
      subQuery.where().name().isLikeIgnoreCase(String.format(LIKE_FORMAT, text.toLowerCase()));
      query.where().or(subQuery);
    });
    return query;
  }

  public CaseQuery buildNotContainsQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getTexts())) {
      return null;
    }
    CaseQuery query = CaseQuery.create();
    filter.getTexts().forEach(text -> {
      CaseQuery subQuery = CaseQuery.create();
      subQuery.where().name().isNotLikeIgnoreCase(String.format(LIKE_FORMAT, text.toLowerCase()));
      query.where().and(subQuery);
    });
    return query;
  }
}
