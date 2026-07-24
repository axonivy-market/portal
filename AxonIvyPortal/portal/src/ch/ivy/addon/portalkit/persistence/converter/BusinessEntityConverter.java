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
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

import ch.ivy.addon.portalkit.bo.PortalJsonViews;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivyteam.ivy.environment.Ivy;

/**
 * This class provides method to convert Business entity object into JSON value and reverse
 */
public class BusinessEntityConverter {
  
  public static ObjectMapper objectMapper;

  public BusinessEntityConverter() {}

  public static String entityToJsonValue(Object entity) {
    return objectEntityToJsonValue(entity);
  }
  
  public static String entityToJsonValueExcludeInternalView(Object entity) {
    return objectEntityToJsonValueExcludeInternalView(entity);
  }

  private static String objectEntityToJsonValue(Object entity) {
    try {
      return getObjectMapper().writeValueAsString(entity);
    } catch (JsonProcessingException e) {
      throw new PortalException(e);
    }
  }
  
  private static String objectEntityToJsonValueExcludeInternalView(Object entity) {
    try {
      return getObjectMapper().writerWithView(PortalJsonViews.Public.class).writeValueAsString(entity);
    } catch (JsonProcessingException e) {
      throw new PortalException(e);
    }
  }

  public static String prettyPrintEntityToJsonValue(Object entity) {
    return prettyPrintObjectEntityToJsonValue(entity);
  }

  private static String prettyPrintObjectEntityToJsonValue(Object entity) {
    try {
      return getObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(entity);
    } catch (JsonProcessingException e) {
      throw new PortalException(e);
    }
  }

  public static <T> T jsonValueToEntity(String jsonValue, Class<T> classType) {
    if (StringUtils.isBlank(jsonValue)) {
      return null;
    }
    try {
      JsonNode rootNode = getObjectMapper().readTree(jsonValue);
      if (rootNode.isObject()) {
        if (rootNode.fieldNames().hasNext()) {   
          String rootName = rootNode.fieldNames().next();
          return getObjectMapper().readValue(rootNode.get(rootName).toString(), classType);
        }
      } else if (rootNode.isArray()) {
        return getObjectMapper().readValue(jsonValue, classType);
      } 

      return null;
    } catch (IOException e) {
      throw new PortalException(e);
    }
  }

  public static <T> T inputStreamToEntity(InputStream inputStream, Class<T> classType) {
    try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
      return getObjectMapper().readValue(reader, classType);
    } catch (IOException e) {
      throw new PortalException(e);
    }
  }

  public static <T> List<T> jsonValueToEntities(String jsonValue, Class<T> classType) {
    if (StringUtils.isBlank(jsonValue)) {
      return new ArrayList<>();
    }
    try {
      JsonNode rootNode = getObjectMapper().readTree(jsonValue);
      
      if (rootNode.isObject()) {
        if (rootNode.fieldNames().hasNext()) {        
          String rootName = rootNode.fieldNames().next();
          Ivy.log().error("The root node is an object, the root name is: " + rootName);
          if (rootNode.has(rootName)) {
            return getObjectMapper().readValue(rootNode.get(rootName).toString(), getListOfJavaType(classType));
          }
        }
      } else if (rootNode.isArray()) {
        return getObjectMapper().readValue(jsonValue, getListOfJavaType(classType));
      } 
      return new ArrayList<>();
    } catch (IOException e) {
      throw new PortalException(e);
    }
  }

  public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
    return getObjectMapper().convertValue(fromValue, toValueType);
  }

  public static <T> List<T> convertJsonNodeToList(JsonNode jsonNode, Class<T> classType) {
    Ivy.log().error("JsonNode at the beginning of function is {0}", jsonNode.toString().substring(0, Math.min(jsonNode.toString().length(), 100)));
    if (!Optional.ofNullable(jsonNode).isPresent()) {
      return new ArrayList<>();
    }
    try {
      JsonNode nodeToConvert = jsonNode;

      // Handle root-wrapped format {"ArrayList": [...]} produced by WRAP_ROOT_VALUE.
      // Only unwrap if the first field contains an array whose elements are objects
      // (i.e. real entity nodes), not primitive/string arrays like "permissions":["Everybody"].
      if (nodeToConvert.isObject()) {
        JsonNode candidateArray = null;
        if (nodeToConvert.fieldNames().hasNext()) {
          String rootName = nodeToConvert.fieldNames().next();
          JsonNode firstValue = nodeToConvert.get(rootName);
          if (firstValue != null && firstValue.isArray()
              && firstValue.size() > 0 && firstValue.get(0).isObject()) {
            candidateArray = firstValue;
          }
        }
        if (candidateArray != null) {
          nodeToConvert = candidateArray;
        } else {
          // Single-entity ObjectNode (plain Dashboard or primitive-valued wrapper) — wrap in a list
          List<T> result = new ArrayList<>();
          result.add(getObjectMapper().treeToValue(nodeToConvert, classType));
          return result;
        }
      }

      // Handle array that may contain nested arrays (corrupted format [[{...}], {...}])
      if (nodeToConvert.isArray()) {
        boolean hasNestedArrays = false;
        for (JsonNode element : nodeToConvert) {
          if (element.isArray()) {
            hasNestedArrays = true;
            break;
          }
        }
        if (hasNestedArrays) {
          Ivy.log().error("Detected nested arrays in dashboard JSON, flattening");
          List<T> result = new ArrayList<>();
          for (JsonNode element : nodeToConvert) {
            if (element.isArray()) {
              for (JsonNode inner : element) {
                if (inner.isObject()) {
                  result.add(getObjectMapper().treeToValue(inner, classType));
                }
              }
            } else if (element.isObject()) {
              result.add(getObjectMapper().treeToValue(element, classType));
            }
          }
          return result;
        }
      }

      return getObjectMapper().treeToValue(nodeToConvert, getListOfJavaType(classType));
    } catch (IOException e) {
      throw new PortalException(e);
    }
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
          .enable(SerializationFeature.WRAP_ROOT_VALUE)
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