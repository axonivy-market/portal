package ch.ivy.addon.portalkit.service;

import java.lang.reflect.Constructor;
import java.util.List;

import ch.ivy.addon.portalkit.persistence.dao.AbstractDao;
import ch.ivy.addon.portalkit.persistence.dao.ExecuteAsSystemDecorator;
import ch.ivy.addon.portalkit.persistence.domain.BusinessEntity;

class AbstractService<T extends BusinessEntity> {

  private AbstractDao<T> dao;
  private PortalConnectorDetector portalConnectorDetector;
  
  public AbstractService(AbstractDao<T> abstractDao, PortalConnectorDetector portalConnectorDetector) {
    this.portalConnectorDetector = portalConnectorDetector;
    this.dao = abstractDao;
  }

  /**
   * Proxy constructor of {@link AbstratService} instance to implicitly pass PortalConnector
   * Application to {@link AbstractDao} property
   * 
   * @param daoClassType
   */
  public AbstractService(Class<? extends AbstractDao<T>> daoClassType) {
    this.portalConnectorDetector = new PortalConnectorDetector();
    this.dao = ExecuteAsSystemDecorator.decorate(newInstance(daoClassType), this.portalConnectorDetector.getPortalConnectorApplication());
  }

  /**
   * Try to instantiate instance of AbstractDao with IApplication parameter, then by pass
   * PortalConnectorApplication
   * 
   * @param abstractDaoClassType
   * @return {@link AbstractDao}
   */
  @SuppressWarnings("unchecked")
  public static <U extends AbstractDao<?>> U newInstance(Class<U> abstractDaoClassType) {
    try {
      for (Constructor<?> c : abstractDaoClassType.getConstructors()) {
        if (isDefaultConstructor(c)) {
          return (U) c.newInstance();
        }
      }
      return abstractDaoClassType.newInstance();
    } catch (Exception e) {
      throw new RuntimeException("Cannot instantiate Dao instance", e);
    }
  }

  private static boolean isDefaultConstructor(Constructor<?> c) {
    return c.getParameterTypes().length == 0;
  }

  protected PortalConnectorDetector getPortalConnectorDetector() {
    return this.portalConnectorDetector;
  }

  protected AbstractDao<T> getDao() {
    return dao;
  }

  protected void setDao(AbstractDao<T> dao) {
    this.dao = dao;
  }

  /**
   * Creates a new entity or updates an old entity in ivy application property.
   * 
   * @return The newly created or updated entity.
   */
  public T save(T entity) {
    return dao.save(entity);
  }

  /**
   * Deletes a entity in ivy application property
   * 
   * @param entity
   */
  public void delete(T entity) {
    dao.delete(entity);
  }

  /**
   * Gets entity object from ivy application property by id
   */
  public T findById(long id) {
    return getDao().findById(id);
  }

  /**
   * Gets all entity object from ivy application property
   */
  public List<T> findAll() {
    return getDao().findAll();
  }

  /**
   * Creates entities or updates old entities in ivy application property.
   * 
   * @return The newly created or updated entity.
   */
  public List<T> saveAll(List<T> entities) {
    return getDao().saveAll(entities);
  }

  /**
   * Deletes entities in ivy application property
   * 
   * @param entity
   */
  public void deleteAll(List<T> entities) {
    getDao().deleteAll(entities);
  }


  public long getIncrementId() {
    return getDao().getIncrementId();
  }
}
