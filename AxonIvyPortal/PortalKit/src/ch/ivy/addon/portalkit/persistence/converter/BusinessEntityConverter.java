package ch.ivy.addon.portalkit.persistence.converter;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portalkit.persistence.domain.BusinessEntity;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.environment.Ivy;

/**
 * This class provides method to convert Business
 * entity object into Json value and reverse
 */
public final class BusinessEntityConverter {

  private BusinessEntityConverter() {
  }

  public static String entityToJsonValue(BusinessEntity entity) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.writeValueAsString(entity);
    } catch (JsonProcessingException e) {
      Ivy.log().error("Can't write json value", e);
      throw new PortalException(e);
    }
  }

  public static <T> T jsonValueToEntity(String jsonValue, Class<T> classType) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(jsonValue, classType);
    } catch (IOException e) {
      Ivy.log().error("Can't read json value", e);
      throw new PortalException(e);
    }
  }
}
