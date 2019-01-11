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
  public List<Application> findOtherApplicationsHaveSameNameAndServer(Application application) {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.NAME.toString())
            .searchIndex(EntityProperty.SERVER_ID.toString()).build(long.class, Application.class).init(findAll());

    List<Application> otherApplicationsHaveSameNameAndServer =
        repo.query(ObjectFilter.notEq(EntityProperty.ID, application.getId()),
            ObjectFilter.eq(EntityProperty.NAME, application.getName()));

    setRelationShipDataForApplications(otherApplicationsHaveSameNameAndServer);

    return otherApplicationsHaveSameNameAndServer;
  }

  @ExecuteAsSystem
  public List<Application> findOnlineAppByServerId(Long serverId) {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.SERVER_ID.toString())
            .build(Long.class, Application.class).init(findAll());
    Criterion<Long> objectFilterForServerId = ObjectFilter.eq(EntityProperty.SERVER_ID, serverId);
    Criterion<Long> objectFilterForIsOnline = ObjectFilter.eq(EntityProperty.IS_ONLINE, true);
    List<Application> applications = repo.query(objectFilterForServerId, objectFilterForIsOnline);

    setRelationShipDataForApplications(applications);

    return applications;
  }

  @ExecuteAsSystem
  public List<Application> findOnlineIvyApps() {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.SERVER_ID.toString())
            .build(Long.class, Application.class).init(findAll());
    Criterion<Long> objectFilterForIsOnline = ObjectFilter.eq(EntityProperty.IS_ONLINE, true);
    List<Application> applications = repo.query(objectFilterForIsOnline);

    setRelationShipDataForApplications(applications);

    return applications;
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
    setRelationShipDataForApplications(applications);
    return applications;
  }
  
  @ExecuteAsSystem
  public List<Application> findOnlineAndVisibleIvyAppsBy(List<String> names) {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.NAME.toString())
            .build(long.class, Application.class).init(findAll());

    Criterion<Long> objectFilterForInNames = ObjectFilter.in(EntityProperty.NAME.toString(), names);
    Criterion<Long> objectFilterForIsOnline = ObjectFilter.eq(EntityProperty.IS_ONLINE, true);
    Criterion<Long> objectFilterForVisible = ObjectFilter.eq(EntityProperty.IS_VISIBLE, true);
    List<Application> applications = repo.query(objectFilterForInNames, objectFilterForIsOnline, objectFilterForVisible);
    setRelationShipDataForApplications(applications);
    return applications;
  }
  
  @ExecuteAsSystem
  public List<Application> findAbsenceEnableAndOnlineAndVisibleIvyAppsBy(List<String> names) {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.NAME.toString())
            .build(long.class, Application.class).init(findAll());

    Criterion<Long> objectFilterForInNames = ObjectFilter.in(EntityProperty.NAME.toString(), names);
    Criterion<Long> objectFilterForIsOnline = ObjectFilter.eq(EntityProperty.IS_ONLINE, true);
    Criterion<Long> objectFilterForVisible = ObjectFilter.eq(EntityProperty.IS_VISIBLE, true);
    Criterion<Long> objectFilterForIsAbsenceEnabled = ObjectFilter.eq(EntityProperty.IS_ABSENCE_ENABLED, true);
    List<Application> applications = repo.query(objectFilterForInNames, objectFilterForIsOnline, objectFilterForVisible, objectFilterForIsAbsenceEnabled);
    setRelationShipDataForApplications(applications);
    return applications;
  }

  private void setRelationShipDataForApplications(List<Application> applications) {
    for (Application application : applications) {
      setRelationshipDataFor(application);
    }
  }
}
