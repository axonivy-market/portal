package com.axonivy.portal.developerexamples.dto;

import com.axonivy.portal.developerexamples.configuration.AbstractConfiguration;
import com.axonivy.portal.developerexamples.enums.GlobalVariable;
import com.axonivy.portal.developerexamples.enums.GlobalVariableType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GlobalSetting extends AbstractConfiguration {
  private String key;
  private String value;
  
  public GlobalSetting(String key, String value) {
    this.key = key;
    this.value = value;
    setIsPublic(true);
  }
  
  public GlobalSetting() {
    setIsPublic(true);
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
  

  public void setValueToDefault() {
    GlobalVariable variable = GlobalVariable.valueOfKey(key);
    if (variable.getType() == GlobalVariableType.MULTI_EXTERNAL_SELECTIONS) {
      value =  String.join(",", variable.getDefaultValues().keySet());
    } else {
      value = variable.getDefaultValue();
    }
  }

  @JsonIgnore
  public String getNote() {
    return GlobalVariable.valueOfKey(key).getNote();
  }

  @Override
  public String toString() {
    return "GlobalSetting [key=" + key + ", value=" + value + ", id=" + getId() + "]";
  }
}
