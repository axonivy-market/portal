package com.axonivy.portal.util.filter.field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.util.filter.field.task.TaskFilterFieldApplication;
import com.axonivy.portal.util.filter.field.task.TaskFilterFieldCategory;
import com.axonivy.portal.util.filter.field.task.TaskFilterFieldCreatedDate;
import com.axonivy.portal.util.filter.field.task.TaskFilterFieldDescription;
import com.axonivy.portal.util.filter.field.task.TaskFilterFieldExpiryDate;
import com.axonivy.portal.util.filter.field.task.TaskFilterFieldFinishedDate;
import com.axonivy.portal.util.filter.field.task.TaskFilterFieldId;
import com.axonivy.portal.util.filter.field.task.TaskFilterFieldName;
import com.axonivy.portal.util.filter.field.task.TaskFilterFieldPriority;
import com.axonivy.portal.util.filter.field.task.TaskFilterFieldResponsible;
import com.axonivy.portal.util.filter.field.task.TaskFilterFieldState;
import com.axonivy.portal.util.filter.field.task.custom.TaskFilterFieldCustomNumber;
import com.axonivy.portal.util.filter.field.task.custom.TaskFilterFieldCustomString;
import com.axonivy.portal.util.filter.field.task.custom.TaskFilterFieldCustomText;
import com.axonivy.portal.util.filter.field.task.custom.TaskFilterFieldCustomTimestamp;
import com.axonivy.portal.util.filter.field.task.custom.caze.TaskFilterCaseFieldCustomNumber;
import com.axonivy.portal.util.filter.field.task.custom.caze.TaskFilterCaseFieldCustomString;
import com.axonivy.portal.util.filter.field.task.custom.caze.TaskFilterCaseFieldCustomText;
import com.axonivy.portal.util.filter.field.task.custom.caze.TaskFilterCaseFieldCustomTimestamp;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;

public class TaskFilterFieldFactory {

  private static final Map<String, FilterField> STANDARD_FILTER_FIELD = new HashMap<>();
  private static final Map<String, CustomFilterField> CUSTOM_FILTER_FIELD = new HashMap<>();
  private static final Map<String, CustomFilterField> CUSTOM_CASE_FILTER_FIELD = new HashMap<>();
  private static final Map<String, Map<String, FilterField>> WIDGET_FILTER_FIELD = new HashMap<>();
  
  static {
    STANDARD_FILTER_FIELD.putAll(initMapFilterField());
  }
  
  public static Map<String, FilterField> initMapFilterField() {
    Map<String, FilterField> standarFieldMap = new HashMap<>();
    standarFieldMap.put(DashboardStandardTaskColumn.ID.getField(), new TaskFilterFieldId());
    standarFieldMap.put(DashboardStandardTaskColumn.NAME.getField(), new TaskFilterFieldName());
    standarFieldMap.put(DashboardStandardTaskColumn.DESCRIPTION.getField(), new TaskFilterFieldDescription());
    standarFieldMap.put(DashboardStandardTaskColumn.CREATED.getField(), new TaskFilterFieldCreatedDate());
    standarFieldMap.put(DashboardStandardTaskColumn.COMPLETED.getField(), new TaskFilterFieldFinishedDate());
    standarFieldMap.put(DashboardStandardTaskColumn.EXPIRY.getField(), new TaskFilterFieldExpiryDate());
    standarFieldMap.put(DashboardStandardTaskColumn.RESPONSIBLE.getField(), new TaskFilterFieldResponsible());
    standarFieldMap.put(DashboardStandardTaskColumn.CATEGORY.getField(), new TaskFilterFieldCategory());
    standarFieldMap.put(DashboardStandardTaskColumn.PRIORITY.getField(), new TaskFilterFieldPriority());
    standarFieldMap.put(DashboardStandardTaskColumn.STATE.getField(), new TaskFilterFieldState());
    standarFieldMap.put(DashboardStandardTaskColumn.APPLICATION.getField(), new TaskFilterFieldApplication());
    return standarFieldMap;
  }

  public static FilterField findBy(String widgetId, String field, DashboardColumnType type) {
    initCustomFields();

    return switch (type) {
    case STANDARD -> getStandardFilterField(widgetId).get(field);
    case CUSTOM -> CUSTOM_FILTER_FIELD.get(field);
    case CUSTOM_CASE -> CUSTOM_CASE_FILTER_FIELD.get(field);
    case CUSTOM_BUSINESS_CASE -> CUSTOM_CASE_FILTER_FIELD.get(field);
    default -> throw new IllegalArgumentException("Unexpected value: " + type.name());
    };
  }
  
