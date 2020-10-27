package ch.ivy.addon.portalkit.service;

import static ch.ivy.addon.portalkit.constant.IvyCacheIdentifier.THIRD_PARTY_APPLICATIONS;

import java.util.List;
import java.util.Optional;

import ch.ivy.addon.portalkit.persistence.dao.ApplicationDao;
import ch.ivy.addon.portalkit.persistence.domain.Application;

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
    Optional<Object> sessionCache = IvyCacheService.newInstance().getSessionCacheValue(THIRD_PARTY_APPLICATIONS, THIRD_PARTY_APPLICATIONS);
    if (!sessionCache.isPresent() || sessionCache.isEmpty()) {
      List<Application> applications = getDao().findAllThirdPartyApplications();
      IvyCacheService.newInstance().setSessionCache(THIRD_PARTY_APPLICATIONS, THIRD_PARTY_APPLICATIONS, applications);
      return applications;
    } else {
      return (List<Application>) sessionCache.get();
    }
  }
}
