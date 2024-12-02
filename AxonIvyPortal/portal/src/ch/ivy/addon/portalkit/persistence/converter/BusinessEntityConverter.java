package ch.ivy.addon.portalkit.persistence.converter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.util.DashboardUtils;

/**
 * This class provides method to convert Business entity object into JSON value and reverse
 */
public class BusinessEntityConverter {
  
  public static ObjectMapper objectMapper;

  public BusinessEntityConverter() {}

  public static String entityToJsonValue(Object entity) {
    return objectEntityToJsonValue(entity);
  }

  private static String objectEntityToJsonValue(Object entity) {
    try {
      return getObjectMapper().writeValueAsString(entity);
    } catch (JsonProcessingException e) {
      throw new PortalException(e);
    }
  }

  public static String prettyPrintEntityToJsonValue(Object entity) {
    return prettyPrintObjectEntityToJsonValue(entity);
  }

  private static String prettyPrintObjectEntityToJsonValue(Object entity) {
    try {
      return getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(entity);
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

  public static <T> T inputStreamToEntity(InputStream inputStream, Class<T> classType) {
    try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
      return getObjectMapper().readValue(inputStream, classType);
    } catch (IOException e) {
      throw new PortalException(e);
    }
  }

  public static <T> List<T> jsonValueToEntities(String jsonValue, Class<T> classType) {
    if (StringUtils.isBlank(jsonValue)) {
      return new ArrayList<>();
    }
    try {
      return getObjectMapper().readValue(jsonValue,
          getListOfJavaType(classType));
    } catch (IOException e) {
      throw new PortalException(e);
    }
  }

  public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
    return getObjectMapper().convertValue(fromValue, toValueType);
  }

  public static <T> List<T> convertJsonNodeToList(JsonNode jsonNode, Class<T> classType) {
    if (Optional.ofNullable(jsonNode).isPresent()) {
      try {
        return getObjectMapper().treeToValue(jsonNode, getListOfJavaType(classType));
      } catch (IOException e) {
        throw new PortalException(e);
      }
    }
    return new ArrayList<>();
  }

  public static <T> T convertJsonNodeToEntity(JsonNode jsonNode, Class<T> classType) {
    try {
      return getObjectMapper().treeToValue(jsonNode, classType);
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

  public static List<String> convertJsonToListString(String value)
      throws JsonMappingException, JsonProcessingException {
    if (StringUtils.isBlank(value)) {
      return null;
    }
    try {
      return Arrays.asList(objectMapper.readValue(value, String[].class));
    } catch (Exception e) {
      Arrays.asList(objectMapper.readValue(value, String.class));
    }
    return new ArrayList<>();

  }

  public static String entityToJsonValue(List<Dashboard> dashboards) {
    DashboardUtils.updatePropertiesToNullIfCurrentValueIsDefaultValue(dashboards);
    return objectEntityToJsonValue(dashboards);
  }

  public static String prettyPrintEntityToJsonValue(List<Dashboard> dashboards) {
    DashboardUtils.updatePropertiesToNullIfCurrentValueIsDefaultValue(dashboards);
    return prettyPrintObjectEntityToJsonValue(dashboards);
  }
}
