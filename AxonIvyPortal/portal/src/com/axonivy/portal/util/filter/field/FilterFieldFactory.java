package com.axonivy.portal.util.filter.field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldApplication;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldCategory;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldCreatedDate;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldCreator;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldDescription;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldFinishedDate;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldId;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldName;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldState;
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
    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.ID.getField(), new CaseFilterFieldId());
    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.NAME.getField(), new CaseFilterFieldName());
    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.DESCRIPTION.getField(), new CaseFilterFieldDescription());
    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.CREATED.getField(), new CaseFilterFieldCreatedDate());
    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.FINISHED.getField(), new CaseFilterFieldFinishedDate());
    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.CREATOR.getField(), new CaseFilterFieldCreator());
    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.CATEGORY.getField(), new CaseFilterFieldCategory());
    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.STATE.getField(), new CaseFilterFieldState());
    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.APPLICATION.getField(), new CaseFilterFieldApplication());
  }

  public static FilterField findBy(String field) {
    FilterField result = STANDARD_FILTER_FIELD.get(field);
    if (result == null) {
      result = findCustomFieldBy(field);
    }
    return BaseFilter.DEFAULT.contentEquals(field) ? new FilterFieldDefault() : result;
  }

  public static CustomFilterField findCustomFieldBy(String field) {
    initCustomFields();
    return CUSTOM_FILTER_FIELD.entrySet().stream().map(Entry<String, CustomFilterField>::getValue)
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
    CUSTOM_FILTER_FIELD.clear();
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

  public static FilterField getDefaultFilterField() {
    return new FilterFieldDefault();
  }
}
