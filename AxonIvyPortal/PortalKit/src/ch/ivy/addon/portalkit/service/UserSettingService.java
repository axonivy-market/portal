package ch.ivy.addon.portalkit.service;

import ch.ivy.addon.portalkit.persistence.dao.UserSettingDao;
import ch.ivy.addon.portalkit.persistence.domain.UserSetting;

public class UserSettingService extends AbstractService<UserSetting> {

  public UserSettingService() {
    super(UserSettingDao.class);
  }

  @Override
  protected UserSettingDao getDao() {
    return (UserSettingDao) super.getDao();
  }

  public UserSetting findByUserName(String userName) {
    return getDao().findByUserName(userName);
  }

}
