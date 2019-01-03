package ch.ivy.addon.portalkit.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.boon.criteria.ObjectFilter;
import org.boon.datarepo.Repo;
import org.boon.datarepo.Repos;

import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.domain.User;
import ch.ivy.addon.portalkit.support.DataCache;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;

@SuppressWarnings("unchecked")
public class UserDao extends AbstractDao<User> {

  public UserDao() {
    super();
  }

  public UserDao(IApplication application) {
    super(application);
  }

  @ExecuteAsSystem
  private void getRepoIndexedByUserName() {
    repo = DataCache.getUserRepoFromCache();
    if (repo == null) {
      repo = buildRepoIndexedByUserName(getAllUsers());
      DataCache.cacheUsersRepo(Ivy.wf().getApplication().getName(), repo);
    }
  }

  public Repo<Long, User> buildRepoIndexedByUserName(List<User> users) {
	  return Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.USER_NAME.toString())
              .build(long.class, User.class).init(users);
  }
  
  @ExecuteAsSystem
  private List<User> getAllUsers() {
    List<User> users = DataCache.getAllUsersFromCache();
    if (users == null) {
      users = findAll();
      DataCache.cacheAllUsers(Ivy.wf().getApplication().getName(), users);
    }
    return users;
  }

  @ExecuteAsSystem
  public List<User> findByUserName(String userName) {
    getRepoIndexedByUserName();
    List<User> users = repo.query(ObjectFilter.eq(EntityProperty.USER_NAME.toString(), userName));
    return users;
  }

  @ExecuteAsSystem
  public List<User> findByApplication(Application application) {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString())
            .searchIndex(EntityProperty.APPLICATION_NAME.toString()).searchIndex(EntityProperty.SERVER_ID.toString())
            .build(long.class, User.class).init(getAllUsers());

    List<User> users =
        repo.query(ObjectFilter.eq(EntityProperty.APPLICATION_NAME.toString(), application.getName()),
            ObjectFilter.eq(EntityProperty.SERVER_ID.toString(), application.getServerId()));
    return users;
  }

  @ExecuteAsSystem
  public List<String> findApplicationNamesUserCanWorkOn(String userName, long serverId) {
    List<User> users = findByUserName(userName);
    List<String> applicationNamesUserCanWorkOn = new ArrayList<>();
    if (users != null) {
      for (User user : users) {
        if (user.getServerId() == serverId) {
          applicationNamesUserCanWorkOn.add(user.getApplicationName());
        }
      }
    }
    return applicationNamesUserCanWorkOn;
  }
}
