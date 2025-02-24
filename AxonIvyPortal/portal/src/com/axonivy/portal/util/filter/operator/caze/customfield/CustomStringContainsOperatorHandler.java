package com.axonivy.portal.util.filter.operator.caze.customfield;

import java.util.List;


import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.util.PortalCustomFieldUtils;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.CaseQuery.IFilterQuery;

public class CustomStringContainsOperatorHandler {

  private static final String LIKE_FORMAT = "%%%s%%";

  private static CustomStringContainsOperatorHandler instance;

  public static CustomStringContainsOperatorHandler getInstance() {
    if (instance == null) {
      instance = new CustomStringContainsOperatorHandler();
    }
    return instance;
  }

  public CaseQuery buildContainsQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    CaseQuery query = CaseQuery.create(); // TODO filterfield correct? business and/or technical cases?
    filter.getValues().forEach(text -> {
      CaseQuery subQuery = CaseQuery.create();
      subQuery.where().customField().stringField(filter.getField())
          .isLikeIgnoreCase(String.format(LIKE_FORMAT, text.toLowerCase()));
      query.where().or(subQuery);
    });
    if (PortalCustomFieldUtils.isSupportMultiLanguageCaseField(filter.getField())) {
      CaseQuery addingQuery = buildQueryForCustomFieldWithCmsValue(filter);
      query.where().or(addingQuery);
    }
    return query;
  }

  public CaseQuery buildNotContainsQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    CaseQuery query = CaseQuery.create();
    filter.getValues().forEach(text -> {
      CaseQuery subQuery = CaseQuery.create();
      subQuery.where().customField().stringField(filter.getField())
          .isNotLikeIgnoreCase(String.format(LIKE_FORMAT, text.toLowerCase()));
      query.where().and(subQuery);
    });
    return query;
  }
  
  public CaseQuery buildQueryForCustomFieldWithCmsValue(DashboardFilter filter) {
    List<String> keywordList = PortalCustomFieldUtils.getCmsValuesMatchingWithKeywordList(filter.getField(), DashboardColumnType.CUSTOM_CASE, filter.getValues());

    CaseQuery query = CaseQuery.create();
    IFilterQuery filterQuery = query.where();
    if (CollectionUtils.isEmpty(keywordList)) {
      // Using an incorrect condition to return empty result
      filterQuery.caseId().isNull().and().caseId().isNotNull();
      return query;
    }
    for (String keyword : keywordList) {
      filterQuery.or().customField().stringField(filter.getField()).isEqual(keyword);
    }
    return query;
  }
}
