package com.axonivy.portal.util.statisticfilter.field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.util.statisticfilter.field.task.TaskFilterFieldCanWorkOn;
import com.axonivy.portal.util.statisticfilter.field.task.TaskFilterFieldCategory;
import com.axonivy.portal.util.statisticfilter.field.task.TaskFilterFieldCreatedDate;
import com.axonivy.portal.util.statisticfilter.field.task.TaskFilterFieldExpiryDate;
import com.axonivy.portal.util.statisticfilter.field.task.TaskFilterFieldPriority;
import com.axonivy.portal.util.statisticfilter.field.task.TaskFilterFieldResponsible;
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
    STANDARD_FILTER_FIELD.put(DashboardStandardTaskColumn.RESPONSIBLE.getField(), new TaskFilterFieldResponsible());
    STANDARD_FILTER_FIELD.put(DashboardStandardTaskColumn.EXPIRY.getField(), new TaskFilterFieldExpiryDate());
    STANDARD_FILTER_FIELD.put(StatisticConstants.CAN_WORK_ON, new TaskFilterFieldCanWorkOn());
  }

  public static List<FilterField> getStandardFilterableFields() {
    return new ArrayList<FilterField>(STANDARD_FILTER_FIELD.values());
  }
  
  public static FilterField findBy(String field) {
    if (STANDARD_FILTER_FIELD.containsKey(field)) {
      return STANDARD_FILTER_FIELD.get(field);
    }
    return BaseFilter.DEFAULT.contentEquals(field)
        ? new FilterFieldDefault()
        : null;
  }

  public static FilterField findBy(String field, DashboardColumnType type) {
    return switch (type) {
    case STANDARD -> STANDARD_FILTER_FIELD.get(field);
    default -> throw new IllegalArgumentException("Unexpected value: " + type.name());
    };
  }
  
  public static FilterField getDefaultFilterField() {
    return new FilterFieldDefault();
  }
}
