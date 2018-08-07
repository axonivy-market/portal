package ch.ivy.addon.portalkit.service;

import java.util.List;

import ch.ivy.addon.portalkit.persistence.dao.UserProcessDao;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;

public class UserProcessService extends AbstractService<UserProcess> {

  public UserProcessService() {
    super(UserProcessDao.class);
  }

  @Override
  protected UserProcessDao getDao() {
    return (UserProcessDao) super.getDao();
  }

  public List<UserProcess> findByUserName(String userName) {
    return getDao().findByUserName(userName);
  }

}
