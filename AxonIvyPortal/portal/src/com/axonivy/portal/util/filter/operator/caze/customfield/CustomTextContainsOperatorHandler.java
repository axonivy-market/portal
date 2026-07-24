package com.axonivy.portal.util.filter.operator.caze.customfield;

import static com.axonivy.portal.util.CaseQueryUtils.initCaseQuery;
import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class CustomTextContainsOperatorHandler {

  private static final String LIKE_FORMAT = "%%%s%%";

  private static CustomTextContainsOperatorHandler instance;

  public static CustomTextContainsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTextContainsOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildContainsQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    CaseQuery query = initCaseQuery(filter.getFilterType()); // TODO filterfield correct? business and/or technical cases?
    filter.getValues().forEach(text -> {
      CaseQuery subQuery = initCaseQuery(filter.getFilterType());
      subQuery.where().customField().textField(filter.getField())
          .isLikeIgnoreCase(String.format(LIKE_FORMAT, text.toLowerCase()));
      query.where().or(subQuery);
    });
    return query;
  }

  public CaseQuery buildNotContainsQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    CaseQuery query = initCaseQuery(filter.getFilterType());
    filter.getValues().forEach(text -> {
      CaseQuery subQuery = initCaseQuery(filter.getFilterType());
      subQuery.where().customField().textField(filter.getField())
          .isNotLikeIgnoreCase(String.format(LIKE_FORMAT, text.toLowerCase()));
      query.where().and(subQuery);
    });
    return query;
  }
}