package ch.ivy.addon.portalkit.service;

import java.util.List;

import ch.ivy.addon.portalkit.persistence.dao.AbstractDao;
import ch.ivy.addon.portalkit.persistence.dao.ExecuteAsSystemDecorator;
import ch.ivy.addon.portalkit.persistence.domain.BusinessEntity;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.util.Property;

/**
 * PortalDataService is to provide methods those retrieve {@link BusinessEntity} of portal data
 */
public class PortalDataService {

  private AbstractDao<BusinessEntity> abstractDao;

  public PortalDataService() {
    abstractDao = ExecuteAsSystemDecorator.decorate(new BusinessEntityDao(), Ivy.request().getApplication());
  }

  public List<Property> getAllPortalCustomProperty() {
    return abstractDao.getAllPortalDataProperties();
  }

  public long getPortalIncrementIdValue() {
    return abstractDao.getIncrementId();
  }

  /**
   * This class is common Dao for business entity to provide common methods for PortalDataService
   */
  private static class BusinessEntityDao extends AbstractDao<BusinessEntity> {


    public BusinessEntityDao() {
      super();
    }

    /**
     * This method is unused but have to exsist for Proxy initialization
     * @param application 
     */
    @SuppressWarnings("unused")
    public BusinessEntityDao(IApplication application) {
      super(application);
    }

  }
}
