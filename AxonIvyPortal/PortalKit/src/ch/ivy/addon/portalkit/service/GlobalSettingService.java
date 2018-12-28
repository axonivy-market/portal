package ch.ivy.addon.portalkit.service;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.persistence.dao.GlobalSettingDao;
import ch.ivy.addon.portalkit.persistence.domain.GlobalSetting;
import ch.ivy.addon.portalkit.persistence.variable.GlobalVariable;
import ch.ivy.addon.portalkit.support.DataCache;
import ch.ivy.addon.portalkit.util.PermissionUtils;

public class GlobalSettingService extends AbstractService<GlobalSetting> {

  public GlobalSettingService() {
    super(GlobalSettingDao.class);
  }

  @Override
  protected GlobalSettingDao getDao() {
    return (GlobalSettingDao) super.getDao();
  }

  public String findGlobalSettingValue(String variableName) {
    Object atttributeValue = DataCache.getGlobalSettingFromCache(variableName);
    if (atttributeValue == null){
      String findGlobalSettingValue = getDao().findGlobalSettingValue(variableName);
      DataCache.cacheGlobalSetting(variableName, findGlobalSettingValue);
      return findGlobalSettingValue;
    }
    return String.valueOf(atttributeValue);
  }
  
  public Boolean findGlobalSettingValueAsBoolean(String variableName) {
    Object atttributeValue = DataCache.getGlobalSettingFromCache(variableName);
    if (atttributeValue == null){
      String findGlobalSettingValue = getDao().findGlobalSettingValue(variableName);
      DataCache.cacheGlobalSetting(variableName, findGlobalSettingValue);
      return Boolean.valueOf(findGlobalSettingValue);
    }
    return Boolean.valueOf((String)atttributeValue);
  }
  
  public boolean isGlobalSettingAvailable(String variableName) {
    return getDao().isGlobalSettingAvailable(variableName);
  }

  public boolean findHideSystemTasksFromHistorySettingValue() {
    String globalVariable = GlobalVariable.HIDE_SYSTEM_TASKS_FROM_HISTORY;
    boolean defaultValue = true;
    if (PermissionUtils.isSessionUserHasAdminRole()) {
      globalVariable = GlobalVariable.HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR;
      defaultValue = false;
    }
    Object atttributeValue = DataCache.getGlobalSettingFromCache(globalVariable.toString());
    if (atttributeValue == null){
      String settingValue = findGlobalSettingValue(globalVariable.toString());
      boolean booleanValue = StringUtils.isBlank(settingValue) ? defaultValue : Boolean.parseBoolean(settingValue);
      DataCache.cacheGlobalSetting(globalVariable.toString(), String.valueOf(booleanValue));
      return booleanValue;
    } 
    return Boolean.parseBoolean((String)atttributeValue);
  }
}
