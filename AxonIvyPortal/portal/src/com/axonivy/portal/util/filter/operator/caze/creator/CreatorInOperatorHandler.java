package com.axonivy.portal.util.filter.operator.caze.creator;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class CreatorInOperatorHandler {
  private static CreatorInOperatorHandler instance;

  public static CreatorInOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CreatorInOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildInQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    IFilterQuery filterQuery = query.where();
    for (String creator : filter.getValues()) {
      filterQuery.or().creatorUserName().isEqual(creator.replace("#", ""));
    }

    return query;
  }

  public CaseQuery buildNotInQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }

    CaseQuery query = CaseQuery.create();
    IFilterQuery filterQuery = query.where();
    for (String creator : filter.getValues()) {
      filterQuery.and().creatorUserName().isNotEqual(creator.replace("#", ""));
    }

    return query;
  }
}
