package ch.ivy.addon.portalkit.service;


import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

abstract class JsonConfigurationService<T extends AbstractConfiguration> {

  public List<T> saveAllPublicConfig(List<T> entities) {
    var existedIds = entities.stream().map(e -> e.getId()).collect(toList());
    List<T> existedEntities = getPublicConfig();
    existedEntities.removeIf(e -> existedIds.contains(e.getId()));
    existedEntities.addAll(entities);
    savePublicConfig(existedEntities);
    return entities;
  }

  public List<T> setPrivateConfig(List<T> entities) {
    sessionUser().setProperty(getConfigKey(), BusinessEntityConverter.entityToJsonValue(entities));
    return entities;
  }

  public T findById(String id) {
    T entity = getPrivateConfig().stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
    if (entity != null) {
      return entity;
    } else {
      return getPublicConfig().stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
    }
  }

  public List<T> findAll() {
    List<T> publicConfigs = getPublicConfig();
    List<T> privateConfigs = getPrivateConfig();
    List<T> allConfigs = new ArrayList<>(publicConfigs);
    allConfigs.addAll(privateConfigs);
    return allConfigs;
  }

  public List<T> getPublicConfig() {
    String jsonValue = Ivy.var().get(getConfigKey());
    if (StringUtils.isBlank(jsonValue)) {
      return new ArrayList<>();
    }
    List<T> entities = BusinessEntityConverter.jsonValueToEntities(jsonValue, getType());
    entities.stream().forEach(e -> e.setIsPublic(true));
    return entities;
  }

  public List<T> getPrivateConfig() {
    String jsonValue = sessionUser().getProperty(getConfigKey());
    if (StringUtils.isBlank(jsonValue)) {
      return new ArrayList<>();
    }
    return BusinessEntityConverter.jsonValueToEntities(jsonValue, getType());
  }

  public T save(T entity) {
    boolean isExisted = findById(entity.getId()) != null;
    if (entity.getIsPublic()) {
      List<T> entities = getPublicConfig();
      updateEntities(isExisted, entity, entities);
      savePublicConfig(entities);
    } else {
      List<T> entities = getPrivateConfig();
      updateEntities(isExisted, entity, entities);
      savePrivateConfig(entities);
    }
    return entity;
  }

  public void delete(String id) {
    List<T> entities = getPrivateConfig();
    boolean isRemoved = entities.removeIf(e -> e.getId().equals(id));
    if (isRemoved) {
      savePrivateConfig(entities);
    } else {
      entities = getPublicConfig();
      isRemoved = entities.removeIf(e -> e.getId().equals(id));
      if (isRemoved) {
        savePublicConfig(entities);
      }
    }
  }

  public void deleteFromPrivateConfig(List<T> entities) {
    var existedIds = entities.stream().map(e -> e.getId()).collect(toList());
    List<T> existedEntities = getPrivateConfig();
    existedEntities.removeIf(e -> existedIds.contains(e.getId()));
    savePrivateConfig(existedEntities);
  }

  public void deletePrivateConfig() {
    sessionUser().removeProperty(getConfigKey());
  }

  public abstract Class<T> getType();

  public abstract String getConfigKey();

  protected void savePublicConfig(Object object) {
    Ivy.var().set(getConfigKey(), BusinessEntityConverter.entityToJsonValue(object));
  }

  protected void savePrivateConfig(Object object) {
    sessionUser().setProperty(getConfigKey(), BusinessEntityConverter.entityToJsonValue(object));
  }

  protected IUser sessionUser() {
    return Ivy.session().getSessionUser();
  }

  private void updateEntities(boolean isExisted, T entity, List<T> entities) {
    if (isExisted) {
      for (T e : entities) {
        if (e.getId().equals(entity.getId())) {
          entities.set(entities.indexOf(e), entity);
          break;
        }
      }
    } else {
      entities.add(entity);
    }
  }
}
