package ch.ivy.addon.portalkit.configuration;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.addon.portal.generic.userprofile.homepage.HomepageUtils;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.GlobalVariableType;
import ch.ivyteam.ivy.environment.Ivy;

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
  
  @JsonIgnore
  public String getDefaultValue() {
    GlobalVariable variable = GlobalVariable.valueOfKey(key);
    String defaultValue = variable.getDefaultValue();
    if (variable.getType() == GlobalVariableType.SELECTION) {
      return GlobalVariable.Option.valueOf(StringUtils.upperCase(defaultValue)).translate();
    } else if (variable == GlobalVariable.DEFAULT_HOMEPAGE) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/dashboard");
    } else if (variable.getType() == GlobalVariableType.EXTERNAL_SELECTION && variable.getExternalOptions() != null && !variable.getExternalOptions().isEmpty()) {
      return variable.getExternalOptions().get(defaultValue);
    }
    return defaultValue;
  }
  
  @JsonIgnore
  public String getDisplayValue() {
    GlobalVariable variable = GlobalVariable.valueOfKey(key);
    if (variable.getType() == GlobalVariableType.SELECTION) {
      return GlobalVariable.Option.valueOf(StringUtils.upperCase(value)).translate();
    } else if (variable == GlobalVariable.DEFAULT_HOMEPAGE) {
      return HomepageUtils.findDefaultHomepage().getLabel();
    } else if (variable.getType() == GlobalVariableType.EXTERNAL_SELECTION && variable.getExternalOptions() != null && !variable.getExternalOptions().isEmpty()) {
      return variable.getExternalOptions().get(value);
    }
    return value;
  }

  public void setValueToDefault() {
    value = GlobalVariable.valueOfKey(key).getDefaultValue();
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
