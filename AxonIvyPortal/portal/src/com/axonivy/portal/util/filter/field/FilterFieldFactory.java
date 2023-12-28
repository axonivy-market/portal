package com.axonivy.portal.util.filter.field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldCreatedDate;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldDescription;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldFinishedDate;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldName;
import com.axonivy.portal.util.filter.field.caze.custom.CaseFilterFieldCustomNumber;
import com.axonivy.portal.util.filter.field.caze.custom.CaseFilterFieldCustomString;
import com.axonivy.portal.util.filter.field.caze.custom.CaseFilterFieldCustomText;
import com.axonivy.portal.util.filter.field.caze.custom.CaseFilterFieldCustomTimestamp;

import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;

public class FilterFieldFactory {

  private static final Map<String, FilterField> STANDARD_FILTER_FIELD = new HashMap<>();
  private static final Map<String, CustomFilterField> CUSTOM_FILTER_FIELD = new HashMap<>();

  static {
    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.NAME.getField(), new CaseFilterFieldName());
    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.DESCRIPTION.getField(), new CaseFilterFieldDescription());
    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.CREATED.getField(), new CaseFilterFieldCreatedDate());
    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.FINISHED.getField(), new CaseFilterFieldFinishedDate());
    for (ICustomFieldMeta customField : ICustomFieldMeta.cases()) {
      switch (customField.type()) {
        case STRING -> CUSTOM_FILTER_FIELD.put(customField.name(), new CaseFilterFieldCustomString(customField));
        case TEXT -> CUSTOM_FILTER_FIELD.put(customField.name(), new CaseFilterFieldCustomText(customField));
        case TIMESTAMP -> CUSTOM_FILTER_FIELD.put(customField.name(), new CaseFilterFieldCustomTimestamp(customField));
        case NUMBER -> CUSTOM_FILTER_FIELD.put(customField.name(), new CaseFilterFieldCustomNumber(customField));
        default -> throw new IllegalArgumentException("Unexpected value: " + customField.type());
      }
    }
  }

  public static FilterField findBy(String field) {
    FilterField result = STANDARD_FILTER_FIELD.get(field);
    if (result == null) {
      result = findCustomFieldBy(field);
    }
    return result;
  }

  private static CustomFilterField findCustomFieldBy(String field) {
    return CUSTOM_FILTER_FIELD.entrySet().stream().map(Entry<String,CustomFilterField>::getValue)
      .filter(customField -> customField.getName().contentEquals(field))
      .findFirst().get();
  }

  public static List<FilterField> getStandardFilterableFields() {
    return new ArrayList<FilterField>(STANDARD_FILTER_FIELD.values());
  }

  public static List<FilterField> getCustomFilterableFields() {
    return new ArrayList<FilterField>(CUSTOM_FILTER_FIELD.values());
  }
}
