package ch.ivy.addon.portalkit.util;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.TaskDetailsCustomWidgetParamPattern;
import ch.ivy.addon.portalkit.enums.TaskDetailsCustomWidgetParam;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.custom.field.ICustomField;

public class TaskDetailsCustomWidgetUtils {

  public static String getTaskPropertyByKeyPattern(ITask task, String keyPattern) {
    if (task != null) {
      String[] keyParts = keyPattern.split(TaskDetailsCustomWidgetParamPattern.TASK_PROPERTY_KEY_PATTERN_DELIMITER);
      if (keyParts.length == 2) {
        switch (keyParts[0]) {
          case TaskDetailsCustomWidgetParamPattern.TASK_PROPERTY_PREFIX:
            return getTaskPropertyByKey(task, keyParts[1]);
          case TaskDetailsCustomWidgetParamPattern.TASK_CUSTOM_FIELD_PREFIX:
            return getTaskCustomFieldByKey(task, keyParts[1]);
          default:
            return keyPattern;
        }
      }
    }
    return keyPattern;
  }

  private static String getTaskPropertyByKey(ITask task, String key) {
    if (TaskDetailsCustomWidgetParam.ID.getValue().equals(key)) {
      return String.valueOf(task.getId());
    } else if (TaskDetailsCustomWidgetParam.CATEGORY.getValue().equals(key)) {
      return String.valueOf(task.getCategory().getPath());
    } else {
      return StringUtils.EMPTY;
    }
  }

  private static String getTaskCustomFieldByKey(ITask task, String key) {
    ICustomField<?> customField = task.customFields().all().stream().filter(field -> field.name().equals(key)).findFirst().orElse(null);
    Object customFieldValue = customField != null ? customField.getOrNull() : null;
    return customFieldValue != null ? String.valueOf(customFieldValue) : StringUtils.EMPTY;
  }

}
