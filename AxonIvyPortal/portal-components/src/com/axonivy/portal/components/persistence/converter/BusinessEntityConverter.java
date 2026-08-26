package com.axonivy.portal.components.persistence.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.JsonListWrapper;
import com.axonivy.portal.components.persistence.domain.BusinessEntity;
import com.axonivy.portal.components.service.exception.PortalException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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
      if (rootNode.isArray() || isListWrapper(rootNode)) {
        throw new PortalException(
            "Expected a single " + classType.getSimpleName() + " JSON object, but got a list/array shape.");
      }
      return mapper.treeToValue(rootNode, classType);
      //return getObjectMapper().readValue(jsonValue, classType);
    } catch (IOException e) {
      throw new PortalException(e);
    }
  }

  private static boolean isListWrapper(JsonNode node) {
    return node.isObject()
        && node.has(JsonListWrapper.ITEMS_FIELD_NAME)
        && node.get(JsonListWrapper.ITEMS_FIELD_NAME).isArray();
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

  public static <T> List<T> jsonValueToEntities(String jsonValue, Class<T> classType) {
    if (StringUtils.isBlank(jsonValue)) {
      return new ArrayList<>();
    }
    try {
      ObjectMapper mapper = getObjectMapper();
      JsonNode rootNode = mapper.readTree(jsonValue);

      // Canonical shape: {"version": "...", "items": [...]}
      if (isListWrapper(rootNode)) {
        JavaType wrapperType = mapper.getTypeFactory()
            .constructParametricType(JsonListWrapper.class, classType);
        JsonListWrapper<T> wrapper = mapper.convertValue(rootNode, wrapperType);
        return Optional.ofNullable(wrapper.getItems()).orElseGet(ArrayList::new);
      }

      // Legacy shapes below, kept for files exported before this migration.
      if (rootNode.isArray()) {
        return mapper.readValue(jsonValue, getListOfJavaType(classType));
      }

      return new ArrayList<>();
    } catch (IOException e) {
      throw new PortalException(e);
    }
  }

  private static <T> JavaType getListOfJavaType (Class<T> type) {
    return getObjectMapper().getTypeFactory().constructCollectionType(List.class, type);
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
