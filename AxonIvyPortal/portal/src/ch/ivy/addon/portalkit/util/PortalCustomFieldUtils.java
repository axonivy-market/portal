package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Iterables;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldValues.ValueLabel;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFields;

public class PortalCustomFieldUtils {

  private static final String HAS_CMS_VALUES_ATTRIBUTE = "HasCmsValues";

  public static boolean isSupportMultiLanguageCaseField(String columnField) {
    return hasCmsValues(columnField, ICustomFieldMeta.cases());
  }
  
  public static boolean isSupportMultiLanguageTaskField(String columnField) {
    return hasCmsValues(columnField, ICustomFieldMeta.tasks());
  }

  private static boolean hasCmsValues(String columnField, Set<ICustomFieldMeta> customFieldMetaList) {
    for (ICustomFieldMeta customFieldMeta : customFieldMetaList) {
      if (customFieldMeta.name().equals(columnField) && Boolean.valueOf(customFieldMeta.attribute(HAS_CMS_VALUES_ATTRIBUTE))) {
        return true;
      }
    }
    return false;
  }

  public static List<String> getCmsValuesMatchingWithKeywordList(String columnField, DashboardColumnType type, List<String> keywordList) {
    Set<ICustomFieldMeta> icustomFieldMetaList = type == DashboardColumnType.CUSTOM ? ICustomFieldMeta.tasks() : ICustomFieldMeta.cases();
    List<String> matchingValueList = new ArrayList<>();

    icustomFieldMetaList.stream().filter(customField -> customField.type().equals(CustomFieldType.STRING) && customField.name().equals(columnField)).forEach(field -> {
      for (String keyword: keywordList) {
        Iterable<Object> list =  field.values().matching(keyword);
        if (!Iterables.isEmpty(list)) {
          for (Object obj : list) {
            matchingValueList.add(obj.toString());
          }
      }
      }
    });
    
    return matchingValueList;
  }

  public static List<String> getAllLocalizedValueOnCaseField(String columnField) {
    ICustomFieldMeta meta = ICustomFieldMeta.cases().stream().filter(m -> columnField.equals(m.name())).findAny().orElse(null);
    return getAllLocalizedValueByField(meta);
  }
  
  public static List<String> getAllLocalizedValueOnTaskField(String columnField) {
    ICustomFieldMeta meta = ICustomFieldMeta.tasks().stream().filter(m -> columnField.equals(m.name())).findAny().orElse(null);
    return getAllLocalizedValueByField(meta);
  }

  private static List<String> getAllLocalizedValueByField(ICustomFieldMeta meta) {
    if (meta == null) {
      return new ArrayList<String>();
    }
    
    return meta.values()
        .labels().stream().sorted(Comparator.comparing(ValueLabel<Object>::label))
        .map(vl -> vl.value().toString())
        .toList();
  }

  public static String getDisplayValueByField(ICustomFields customFields, String field) {
    String searchPattern = customFields.stringField(field).getOrNull();
    if (searchPattern == null) {
      return searchPattern;
    }
    return customFields.stringField(field).meta().values().label(searchPattern);
  }
}
