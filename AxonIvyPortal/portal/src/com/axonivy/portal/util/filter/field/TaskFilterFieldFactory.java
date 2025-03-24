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

  static {
    STANDARD_FILTER_FIELD.put(DashboardStandardTaskColumn.ID.getField(), new TaskFilterFieldId());
    STANDARD_FILTER_FIELD.put(DashboardStandardTaskColumn.NAME.getField(), new TaskFilterFieldName());
    STANDARD_FILTER_FIELD.put(DashboardStandardTaskColumn.DESCRIPTION.getField(), new TaskFilterFieldDescription());
    STANDARD_FILTER_FIELD.put(DashboardStandardTaskColumn.CREATED.getField(), new TaskFilterFieldCreatedDate());
    STANDARD_FILTER_FIELD.put(DashboardStandardTaskColumn.EXPIRY.getField(), new TaskFilterFieldExpiryDate());
    STANDARD_FILTER_FIELD.put(DashboardStandardTaskColumn.RESPONSIBLE.getField(), new TaskFilterFieldResponsible());
    STANDARD_FILTER_FIELD.put(DashboardStandardTaskColumn.CATEGORY.getField(), new TaskFilterFieldCategory());
    STANDARD_FILTER_FIELD.put(DashboardStandardTaskColumn.PRIORITY.getField(), new TaskFilterFieldPriority());
    STANDARD_FILTER_FIELD.put(DashboardStandardTaskColumn.STATE.getField(), new TaskFilterFieldState());
    STANDARD_FILTER_FIELD.put(DashboardStandardTaskColumn.APPLICATION.getField(), new TaskFilterFieldApplication());
  }

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

  public static List<FilterField> getStandardFilterableFields() {
    return new ArrayList<FilterField>(STANDARD_FILTER_FIELD.values());
  }

  public static List<FilterField> getCustomFilterableFields() {
    return new ArrayList<FilterField>(CUSTOM_FILTER_FIELD.values());
  }

  private static void initCustomFields() {
    for (ICustomFieldMeta customField : ICustomFieldMeta.tasks()) {
      switch (customField.type()) {
      case STRING -> CUSTOM_FILTER_FIELD.put(customField.name(), new TaskFilterFieldCustomString(customField));
      case TEXT -> CUSTOM_FILTER_FIELD.put(customField.name(), new TaskFilterFieldCustomText(customField));
      case TIMESTAMP -> CUSTOM_FILTER_FIELD.put(customField.name(), new TaskFilterFieldCustomTimestamp(customField));
      case NUMBER -> CUSTOM_FILTER_FIELD.put(customField.name(), new TaskFilterFieldCustomNumber(customField));
      default -> throw new IllegalArgumentException("Unexpected value: " + customField.type());
      }
    }

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
