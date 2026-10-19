package ch.ivy.addon.portalkit.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.type.CollectionType;

import ch.ivy.addon.portalkit.service.exception.PortalException;

/**
 * This class provides method to convert User entity object into Json value and reverse
 */
public final class UserEntityConverter {

  private UserEntityConverter() {}

  public static <T> String entitiesToJson(List<T> entities) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.writeValueAsString(entities);
    } catch (JacksonException e) {
      throw new PortalException(e);
    }
  }

  public static <T> String entityToJson(T entity) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.writeValueAsString(entity);
    } catch (JacksonException e) {
      throw new PortalException(e);
    }
  }

  public static <T> List<T> jsonToEntities(String jsonValue, Class<T> classType) {
    ObjectMapper objectMapper = JsonMapper.builder()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).build();
    try {
      CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, classType);
      return objectMapper.readValue(jsonValue, listType);
    } catch (JacksonException e) {
      throw new PortalException(e);
    }
  }

  public static <T> T jsonToEntity(String jsonValue, Class<T> classType) {
    ObjectMapper objectMapper = JsonMapper.builder()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).build();
    try {
      return objectMapper.readValue(jsonValue, classType);
    } catch (JacksonException e) {
      throw new PortalException(e);
    }
  }
}
