package com.axonivy.portal.util.filter.operator.task.id;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class IdContainsOperatorHandler {

  private static final String LIKE_FORMAT = "%%%s%%";
  
  private static IdContainsOperatorHandler instance;
  
  public static IdContainsOperatorHandler getInstance() {
    if(instance == null) {
      instance = new IdContainsOperatorHandler();
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
      subQuery.where().taskId().isLikeIgnoreCase(String.format(LIKE_FORMAT, text.toLowerCase()));
      query.where().or(subQuery);
    });
    return query;
  }

}
