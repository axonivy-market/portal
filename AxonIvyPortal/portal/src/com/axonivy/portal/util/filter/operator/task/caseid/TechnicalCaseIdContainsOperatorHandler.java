package com.axonivy.portal.util.filter.operator.task.caseid;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TechnicalCaseIdContainsOperatorHandler {

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
    for (String value : filter.getValues()) {
      if (!NumberUtils.isParsable(value)) {
        continue;
      }
      long id = Long.parseLong(value.trim());
      TaskQuery subQuery = TaskQuery.create();
      subQuery.where().caseId().isEqual(id);
      query.where().or(subQuery);
    }
    return query;
  }
}
