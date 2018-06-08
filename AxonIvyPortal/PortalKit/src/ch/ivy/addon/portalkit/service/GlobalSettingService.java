package ch.ivy.addon.portalkit.service;

import java.util.List;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.persistence.dao.GlobalSettingDao;
import ch.ivy.addon.portalkit.persistence.domain.GlobalSetting;

public class GlobalSettingService extends AbstractService<GlobalSetting> {
  
  public static final String HIDE_TIME = "HIDE_TIME";

  public GlobalSettingService() {
    super(GlobalSettingDao.class);
  }

  @Override
  protected GlobalSettingDao getDao() {
    return (GlobalSettingDao) super.getDao();
  }

  public String findGlobalSettingValue(String variableName) {
    return getDao().findGlobalSettingValue(variableName);
  }
  
  public boolean isGlobalSettingAvailable(String variableName) {
    return getDao().isGlobalSettingAvailable(variableName);   
  }

  public List<GlobalSetting> findAllGlobalSetting() {
    List<GlobalSetting> globalSettings = super.findAll();
    List<String> allGlobalSettingKeys = globalSettings.stream().map(GlobalSetting::getKey).collect(Collectors.toList());
    for (GlobalVariable globalVariable : GlobalVariable.values()) {
      if (!allGlobalSettingKeys.contains(globalVariable.toString())) {
        GlobalSetting newGlobalSetting = new GlobalSetting();
        newGlobalSetting.setKey(globalVariable.toString());
        newGlobalSetting.setValue(globalVariable.getDefaultValue());
        globalSettings.add(newGlobalSetting);
      }
    }
    sortByEnumValues(globalSettings);
    return globalSettings;
  }

  private void sortByEnumValues(List<GlobalSetting> globalSettings) {
    globalSettings.sort((GlobalSetting setting1, GlobalSetting setting2) -> GlobalVariable.valueOf(setting1.getKey()).compareTo(GlobalVariable.valueOf(setting2.getKey())));
  }

  public void resetGlobalSetting(String variableName) {
    getDao().resetGlobalSettingValue(variableName);
  }
}
