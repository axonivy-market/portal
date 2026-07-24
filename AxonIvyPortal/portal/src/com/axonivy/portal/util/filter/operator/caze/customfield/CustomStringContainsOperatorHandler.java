package com.axonivy.portal.util.filter.operator.caze.customfield;

import static com.axonivy.portal.util.CaseQueryUtils.initCaseQuery;

import java.util.List;


import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.validator.internal.util.stereotypes.ThreadSafe;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.CaseQueryUtils;

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
    CaseQuery query = initCaseQuery(filter.getFilterType());
    filter.getValues().forEach(text -> {
      CaseQuery subQuery = initCaseQuery(filter.getFilterType());
      subQuery.where().customField().stringField(filter.getField())
          .isLikeIgnoreCase(String.format(LIKE_FORMAT, text.toLowerCase()));
      query.where().or(subQuery);
    });

    if (PortalCustomFieldUtils.isSupportMultiLanguageCaseField(filter.getField())) {
      List<String> keywordList = PortalCustomFieldUtils.getCmsValuesMatchingWithKeywordList(filter.getField(),
          filter.getFilterType(), filter.getValues());
      if (!keywordList.isEmpty()) {
        CaseQuery addingQuery = buildQueryForCustomFieldWithCmsValue(filter, keywordList);
        query.where().or(addingQuery);
      }
    }

    return query;
  }

  public CaseQuery buildNotContainsQuery(DashboardFilter filter) {
    if (CollectionUtils.isEmpty(filter.getValues())) {
      return null;
    }
    CaseQuery query = initCaseQuery(filter.getFilterType());
    filter.getValues().forEach(text -> {
      CaseQuery subQuery = initCaseQuery(filter.getFilterType());
      subQuery.where().customField().stringField(filter.getField())
          .isNotLikeIgnoreCase(String.format(LIKE_FORMAT, text.toLowerCase()));
      query.where().and(subQuery);
    });
    return query;
  }
  
  public CaseQuery buildQueryForCustomFieldWithCmsValue(DashboardFilter filter, List<String> keywordList) {

    CaseQuery query = initCaseQuery(filter.getFilterType());
    IFilterQuery filterQuery = query.where();

    for (String keyword : keywordList) {
      filterQuery.or().customField().stringField(filter.getField()).isEqual(keyword);
    }
    return query;
  }

}
