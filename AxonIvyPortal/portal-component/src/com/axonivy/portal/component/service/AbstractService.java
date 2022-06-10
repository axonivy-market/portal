package com.axonivy.portal.component.service;

import static ch.ivyteam.ivy.server.ServerFactory.getServer;

import java.lang.reflect.Constructor;
import java.util.List;

import com.axonivy.portal.component.persistence.dao.AbstractDao;
import com.axonivy.portal.component.persistence.dao.ExecuteAsSystemDecorator;
import com.axonivy.portal.component.persistence.domain.BusinessEntity;
import com.axonivy.portal.component.service.exception.PortalException;
import com.axonivy.portal.component.util.IvyExecutor;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;

class AbstractService<T extends BusinessEntity> {

  private AbstractDao<T> dao;
  
  public AbstractService(AbstractDao<T> abstractDao) {
    this.dao = abstractDao;
  }

  /**
   * Proxy constructor of {@link AbstractService} instance to implicitly 
   * Application to {@link AbstractDao} property
   * 
   * @param daoClassType
   */
  public AbstractService(Class<? extends AbstractDao<T>> daoClassType) {
    IApplication app = getSystemApp();
    if (app == null) {
      app = Ivy.request().getApplication();
    }
    this.dao = ExecuteAsSystemDecorator.decorate(newInstance(daoClassType), app);
  }
  
  private IApplication getSystemApp() {
    try {
      return IvyExecutor.executeAsSystem(() -> getServer().getApplicationConfigurationManager().getSystemApplication());
    } catch (Exception e) {
      Ivy.log().error("Can not find system application", e);
      return null;
    }
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
