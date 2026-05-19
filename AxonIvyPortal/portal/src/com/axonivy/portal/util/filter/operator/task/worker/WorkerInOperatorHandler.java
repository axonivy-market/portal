package com.axonivy.portal.util.filter.operator.task.worker;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class WorkerInOperatorHandler {
  private static WorkerInOperatorHandler instance;

  public static WorkerInOperatorHandler getInstance() {
    if (instance == null) {
      instance = new WorkerInOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildInQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    IFilterQuery filterQuery = query.where();
    for (String worker : filter.getValues()) {
      ISecurityMember member = Ivy.security().members().find(worker);
      if (member == null) {
        continue;
      }
      filterQuery.or().workerId().isEqual(member.getSecurityMemberId());
    }

    return query;
  }

  public TaskQuery buildNotInQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    IFilterQuery filterQuery = query.where();
    for (String worker : filter.getValues()) {
      ISecurityMember member = Ivy.security().members().find(worker);
      if (member == null) {
        continue;
      }
      filterQuery.and().workerId().isNotEqual(member.getSecurityMemberId());
    }

    return query;
  }
}
