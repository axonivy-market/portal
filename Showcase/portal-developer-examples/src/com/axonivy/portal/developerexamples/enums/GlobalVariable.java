package com.axonivy.portal.developerexamples.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

public enum GlobalVariable {
  DEFAULT_HOMEPAGE("Portal.Homepage", GlobalVariableType.EXTERNAL_SELECTION,
      StringUtils.capitalize(HomepageType.DASHBOARD.name().toLowerCase()),
      "defaultHomepage"),
  DEFAULT_PROCESS_IMAGE("Portal.Processes.DefaultImage", GlobalVariableType.EXTERNAL_SELECTION, DefaultImage.DEFAULT.name(), "defaultProcessImage", getDefaultProcessImage()),
  GLOBAL_FOOTER_INFO("Portal.GlobalFooterInfo", GlobalVariableType.TEXT, "GlobalFooterInfo"),
  DEEPL_AUTH_KEY("Portal.DeepL.AuthKey", GlobalVariableType.PASSWORD, "", "deepLAuthKey"), ENABLE_DEEPL_TRANSLATION(
      "Portal.DeepL.Enable", GlobalVariableType.SELECTION, Option.FALSE.toString(), "enableDeepLTranslation");

  private String key;
  private GlobalVariableType type;
  private String defaultValue;
  private Map<String, Object> defaultValues;
  private String noteCMS;
  private Option[] options;
  private Object[] objectOptions;
  private Map<String, Object> externalOptions;
  private static Map<String, GlobalVariable> keyToVariable =
      Stream.of(GlobalVariable.values()).collect(Collectors.toMap(GlobalVariable::getKey, v -> v));

  public enum Option {
    FALSE,
    TRUE,
    USERNAME,
    DISPLAY_NAME,
    DISPLAY_NAME_USERNAME,
    USERNAME_DISPLAY_NAME;
    
    public String translate() {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/Enums/GlobalVariable/Option/" + name());
    }
  }

  private GlobalVariable() {}

  private GlobalVariable(String key, GlobalVariableType type, String noteCMS) {
    this.key = key;
    this.type = type;
    this.noteCMS = noteCMS;
  }

  private GlobalVariable(String key, GlobalVariableType type, String defaultValue, String noteCMS) {
    this.key = key;
    this.type = type;
    this.defaultValue = defaultValue;
    this.noteCMS = noteCMS;
    if (type == GlobalVariableType.SELECTION) {
      options = new Option[] {Option.FALSE, Option.TRUE};
    }
  }
  
  private GlobalVariable(String key, GlobalVariableType type, String defaultValue, String noteCMS, Option[] options) {
    this.key = key;
    this.type = type;
    this.defaultValue = defaultValue;
    this.noteCMS = noteCMS;
    this.options = options;
  }
  
  private GlobalVariable(String key, GlobalVariableType type, String defaultValue, String noteCMS,
      Map<String, Object> externalOptions) {
    this.key = key;
    this.type = type;
    this.defaultValue = defaultValue;
    this.noteCMS = noteCMS;
    this.externalOptions = externalOptions;
  }

  private GlobalVariable(String key, GlobalVariableType type, String defaultValue, String noteCMS, Object[] objectOptions) {
    this.key = key;
    this.type = type;
    this.defaultValue = defaultValue;
    this.noteCMS = noteCMS;
    this.objectOptions = objectOptions;
  }

  private GlobalVariable(String key, GlobalVariableType type, Map<String, Object> defaultValues, String noteCMS,
      Map<String, Object> externalOptions) {
    this.key = key;
    this.type = type;
    this.setDefaultValues(defaultValues);
    this.noteCMS = noteCMS;
    this.externalOptions = externalOptions;
  }

  public String getKey() {
    return key;
  }

  public GlobalVariableType getType() {
    return type;
  }
  
  public String getDefaultValue() {
    return defaultValue;
  }

  public String getNote() {
    return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/adminSettings/globalVariableNote/" + noteCMS);
  }

  public Object[] getOptions() {
    return options;
  }

  public Object[] getObjectOptions() {
    return objectOptions;
  }

  public Map<String, Object> getExternalOptions() {
    return externalOptions;
  }

  public void setExternalOptions(Map<String, Object> externalOptions) {
    this.externalOptions = externalOptions;
  }

  public Map<String, Object> getDefaultValues() {
    return defaultValues;
  }

  public void setDefaultValues(Map<String, Object> defaultValues) {
    this.defaultValues = defaultValues;
  }

  private static Map<String, Object> getDefaultProcessImage() {
    Map<String, Object> result = new HashMap<>();
    for (DefaultImage defaultImage : DefaultImage.values()) {
      result.put(defaultImage.name(), defaultImage);
    }
    return result;
  }
  public static GlobalVariable valueOfKey(String key) {
    return keyToVariable.get(key);
  }
}
