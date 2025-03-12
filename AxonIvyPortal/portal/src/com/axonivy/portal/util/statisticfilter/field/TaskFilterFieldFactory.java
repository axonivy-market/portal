package com.axonivy.portal.util.statisticfilter.field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.axonivy.portal.util.statisticfilter.field.task.TaskFilterFieldCategory;
import com.axonivy.portal.util.statisticfilter.field.task.TaskFilterFieldCreatedDate;
import com.axonivy.portal.util.statisticfilter.field.task.TaskFilterFieldPriority;
import com.axonivy.portal.util.statisticfilter.field.task.TaskFilterFieldState;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;

public class TaskFilterFieldFactory {
  private static final Map<String, FilterField> STANDARD_FILTER_FIELD = new HashMap<>();

  static {
    STANDARD_FILTER_FIELD.put(DashboardStandardTaskColumn.CATEGORY.getField(), new TaskFilterFieldCategory());
    STANDARD_FILTER_FIELD.put(DashboardStandardTaskColumn.STATE.getField(), new TaskFilterFieldState());
    STANDARD_FILTER_FIELD.put(DashboardStandardTaskColumn.PRIORITY.getField(), new TaskFilterFieldPriority());
    STANDARD_FILTER_FIELD.put(DashboardStandardTaskColumn.CREATED.getField(), new TaskFilterFieldCreatedDate());
  }

  public static List<FilterField> getStandardFilterableFields() {
    return new ArrayList<FilterField>(STANDARD_FILTER_FIELD.values());
  }
  
  public static FilterField findBy(String field) {
    if (STANDARD_FILTER_FIELD.containsKey(field)) {
      return STANDARD_FILTER_FIELD.get(field);
    }
//    else if (CUSTOM_FILTER_FIELD.containsKey(field)) {
//      return CUSTOM_FILTER_FIELD.get(field);
//    }
//    else if (CUSTOM_CASE_FILTER_FIELD.containsKey(field)) {
//      return CUSTOM_CASE_FILTER_FIELD.get(field);
//    }
    return null;
  }

  public static FilterField findBy(String field, DashboardColumnType type) {
    return switch (type) {
    case STANDARD -> STANDARD_FILTER_FIELD.get(field);
    default -> throw new IllegalArgumentException("Unexpected value: " + type.name());
    };
  }
  
}
