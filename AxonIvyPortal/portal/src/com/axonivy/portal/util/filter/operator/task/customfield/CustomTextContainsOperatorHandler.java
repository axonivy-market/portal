package com.axonivy.portal.util.filter.operator.task.customfield;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class CustomTextContainsOperatorHandler {

  private static final String LIKE_FORMAT = "%%%s%%";

  private static CustomTextContainsOperatorHandler instance;

  public static CustomTextContainsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomTextContainsOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildContainsQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    TaskQuery query = TaskQuery.create(); // TODO filterfield correct? business and/or technical cases?
    filter.getValues().forEach(text -> {
      TaskQuery subQuery = TaskQuery.create();
      subQuery.where().customField().textField(filter.getField())
          .isLikeIgnoreCase(String.format(LIKE_FORMAT, text.toLowerCase()));
      query.where().or(subQuery);
    });
    return query;
  }

  public TaskQuery buildNotContainsQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    TaskQuery query = TaskQuery.create();
    filter.getValues().forEach(text -> {
      TaskQuery subQuery = TaskQuery.create();
      subQuery.where().customField().textField(filter.getField())
          .isNotLikeIgnoreCase(String.format(LIKE_FORMAT, text.toLowerCase()));
      query.where().and(subQuery);
    });
    return query;
  }

  public TaskQuery buildContainsQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomTextContainsOperatorHandler
        .getInstance().buildContainsQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }

  public TaskQuery buildNotContainsQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomTextContainsOperatorHandler
        .getInstance().buildNotContainsQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }
}