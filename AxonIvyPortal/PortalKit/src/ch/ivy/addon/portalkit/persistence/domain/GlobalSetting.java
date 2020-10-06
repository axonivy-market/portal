package ch.ivy.addon.portalkit.persistence.domain;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.addon.portal.generic.userprofile.homepage.HomepageUtils;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.GlobalVariableType;

public class GlobalSetting extends BusinessEntity {
  private String key;
  private String value;
  
  public GlobalSetting(String key, String value) {
    this.key = key;
    this.value = value;
  }
  
  public GlobalSetting(String key) {
    this.key = key;
  }
  
  public GlobalSetting() {
  }
  
  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
  
  @JsonIgnore
  public String getDefaultValue() {
    String defaultValue = GlobalVariable.valueOf(key).getDefaultValue();
    GlobalVariable variable = GlobalVariable.valueOf(key);
    if (variable.getType() == GlobalVariableType.SELECTION) {
      if (variable.getExternalOptions() != null && !variable.getExternalOptions().isEmpty()) {
        return variable.getExternalOptions().get(defaultValue);
      } else {
        return GlobalVariable.Option.valueOf(defaultValue).translate();
      }
    }
    return defaultValue;
  }
  
  @JsonIgnore
  public String getDisplayValue() {
    GlobalVariable variable = GlobalVariable.valueOf(key);
    if (variable.getType() == GlobalVariableType.SELECTION) {
      if (variable.getExternalOptions() != null && !variable.getExternalOptions().isEmpty()) {
        return variable.getExternalOptions().get(value);
      } else {
        return GlobalVariable.Option.valueOf(StringUtils.upperCase(value)).translate();
      }
    } else if (variable == GlobalVariable.DEFAULT_HOMEPAGE) {
      return HomepageUtils.getHomepageForAdminSettings().getLabel();
    }
    return value;
  }

  @JsonIgnore
  public String getDisplayStringOfExternalOption(String optionKey) {
    GlobalVariable variable = GlobalVariable.valueOf(key);
    if (variable.getType() == GlobalVariableType.SELECTION && variable.getExternalOptions() != null && !variable.getExternalOptions().isEmpty()) {
      String displayString = variable.getExternalOptions().get(optionKey);
      return org.apache.commons.lang3.StringUtils.isBlank(displayString) ? optionKey : displayString;
    }
    return optionKey;
  }

  public void setValueToDefault() {
    value = GlobalVariable.valueOf(key).getDefaultValue();
  }

  @JsonIgnore
  public String getNote() {
    return GlobalVariable.valueOf(key).getNote();
  }

  @Override
  public String toString() {
    return "GlobalSetting [key=" + key + ", value=" + value + ", id=" + getId() + "]";
  }

}
