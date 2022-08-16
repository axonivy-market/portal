package com.axonivy.portal.components.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.constant.IvyCacheIdentifier;
import com.axonivy.portal.components.constant.PortalComponentConstants;
import com.axonivy.portal.components.ivydata.bo.IvyApplication;
import com.axonivy.portal.components.ivydata.service.IApplicationService;
import com.axonivy.portal.components.ivydata.service.impl.ApplicationService;
import com.axonivy.portal.components.persistence.dao.ApplicationDao;
import com.axonivy.portal.components.persistence.domain.Application;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;

public class RegisteredApplicationService extends AbstractService<Application> {

  public RegisteredApplicationService() {
    super(ApplicationDao.class);
  }

  @Override
  protected ApplicationDao getDao() { 
    return (ApplicationDao) super.getDao();
  }
  
  /**
   * Finds names of the active applications registered by the admin user; if empty, means there is no
   * configuration in admin settings, finds all of active applications of the engine.
   * 
   * @param username
   * @return {@link java.util.List} of application names
   */
  @SuppressWarnings("unchecked")
  public List<String> findActiveIvyAppsUserCanWorkOn(String username) {
    if (StringUtils.isBlank(username)) {
      return new ArrayList<>();
    }
    
    Optional<Object> cacheValueOpt = IvyCacheService.newInstance().getSessionCacheValue(IvyCacheIdentifier.ONLINE_APPLICATIONS_USER_CAN_WORK_ON, username);
    if (cacheValueOpt.isPresent()) {
      return (List<String>) cacheValueOpt.get();
    }

    List<String> workOnApps = findInvolvedAppsOfUser(username);
    
    IvyCacheService.newInstance().setSessionCache(IvyCacheIdentifier.ONLINE_APPLICATIONS_USER_CAN_WORK_ON, username, workOnApps);
    return workOnApps;
  }
  
  private List<String> findInvolvedAppsOfUser(String username) {
    IApplicationService applicationService = ApplicationService.newInstance();
    List<String> registeredApplicationNames = findAllIvyApplications().stream().map(Application::getName).collect(Collectors.toList());
    return applicationService.findActiveAllInvolvedUser(username).stream()
        .filter(app -> CollectionUtils.isEmpty(registeredApplicationNames) || CollectionUtils.containsAny(registeredApplicationNames, app.getName()))
        .map(IvyApplication::getName)
        .collect(Collectors.toList());
  }

  @SuppressWarnings("unchecked")
  public List<Application> findAllIvyApplications() {
    String sessionUserName = Ivy.session().getSessionUserName();
    Optional<Object> resutls = IvyCacheService.newInstance().getSessionCacheValue(sessionUserName, IvyCacheIdentifier.ALL_IVY_APPLICATIONS);
    if (resutls.isEmpty()) {
      List<Application> allIvyApplications = getDao().findAllIvyApplications();
      IvyCacheService.newInstance().setSessionCache(sessionUserName, IvyCacheIdentifier.ALL_IVY_APPLICATIONS, allIvyApplications);
      return allIvyApplications;
    } else {
      return (List<Application>) resutls.get();
    }
  }

  /**
   * Finds names of the active applications based on the configuration; if the current app is not Portal, returns the current app.
   * Otherwise, finds the active applications registered by the admin user; if empty, means there is no
   * configuration in admin settings, finds all of active applications of the engine.
   * 
   * @param username
   * @return {@link java.util.List} of application names
   */
  @SuppressWarnings("unchecked")
  public List<String> findActiveIvyAppsBasedOnConfiguration(String username) {
    if (StringUtils.isBlank(username)) {
      return new ArrayList<>();
    }
    
    Optional<Object> cacheValueOpt = IvyCacheService.newInstance().getSessionCacheValue(IvyCacheIdentifier.ONLINE_APPLICATIONS_BASED_ON_CONFIGURATION, username);
    if (cacheValueOpt.isPresent()) {
      return (List<String>) cacheValueOpt.get();
    }
    
    List<String> workOnApps;
    String currentApplicationName =
        Optional.ofNullable(Ivy.request().getApplication()).map(IApplication::getName).orElse(StringUtils.EMPTY);
    if (PortalComponentConstants.PORTAL_APPLICATION_NAME.equals(currentApplicationName)) {
      workOnApps = findActiveIvyAppsUserCanWorkOn(Ivy.session().getSessionUserName());
    } else {
      workOnApps = Arrays.asList(currentApplicationName);
    }
    
    IvyCacheService.newInstance().setSessionCache(IvyCacheIdentifier.ONLINE_APPLICATIONS_BASED_ON_CONFIGURATION, username, workOnApps);
    return workOnApps;
  }
  
}
