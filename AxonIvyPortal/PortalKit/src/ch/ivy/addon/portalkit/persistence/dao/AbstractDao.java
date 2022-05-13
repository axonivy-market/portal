package ch.ivy.addon.portalkit.persistence.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.persistence.domain.BusinessEntity;
import ch.ivy.addon.portalkit.persistence.exception.DaoException;
import ch.ivy.addon.portalkit.persistence.variable.PropertyKey;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.property.ICustomProperties;
import ch.ivyteam.ivy.application.property.ICustomProperty;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.util.Property;

public abstract class AbstractDao<T extends BusinessEntity> {

  protected IApplication ivyApplication;

  public AbstractDao(IApplication application) {
    this.ivyApplication = application;
  }

  public AbstractDao() {
    this.ivyApplication = IApplication.current();
  }

  /**
   * Gets all data in portal database. In case there is no data, it will return empty list.
   * 
   * @return {@link List}
   */
  @ExecuteAsSystem
  public List<Property> getAllPortalDataProperties() {
    List<Property> portalDatas = new ArrayList<>();
    try {
      List<ICustomProperty> customProperties = findAllStartingWithPrefix(PropertyKey.PORTAL_PROPERTY_START);
      for (ICustomProperty customProperty : customProperties) {
        String customPropertyName = getCustomPropertyName(customProperty);
        String customPropertyValue = getCustomPropertyValue(customProperty);
        Property property = new Property(customPropertyName, customPropertyValue);
        portalDatas.add(property);
      }
    } catch (Exception exception) {
      String message = String.format("Can not get all data of Portal database");
      Ivy.log().error(message, exception);
      throw new DaoException(message, exception);
    }
    return portalDatas;
  }

  private String getCustomPropertyName(ICustomProperty customProperty) {
    return customProperty.getName();
  }


  @ExecuteAsSystem
  public T findById(long id) {
    T entity = findByIdWithoutRelationship(id);
    return entity;

  }

  /**
   * Finded an entity by its Id. This method will not set relationship data for this entity. E.g.:
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

  @ExecuteAsSystem
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
    return customProperty.getValue();
  }

  private List<ICustomProperty> findAllStartingWithPrefix(String entityPropertyKeyPrefix) {
    return customProperties().findAllStartingWith(entityPropertyKeyPrefix);
  }

  @ExecuteAsSystem
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
    ICustomProperty property = findPropertyByKey(propertyKey);
    property.setValue(value);
  }

  private void setValueForCustomProperty(String propertyKey, long value) {
    ICustomProperty property = findPropertyByKey(propertyKey);
    property.setValue(value);
  }

  @ExecuteAsSystem
  public void delete(T entity) {
    String entityPropertyKey = getEntityPropertyKey(entity.getId());
    customProperties().delete(entityPropertyKey);
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

  @ExecuteAsSystem
  public long getIncrementId() {
    ICustomProperty incrementId = findPropertyByKey(PropertyKey.ENTITY_INCREMENT_ID_KEY);
    return incrementId.getLongValue(1L);
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