  public static FilterField findBy(String widgetId, String field) {
    if (getStandardFilterField(widgetId).containsKey(field)) {
      return getStandardFilterField(widgetId).get(field);
    }
    else if (CUSTOM_FILTER_FIELD.containsKey(field)) {
      return CUSTOM_FILTER_FIELD.get(field);
    }
    else if (CUSTOM_CASE_FILTER_FIELD.containsKey(field)) {
      return CUSTOM_CASE_FILTER_FIELD.get(field);
    }
    return BaseFilter.DEFAULT.contentEquals(field)
        ? new FilterFieldDefault()
        : null;
    
  }
  
  
  @Deprecated
  public static FilterField findBy(String field, DashboardColumnType type) {
    initCustomFields();

    return switch (type) {
    case STANDARD -> STANDARD_FILTER_FIELD.get(field);
    case CUSTOM -> CUSTOM_FILTER_FIELD.get(field);
    case CUSTOM_CASE -> CUSTOM_CASE_FILTER_FIELD.get(field);
    case CUSTOM_BUSINESS_CASE -> CUSTOM_CASE_FILTER_FIELD.get(field);
    default -> throw new IllegalArgumentException("Unexpected value: " + type.name());
    };
  }
  
 
  @Deprecated
  public static FilterField findBy(String field) {
    if (STANDARD_FILTER_FIELD.containsKey(field)) {
      return STANDARD_FILTER_FIELD.get(field);
    }
    else if (CUSTOM_FILTER_FIELD.containsKey(field)) {
      return CUSTOM_FILTER_FIELD.get(field);
    }
    else if (CUSTOM_CASE_FILTER_FIELD.containsKey(field)) {
      return CUSTOM_CASE_FILTER_FIELD.get(field);
    }
    return BaseFilter.DEFAULT.contentEquals(field)
        ? new FilterFieldDefault()
        : null;
    
  }
  
  public static CustomFilterField findCustomFieldBy(String field) {
    return CUSTOM_FILTER_FIELD.entrySet().stream().map(Entry<String,CustomFilterField>::getValue)
      .filter(customField -> customField.getName().contentEquals(field))
      .findFirst().orElse(null);
  }

  public static List<FilterField> getStandardFilterableFields(String widgetId) {
    var filterFields = getStandardFilterField(widgetId);
    return new ArrayList<FilterField>(filterFields.values());
  }
  
  public static Map<String, FilterField> getStandardFilterField(String widgetId) {
    var filterFields = WIDGET_FILTER_FIELD.get(widgetId);
    if (filterFields == null) {
      filterFields = initMapFilterField();
      WIDGET_FILTER_FIELD.put(widgetId, filterFields);
    }
    return filterFields;    
  }
  
  public static List<FilterField> getCustomFilterableFields() {
    return new ArrayList<FilterField>(CUSTOM_FILTER_FIELD.values());
  }

  private static void initCustomFields() {
    CUSTOM_FILTER_FIELD.clear();
    for (ICustomFieldMeta customField : ICustomFieldMeta.tasks()) {
      switch (customField.type()) {
      case STRING -> CUSTOM_FILTER_FIELD.put(customField.name(), new TaskFilterFieldCustomString(customField));
      case TEXT -> CUSTOM_FILTER_FIELD.put(customField.name(), new TaskFilterFieldCustomText(customField));
      case TIMESTAMP -> CUSTOM_FILTER_FIELD.put(customField.name(), new TaskFilterFieldCustomTimestamp(customField));
      case NUMBER -> CUSTOM_FILTER_FIELD.put(customField.name(), new TaskFilterFieldCustomNumber(customField));
      default -> throw new IllegalArgumentException("Unexpected value: " + customField.type());
      }
    }

    CUSTOM_CASE_FILTER_FIELD.clear();
    for (ICustomFieldMeta customCaseField : ICustomFieldMeta.cases()) {
      switch (customCaseField.type()) {
      case STRING ->
        CUSTOM_CASE_FILTER_FIELD.put(customCaseField.name(), new TaskFilterCaseFieldCustomString(customCaseField));
      case TEXT ->
        CUSTOM_CASE_FILTER_FIELD.put(customCaseField.name(), new TaskFilterCaseFieldCustomText(customCaseField));
      case TIMESTAMP ->
        CUSTOM_CASE_FILTER_FIELD.put(customCaseField.name(), new TaskFilterCaseFieldCustomTimestamp(customCaseField));
      case NUMBER ->
        CUSTOM_CASE_FILTER_FIELD.put(customCaseField.name(), new TaskFilterCaseFieldCustomNumber(customCaseField));
      default -> throw new IllegalArgumentException("Unexpected value: " + customCaseField.type());
      }
    }
  }
  
  public static FilterField getDefaultFilterField() {
    return new FilterFieldDefault();
  }
  
}
