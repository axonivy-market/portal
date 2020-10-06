package ch.ivy.addon.portalkit.service;

import java.util.List;

import static ch.ivy.addon.portalkit.constant.IvyCacheIdentifier.THIRD_PARTY_APPLICATIONS;
import ch.ivy.addon.portalkit.persistence.dao.ApplicationDao;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivyteam.ivy.data.cache.IDataCacheEntry;

public class RegisteredApplicationService extends AbstractService<Application> {

  public RegisteredApplicationService() {
    super(new ApplicationDao());
  }

  @Override
  protected ApplicationDao getDao() {
    return (ApplicationDao) super.getDao();
  }

  /**
   * Find all register 3rd party apps
   * @return all register 3rd party apps
   */
  @SuppressWarnings("unchecked")
  public List<Application> findAllThirdPartyApplication(){
    IDataCacheEntry sessionCache = IvyCacheService.newInstance().getSessionCache(THIRD_PARTY_APPLICATIONS, THIRD_PARTY_APPLICATIONS);
    if (sessionCache == null) {
      List<Application> applications = getDao().findAllThirdPartyApplications();
      IvyCacheService.newInstance().setSessionCache(THIRD_PARTY_APPLICATIONS, THIRD_PARTY_APPLICATIONS, applications);
      return applications;
    } else {
      return (List<Application>) sessionCache.getValue();
    }
  }
}
