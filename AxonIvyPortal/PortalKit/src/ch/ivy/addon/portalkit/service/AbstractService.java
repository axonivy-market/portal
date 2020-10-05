package ch.ivy.addon.portalkit.service;


import java.lang.reflect.Constructor;
import java.util.List;

import ch.ivy.addon.portalkit.persistence.dao.AbstractDao;
import ch.ivy.addon.portalkit.persistence.domain.BusinessEntity;
import ch.ivy.addon.portalkit.service.exception.PortalException;

class AbstractService<T extends BusinessEntity> {

  private AbstractDao<T> dao;
  
  public AbstractService(AbstractDao<T> abstractDao) {
    this.dao = abstractDao;
  }

  /**
   * Try to instantiate instance of AbstractDao with IApplication parameter
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
      return abstractDaoClassType.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      throw new PortalException("Cannot instantiate Dao instance", e);
    }
  }

  private static boolean isDefaultConstructor(Constructor<?> c) {
    return c.getParameterTypes().length == 0;
  }

  protected AbstractDao<T> getDao() {
    return dao;
  }

  protected void setDao(AbstractDao<T> dao) {
    this.dao = dao;
  }

  /**
   * Creates a new entity or updates an old entity in ivy application property.
   * @param entity 
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
   * @param id 
   * @return entity
   */
  public T findById(long id) {
    return getDao().findById(id);
  }

  /**
   * Gets all entity object from ivy application property
   * @return entities
   */
  public List<T> findAll() {
    return getDao().findAll();
  }

  /**
   * Creates entities or updates old entities in ivy application property.
   * @param entities 
   * 
   * @return The newly created or updated entity.
   */
  public List<T> saveAll(List<T> entities) {
    return getDao().saveAll(entities);
  }

  /**
   * Deletes entities in ivy application property
   * @param entities 
   */
  public void deleteAll(List<T> entities) {
    getDao().deleteAll(entities);
  }

  public long getIncrementId() {
    return getDao().getIncrementId();
  }
}
