package com.axonivy.portal.util.filter.operator.caze.customfield;
import static com.axonivy.portal.util.CaseQueryUtils.initCaseQuery;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class CustomStringInOperatorHandler {
private static CustomStringInOperatorHandler instance;
  
  public static CustomStringInOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomStringInOperatorHandler();
    }
    return instance;
  }
  
  public CaseQuery buildQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    
    CaseQuery query = initCaseQuery(filter.getFilterType());
    IFilterQuery filterQuery = query.where();
    for (String keyword : filter.getValues()) {
      filterQuery.or().customField().stringField(filter.getField()).isEqual(keyword);
    }
    return query;
  }
}
