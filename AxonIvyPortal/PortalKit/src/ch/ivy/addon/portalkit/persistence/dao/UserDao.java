package ch.ivy.addon.portalkit.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.boon.criteria.ObjectFilter;
import org.boon.datarepo.Repo;
import org.boon.datarepo.Repos;

import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.domain.User;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.data.cache.IDataCache;
import ch.ivyteam.ivy.data.cache.IDataCacheEntry;
import ch.ivyteam.ivy.data.cache.IDataCacheGroup;
import ch.ivyteam.ivy.environment.Ivy;

@SuppressWarnings("unchecked")
public class UserDao extends AbstractDao<User> {

  public static final String PORTAL_CACHE_GROUP_NAME = "portalCache";
  public static final String USERS_LIST_CACHE_ENTRY_NAME = "usersList";
  public static final String USERS_REPO_CACHE_ENTRY_NAME = "usersRepo";

  public UserDao() {
    super();
  }

  public UserDao(IApplication application) {
    super(application);
  }

  @ExecuteAsSystem
  private void getRepoIndexedByUserName() {
    repo = null;
    IDataCache appCache = Ivy.datacache().getAppCache();
    IDataCacheGroup cacheGroup = appCache.getGroup(PORTAL_CACHE_GROUP_NAME);
    if (cacheGroup != null) {
      IDataCacheEntry cacheEntry = cacheGroup.getEntry(USERS_REPO_CACHE_ENTRY_NAME);
      if (cacheEntry != null) {
        repo = (Repo<Long, User>) cacheEntry.getValue();
      }
    }
    if (repo == null) {
      Ivy.log().info("Users repo didn't exist in cache, store in cache now");
      repo = buildRepoIndexedByUserName(getAllUsers());
      appCache.setEntry(PORTAL_CACHE_GROUP_NAME, USERS_REPO_CACHE_ENTRY_NAME, -1, repo);
    }
  }

  public Repo<Long, User> buildRepoIndexedByUserName(List<User> users) {
	  return Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.USER_NAME.toString())
              .build(long.class, User.class).init(users);
  }
  @ExecuteAsSystem
  private List<User> getAllUsers() {
    List<User> users = null;
    IDataCache appCache = Ivy.datacache().getAppCache();
    IDataCacheGroup cacheGroup = appCache.getGroup(PORTAL_CACHE_GROUP_NAME);
    if (cacheGroup != null) {
      IDataCacheEntry cacheEntry = cacheGroup.getEntry(USERS_LIST_CACHE_ENTRY_NAME);
      if (cacheEntry != null) {
        users = (List<User>) cacheEntry.getValue();
      }
    }
    if (users == null) {
      Ivy.log().info("User list didn't exist in cache, store in cache now");
      users = findAll();
      appCache.setEntry(PORTAL_CACHE_GROUP_NAME, USERS_LIST_CACHE_ENTRY_NAME, -1, users);
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
