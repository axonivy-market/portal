package com.axonivy.portal.util.filter.operator.caze.application;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class ApplicationIsOperatorHandler {
  private static ApplicationIsOperatorHandler instance;

  public static ApplicationIsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new ApplicationIsOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    IFilterQuery filterQuery = query.where();
    for (String app : filter.getValues()) {
      var appFindByName = IApplicationRepository.instance().findReleasedByName(app);
      if (appFindByName != null) {
        filterQuery.or().applicationId().isEqual(appFindByName.getId());
      }
    }

    return query;
  }
}