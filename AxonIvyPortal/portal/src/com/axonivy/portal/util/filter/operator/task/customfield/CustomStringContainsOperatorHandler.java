package com.axonivy.portal.util.filter.operator.task.customfield;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.util.PortalCustomFieldUtils;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class CustomStringContainsOperatorHandler {

  private static final String LIKE_FORMAT = "%%%s%%";

  private static CustomStringContainsOperatorHandler instance;

  public static CustomStringContainsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomStringContainsOperatorHandler();
    }
    return instance;
  }

  public TaskQuery buildContainsQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    TaskQuery query = TaskQuery.create(); // TODO filterfield correct? business and/or technical cases?
    if (filter.getFilterType() != DashboardColumnType.STANDARD && PortalCustomFieldUtils.isContainValidCmsPathAttribute(filter.getField(), filter.getFilterType())) {
      query.where().or(buildQueryForCustomFieldWithCmsValue(filter));
    } else {
      filter.getValues().forEach(text -> {
        TaskQuery subQuery = TaskQuery.create();
        subQuery.where().customField().stringField(filter.getField())
            .isLikeIgnoreCase(String.format(LIKE_FORMAT, text.toLowerCase()));
        query.where().or(subQuery);
      });
    }

    return query;
  }

  public TaskQuery buildNotContainsQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    TaskQuery query = TaskQuery.create();
    filter.getValues().forEach(text -> {
      TaskQuery subQuery = TaskQuery.create();
      subQuery.where().customField().stringField(filter.getField())
          .isNotLikeIgnoreCase(String.format(LIKE_FORMAT, text.toLowerCase()));
      query.where().and(subQuery);
    });
    return query;
  }

  public TaskQuery buildContainsQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomStringContainsOperatorHandler
        .getInstance().buildContainsQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }

  public TaskQuery buildNotContainsQueryByCase(DashboardFilter filter) {
    CaseQuery caseQuery = com.axonivy.portal.util.filter.operator.caze.customfield.CustomStringContainsOperatorHandler
        .getInstance().buildNotContainsQuery(filter);

    if (caseQuery == null) {
      return null;
    }

    TaskQuery query = TaskQuery.create();
    query.where().cases(caseQuery);
    return query;
  }
  
  public TaskQuery buildQueryForCustomFieldWithCmsValue(DashboardFilter filter) {
    List<String> keywordList = PortalCustomFieldUtils.getCmsValuesMatchingWithKeywordList(filter.getField(), filter.getFilterType(), filter.getValues());

    TaskQuery query = TaskQuery.create();
    IFilterQuery filterQuery = query.where();
    if (CollectionUtils.isEmpty(keywordList)) {
      // Using an incorrect condition to return empty result
      filterQuery.taskId().isNull().and().taskId().isNotNull();
      return query;
    }
    for (String keyword : keywordList) {
      filterQuery.or().customField().stringField(filter.getField()).isEqual(keyword);
    }
    return query;
  }
}
