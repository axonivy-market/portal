package com.axonivy.portal.util.filter.operator.caze.customfield;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomStringStartWithOperatorHandler {

  private static final String START_WTIH_FORMAT = "%s%%";

  private static CustomStringStartWithOperatorHandler instance;

  public static CustomStringStartWithOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomStringStartWithOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildStartWithQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    CaseQuery query = CaseQuery.create();
    filter.getValues().forEach(text -> {
      CaseQuery subQuery = CaseQuery.create();
      subQuery.where().customField().stringField(filter.getField())
          .isLikeIgnoreCase(String.format(START_WTIH_FORMAT, text.toLowerCase()));
      query.where().or(subQuery);
    });
    return query;
  }

  public CaseQuery buildNotStartWithQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    CaseQuery query = CaseQuery.create();
    filter.getValues().forEach(text -> {
      CaseQuery subQuery = CaseQuery.create();
      subQuery.where().customField().stringField(filter.getField())
          .isNotLikeIgnoreCase(String.format(START_WTIH_FORMAT, text.toLowerCase()));
      query.where().and(subQuery);
    });
    return query;
  }
}
