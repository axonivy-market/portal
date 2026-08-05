package com.axonivy.portal.components.persistence.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.persistence.domain.BusinessEntity;
import com.axonivy.portal.components.service.exception.PortalException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.json.JsonMapper;

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
    if (StringUtils.isBlank(jsonValue)) {
      return null;
    }
    try {
      ObjectMapper mapper = getObjectMapper();
      JsonNode rootNode = mapper.readTree(jsonValue);
      JsonNode targetNode = unwrapIfNeeded(rootNode, classType, mapper);
      return mapper.treeToValue(targetNode, classType);
    } catch (IOException e) {
      throw new PortalException(e);
    }
  }

  private static JsonNode unwrapIfNeeded(JsonNode rootNode, Class<?> classType, ObjectMapper mapper) {
    // A wrapper looks like {"someRootKey": {...actual object...}} — exactly one field,
    // whose value is itself an object, and whose name isn't one of the target class's own properties.
    if (!rootNode.isObject() || rootNode.size() != 1) {
      return rootNode;
    }
    Map.Entry<String, JsonNode> onlyField = rootNode.properties().iterator().next();
    if (!onlyField.getValue().isObject()) {
      return rootNode;
    }
    Set<String> knownProperties = mapper.getSerializationConfig()
        .introspect(mapper.constructType(classType))
        .findProperties().stream()
        .map(BeanPropertyDefinition::getName)
        .collect(Collectors.toSet());
    return knownProperties.contains(onlyField.getKey()) ? rootNode : onlyField.getValue();
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
      objectMapper = JsonMapper
          .builder()
          .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
          .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
          .build(); 
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
