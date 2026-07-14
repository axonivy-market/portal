package com.axonivy.portal.util.filter.operator.caze.customfield;

import static com.axonivy.portal.util.CaseQueryUtils.initCaseQuery;
import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomStringEndWithOperatorHandler {

  private static final String END_WTIH_FORMAT = "%%%s";

  private static CustomStringEndWithOperatorHandler instance;

  public static CustomStringEndWithOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomStringEndWithOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildEndWithQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    CaseQuery query = initCaseQuery(filter.getFilterType());
    filter.getValues().forEach(text -> {
      CaseQuery subQuery = initCaseQuery(filter.getFilterType());
      subQuery.where().customField().stringField(filter.getField())
          .isLikeIgnoreCase(String.format(END_WTIH_FORMAT, text.toLowerCase()));
      query.where().or(subQuery);
    });
    return query;
  }

  public CaseQuery buildNotEndWithQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    CaseQuery query = initCaseQuery(filter.getFilterType());
    filter.getValues().forEach(text -> {
      CaseQuery subQuery = initCaseQuery(filter.getFilterType());
      subQuery.where().customField().stringField(filter.getField())
          .isNotLikeIgnoreCase(String.format(END_WTIH_FORMAT, text.toLowerCase()));
      query.where().and(subQuery);
    });
    return query;
  }
}
