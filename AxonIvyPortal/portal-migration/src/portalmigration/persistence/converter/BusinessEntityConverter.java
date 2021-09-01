package portalmigration.persistence.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivyteam.di.restricted.DiCore;
import ch.ivyteam.ivy.business.data.store.restricted.BusinessDataTypeLoader;
import ch.ivyteam.ivy.business.data.store.serializer.restricted.jackson.JacksonSerializer;
import ch.ivyteam.ivy.environment.Ivy;
import portalmigration.service.exception.PortalException;
import portalmigration.version93.configuration.ConfigurationWrapper;

/**
 * This class provides method to convert Business entity object into JSON value and reverse
 */
@SuppressWarnings("restriction")
public class BusinessEntityConverter {

  public BusinessEntityConverter() {}

  public static String entityToJsonValue(Object entity) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.writeValueAsString(entity);
    } catch (JsonProcessingException e) {
      Ivy.log().error("Can't write json value", e);
      throw new PortalException(e);
    }
  }

  public static <T> T jsonValueToEntity(String jsonValue, Class<T> classType) {
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    try {
      return objectMapper.readValue(jsonValue, classType);
    } catch (IOException e) {
      Ivy.log().error("Can't read json value", e);
      throw new PortalException(e);
    }
  }

  public static <T> List<T> jsonValueToEntities(String jsonValue, Class<T> classType) {
    if (StringUtils.isBlank(jsonValue)) {
      return new ArrayList<>();
    }
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    try {
      return objectMapper.readValue(jsonValue,
          objectMapper.getTypeFactory().constructCollectionType(List.class, classType));
    } catch (IOException e) {
      Ivy.log().error("Can't read json value", e);
      throw new PortalException(e);
    }
  }

  public static String configurationToJson(ConfigurationWrapper configuration) {
    JacksonSerializer<ConfigurationWrapper> serializer = buildConfigurationWrapperSerializer();
    return serializer.serialize(configuration);
  }

  @SuppressWarnings({"unchecked"})
  public static <T> List<T> jsonToConfigurations(String json) {
    ConfigurationWrapper wrapper = jsonToConfigurationWrapper(json);
    List<T> configurations = (List<T>) wrapper.getConfigurations();
    if (configurations == null) {
      return new ArrayList<>();
    }
    return configurations;
  }  

  public static ConfigurationWrapper jsonToConfigurationWrapper(String json) {
    JacksonSerializer<ConfigurationWrapper> serializer = buildConfigurationWrapperSerializer();
    ConfigurationWrapper wrapper = serializer.deserialize(json);
    return wrapper;
  }

  private static JacksonSerializer<ConfigurationWrapper> buildConfigurationWrapperSerializer() {
    BusinessDataTypeLoader typeLoader = DiCore.getGlobalInjector().getInstance(BusinessDataTypeLoader.class);
    return JacksonSerializer.forType(ConfigurationWrapper.class)
        .withContextClassLoader(typeLoader.getContextClassLoader(ConfigurationWrapper.class));
  }
}
