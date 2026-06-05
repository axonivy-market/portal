package com.axonivy.portal.util.filter.operator.task.caseid;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TechnicalCaseIdContainsOperatorHandler {

  private static final String LIKE_FORMAT = "%%%s%%";

  private static TechnicalCaseIdContainsOperatorHandler instance;

  public static TechnicalCaseIdContainsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new TechnicalCaseIdContainsOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildContainsQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    TaskQuery query = TaskQuery.create();
    filter.getValues().forEach(text -> {
      TaskQuery subQuery = TaskQuery.create();
      subQuery.where().caseId().isLikeIgnoreCase(String.format(LIKE_FORMAT, text.toLowerCase()));
      query.where().or(subQuery);
    });
    return query;
  }
}
