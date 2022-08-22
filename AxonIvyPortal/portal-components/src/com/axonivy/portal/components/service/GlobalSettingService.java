package com.axonivy.portal.components.service;

import org.apache.commons.lang.StringUtils;

import com.axonivy.portal.components.enums.GlobalVariable;

import ch.ivyteam.ivy.environment.Ivy;

public class GlobalSettingService {

  private static GlobalSettingService instance;

  private GlobalSettingService() {}

  public static GlobalSettingService getInstance() {
    if (instance == null) {
      synchronized (GlobalSettingService.class) {
        if (instance == null) {
          instance = new GlobalSettingService();
        }
      }
    }
    return instance;
  }

  public String findGlobalSettingValue(GlobalVariable variable) {
    String key = variable.getKey();
    return Ivy.var().get(key);
  }

  public Boolean findGlobalSettingValueAsBoolean(GlobalVariable variable, boolean defaultIfEmpty) {
    String valueStr = findGlobalSettingValue(variable);
    return StringUtils.isEmpty(valueStr) ? defaultIfEmpty : Boolean.valueOf(valueStr);
  }

  public String findGlobalSettingValueAsString(GlobalVariable variable, String defaultIfEmpty) {
    String valueStr = findGlobalSettingValue(variable);
    return StringUtils.isEmpty(valueStr) ? defaultIfEmpty : valueStr;
  }
}
