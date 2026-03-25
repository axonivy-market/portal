package com.axonivy.portal.util.filter.operator.task.application;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class ApplicationInOperatorHandler {
  private static ApplicationInOperatorHandler instance;

  public static ApplicationInOperatorHandler getInstance() {
    if (instance == null) {
      instance = new ApplicationInOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    IFilterQuery filterQuery = query.where();

    boolean hasApp = false;
    for (String app : filter.getValues()) {
      var appFindByName = IApplicationRepository.instance().findReleasedByName(app);
      if (appFindByName != null) {
        filterQuery.or().applicationId().isEqual(appFindByName.getId());
        hasApp = true;
      }
    }

    return hasApp ? query : null;
  }
}