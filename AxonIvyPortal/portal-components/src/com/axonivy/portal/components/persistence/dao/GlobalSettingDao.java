package com.axonivy.portal.components.persistence.dao;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.persistence.domain.GlobalSetting;

import ch.ivyteam.ivy.application.IApplication;

public class GlobalSettingDao extends AbstractDao<GlobalSetting> {
  public GlobalSettingDao() {
    super();
  }

  public GlobalSettingDao(IApplication application) {
    super(application);
  }

  public String findGlobalSettingValue(String variableName) {
    GlobalSetting globalSetting = findGlobalSetting(variableName);
    if (globalSetting == null) {
      return StringUtils.EMPTY;
    } else {
      return globalSetting.getValue();
    }
  }

  @ExecuteAsSystem
  public GlobalSetting findGlobalSetting(String variableName) {
    return findAll().stream()
        .filter(globalSetting -> StringUtils.equals(variableName, globalSetting.getKey()))
        .findFirst()
        .orElse(null);
  }

  @ExecuteAsSystem
  public void resetGlobalSettingValue(String variableName) {
    GlobalSetting globalSetting = findGlobalSetting(variableName);
    if (globalSetting == null) {
      return;
    }
    delete(globalSetting);
  }
}
