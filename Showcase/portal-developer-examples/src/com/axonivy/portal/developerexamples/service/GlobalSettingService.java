package com.axonivy.portal.developerexamples.service;

import com.axonivy.portal.developerexamples.dto.GlobalSetting;
import com.axonivy.portal.developerexamples.enums.GlobalVariable;

import ch.ivyteam.ivy.environment.Ivy;

public class GlobalSettingService {

  private static GlobalSettingService instance;

  public static GlobalSettingService getInstance() {
    if (instance == null) {
      instance = new GlobalSettingService();
    }
    return instance;
  }

  public String findGlobalSettingValue(GlobalVariable variable) {
    String key = variable.getKey();
    return Ivy.var().get(key);
  }


  public GlobalSetting findGlobalSettingByGlobalVariable(GlobalVariable variable) {
    return new GlobalSetting(variable.getKey(), findGlobalSettingValue(variable));
  }

  public Boolean findGlobalSettingValueAsBoolean(GlobalVariable variable) {
    return Boolean.valueOf(findGlobalSettingValue(variable));
  }

  public void resetGlobalSetting(String key) {
    Ivy.var().reset(key);
  }

}
