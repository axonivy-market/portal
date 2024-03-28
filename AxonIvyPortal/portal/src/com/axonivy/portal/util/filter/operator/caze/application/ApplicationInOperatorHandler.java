package com.axonivy.portal.util.filter.operator.caze.application;

import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class ApplicationInOperatorHandler {
  private static ApplicationInOperatorHandler instance;

  public static ApplicationInOperatorHandler getInstance() {
    if (instance == null) {
      instance = new ApplicationInOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    IFilterQuery filterQuery = query.where();

    boolean hasApp = false;
    for (String app : filter.getValues()) {
      final Optional<IApplication> appFindByName = IApplicationRepository.instance().findByName(app);
      if (appFindByName.isPresent()) {
        filterQuery.or().applicationId().isEqual(appFindByName.get().getId());
        hasApp = true;
      }
    }

    return hasApp ? query : null;
  }
}