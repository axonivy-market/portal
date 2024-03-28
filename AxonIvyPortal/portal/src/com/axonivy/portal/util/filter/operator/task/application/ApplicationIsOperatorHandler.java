package com.axonivy.portal.util.filter.operator.task.application;

import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class ApplicationIsOperatorHandler {
  private static ApplicationIsOperatorHandler instance;

  public static ApplicationIsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new ApplicationIsOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    IFilterQuery filterQuery = query.where();
    for (String app : filter.getValues()) {
      final Optional<IApplication> appFindByName = IApplicationRepository.instance().findByName(app);
      if (appFindByName.isPresent()) {
        filterQuery.or().applicationId().isEqual(appFindByName.get().getId());
      }
    }

    return query;
  }
}