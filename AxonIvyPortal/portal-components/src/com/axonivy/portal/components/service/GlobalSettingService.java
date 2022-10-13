package com.axonivy.portal.components.service;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.persistence.dao.GlobalSettingDao;
import com.axonivy.portal.components.persistence.domain.GlobalSetting;

public class GlobalSettingService extends AbstractService<GlobalSetting> {

  private static GlobalSettingService instance;

  private GlobalSettingService() {
    super(GlobalSettingDao.class);
  }

  @Override
  protected GlobalSettingDao getDao() {
    return (GlobalSettingDao) super.getDao();
  }

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

  public String findGlobalSettingValue(String variableName) {
    Object atttributeValue = IvyCacheService.newInstance().getGlobalSettingFromCache(variableName);
    if (atttributeValue == null) {
      return getDao().findGlobalSettingValue(variableName);
    }
    return String.valueOf(atttributeValue);
  }

  public Boolean findGlobalSettingValueAsBoolean(String variableName, boolean defaultIfEmpty) {
    String valueStr = findGlobalSettingValue(variableName);
    return StringUtils.isEmpty(valueStr) ? defaultIfEmpty : Boolean.valueOf(valueStr);
  }

  public String findGlobalSettingValueAsString(String variableName, String defaultIfEmpty) {
    String valueStr = findGlobalSettingValue(variableName);
    return StringUtils.isEmpty(valueStr) ? defaultIfEmpty : valueStr;
  }
}
