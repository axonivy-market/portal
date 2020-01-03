package ch.ivy.addon.portalkit.service;

import java.util.List;

import ch.ivy.addon.portalkit.bo.ExternalLink;
import ch.ivy.addon.portalkit.persistence.dao.ExternalLinkDao;

public class ExternalLinkService extends AbstractService<ExternalLink> {

  private static ExternalLinkService externalLinkServiceInstance;
  
  public ExternalLinkService() {
    super(ExternalLinkDao.class);
  }
  
  public static ExternalLinkService getInstance() {
    if (externalLinkServiceInstance == null) {
      externalLinkServiceInstance = new ExternalLinkService();
    }
    return externalLinkServiceInstance;
  }
  
  @Override
  protected ExternalLinkDao getDao() {
    return (ExternalLinkDao) super.getDao();
  }
  
  public List<ExternalLink> findStartableLink(String username) {
    return getDao().findStartableLink(username);
  }
  
  public void delete(long id) {
    getDao().delete(id);
  }

}
