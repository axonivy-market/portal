package ch.ivy.addon.portalkit.persistence.dao;

import java.util.List;

import org.boon.criteria.Criterion;
import org.boon.criteria.ObjectFilter;
import org.boon.datarepo.Repos;

import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivyteam.ivy.application.IApplication;

@SuppressWarnings("unchecked")
public class ApplicationDao extends AbstractDao<Application> {

  public ApplicationDao() {
    super();
  }

  public ApplicationDao(IApplication application) {
    super(application);
  }

  @ExecuteAsSystem
  public List<Application> findAllThirdPartyApplications() {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.SERVER_ID.toString())
            .build(long.class, Application.class).init(findAll());

    return repo.query(ObjectFilter.isNull(EntityProperty.SERVER_ID.toString()));
  }

  @ExecuteAsSystem
  public List<Application> findAllIvyApplications() {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.SERVER_ID.toString())
            .build(long.class, Application.class).init(findAll());

    return repo.query(ObjectFilter.notNull(EntityProperty.SERVER_ID.toString()));
  }

  @ExecuteAsSystem
  public Application findByDisplayNameAndName(String displayName, String applicationName) {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.NAME.toString())
            .searchIndex(EntityProperty.SERVER_ID.toString()).build(Long.class, Application.class).init(findAll());
    Criterion<String> objectFilterForDisplayName = ObjectFilter.eq(EntityProperty.DISPLAY_NAME, displayName);
    Criterion<String> objectFilterForAppName = ObjectFilter.eq(EntityProperty.NAME, applicationName);
    List<Application> applications =
        repo.query(objectFilterForDisplayName, objectFilterForAppName);
    if (!applications.isEmpty()) {
      return applications.get(0);
    }

    return null;
  }

  @ExecuteAsSystem
  public Application findByName(String name) {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.NAME.toString())
            .build(long.class, Application.class).init(findAll());

    List<Application> applications = repo.query(ObjectFilter.eq(EntityProperty.NAME.toString(), name));
    if (!applications.isEmpty()) {
      return applications.get(0);
    }
    return null;
  }

  @ExecuteAsSystem
  public List<Application> findByNames(List<String> names) {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.NAME.toString())
            .build(long.class, Application.class).init(findAll());

    return repo.query(ObjectFilter.in(EntityProperty.NAME.toString(), names));
  }
}
