package ch.ivy.addon.portalkit.service;


import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.bo.jsonversion.DashboardFilterJsonVersion;
import com.axonivy.portal.migration.dashboard.migrator.JsonDashboardMigrator;
import com.axonivy.portal.migration.dashboardfilter.migrator.JsonDashboardFilterMigrator;
import com.axonivy.portal.migration.dashboardtemplate.migrator.JsonDashboardTemplateMigrator;
import com.axonivy.portal.migration.statistic.migrator.JsonStatisticMigrator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardTemplate;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetFilterModel;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

public abstract class JsonConfigurationService<T extends AbstractConfiguration> {

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
    return getPrivateConfig().stream()
      .filter(e -> e.getId().equals(id))
      .findFirst()
      .orElseGet(() -> getPublicConfig().stream()
        .filter(e -> e.getId().equals(id))
        .findFirst()
        .orElse(null));
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
    List<T> entities = Optional.ofNullable(convertToLatestVersion(jsonValue))
      .orElseGet(() -> BusinessEntityConverter.jsonValueToEntities(jsonValue, getType()));
    entities.forEach(e -> e.setIsPublic(true));
    return entities;
  }

  public List<T> getPrivateConfig() {
    String jsonValue = sessionUser().getProperty(getConfigKey());
    if (StringUtils.isBlank(jsonValue)) {
      return new ArrayList<>();
    }
    return Optional.ofNullable(convertToLatestVersion(jsonValue))
      .orElseGet(() -> BusinessEntityConverter.jsonValueToEntities(jsonValue, getType()));
  }

  private List<T> convertToLatestVersion(String jsonValue) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      if (getType() == WidgetFilterModel.class) {
        JsonDashboardFilterMigrator migrator = new JsonDashboardFilterMigrator(mapper.readTree(jsonValue));
        return BusinessEntityConverter.convertJsonNodeToList(migrator.migrate(), getType());
      }
      if (getType() == Dashboard.class) {
        JsonDashboardMigrator migrator = new JsonDashboardMigrator(mapper.readTree(jsonValue));
        return BusinessEntityConverter.convertJsonNodeToList(migrator.migrate(), getType());
      }
      if (getType() == DashboardTemplate.class) {
        JsonDashboardTemplateMigrator migrator = new JsonDashboardTemplateMigrator(mapper.readTree(jsonValue));
        return BusinessEntityConverter.convertJsonNodeToList(migrator.migrate(), getType());
      }
      if (getType() == Statistic.class) {
        JsonStatisticMigrator migrator = new JsonStatisticMigrator(mapper.readTree(jsonValue));
        return BusinessEntityConverter.convertJsonNodeToList(migrator.migrate(), getType());
      }
    } catch (JsonProcessingException ex) {
      Ivy.log().error("Failed to read dashboard template from JSON {0}", ex, jsonValue);
    }
    return null;
  }

  public T save(T entity) {
    boolean isExisted = findById(entity.getId()) != null;
    List<T> entities = entity.getIsPublic() ? getPublicConfig() : getPrivateConfig();
    updateEntities(isExisted, entity, entities);
    if (entity.getIsPublic()) {
      savePublicConfig(entities);
    } else {
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
      for (int i = 0; i < entities.size(); i++) {
        if (entities.get(i).getId().equals(entity.getId())) {
          entities.set(i, entity);
          return;
        }
      }
    } else {
      entity.setVersion(DashboardFilterJsonVersion.LATEST_VERSION.getValue());
      entities.add(entity);
    }
  }
}
