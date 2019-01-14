package ch.ivy.addon.portalkit.persistence.dao;

import java.util.List;

import org.boon.criteria.ObjectFilter;
import org.boon.datarepo.Repos;

import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.domain.User;
import ch.ivyteam.ivy.application.IApplication;

@SuppressWarnings("unchecked")
public class UserDao extends AbstractDao<User> {

  public UserDao() {
    super();
  }

  public UserDao(IApplication application) {
    super(application);
  }

  @ExecuteAsSystem
  public List<User> findByUserName(String userName) {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.USER_NAME.toString())
            .build(long.class, User.class).init(findAll());

    return repo.query(ObjectFilter.eq(EntityProperty.USER_NAME.toString(), userName));
  }

  @ExecuteAsSystem
  public List<User> findByApplication(Application application) {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString())
            .searchIndex(EntityProperty.APPLICATION_NAME.toString()).searchIndex(EntityProperty.SERVER_ID.toString())
            .build(long.class, User.class).init(findAll());

    return repo.query(ObjectFilter.eq(EntityProperty.APPLICATION_NAME.toString(), application.getName())
        , ObjectFilter.eq(EntityProperty.SERVER_ID.toString(), application.getServerId()));
  }
}
