package com.axonivy.portal.util.filter.operator.caze.customfield;
import static com.axonivy.portal.util.CaseQueryUtils.initCaseQuery;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomTextIsOperatorHandler {

  private static CustomTextIsOperatorHandler instance;

  public static CustomTextIsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTextIsOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildIsQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    CaseQuery query = initCaseQuery(filter.getFilterType());
    filter.getValues().forEach(text -> {
      CaseQuery subQuery = initCaseQuery(filter.getFilterType());
      subQuery.where().customField().textField(filter.getField()).isEqualIgnoreCase(text.toLowerCase());
      query.where().or(subQuery);
    });
    return query;
  }

  public CaseQuery buildIsNotQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    CaseQuery query = initCaseQuery(filter.getFilterType());
    filter.getValues().forEach(text -> {
      CaseQuery subQuery = initCaseQuery(filter.getFilterType());
      subQuery.where().customField().textField(filter.getField()).isNotEqualIgnoreCase(text.toLowerCase());
      query.where().and(subQuery);
    });
    return query;
  }
}