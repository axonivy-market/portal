package ch.ivy.addon.portalkit.persistence.dao;

import java.util.List;

import org.boon.criteria.Criterion;
import org.boon.criteria.ObjectFilter;
import org.boon.datarepo.Repos;

import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.domain.Server;
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

    List<Application> thirdPartyApplications = repo.query(ObjectFilter.isNull(EntityProperty.SERVER_ID.toString()));
    return thirdPartyApplications;
  }

  @ExecuteAsSystem
  public List<Application> findAllIvyApplications() {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.SERVER_ID.toString())
            .build(long.class, Application.class).init(findAll());

    List<Application> ivyApplications = repo.query(ObjectFilter.notNull(EntityProperty.SERVER_ID.toString()));
    return ivyApplications;
  }

  @ExecuteAsSystem
  public List<Application> findOtherApplicationsHaveSameNameAndServer(Application application) {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.NAME.toString())
            .searchIndex(EntityProperty.SERVER_ID.toString()).build(long.class, Application.class).init(findAll());

    List<Application> otherApplicationsHaveSameNameAndServer =
        repo.query(ObjectFilter.notEq(EntityProperty.ID, application.getId()),
            ObjectFilter.eq(EntityProperty.NAME, application.getName()),
            ObjectFilter.eq(EntityProperty.SERVER_ID, application.getServerId()));

    setRelationShipDataFor(otherApplicationsHaveSameNameAndServer);

    return otherApplicationsHaveSameNameAndServer;
  }

  @ExecuteAsSystem
  public List<Application> findByNameAndServerId(String applicationName, Long serverId) {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.NAME.toString())
            .searchIndex(EntityProperty.SERVER_ID.toString()).build(Long.class, Application.class).init(findAll());
    Criterion<String> objectFilterForAppName = ObjectFilter.eq(EntityProperty.NAME, applicationName);
    Criterion<Long> objectFilterForServerId = ObjectFilter.eq(EntityProperty.SERVER_ID, serverId);
    List<Application> applications = repo.query(objectFilterForAppName, objectFilterForServerId);

    setRelationShipDataFor(applications);

    return applications;
  }

  @ExecuteAsSystem
  public List<Application> findOnlineAppByServerId(Long serverId) {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.SERVER_ID.toString())
            .build(Long.class, Application.class).init(findAll());
    Criterion<Long> objectFilterForServerId = ObjectFilter.eq(EntityProperty.SERVER_ID, serverId);
    Criterion<Long> objectFilterForIsOnline = ObjectFilter.eq(EntityProperty.IS_ONLINE, true);
    List<Application> applications = repo.query(objectFilterForServerId, objectFilterForIsOnline);

    setRelationShipDataFor(applications);

    return applications;
  }
  
  @ExecuteAsSystem
  public List<Application> findOnlineIvyApps() {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.SERVER_ID.toString())
            .build(Long.class, Application.class).init(findAll());
    Criterion<Long> objectFilterForIsOnline = ObjectFilter.eq(EntityProperty.IS_ONLINE, true);
    List<Application> applications = repo.query(objectFilterForIsOnline);

    setRelationShipDataFor(applications);

    return applications;
  }

  @ExecuteAsSystem
  public Application findByDisplayNameAndNameAndServerId(String displayName, String applicationName, Long serverId) {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.NAME.toString())
            .searchIndex(EntityProperty.SERVER_ID.toString()).build(Long.class, Application.class).init(findAll());
    Criterion<String> objectFilterForDisplayName = ObjectFilter.eq(EntityProperty.DISPLAY_NAME, displayName);
    Criterion<String> objectFilterForAppName = ObjectFilter.eq(EntityProperty.NAME, applicationName);
    Criterion<Long> objectFilterForServerId = ObjectFilter.eq(EntityProperty.SERVER_ID, serverId);
    List<Application> applications =
        repo.query(objectFilterForDisplayName, objectFilterForAppName, objectFilterForServerId);
    if (!applications.isEmpty()) {
      Application application = applications.get(0);
      setRelationshipDataFor(application);
      return application;
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
      Application application = applications.get(0);
      setRelationshipDataFor(application);
      return application;
    }
    return null;
  }

  @ExecuteAsSystem
  public List<Application> findByNames(List<String> names) {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.NAME.toString())
            .build(long.class, Application.class).init(findAll());

    List<Application> applications = repo.query(ObjectFilter.in(EntityProperty.NAME.toString(), names));
    setRelationShipDataFor(applications);
    return applications;
  }

  @Override
  protected void setRelationshipDataFor(Application application) {
    if (isNotThirdPartyApplication(application)) {
      ServerDao serverDao = new ServerDao(this.ivyApplication);
      Server server = serverDao.findByIdWithoutRelationship(application.getServerId());
      application.setServer(server);
    }
  }

  private boolean isNotThirdPartyApplication(Application application) {
    return application.getServerId() != null;
  }

  private void setRelationShipDataFor(List<Application> applications) {
    for (Application application : applications) {
      setRelationshipDataFor(application);
    }
  }
}
