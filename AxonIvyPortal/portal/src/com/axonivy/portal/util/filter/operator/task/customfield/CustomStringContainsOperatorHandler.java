package com.axonivy.portal.util.filter.operator.task.customfield;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.google.common.collect.Iterables;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.service.WidgetFilterService;
import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery.IFilterQuery;

public class CustomStringContainsOperatorHandler {

  private static final String LIKE_FORMAT = "%%%s%%";
  private static final String CMS_PATH = "CmsPath";
  private static final String CMS_PATH_PATTERN_TASK = "/CustomFields/Tasks/([^/]+)/Values/([^/]+)";
  private static final String CMS_PATH_PATTERN_CASE = "/CustomFields/Cases/([^/]+)/Values/([^/]+)";

  private static CustomStringContainsOperatorHandler instance;
  private WidgetFilterService widgetFilterService = WidgetFilterService.getInstance();

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
    if (filter.getFilterType() != DashboardColumnType.STANDARD && widgetFilterService.isContainValidCmsPathAttribute(filter.getField(), filter.getFilterType())) {
      return buildInQueryForCustomFieldWithCmsValues(filter);
    }
    filter.getValues().forEach(text -> {
      TaskQuery subQuery = TaskQuery.create();
      subQuery.where().customField().stringField(filter.getField())
          .isLikeIgnoreCase(String.format(LIKE_FORMAT, text.toLowerCase()));
      query.where().or(subQuery);
    });
    return query;
  }
  
  public boolean isContainValidCmsPathAttribute(String field, DashboardColumnType type) {
    Set<ICustomFieldMeta> customFieldMetaList = type == DashboardColumnType.CUSTOM ? ICustomFieldMeta.tasks() : ICustomFieldMeta.cases();
    for (ICustomFieldMeta customField : customFieldMetaList) {
        if (customField.name().equals(field)) {
            String cmsPath = customField.attribute(CMS_PATH);
            if (cmsPath != null) {
                cmsPath = cmsPath + "/" + field;
                return cmsPath.matches(type == DashboardColumnType.CUSTOM ? CMS_PATH_PATTERN_TASK : CMS_PATH_PATTERN_CASE);
            }
            return false; // CmsPath attribute is null
        }
    }
    return false;
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
  
  public TaskQuery buildInQueryForCustomFieldWithCmsValues(DashboardFilter filter) {
    List<String> keywordList = widgetFilterService.getCmsValuesMatchingWithKeywordList(filter.getField(), filter.getFilterType(), filter.getValues());
    if (CollectionUtils.isEmpty(keywordList)) {
      return null;
    }
    
    TaskQuery query = TaskQuery.create();
    IFilterQuery filterQuery = query.where();
    for (String keyword : keywordList) {
      filterQuery.or().customField().stringField(filter.getField()).isEqual(keyword);
    }
    return query;
  }
}
