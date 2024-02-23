package com.axonivy.portal.util.filter.field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;

public class TaskFilterFieldFactory {

  private static final Map<String, FilterField> STANDARD_FILTER_FIELD = new HashMap<>();
  private static final Map<String, CustomFilterField> CUSTOM_FILTER_FIELD = new HashMap<>();

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

//    Note: will handle in Complex Filter Custom Field
//    for (ICustomFieldMeta customField : ICustomFieldMeta.tasks()) {
//      switch (customField.type()) {
//        case STRING -> CUSTOM_FILTER_FIELD.put(customField.name(), new TaskFilterFieldCustomString(customField));
//        case TEXT -> CUSTOM_FILTER_FIELD.put(customField.name(), new TaskFilterFieldCustomText(customField));
//        case TIMESTAMP -> CUSTOM_FILTER_FIELD.put(customField.name(), new TaskFilterFieldCustomTimestamp(customField));
//        case NUMBER -> CUSTOM_FILTER_FIELD.put(customField.name(), new TaskFilterFieldCustomNumber(customField));
//        default -> throw new IllegalArgumentException("Unexpected value: " + customField.type());
//      }
//    }
  }

  public static FilterField findBy(String field) {
    FilterField result = STANDARD_FILTER_FIELD.get(field);
    if (result == null) {
      result = findCustomFieldBy(field);
    }
    return result;
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

}
