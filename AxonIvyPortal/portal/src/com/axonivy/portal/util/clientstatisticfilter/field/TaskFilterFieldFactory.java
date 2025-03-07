package com.axonivy.portal.util.clientstatisticfilter.field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.axonivy.portal.util.clientstatisticfilter.field.task.TaskFilterFieldCategory;

import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;

public class TaskFilterFieldFactory {
  private static final Map<String, FilterField> STANDARD_FILTER_FIELD = new HashMap<>();

  static {
    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.CATEGORY.getField(), new TaskFilterFieldCategory());
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

  
}
