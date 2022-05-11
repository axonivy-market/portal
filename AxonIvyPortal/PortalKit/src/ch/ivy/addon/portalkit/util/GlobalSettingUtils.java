package ch.ivy.addon.portalkit.util;

import org.apache.commons.collections.MapUtils;

import ch.ivy.addon.portalkit.enums.BehaviourWhenClickingOnLineInTaskList;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.GlobalVariableType;

public class GlobalSettingUtils {
  public static String getSettingDisplayValue(GlobalVariable variable, String value) {
    if (variable.getType() == GlobalVariableType.SELECTION) {
      // Fix bug: can not find Enum of variable with lower case value for migrating from 7.x to 8.0.25
      return GlobalVariable.Option.valueOf(value.toUpperCase()).translate();
    } else if (variable.getType() == GlobalVariableType.EXTERNAL_SELECTION && MapUtils.isNotEmpty(variable.getExternalOptions())) {
      Object object = variable.getExternalOptions().get(value);
      return getDisplayValue(object);
    }
    return value;
  }

  private static String getDisplayValue(Object object) {
    if (object instanceof BehaviourWhenClickingOnLineInTaskList) {
      return ((BehaviourWhenClickingOnLineInTaskList) object).getLabel();
    } else {
      return (String) object;
    }
  }

}
