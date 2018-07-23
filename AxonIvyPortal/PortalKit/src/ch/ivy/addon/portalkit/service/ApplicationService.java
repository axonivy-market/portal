package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

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

  public List<Application> findAllThirdPartyApplications() {
    return getDao().findAllThirdPartyApplications();
  }
  
  public List<Application> findAllIvyApplications() {
    return getDao().findAllIvyApplications();
  }

  public List<Application> findOtherApplicationsHaveSameNameAndServer(Application application) {
    return getDao().findOtherApplicationsHaveSameNameAndServer(application);
  }

  public List<Application> findByNameAndServerId(String name, Long serverId) {
    return getDao().findByNameAndServerId(name, serverId);
  }
  
  public Application findByDisplayNameAndNameAndServerId(String displayName, String name, Long serverId) {
    return getDao().findByDisplayNameAndNameAndServerId(displayName, name, serverId);
  }
  
  public List<Application> findOnlineApplicationByServerId(Long serverId) {
    return getDao().findOnlineAppByServerId(serverId);
  }
  
  public List<Application> findOnlineIvyApps() {
    return getDao().findOnlineIvyApps();
  }
  

  public Application findByName(String name) {
    return getDao().findByName(name);
  }
  
  public List<Application> findByNames(List<String> names) {
    return getDao().findByNames(names);
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
  
  public List<Long> getApplicationIds(List<IvyApplication> ivyApplications) {
	if (CollectionUtils.isEmpty(ivyApplications)) {
		return Collections.emptyList();
	}
	return ivyApplications.stream()
			.filter(app -> app.getIsActive())
			.map(IvyApplication::getId)
			.collect(Collectors.toList());
  }
  
  public List<Long> getApplicationIdsByApplicationNames(List<String> applicationNames, List<IvyApplication> ivyApplications){
	if (CollectionUtils.isEmpty(applicationNames) || CollectionUtils.isEmpty(ivyApplications)) {
		return Collections.emptyList();
	}
	return ivyApplications.stream()
			.filter(app -> applicationNames.contains(app.getName()))
			.map(IvyApplication::getId)
			.collect(Collectors.toList());
  }
  
  public String convertApplicationIdsToString(List<Long> applicationIds) {
	if (CollectionUtils.isEmpty(applicationIds)) {
		return StringUtils.EMPTY;
	}
	return applicationIds.stream()
			.map(id -> String.valueOf(id))
			.collect(Collectors.joining(","));
  }
  
  public long countIvyApplications(List<Application> applications) {
    return applications.stream().filter(application -> application.getServerId() != null).count();
  }
}
