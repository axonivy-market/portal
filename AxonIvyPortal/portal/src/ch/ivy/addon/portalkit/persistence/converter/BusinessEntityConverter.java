package ch.ivy.addon.portalkit.persistence.converter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.environment.Ivy;

/**
 * This class provides method to convert Business entity object into JSON value and reverse
 */
public class BusinessEntityConverter {
  
  public static ObjectMapper objectMapper;

  public BusinessEntityConverter() {}

  public static String entityToJsonValue(Object entity) {
    try {
      return getObjectMapper().writeValueAsString(entity);
    } catch (JsonProcessingException e) {
      Ivy.log().error("Can't write json value", e);
      throw new PortalException(e);
    }
  }

  public static String prettyPrintEntityToJsonValue(Object entity) {
    try {
      return getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(entity);
    } catch (JsonProcessingException e) {
      Ivy.log().error("Can't write json value", e);
      throw new PortalException(e);
    }
  }

  public static <T> T jsonValueToEntity(String jsonValue, Class<T> classType) {
    try {
      return getObjectMapper().readValue(jsonValue, classType);
    } catch (IOException e) {
      Ivy.log().error("Can't read json value", e);
      throw new PortalException(e);
    }
  }

  public static <T> T inputStreamToEntity(InputStream inputStream, Class<T> classType) {
    try {
      new InputStreamReader(inputStream, StandardCharsets.UTF_8);
      return getObjectMapper().readValue(inputStream, classType);
    } catch (IOException e) {
      Ivy.log().error("Can't read json value", e);
      throw new PortalException(e);
    }
  }

  public static <T> List<T> jsonValueToEntities(String jsonValue, Class<T> classType) {
    if (StringUtils.isBlank(jsonValue)) {
      return new ArrayList<>();
    }
    try {
      return getObjectMapper().readValue(jsonValue,
          getObjectMapper().getTypeFactory().constructCollectionType(List.class, classType));
    } catch (IOException e) {
      Ivy.log().error("Can't read json value", e);
      throw new PortalException(e);
    }
  }

  public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
    return getObjectMapper().convertValue(fromValue, toValueType);
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
}
