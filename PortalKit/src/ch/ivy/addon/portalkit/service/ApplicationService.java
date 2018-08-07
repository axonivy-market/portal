package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.persistence.dao.ApplicationDao;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.ws.addon.IvyApplication;

public class ApplicationService extends AbstractService<Application> {

  public ApplicationService() {
    super(ApplicationDao.class);
  }

  @Override
  protected ApplicationDao getDao() {
    return (ApplicationDao) super.getDao();
  }

  public List<Application> findThirdPartyApplications() {
    return getDao().findThirdPartyApplications();
  }

  public List<Application> findOtherApplicationsHaveSameNameAndServer(Application application) {
    return getDao().findOtherApplicationsHaveSameNameAndServer(application);
  }

  public List<Application> findByNameAndServerId(String name, Long serverId) {
    return getDao().findByNameAndServerId(name, serverId);
  }

  public Application findByName(String name) {
    return getDao().findByName(name);
  }

  public List<String> getApplicationNames(List<IvyApplication> ivyApplications) {
    List<String> applicationNames = new ArrayList<>();
    for (IvyApplication ivyApplication : ivyApplications) {
      if (ivyApplication.getIsActive()) {
        applicationNames.add(ivyApplication.getName());
      }
    }
    return applicationNames;
  }
}
