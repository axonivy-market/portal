package com.axonivy.portal.util.filter.operator.caze.id;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class IdContainsOperatorHandler {

  private static final String LIKE_FORMAT = "%%%s%%";
  
  private static IdContainsOperatorHandler instance;
  
  public static IdContainsOperatorHandler getInstance() {
    if(instance == null) {
      instance = new IdContainsOperatorHandler();
    }
    
    return instance;
  }

  public CaseQuery buildContainsQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getTexts())) {
      return null;
    }
    CaseQuery query = CaseQuery.create();
    filter.getTexts().forEach(text -> {
      CaseQuery subQuery = CaseQuery.create();
      subQuery.where().caseId().isLikeIgnoreCase(String.format(LIKE_FORMAT, text.toLowerCase()));
      query.where().or(subQuery);
    });
    return query;
  }

}
