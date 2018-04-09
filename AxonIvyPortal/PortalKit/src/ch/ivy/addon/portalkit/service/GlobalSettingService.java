package ch.ivy.addon.portalkit.service;

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
}
