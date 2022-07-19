package com.axonivy.portal.component.service;

import com.axonivy.portal.component.enums.GlobalVariable;

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

  public Boolean findGlobalSettingValueAsBoolean(GlobalVariable variable) {
    return Boolean.valueOf(findGlobalSettingValue(variable));
  }
}
