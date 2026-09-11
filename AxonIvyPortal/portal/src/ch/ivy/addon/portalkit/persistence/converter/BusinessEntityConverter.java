package ch.ivy.addon.portalkit.persistence.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.JsonListWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
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
      return getObjectMapper().writeValueAsString(wrapIfList(entity));
    } catch (JsonProcessingException e) {
      throw new PortalException(e);
    }
  }
  
  private static String objectEntityToJsonValueExcludeInternalView(Object entity) {
    try {
      return getObjectMapper().writerWithView(PortalJsonViews.Public.class).writeValueAsString(wrapIfList(entity));
    } catch (JsonProcessingException e) {
      throw new PortalException(e);
    }
  }

  /**
   * Wraps raw {@code List} values with the canonical version+items wrapper so
   * every list, not just {@code List<Dashboard>}, gets a stable, type-safe
   * root shape instead of falling through to Jackson's runtime-class-name
   * fallback. Non-list entities pass through unchanged.
   */
  private static Object wrapIfList(Object entity) {
    if (entity instanceof List<?> list) {
      return new JsonListWrapper<>(JsonListWrapper.FORMAT_VERSION, list);
    }
    return entity;
  }

  public static String prettyPrintEntityToJsonValue(Object entity) {
    return prettyPrintObjectEntityToJsonValue(entity);
  }

  private static String prettyPrintObjectEntityToJsonValue(Object entity) {
    try {
      return getObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(wrapIfList(entity));
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
      // Only reject array/list-wrapper shapes when classType itself expects a single object.
      // When classType is an array type (e.g. WidgetLayout[].class, used by
      // DashboardWidgetUtils.getWidgetLayoutFromRequest to deserialize the "nodes" request param),
      // a JSON array root node is exactly the correct, expected shape - not an error.
      if (!classType.isArray() && (rootNode.isArray() || JsonListWrapper.isListWrapper(rootNode))) {
        throw new PortalException(
            "Expected a single " + classType.getSimpleName() + " JSON object, but got a list/array shape.");
      }
      return mapper.treeToValue(rootNode, classType);
    } catch (IOException e) {
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
      if (JsonListWrapper.isListWrapper(rootNode)) {
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

  public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
    return getObjectMapper().convertValue(fromValue, toValueType);
  }

  public static <T> List<T> convertJsonNodeToList(JsonNode jsonNode, Class<T> classType) {
    if (!Optional.ofNullable(jsonNode).isPresent()) {
      return new ArrayList<>();
    }
    try {
      // Canonical shape: {"version": "...", "items": [...]}
      if (JsonListWrapper.isListWrapper(jsonNode)) {
        List<T> result = new ArrayList<>();
        for (JsonNode element : jsonNode.get(JsonListWrapper.ITEMS_FIELD_NAME)) {
          result.add(getObjectMapper().treeToValue(element, classType));
        }
        return result;
      }

      JsonNode nodeToConvert = jsonNode;

      // Handle empty node like {}
      if (nodeToConvert.isObject()) {
        if (!nodeToConvert.fieldNames().hasNext()){
          return new ArrayList<>();
        } else {
          // NEW: a single JSON object (not the wrapper, not an array) is one entity —
          // treat it as a one-element list instead of forcing it through treeToValue
          // as a List<T>, which requires a JSON array token and throws otherwise.        
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



  /**
   * Serializes a list of entities using the canonical
   * {@code {"version": "...", "items": [...]}} wrapper shape.
   */
  public static <T> String entityToJsonValue(List<T> entities, String version) {
    JsonListWrapper<T> wrapper = new JsonListWrapper<>(version, entities);
    try {
      return getObjectMapper().writeValueAsString(wrapper);
    } catch (JsonProcessingException e) {
      throw new PortalException(e);
    }
  }

  public static String entityToJsonValue(List<Dashboard> dashboards) {
    DashboardUtils.updatePropertiesToNullIfCurrentValueIsDefaultValue(dashboards);
    return entityToJsonValue(dashboards, JsonListWrapper.FORMAT_VERSION);
  }

  public static String prettyPrintEntityToJsonValue(List<Dashboard> dashboards) {
    DashboardUtils.updatePropertiesToNullIfCurrentValueIsDefaultValue(dashboards);
    return prettyPrintObjectEntityToJsonValue(dashboards);
  }

}