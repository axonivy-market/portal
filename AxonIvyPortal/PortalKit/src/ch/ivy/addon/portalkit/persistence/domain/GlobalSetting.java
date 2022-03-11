package ch.ivy.addon.portalkit.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.util.GlobalSettingUtils;

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
    return GlobalSettingUtils.getSettingDisplayValue(variable, defaultValue);
  }

  @JsonIgnore
  public String getDisplayValue() {
    GlobalVariable variable = GlobalVariable.valueOf(key);
    return GlobalSettingUtils.getSettingDisplayValue(variable, value);
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
