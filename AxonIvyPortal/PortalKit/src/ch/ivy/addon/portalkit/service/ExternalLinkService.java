package ch.ivy.addon.portalkit.service;

import java.util.List;

import ch.ivy.addon.portalkit.bo.ExternalLink;
import ch.ivy.addon.portalkit.persistence.dao.ExternalLinkDao;

public class ExternalLinkService extends AbstractService<ExternalLink> {

  public ExternalLinkService() {
    super(ExternalLinkDao.class);
  }
  
  @Override
  protected ExternalLinkDao getDao() {
    return (ExternalLinkDao) super.getDao();
  }
  
  public List<ExternalLink> findByUserName(String userName) {
    return getDao().findByUsername(userName);
  }
}
