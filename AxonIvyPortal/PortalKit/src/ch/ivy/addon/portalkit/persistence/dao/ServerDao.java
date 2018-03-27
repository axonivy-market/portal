package ch.ivy.addon.portalkit.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.boon.criteria.ObjectFilter;
import org.boon.datarepo.Repo;
import org.boon.datarepo.Repos;

import ch.ivy.addon.portalkit.enums.WSAuthenticationType;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivy.addon.portalkit.util.PasswordUtils;
import ch.ivyteam.ivy.application.ActivityOperationState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;

public class ServerDao extends AbstractDao<Server> {

  public ServerDao() {}

  public ServerDao(IApplication application) {
    super(application);
  }

  @SuppressWarnings("unchecked")
  @ExecuteAsSystem
  public List<Server> findActiveServers() {
    repo =
        Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.IS_ONLINE.toString())
            .build(long.class, Server.class).init(findAll());

    List<Server> activeServers = repo.query(ObjectFilter.eqBoolean(EntityProperty.IS_ONLINE.toString(), true));
    setRelationShipDataFor(activeServers);
    return activeServers;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void setRelationshipDataFor(Server server) {
    ApplicationDao applicationDao = new ApplicationDao(this.ivyApplication);
    Repo<Long, Application> applicationRepo =
        Repos.builder().primaryKey(EntityProperty.ID.toString()).searchIndex(EntityProperty.SERVER_ID.toString())
            .build(long.class, Application.class).init(applicationDao.findAll());

    List<Application> applications =
        applicationRepo.query(ObjectFilter.eq(EntityProperty.SERVER_ID.toString(), server.getId()));
    server.setApplications(applications);
  }

  private void setRelationShipDataFor(List<Server> servers) {
    for (Server server : servers) {
      setRelationshipDataFor(server);
    }
  }

  @ExecuteAsSystem
  public List<Application> getAllApplicationsUserCanWorkOn(Server server) {
    List<Application> portalApplications = new ArrayList<>();
    List<IApplication> ivyApplications =
        ServerFactory.getServer().getApplicationConfigurationManager().getApplications();
    for (IApplication ivyApplication : ivyApplications) {
      if (ivyApplication.isSystem()) {
        continue;
      }
      if (ActivityOperationState.ACTIVE.equals(ivyApplication.getActivityOperationState())) {
        IUser user = ivyApplication.getSecurityContext().findUser(Ivy.session().getSessionUserName());
        if (user != null) {
          Application application = toApplication(server, ivyApplication);
          portalApplications.add(application);
        }
      }
    }
    return portalApplications;

  }

  @Override
  @ExecuteAsSystem
  public Server findById(long id) {
    Server server = super.findById(id);
    fillPassword(server);
    return server;
  }

  @Override
  @ExecuteAsSystem
  public List<Server> findAll() {
    List<Server> servers = super.findAll();
    servers.forEach(this::fillPassword);
    return servers;
  }

  @Override
  @ExecuteAsSystem
  public Server save(Server entity) {
    if (hasPassword(entity)) {
      PasswordUtils.save(entity.getPath(), entity.getPassword());
    }
    entity.setPassword(null);
    Server server = super.save(entity);
    fillPassword(server);
    return server;
  }

  @Override
  @ExecuteAsSystem
  public void delete(Server entity) {
    super.delete(entity);
    if (hasPassword(entity)) {
      PasswordUtils.delete(entity.getPath());
    }
  }

  private boolean hasPassword(Server entity) {
    return WSAuthenticationType.NONE != entity.getWsAuthenticationType();
  }

  private void fillPassword(Server server) {
    if (hasPassword(server)) {
      server.setPassword(PasswordUtils.find(server.getPath()));
    }
  }

  private Application toApplication(Server server, IApplication iApplication) {
    Application application = new Application();
    application.setIsOnline(true);
    application.setIsSupportAbsenceSettings(true);
    application.setIsSupportEmailSettings(true);
    application.setIsVisible(true);
    application.setName(iApplication.getName());
    application.setDisplayName(iApplication.getName());
    application.setServerId(server.getId());
    application.setId(iApplication.getId());
    return application;
  }
}
