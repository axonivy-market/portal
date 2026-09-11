package com.axonivy.portal.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.enums.SearchScopeCaseField;
import com.axonivy.portal.enums.SearchScopeTaskField;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivyteam.ivy.environment.Ivy;

public class SearchScopeUtils {

  public static List<SearchScopeTaskField> getSearchScopeTaskFields() {
    return getSearchScopeFields(GlobalVariable.SEARCH_SCOPE_BY_TASK_FIELDS, SearchScopeTaskField.class);
  }

  public static List<SearchScopeCaseField> getSearchScopeCaseFields() {
    return getSearchScopeFields(GlobalVariable.SEARCH_SCOPE_BY_CASE_FIELDS, SearchScopeCaseField.class);
  }

  private static <E extends Enum<E>> List<E> getSearchScopeFields(GlobalVariable variable, Class<E> fieldType) {
    List<E> fields = new ArrayList<>();
    String configuredFields = Ivy.var().get(variable.getKey());
    if (StringUtils.isBlank(configuredFields)) {
      return fields;
    }
    for (String field : configuredFields.split(",")) {
      String fieldName = field.trim().toUpperCase();
      if (StringUtils.isNotEmpty(fieldName)) {
        fields.add(Enum.valueOf(fieldType, fieldName));
      }
    }
    return fields;
  }

}
