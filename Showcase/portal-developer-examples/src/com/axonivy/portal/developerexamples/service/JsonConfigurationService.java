package com.axonivy.portal.developerexamples.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.developerexamples.configuration.AbstractConfiguration;
import com.axonivy.portal.developerexamples.converter.BusinessEntityConverter;

import ch.ivyteam.ivy.environment.Ivy;

public abstract class JsonConfigurationService<T extends AbstractConfiguration> {

  public List<T> getPublicConfig() {
    String jsonValue = Ivy.var().get(getConfigKey());
    if (StringUtils.isBlank(jsonValue)) {
      return new ArrayList<>();
    }
    List<T> entities = BusinessEntityConverter.jsonValueToEntities(jsonValue, getType());
    entities.stream().forEach(e -> e.setIsPublic(true));
    return entities;
  }

  public abstract Class<T> getType();

  public abstract String getConfigKey();

}
