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

import com.axonivy.portal.components.configuration.CustomSubMenuItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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
      return getObjectMapper().writer().withRootName("dashboard").withDefaultPrettyPrinter().writeValueAsString(entity);
    } catch (JsonProcessingException e) {
      throw new PortalException(e);
    }
  }

  public static <T> T jsonValueToEntity(String jsonValue, Class<T> classType) {
    try {
      JsonNode rootNode = getObjectMapper().readTree(jsonValue);
      Ivy.log().error("jsonValueToEntity {0}, and class type is {1}", jsonValue, classType.getName());
      if (rootNode.isObject() && rootNode.has("dashboard")) {
        Ivy.log().error("IS OBJECT and dashboard json is {0}", rootNode.get("dashboard").toString());
        rootNode.get("dashboard").toString();
        return getObjectMapper().readValue(rootNode.get("dashboard").toString(), classType);
      } else if (rootNode.isArray()) {
        Ivy.log().error("IS ARRAY and dashboard json is {0}", rootNode.toString());
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
    Ivy.log().error("jsonValueToEntity {0}, and class type is {1}", jsonValue, classType.getName());
    try {
      JsonNode rootNode = getObjectMapper().readTree(jsonValue);
      if (rootNode.isObject()) {
        Ivy.log().error("IS OBJECT ");
        if (rootNode.has("dashboard")){
          
          return getObjectMapper().readValue(rootNode.get("dashboard").toString(), getListOfJavaType(classType));
        }
        
      } else if (rootNode.isArray()) {
        Ivy.log().error("IS ARRAY and dashboard json is {0}", rootNode.toString());
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
    if (Optional.ofNullable(jsonNode).isPresent()) {
      try {
        List<T> a = getObjectMapper().treeToValue(jsonNode, getListOfJavaType(classType));
        for (T x : a) {
          if (x instanceof Dashboard dashboard) {
            Dashboard dasboard = (Dashboard) x;
            Ivy.log().error("dashboard id is {0}, name is {1}", dasboard.getId(), dashboard.getDescription());
          }

        }
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
          .configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false)
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

  
    public static void main(String[] args) throws Exception {
      List<CustomSubMenuItem> items = jsonValueToEntities("{}", CustomSubMenuItem.class);
    }

}


class User {
    public String name;
}