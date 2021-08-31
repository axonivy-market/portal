package portalmigration.version93.service;

import static ch.ivy.addon.portalkit.enums.FilterType.ALL_ADMINS;
import static ch.ivy.addon.portalkit.enums.FilterType.ALL_USERS;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portalkit.enums.FilterType;
import ch.ivy.addon.portalkit.filter.AbstractFilter;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import portalmigration.persistence.converter.BusinessEntityConverter;
import portalmigration.service.exception.PortalException;
import portalmigration.version93.configuration.AbstractFilterData;
import portalmigration.version93.configuration.ConfigurationWrapper;

public abstract class AbstractFilterService<T extends AbstractFilterData<?>> {

  public static final String FILTER_GROUP_ID = "filterGroupId";
  public static final String DATA = "data";
  private ObjectMapper mapper = new ObjectMapper();

  public List<T> getPublicFilter(Long filterGroupId) {
    List<T> filters = getPublicFilterForAdmin(filterGroupId);
    List<T> allUsersFilters = filters.stream().filter(filter -> (ALL_USERS == filter.getType())).collect(Collectors.toList());
    return allUsersFilters;
  }

  public List<T> getPublicFilterForAdmin(Long filterGroupId) {
    if (filterGroupId == null) {
      return new ArrayList<>();
    }
    List<T> filters = getFiltersByGroupId(filterGroupId, true);
    return filters.stream().sorted(sortedByFilterName()).collect(Collectors.toList());
  }

  public List<T> getPrivateFilterForCurrentUser(Long filterGroupId) {
    if (filterGroupId == null) {
      return new ArrayList<>();
    }
    List<T> filters = getFiltersByGroupId(filterGroupId, false);
    return filters.stream().sorted(sortedByFilterName()).collect(Collectors.toList());
  }

  public List<T> sortFilters(List<T> filters) {
    filters.sort(sortedByFilterName());
    return filters;
  }

  public void copyFilterValues(AbstractFilter<?> filter, AbstractFilter<?> savedFilter)
      throws ReflectiveOperationException {
    Field[] fields = filter.getClass().getDeclaredFields();
    for (Field field : fields) {
      if (field.getAnnotation(JsonIgnore.class) == null) {
        String fieldName = field.getName();
        BeanUtils.copyProperty(filter, fieldName, PropertyUtils.getProperty(savedFilter, fieldName));
      }
    }
  }

  public boolean isFilterExisted(String name, FilterType type, Long filterGroupId) {
    switch (type) {
      case ONLY_ME:
        return getPrivateFilterForCurrentUser(filterGroupId).stream()
            .anyMatch(filter -> filter.getFilterName().equalsIgnoreCase(name));
      case ALL_ADMINS:
        return getPublicFilterForAdmin(filterGroupId).stream()
            .anyMatch(filter -> ALL_ADMINS == filter.getType() && filter.getFilterName().equalsIgnoreCase(name));
      default:
        return getPublicFilterForAdmin(filterGroupId).stream()
            .anyMatch(filter -> ALL_USERS == filter.getType() && filter.getFilterName().equalsIgnoreCase(name));
    }
  }

  public void delete(T entity) {
    long groupId = entity.getFilterGroupId();
    boolean isPublic = entity.getIsPublic();
    List<T> entities = getFiltersByGroupId(groupId, isPublic);
    boolean isDeleted = entities.removeIf(filterById(entity.getId()));
    if (isDeleted) {
      saveOrUpdateFilters(groupId, isPublic, entities);
    }
  }

  public T save(T entity) {
    long groupId = entity.getFilterGroupId();
    boolean isPublic = entity.getIsPublic();

    List<T> entities = getFiltersByGroupId(groupId, isPublic);
    entities.removeIf(filterById(entity.getId()));
    // Update Entity
    entities.add(entity);
    saveOrUpdateFilters(groupId, isPublic, entities);
    return entity;
  }

  public T findPublicFilter(String id, long filterGroupId) {
    List<T> entities = getPublicFilterForAdmin(filterGroupId);
    return entities.stream().filter(filterById(id)).findFirst().orElse(null);
  }

  public T findPrivateFilter(String id, long filterGroupId) {
    List<T> entities = getPrivateFilterForCurrentUser(filterGroupId);
    return entities.stream().filter(filterById(id)).findFirst().orElse(null);
  }


