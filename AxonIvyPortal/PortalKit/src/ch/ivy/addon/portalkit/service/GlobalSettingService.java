package ch.ivy.addon.portalkit.service;

import org.apache.commons.lang.StringUtils;

import ch.ivy.addon.portalkit.persistence.dao.GlobalSettingDao;
import ch.ivy.addon.portalkit.persistence.domain.GlobalSetting;
import ch.ivy.addon.portalkit.persistence.variable.GlobalVariable;
import ch.ivy.addon.portalkit.util.PermissionUtils;

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

  public boolean findHideSystemTasksFromHistorySettingValue() {
    String globalVariable = GlobalVariable.HIDE_SYSTEM_TASKS_FROM_HISTORY;
    boolean defaultValue = true;
    if (PermissionUtils.isSessionUserHasAdminRole()) {
      globalVariable = GlobalVariable.HIDE_SYSTEM_TASKS_FROM_HISTORY_ADMINISTRATOR;
      defaultValue = false;
    }
    String settingValue = findGlobalSettingValue(globalVariable.toString());
    return StringUtils.isBlank(settingValue) ? defaultValue : Boolean.valueOf(settingValue);
  }
}
