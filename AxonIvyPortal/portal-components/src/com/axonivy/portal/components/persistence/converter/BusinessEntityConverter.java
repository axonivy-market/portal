package com.axonivy.portal.components.persistence.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.persistence.domain.BusinessEntity;
import com.axonivy.portal.components.service.exception.PortalException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class provides method to convert Business entity object into Json value
 * and reverse
 */
public final class BusinessEntityConverter {
  public static ObjectMapper objectMapper;

  private BusinessEntityConverter() {
  }

  public static String entityToJsonValue(BusinessEntity entity) {
    try {
      return getObjectMapper().writeValueAsString(entity);
    } catch (JsonProcessingException e) {
      throw new PortalException(e);
    }
  }

  public static <T> T jsonValueToEntity(String jsonValue, Class<T> classType) {
    try {
      return getObjectMapper().readValue(jsonValue, classType);
    } catch (IOException e) {
      throw new PortalException(e);
    }
  }

  public static String entityToJsonValue(Object entity) {
    try {
      return getObjectMapper().writeValueAsString(entity);
    } catch (JsonProcessingException e) {
      throw new PortalException(e);
    }
  }

  public static JsonNode entityToJsonNode(Object entity) {
    try {
      return getObjectMapper().readTree(entityToJsonValue(entity));
    } catch (JsonProcessingException e) {
      throw new PortalException(e);
    }
  }

  public static <T> List<T> jsonValueToEntities(String jsonValue,
      Class<T> classType) {
    if (StringUtils.isBlank(jsonValue)) {
      return new ArrayList<>();
    }
    try {
      return getObjectMapper().readValue(jsonValue, getObjectMapper()
          .getTypeFactory().constructCollectionType(List.class, classType));
    } catch (IOException e) {
      throw new PortalException(e);
    }
  }

  public static ObjectMapper getObjectMapper() {
    if (objectMapper == null) {
      objectMapper = new ObjectMapper()
          .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    return objectMapper;
  }

  public static String prettyPrintEntityToJsonValue(Object entity) {
    try {
      return getObjectMapper().writerWithDefaultPrettyPrinter()
          .writeValueAsString(entity);
    } catch (JsonProcessingException e) {
      throw new PortalException(e);
    }
  }
}
