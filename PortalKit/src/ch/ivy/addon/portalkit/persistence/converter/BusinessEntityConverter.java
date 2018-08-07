package ch.ivy.addon.portalkit.persistence.converter;

import org.boon.json.ObjectMapper;
import org.boon.json.implementation.ObjectMapperImpl;

import ch.ivy.addon.portalkit.persistence.domain.BusinessEntity;

/**
 * This class provides method that uses API of org.boon.json to convert Business
 * entity object into Json value and reverse
 */
public final class BusinessEntityConverter {

  private BusinessEntityConverter() {
  }

  public static String entityToJsonValue(BusinessEntity entity) {
    ObjectMapper objectMapper = new ObjectMapperImpl();
    return objectMapper.writeValueAsString(entity);
  }

  public static <T> T jsonValueToEntity(String jsonValue, Class<T> classType) {
    ObjectMapper objectMapper = new ObjectMapperImpl();
    return objectMapper.readValue(jsonValue, classType);
  }
}
