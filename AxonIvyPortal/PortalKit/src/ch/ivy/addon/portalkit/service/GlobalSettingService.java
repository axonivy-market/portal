package ch.ivy.addon.portalkit.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.persistence.dao.GlobalSettingDao;
import ch.ivy.addon.portalkit.persistence.domain.GlobalSetting;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.data.cache.IDataCacheEntry;

public class GlobalSettingService extends AbstractService<GlobalSetting> {

  public GlobalSettingService() {
    super(GlobalSettingDao.class);
  }

  @Override
  protected GlobalSettingDao getDao() {
    return (GlobalSettingDao) super.getDao();
  }

  public String findGlobalSettingValue(String variableName) {
    Object atttributeValue = IvyCacheService.newInstance().getGlobalSettingFromCache(variableName);
    if (atttributeValue == null){
       GlobalSetting setting = findAllGlobalSetting().stream()
          .filter(globalSetting -> StringUtils.equals(globalSetting.getKey(), variableName))
          .findFirst()
          .orElse(new GlobalSetting());
       return StringUtils.defaultString(setting.getValue());
    }
    return String.valueOf(atttributeValue);
  }

  public boolean isGlobalSettingAvailable(String variableName) {
    Object atttributeValue = IvyCacheService.newInstance().getGlobalSettingFromCache(variableName);
    if (atttributeValue == null){
       GlobalSetting setting = findAllGlobalSetting().stream()
          .filter(globalSetting -> StringUtils.equals(globalSetting.getKey(), variableName))
          .findFirst()
          .orElse(new GlobalSetting());
       return StringUtils.isNotBlank(setting.getValue());
    }
    return StringUtils.isNotBlank(String.valueOf(atttributeValue));
  }

  public List<GlobalSetting> findAllGlobalSetting() {
    List<IDataCacheEntry> allGlobalSettingsFromCache = IvyCacheService.newInstance().getAllGlobalSettingsFromCache();
    if (allGlobalSettingsFromCache != null) {
      return allGlobalSettingsFromCache.stream()
          .map(cacheEntry -> new GlobalSetting(cacheEntry.getIdentifier(), String.valueOf(cacheEntry.getValue())))
          .collect(Collectors.toList());
    } else {
      List<GlobalSetting> globalSettings = super.findAll();
      globalSettings = globalSettings.stream()
          .filter(setting -> EnumUtils.isValidEnum(GlobalVariable.class, setting.getKey()))
          .collect(Collectors.toList());
      
      List<String> allGlobalSettingKeys = globalSettings.stream()
          .map(GlobalSetting::getKey)
          .collect(Collectors.toList());
      
      for (GlobalVariable globalVariable : GlobalVariable.values()) {
        if (!allGlobalSettingKeys.contains(globalVariable.toString())) {
          globalSettings.add(new GlobalSetting(globalVariable.toString(), globalVariable.getDefaultValue()));
        }
      }
      
      globalSettings.forEach(setting -> IvyCacheService.newInstance().cacheGlobalSetting(setting.getKey(), StringUtils.defaultString(setting.getValue())));
      sortByAlphabet(globalSettings);
      return globalSettings;
    }
  }
  
  private void sortByAlphabet(List<GlobalSetting> globalSettings) {
    globalSettings
        .sort((GlobalSetting setting1, GlobalSetting setting2) -> setting1.getKey().compareTo(setting2.getKey()));
  }

  public void resetGlobalSetting(String variableName) {
    getDao().resetGlobalSettingValue(variableName);
  }

  public boolean findHideSystemTasksFromHistorySettingValue() {
    GlobalVariable globalVariable =
        PermissionUtils.isSessionUserHasAdminRole() ? GlobalVariable.HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR
            : GlobalVariable.HIDE_SYSTEM_TASKS_FROM_HISTORY;
    String settingValue = findGlobalSettingValue(globalVariable.toString());
    return StringUtils.isBlank(settingValue) ? Boolean.valueOf(globalVariable.getDefaultValue())
        : Boolean.valueOf(settingValue);
  }
}
