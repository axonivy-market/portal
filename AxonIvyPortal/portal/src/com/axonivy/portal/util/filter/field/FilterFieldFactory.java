package com.axonivy.portal.util.filter.field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldCreatedDate;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldCustomNumber;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldCustomString;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldCustomTimestamp;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldDescription;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldFinishedDate;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldName;

import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;

public class FilterFieldFactory {

  private static final Map<String, FilterField> NAME_TO_FILTER_FIELD = new HashMap<>();

  static {
    NAME_TO_FILTER_FIELD.put("name", new CaseFilterFieldName("name"));
    NAME_TO_FILTER_FIELD.put("description", new CaseFilterFieldDescription("description"));
    NAME_TO_FILTER_FIELD.put("startTimestamp", new CaseFilterFieldCreatedDate("startTimestamp"));
    NAME_TO_FILTER_FIELD.put("endTimestamp", new CaseFilterFieldFinishedDate("endTimestamp"));
    for (ICustomFieldMeta customField : ICustomFieldMeta.cases()) {
      switch (customField.type()) {
        case STRING, TEXT -> NAME_TO_FILTER_FIELD.put(customField.name(), new CaseFilterFieldCustomString(customField));
        case TIMESTAMP -> NAME_TO_FILTER_FIELD.put(customField.name(), new CaseFilterFieldCustomTimestamp(customField));
        case NUMBER -> NAME_TO_FILTER_FIELD.put(customField.name(), new CaseFilterFieldCustomNumber(customField));
        default -> throw new IllegalArgumentException("Unexpected value: " + customField.type());
      }
    }
  }

  public static FilterField findBy(String field) {
    return NAME_TO_FILTER_FIELD.get(field);
  }

  public static List<FilterField> getFilterableFields() {
    return new ArrayList<FilterField>(NAME_TO_FILTER_FIELD.values());
  }
}
