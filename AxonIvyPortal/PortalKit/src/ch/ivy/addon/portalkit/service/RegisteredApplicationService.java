package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.IvyCacheIdentifier;
import ch.ivy.addon.portalkit.persistence.dao.ApplicationDao;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivyteam.ivy.data.cache.IDataCacheEntry;

public class RegisteredApplicationService extends AbstractService<Application> {

  public RegisteredApplicationService() {
    super(ApplicationDao.class);
  }

  @Override
  protected ApplicationDao getDao() {
    return (ApplicationDao) super.getDao();
  }

  public Application findByDisplayNameAndName(String displayName, String name) {
    return getDao().findByDisplayNameAndName(displayName, name);
  }

  public Application findByName(String name) {
    return getDao().findByName(name);
  }

  public List<Application> findByNames(List<String> names) {
    return getDao().findByNames(names);
  }

  public String convertApplicationIdsToString(List<Long> applicationIds) {
    if (CollectionUtils.isEmpty(applicationIds)) {
      return StringUtils.EMPTY;
    }
    return applicationIds.stream().map(String::valueOf).collect(Collectors.joining(","));
  }

  /**
   * Find all register 3rd party apps
   * @return all register 3rd party apps
   */
  @SuppressWarnings("unchecked")
  public List<Application> findAllThirdPartyApplication(){
    IDataCacheEntry sessionCache = IvyCacheService.newInstance().getSessionCache(IvyCacheIdentifier.APPLICATION_GROUP_NAME, IvyCacheIdentifier.THIRD_PARTY_APPLICATIONS);
    if (sessionCache == null) {
      List<Application> applications = new ArrayList<>();
      applications.addAll(getDao().findAllThirdPartyApplications());
      IvyCacheService.newInstance().setSessionCache(IvyCacheIdentifier.APPLICATION_GROUP_NAME, IvyCacheIdentifier.THIRD_PARTY_APPLICATIONS, applications);
      return applications;
    } else {
      return (List<Application>) sessionCache.getValue();
    }
  }
}
