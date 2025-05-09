package com.axonivy.portal.util.statisticfilter.field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.axonivy.portal.constant.StatisticConstants;
import com.axonivy.portal.dto.dashboard.filter.BaseFilter;
import com.axonivy.portal.util.filter.field.CustomFilterField;
import com.axonivy.portal.util.filter.field.FilterField;
import com.axonivy.portal.util.filter.field.FilterFieldDefault;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldApplication;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldCategory;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldCreatedDate;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldCreator;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldFinishedDate;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldState;
import com.axonivy.portal.util.filter.field.caze.custom.CaseFilterFieldCustomNumber;
import com.axonivy.portal.util.filter.field.caze.custom.CaseFilterFieldCustomString;
import com.axonivy.portal.util.filter.field.caze.custom.CaseFilterFieldCustomTimestamp;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;

public class CaseFilterFieldFactory {
  private static final Map<String, FilterField> STANDARD_FILTER_FIELD = new HashMap<>();
  private static final Map<String, CustomFilterField> CUSTOM_FILTER_FIELD = new HashMap<>();

  static {
    STANDARD_FILTER_FIELD.put(StatisticConstants.CREATOR_NAME, new CaseFilterFieldCreator());
    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.APPLICATION.getField(), new CaseFilterFieldApplication());
    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.STATE.getField(), new CaseFilterFieldState());
    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.CATEGORY.getField(), new CaseFilterFieldCategory());
    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.CREATED.getField(), new CaseFilterFieldCreatedDate());
    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.FINISHED.getField(), new CaseFilterFieldFinishedDate());
//    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.NAME.getField(), new CaseFilterFieldName()); // TODO TEST THIS FIELD WITH OPERATOR IN NOT WORKING - MAYBE BECAUSE IT'S FORMAT IS TEXT
                                                                                                         // TODO Also remove NameInOperatorHandler
//    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.DESCRIPTION.getField(), new CaseFilterFieldDescription()); // TODO TEST THIS FIELD WITH OPERATOR IN NOT WORKING - MAYBE BECAUSE IT'S FORMAT IS TEXT
//    STANDARD_FILTER_FIELD.put(DashboardStandardCaseColumn.ID.getField(), new CaseFilterFieldId()); // TODO TEST THIS FIELD WITH OPERATOR IN NOT WORKING - MAYBE BECAUSE IT'S FORMAT IS TEXT
    
    for (ICustomFieldMeta customField : ICustomFieldMeta.cases()) {
      switch (customField.type()) {
        case CustomFieldType.STRING -> CUSTOM_FILTER_FIELD.put(customField.name(), new CaseFilterFieldCustomString(customField));
        case CustomFieldType.TIMESTAMP -> CUSTOM_FILTER_FIELD.put(customField.name(), new CaseFilterFieldCustomTimestamp(customField));
        case CustomFieldType.NUMBER -> CUSTOM_FILTER_FIELD.put(customField.name(), new CaseFilterFieldCustomNumber(customField));
        default -> {}
      }
    }
  }

  public static List<FilterField> getStandardFilterableFields() {
    return new ArrayList<FilterField>(STANDARD_FILTER_FIELD.values());
  }
  
  public static List<FilterField> getCustomFilterableFields() {
    return new ArrayList<FilterField>(CUSTOM_FILTER_FIELD.values());
  }
  
  public static FilterField findBy(String field) {
    if (STANDARD_FILTER_FIELD.containsKey(field)) {
      return STANDARD_FILTER_FIELD.get(field);
    } else if (CUSTOM_FILTER_FIELD.containsKey(field)) {
      return CUSTOM_FILTER_FIELD.get(field);
    }
    return BaseFilter.DEFAULT.contentEquals(field) ? new FilterFieldDefault() : null;
  }

  public static FilterField findBy(String field, DashboardColumnType type) {
    return switch (type) {
      case DashboardColumnType.STANDARD -> STANDARD_FILTER_FIELD.get(field);
      case DashboardColumnType.CUSTOM -> CUSTOM_FILTER_FIELD.get(field);
      default -> throw new IllegalArgumentException("Unexpected value: " + type.name());
    };
  }
  
  public static FilterField getDefaultFilterField() {
    return new FilterFieldDefault();
  }
}