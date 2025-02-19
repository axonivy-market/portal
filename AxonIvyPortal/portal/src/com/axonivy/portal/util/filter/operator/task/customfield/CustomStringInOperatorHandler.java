package com.axonivy.portal.util.filter.operator.task.customfield;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class CustomStringInOperatorHandler {
  private static CustomStringInOperatorHandler instance;
  
  public static CustomStringInOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomStringInOperatorHandler();
    }
    return instance;
  }
  
  public TaskQuery buildInQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    
    TaskQuery query = TaskQuery.create();
    IFilterQuery filterQuery = query.where();
    for (String keyword : filter.getValues()) {
      filterQuery.or().customField().stringField(filter.getField()).isEqual(keyword);
    }
    return query;
  }
}
