package ch.ivy.addon.portalkit.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.boon.criteria.ObjectFilter;
import org.boon.datarepo.Repos;

import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivyteam.ivy.application.IApplication;

public class UserProcessDao extends AbstractDao<UserProcess> {

  public UserProcessDao() {
    super();
  }

  public UserProcessDao(IApplication application) {
    super(application);
  }

  @SuppressWarnings("unchecked")
  @ExecuteAsSystem
  public List<UserProcess> findByUserName(String userName) {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.USER_NAME.toString())
            .build(long.class, UserProcess.class).init(findAll());

    List<UserProcess> userProcesses =
        repo.query(ObjectFilter.eq(EntityProperty.USER_NAME.toString(), userName),
            ObjectFilter.eqBoolean(EntityProperty.DEFAULT_PROCESS.toString(), false));
    return new ArrayList<>(userProcesses);
  }
}
