package com.axonivy.portal.util.filter.operator.caze.description;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class DescriptionStartWithOperatorHandler {

  private static final String START_WTIH_FORMAT = "%s%%";

  private static DescriptionStartWithOperatorHandler instance;

  public static DescriptionStartWithOperatorHandler getInstance() {
    if (instance == null) {
      instance = new DescriptionStartWithOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildStartWithQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getTexts())) {
      return null;
    }
    CaseQuery query = CaseQuery.create();
    filter.getTexts().forEach(text -> {
      CaseQuery subQuery = CaseQuery.create();
      subQuery.where().description().isLikeIgnoreCase(String.format(START_WTIH_FORMAT, text.toLowerCase()));
      query.where().or(subQuery);
    });
    return query;
  }

  public CaseQuery buildNotStartWithQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getTexts())) {
      return null;
    }
    CaseQuery query = CaseQuery.create();
    filter.getTexts().forEach(text -> {
      CaseQuery subQuery = CaseQuery.create();
      subQuery.where().description().isNotLikeIgnoreCase(String.format(START_WTIH_FORMAT, text.toLowerCase()));
      query.where().and(subQuery);
    });
    return query;
  }
}
