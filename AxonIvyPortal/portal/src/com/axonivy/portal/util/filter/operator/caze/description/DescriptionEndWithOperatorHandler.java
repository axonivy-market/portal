package com.axonivy.portal.util.filter.operator.caze.description;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class DescriptionEndWithOperatorHandler {

  private static final String END_WTIH_FORMAT = "%%%s";

  private static DescriptionEndWithOperatorHandler instance;

  public static DescriptionEndWithOperatorHandler getInstance() {
    if (instance == null) {
      instance = new DescriptionEndWithOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildEndWithQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getTexts())) {
      return null;
    }
    CaseQuery query = CaseQuery.create();
    filter.getTexts().forEach(text -> {
      CaseQuery subQuery = CaseQuery.create();
      subQuery.where().description().isLikeIgnoreCase(String.format(END_WTIH_FORMAT, text.toLowerCase()));
      query.where().or(subQuery);
    });
    return query;
  }

  public CaseQuery buildNotEndWithQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getTexts())) {
      return null;
    }
    CaseQuery query = CaseQuery.create();
    filter.getTexts().forEach(text -> {
      CaseQuery subQuery = CaseQuery.create();
      subQuery.where().description().isNotLikeIgnoreCase(String.format(END_WTIH_FORMAT, text.toLowerCase()));
      query.where().and(subQuery);
    });
    return query;
  }
}
