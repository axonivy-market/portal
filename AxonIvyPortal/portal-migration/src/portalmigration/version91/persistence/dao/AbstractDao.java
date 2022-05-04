package portalmigration.version91.persistence.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.property.ICustomProperties;
import ch.ivyteam.ivy.application.property.ICustomProperty;
import portalmigration.persistence.converter.BusinessEntityConverter;
import portalmigration.util.IvyExecutor;
import portalmigration.version91.persistence.domain.BusinessEntity;
import portalmigration.version91.persistence.variable.PropertyKey;

@SuppressWarnings("removal")
public abstract class AbstractDao<T extends BusinessEntity> {

  protected IApplication ivyApplication;

  public void setIvyApplication(IApplication ivyApplication) {
    this.ivyApplication = ivyApplication;
  }

  public AbstractDao() {
    this.ivyApplication = IApplication.current();
  }

  public T findById(long id) {
    T entity = findByIdWithoutRelationship(id);
    return entity;

  }

  /**
   * Find an entity by its Id. This method will not set relationship data for this entity. E.g.:
   * It won't set Server object for Application.
   * 
   * @param id
   * @return {@link BusinessEntity entity}
   */
  protected T findByIdWithoutRelationship(long id) {
    String entityPropertyKey = getEntityPropertyKey(id);
    ICustomProperty customProperty = findPropertyByKey(entityPropertyKey);
    String jsonValue = getCustomPropertyValue(customProperty);
    return parseJsonToObject(jsonValue);
  }

  public List<T> findAll() {
    List<T> entities = new ArrayList<>();
    String propertyPrefixKey = getPropertyPrefixKey();
    List<ICustomProperty> properties = findAllStartingWithPrefix(propertyPrefixKey);
    for (ICustomProperty property : properties) {
      String propertyValue = getCustomPropertyValue(property);
      T entity = parseJsonToObject(propertyValue);
      entities.add(entity);
    }
    return entities;
  }

  private String getPropertyPrefixKey() {
    return String.format(PropertyKey.ENTITY_PROPERTY_KEY_PREFIX, getEntityClassName() + ".");
  }

  private String getCustomPropertyValue(ICustomProperty customProperty) {
    return IvyExecutor.executeAsSystem(() ->{
      return customProperty.getValue();
    });
  }

  private List<ICustomProperty> findAllStartingWithPrefix(String entityPropertyKeyPrefix) {
    return IvyExecutor.executeAsSystem(()->{
      return customProperties().findAllStartingWith(entityPropertyKeyPrefix);
    });
  }

  public T save(T entity) {
    String entityValue = "";
    if (isAddMode(entity)) {
      entityValue = saveNewData(entity);
    } else {
      entityValue = modifyData(entity);
    }
    return parseJsonToObject(entityValue);
  }

  private boolean isAddMode(T entity) {
    return entity.getId() == null;
  }

  private String saveNewData(T entity) {
    String entityValueInJsonFormat;
    Long entityId = getIncrementId();
    entity.setId(entityId);
    entityValueInJsonFormat = modifyData(entity);
    increaseIdPropertyByOne(entityId);
    return entityValueInJsonFormat;
  }

  private String modifyData(T entity) {
    String entityDataInJsonFormat;
    String entityPropertyKey = getEntityPropertyKey(entity.getId());
    entityDataInJsonFormat = parseObjectToJson(entity);
    setValueForCustomProperty(entityPropertyKey, entityDataInJsonFormat);
    return entityDataInJsonFormat;
  }

  private void setValueForCustomProperty(String propertyKey, String value) {
    IvyExecutor.executeAsSystem(() -> {
      ICustomProperty property = findPropertyByKey(propertyKey);
      property.setValue(value);
      return Void.class;
    });
  }

  private void setValueForCustomProperty(String propertyKey, long value) {
    IvyExecutor.executeAsSystem(() -> {
      ICustomProperty property = findPropertyByKey(propertyKey);
      property.setValue(value);
      return Void.class;
    });
  }

  public void delete(T entity) {
    IvyExecutor.executeAsSystem(() -> {
      String entityPropertyKey = getEntityPropertyKey(entity.getId());
      customProperties().delete(entityPropertyKey);
      return Void.class;
    });
  }

  public List<T> saveAll(List<T> entities) {
    List<T> savedEntities = new ArrayList<>();
    for (T entity : entities) {
      T savedEntity = save(entity);
      savedEntities.add(savedEntity);
    }
    return savedEntities;
  }

  public void deleteAll(List<T> entities) {
    for (T entity : entities) {
      delete(entity);
    }
  }

  private ICustomProperty findPropertyByKey(String propertyName) {
    return customProperties().property(propertyName);
  }

  /**
   * There is a security risk when publishing this method. In this method, it gets all
   * {@code ICustomProperty} of system without permission checking, so it is very dangerous if user
   * get another {@code ICustomProperty} which we do not allow.
   * 
   * @return {@code ICustomProperties}
   */
  private ICustomProperties customProperties() {
    return ivyApplication.customProperties();
  }

  private String getEntityPropertyKey(long id) {
    return String.format(PropertyKey.ENTITY_PROPERTY_KEY, getEntityClassName(), id);
  }

  private String getEntityClassName() {
    return determineEntityType().getSimpleName();
  }

  private String parseObjectToJson(T entity) {
    return BusinessEntityConverter.entityToJsonValue(entity);
  }

  private T parseJsonToObject(String jsonValue) {
    return BusinessEntityConverter.jsonValueToEntity(jsonValue, determineEntityType());
  }

  public long getIncrementId() {
    return IvyExecutor.executeAsSystem(() -> {
      ICustomProperty incrementId = findPropertyByKey(PropertyKey.ENTITY_INCREMENT_ID_KEY);
      return incrementId.getLongValue(1L);
    });
  }

  private void increaseIdPropertyByOne(long latestId) {
    setValueForCustomProperty(PropertyKey.ENTITY_INCREMENT_ID_KEY, latestId + 1L);
  }

  @SuppressWarnings("unchecked")
  private Class<T> determineEntityType() {
    Type type = getClass().getGenericSuperclass();
    return (Class<T>) determineEntityType(type);
  }

  private Type determineEntityType(Type type) {
    if (type instanceof ParameterizedType) {
      return ((ParameterizedType) type).getActualTypeArguments()[0];
    } else {
      Type superType = ((Class<?>) type).getGenericSuperclass();
      return determineEntityType(superType);
    }
  }
}