  /**
   * Get all filters in the given filter group id
   * @param filterGroupId the request process model id
   * @param isPublic the indicator to search filters in variables or for only current user
   * @return list of filters
   */
  private List<T> getFiltersByGroupId(long filterGroupId, boolean isPublic) {
    List<T> filters = new ArrayList<>();
    List<JsonNode> filterNodes = getFilterNodes(isPublic);
    for (JsonNode configNode : filterNodes) {
      try {
        long groupId = configNode.get(FILTER_GROUP_ID).asInt();
        if (groupId == filterGroupId) {
          JsonNode dataNode = configNode.get(DATA);
          filters = BusinessEntityConverter.jsonToConfigurations(dataNode.toString());
          break;
        }
      } catch (Exception e) {
        Ivy.log().error(e);
      }
    }
    filters.forEach(filter -> filter.setIsPublic(isPublic));
    return filters;
  }

  private void saveOrUpdateFilters(long groupId, boolean isPublic, Object data) {
    List<T> filters = castToListFilters(data);
    StringWriter writer = buildFiltersWrapper(groupId, filters, isPublic);
    if (isPublic) {
      Ivy.var().set(getConfigKey(), writer.toString());
    } else {
      sessionUser().setProperty(getConfigKey(), writer.toString());
    }
  }

  @SuppressWarnings("unchecked")
  private List<T> castToListFilters(Object object) {
    if (object == null) {
      return new ArrayList<>();
    }
    return (List<T>) object;
  }

  private StringWriter buildFiltersWrapper(Long filterGroupId, List<T> filters, boolean isPublic) {
    List<JsonNode> persistedDataList = getFilterNodes(isPublic);
    List<JsonNode> modifyDataList = new ArrayList<>();
    for (JsonNode wrapperNode : persistedDataList) {
      long groupId = wrapperNode.get(FILTER_GROUP_ID).asInt();
      if (groupId == filterGroupId) {
        modifyDataList.add(wrapperNode);
      }
    }
    persistedDataList.removeAll(modifyDataList);

    ConfigurationWrapper configurationWrapper = new ConfigurationWrapper();
    configurationWrapper.setConfigurations(new ArrayList<>(filters));
    String data = BusinessEntityConverter.configurationToJson(configurationWrapper);

    StringWriter write = new StringWriter();
    try {
      write = buildJsonStringWriter(filterGroupId, data, persistedDataList);
    } catch (IOException e) {
      throw new PortalException("Cannot build json string writer" + e);
    }
    return write;
  }

  private StringWriter buildJsonStringWriter(long filterGroupId, String filterAsString,
      List<JsonNode> persistedDataList) throws IOException {
    StringWriter stringWriter = new StringWriter();
    JsonFactory factory = new JsonFactory();
    JsonGenerator generator = factory.createGenerator(stringWriter);
    generator.writeStartArray();

    // Write modified data to JSON
    generator.writeStartObject();
    generator.writeFieldName(FILTER_GROUP_ID);
    generator.writeNumber(filterGroupId);
    generator.writeFieldName(DATA);
    generator.writeRawValue(filterAsString);
    generator.writeEndObject();

    // Append another elements to JSON
    for (JsonNode configNode : persistedDataList) {
      generator.writeRawValue(configNode.toString());
    }

    generator.writeEndArray();
    generator.close();
    return stringWriter;
  }

  private List<JsonNode> getFilterNodes(boolean isPublic) {
    String jsonValue = EMPTY;
    if (isPublic) {
      jsonValue = Ivy.var().get(getConfigKey());
    } else {
      jsonValue = sessionUser().getProperty(getConfigKey());
    }
    if (StringUtils.isBlank(jsonValue)) {
      return new ArrayList<>();
    }

    List<JsonNode> jsonNodes = new ArrayList<>();
    try {
      JsonNode jsonTree = mapper.readTree(jsonValue);
      Iterator<JsonNode> iteratorNode = jsonTree.elements();
      iteratorNode.forEachRemaining(jsonNodes::add);
    } catch (Exception e) {
      Ivy.log().error(e);
    }
    return jsonNodes;
  }

  private Comparator<? super T> sortedByFilterName() {
    return (f1, f2) -> f1.getFilterName().compareToIgnoreCase(f2.getFilterName());
  }

  private Predicate<? super T> filterById(String filterId) {
    return filter -> filter.getId().equals(filterId);
  }

  protected IUser sessionUser() {
    return Ivy.session().getSessionUser();
  }

  public abstract Class<T> getType();

  public abstract String getConfigKey();

}
