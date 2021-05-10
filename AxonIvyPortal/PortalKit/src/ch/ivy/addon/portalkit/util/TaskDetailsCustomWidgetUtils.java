package ch.ivy.addon.portalkit.util;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.TaskDetailsCustomWidgetParam;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.custom.field.ICustomField;

public class TaskDetailsCustomWidgetUtils {
  public static final String TASK_PROPERTY_KEY_PATTERN_DELIMITER = "\\.";
  public static final String TASK_PROPERTY_PREFIX = "task";
  public static final String TASK_CUSTOM_FIELD_PREFIX = "custom";

  public static String getTaskPropertyByKeyPattern(ITask task, String keyPattern) {
    if (task != null) {
      String[] keyParts = keyPattern.split(TASK_PROPERTY_KEY_PATTERN_DELIMITER);
      if (keyParts.length == 2) {
        switch (keyParts[0]) {
          case TASK_PROPERTY_PREFIX:
            return getTaskPropertyByKey(task, keyParts[1]);
          case TASK_CUSTOM_FIELD_PREFIX:
            return getTaskCustomFieldByKey(task, keyParts[1]);
          default:
            return keyPattern;
        }
      }
    }
    return keyPattern;
  }

  private static String getTaskPropertyByKey(ITask task, String key) {
    TaskDetailsCustomWidgetParam foundKey = Arrays.asList(TaskDetailsCustomWidgetParam.values()).stream().filter(k -> k.getValue().contentEquals(key)).findFirst().orElse(null);
    switch(foundKey) {
      case ID:
          return String.valueOf(task.getId());
      case CATEGORY:
          return String.valueOf(task.getCategory().getPath());
      default:
          return StringUtils.EMPTY;
    }
  }

  private static String getTaskCustomFieldByKey(ITask task, String key) {
    ICustomField<?> customField = task.customFields().all().stream().filter(field -> field.name().equals(key)).findFirst().orElse(null);
    Object customFieldValue = customField != null ? customField.getOrNull() : null;
    return customFieldValue != null ? String.valueOf(customFieldValue) : StringUtils.EMPTY;
  }

}
