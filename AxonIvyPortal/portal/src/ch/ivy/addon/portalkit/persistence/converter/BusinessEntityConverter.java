package ch.ivy.addon.portalkit.persistence.converter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.json.JsonMapper;

import ch.ivy.addon.portalkit.bo.PortalJsonViews;
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
        if (!rootNode.isEmpty()) {        
          String rootName = rootNode.fieldNames().next();
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
        } else {
          // Empty object {} — treated as empty configuration, not a single entity
          return new ArrayList<>();
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
          .propertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)
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
    try {
      return getObjectMapper().writer().withRootName("dashboards").writeValueAsString(dashboards);
    } catch (JsonProcessingException e) {
      throw new PortalException(e);
    }
  }

  public static String prettyPrintEntityToJsonValue(List<Dashboard> dashboards) {
    DashboardUtils.updatePropertiesToNullIfCurrentValueIsDefaultValue(dashboards);
    return prettyPrintObjectEntityToJsonValue(dashboards);
  }

}