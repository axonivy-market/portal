package ch.ivy.addon.portalkit.persistence.dao;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.persistence.domain.GlobalSetting;

public class GlobalSettingDao extends AbstractDao<GlobalSetting> {
  public GlobalSettingDao() {
    super();
  }

  public String findGlobalSettingValue(String variableName) {
    GlobalSetting globalSetting = findGlobalSetting(variableName);
    return globalSetting == null ? "" :  globalSetting.getValue();
  }

  public GlobalSetting findGlobalSetting(String variableName) {
    return findAll().stream()
        .filter(globalSetting -> StringUtils.equals(variableName, globalSetting.getKey()))
        .findFirst()
        .orElse(null);
  }

  public void resetGlobalSettingValue(String variableName) {
    GlobalSetting globalSetting = findGlobalSetting(variableName);
    if (globalSetting == null) {
      return;
    }
    delete(globalSetting);
  }
}
