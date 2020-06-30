package ch.ivy.addon.portalkit.persistence.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
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
    if (CollectionUtils.isEmpty(users)) {
      users = findAll();
      DataCache.cacheAllUsers(Ivy.wf().getApplication().getName(), users);
    }
    return users;
  }

  @ExecuteAsSystem
  public List<User> findByUserName(String userName) {
    getRepoIndexedByUserName();
    return repo.query(ObjectFilter.eq(EntityProperty.USER_NAME.toString(), userName));
  }

  @ExecuteAsSystem
  public List<User> findByApplication(Application application) {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString())
            .searchIndex(EntityProperty.APPLICATION_NAME.toString()).searchIndex(EntityProperty.SERVER_ID.toString())
            .build(long.class, User.class).init(getAllUsers());

    return repo.query(ObjectFilter.eq(EntityProperty.APPLICATION_NAME.toString(), application.getName()),
            ObjectFilter.eq(EntityProperty.SERVER_ID.toString(), application.getServerId()));
  }

  @ExecuteAsSystem
  public List<String> findApplicationNamesUserCanWorkOn(String userName, long serverId) {
    List<User> users = findByUserName(userName);
    return CollectionUtils.emptyIfNull(users)
        .stream()
        .filter(user -> user.getServerId() == serverId)
        .map(user -> user.getApplicationName())
        .collect(Collectors.toList());
  }
}
