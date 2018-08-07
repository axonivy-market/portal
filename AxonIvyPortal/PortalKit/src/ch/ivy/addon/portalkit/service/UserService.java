package ch.ivy.addon.portalkit.service;

import java.util.List;

import ch.ivy.addon.portalkit.persistence.dao.UserDao;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.domain.User;

public class UserService extends AbstractService<User> {

  public UserService() {
    super(UserDao.class);
  }

  @Override
  public UserDao getDao() {
    return (UserDao) super.getDao();
  }

  public List<User> findByUserName(String userName) {
    return getDao().findByUserName(userName);
  }

  public List<User> findByApplication(Application application) {
    return getDao().findByApplication(application);
  }

  public List<String> findApplicationNamesUserCanWorkOn(String userName, long serverId) {
    return getDao().findApplicationNamesUserCanWorkOn(userName, serverId);
  }
}
