package ch.ivy.addon.portalkit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Iterables;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import ch.ivyteam.ivy.workflow.custom.field.CustomFieldType;
import ch.ivyteam.ivy.workflow.custom.field.ICustomFieldMeta;

public class PortalCustomFieldUtils {
  private static final String CMS_PATH = "CmsPath";
  private static final String CMS_PATH_PATTERN_TASK = "/CustomFields/Tasks/([^/]+)/Values/([^/]+)";
  private static final String CMS_PATH_PATTERN_CASE = "/CustomFields/Cases/([^/]+)/Values/([^/]+)";
  
  public static boolean isContainCmsPathAttributeOnTask(String field, DashboardColumnType type) {
    Set<ICustomFieldMeta> customFieldMetaList = (type == DashboardColumnType.CUSTOM) ? ICustomFieldMeta.tasks() : ICustomFieldMeta.cases();
    String cmsPathPattern = (type == DashboardColumnType.CUSTOM) ? CMS_PATH_PATTERN_TASK : CMS_PATH_PATTERN_CASE;

    for (ICustomFieldMeta customField : customFieldMetaList) {
        if (customField.name().equals(field)) {
            String cmsPath = customField.attribute(CMS_PATH);
            if (cmsPath != null) {
                return (cmsPath + "/" + field).matches(cmsPathPattern);
            }
         // CmsPath attribute is null
            return false;
        }
    }

    return false;
}
  
  public static boolean isContainCmsPathAttributeOnCase(String field) {
    Set<ICustomFieldMeta> customFieldMetaList = ICustomFieldMeta.cases();
    for (ICustomFieldMeta customField : customFieldMetaList) {
        if (customField.name().equals(field)) {
            String cmsPath = customField.attribute(CMS_PATH);
            if (cmsPath != null) {
                cmsPath = cmsPath + "/" + field;
                return cmsPath.matches(CMS_PATH_PATTERN_CASE);
            }
         // CmsPath attribute is null
            return false;
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
  
}
