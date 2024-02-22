package com.axonivy.portal.util.filter.operator.task.responsible;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class ResponsibleInOperatorHandler {
  private static ResponsibleInOperatorHandler instance;
  private static final String HASH = "#";

  public static ResponsibleInOperatorHandler getInstance() {
    if (instance == null) {
      instance = new ResponsibleInOperatorHandler();
    }
    return instance;
  }

  /**
   * TongVu | NOTE
   * Right query
   *  SELECT  
      FROM IWA_Task  
      LEFT JOIN IWA_SecurityMember ACTIVATOR ON Column(IWA_Task.ActivatorId) = Column(IWA_SecurityMember.SecurityMemberId)
      WHERE Column(ACTIVATOR.MemberName) = #demo 

     Problem query
      SELECT  
      FROM IWA_Task  
      LEFT JOIN IWA_SecurityMember ACTIVATOR ON Column(IWA_Task.ActivatorId) = Column(IWA_SecurityMember.SecurityMemberId)
      WHERE Column(ACTIVATOR.MemberName) = demo 
      
      I don't know why we need to replace "#" with "" but the code actually rendered the query below and it 
      make this responsible field didn't work.
      
      So I just temporarily change this like this, will discuss with a Nam Mai later
   * */

  public TaskQuery buildInQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    IFilterQuery filterQuery = query.where();
    for (String activator : filter.getValues()) {
      filterQuery.or().activatorName().isEqual(HASH + activator);
    }

    return query;
  }

  public TaskQuery buildNotInQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    IFilterQuery filterQuery = query.where();
    for (String activator : filter.getValues()) {
      filterQuery.and().activatorName().isNotEqual(HASH + activator);
    }

    return query;
  }
}
