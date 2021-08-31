package portalmigration.version93.service;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.EnumUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ch.ivy.addon.portalkit.enums.FilterType;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.IProcessModel;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.business.data.store.BusinessDataRepository;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.business.data.store.search.Query;
import ch.ivyteam.ivy.business.data.store.search.Result;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.query.UserQuery;
import ch.ivyteam.ivy.vars.Variables;
import portalmigration.enums.PortalVariable;
import portalmigration.version93.dto.FilterDataInfo;

public class BusinessDataMigrationService {

  private static final String USER_ID = "userId";
  private static final String FILTER_GROUP_ID = "filterGroupId";
  private static final String FILTER_TYPE = "type";
  
  public static final String DATA_NODE = "data";
  public static final String CLASS_NODE = "@class";
  
  private static final String DATA_PATTERN = "{\"configurations\": [\"Class<java.util.ArrayList>\", %s]}";
  private static final String FILTER_CLASS_PATTERN = "Class<%s>";
  
  private static ObjectMapper objectMapper;
  
  public static void startMigrationData(IApplication app, List<String> errors) {
    objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    
    Ivy.log().info("***Start migrating PortalBusinessData");
    
    Ivy.log().info("***Migrating Portal Announcement");
    migrateAnnouncement(app);

    Ivy.log().info("***Migrating Portal Case filters");
    migrateCaseFilters(app, errors);
    
    Ivy.log().info("***Migrating Portal Task filters");
    migrateTaskFilters(app, errors);
    
    Ivy.log().info("***Migrating Portal TaskAnalysis filters");
    migrateTaskAnalysisFilters(app, errors);

    if (errors.isEmpty()) {
      Ivy.log().info("***End migrating Portal Data successfully");
      return;
    }
    Ivy.log().error("***End migrating Portal Data with {0} error(s)", errors.size());
  }

  private static void migrateAnnouncement(IApplication app) {
    var status = findLatestObjectByAppID(ch.ivy.addon.portalkit.bo.AnnouncementStatus.class, app.getId());
    portalmigration.version93.configuration.Announcement newAnnouncement = null;

    for (ch.ivy.addon.portalkit.bo.Announcement announcement : findAllByAppID(
        ch.ivy.addon.portalkit.bo.Announcement.class, app.getId())) {

      if (newAnnouncement == null) {
        newAnnouncement = new portalmigration.version93.configuration.Announcement();
        newAnnouncement.setContents(new ArrayList<>());
      }

      newAnnouncement.setEnabled(BooleanUtils.toBoolean(status.getEnabled()));
      newAnnouncement.getContents().add(new portalmigration.version93.configuration.LocalizationContent(
          announcement.getLanguage(), announcement.getValue()));
    }

    if (newAnnouncement == null) {
      return;
    }
    
    Variables.of(app).set(portalmigration.enums.PortalVariable.ANNOUNCEMENT.key,
        portalmigration.persistence.converter.BusinessEntityConverter.entityToJsonValue(newAnnouncement));
  }

  private static void migrateCaseFilters(IApplication app, List<String> errors) {
    List<String> removeFilterIds = new ArrayList<>();

    Ivy.log().info("***Migrating old CaseFilters");
    var publicFilterMap = new HashMap<Long, FilterDataInfo>();
    var privateFilterMap = new HashMap<Long, List<FilterDataInfo>>();

    for (var savedFilterDataInfo : findAllBusinessInfoByAppID(ch.ivy.addon.portalkit.casefilter.impl.CaseFilterData.class, app.getId())) {
      Ivy.log().info("***Migrating CaseFilter id: {0}", savedFilterDataInfo.getId());
      
      try {
        JsonNode jsonTree = objectMapper.readTree(savedFilterDataInfo.getRawValue());
        
        var filterGroupId = jsonTree.get(FILTER_GROUP_ID).asLong();
        var type = jsonTree.get(FILTER_TYPE).asText();
        
        var filterType = EnumUtils.getEnum(ch.ivy.addon.portalkit.enums.FilterType.class, type);
        var filterDataInfo = new FilterDataInfo();
        filterDataInfo.setObjectType(savedFilterDataInfo.getTypeName());
        
        // private filter
        if (FilterType.ONLY_ME.equals(filterType)) {
          var listFilterbygroup = new ArrayList<FilterDataInfo>();
          
          var userId = jsonTree.get(USER_ID).asLong();
          if (privateFilterMap.containsKey(userId)) {
            var filters = privateFilterMap.get(userId);
            var sameGroupFilter = filters.stream().filter(filterdata -> filterdata.getFilterGroupId() == filterGroupId)
                  .findFirst().orElseGet(null);
            if (sameGroupFilter != null) {
              sameGroupFilter.getRawValues().add(savedFilterDataInfo.getRawValue());
            } else {
              filterDataInfo.setUserId(userId);
              filterDataInfo.setFilterGroupId(filterGroupId);
              filterDataInfo.setRawValues(new ArrayList<>());
              filterDataInfo.getRawValues().add(savedFilterDataInfo.getRawValue());
              listFilterbygroup.add(filterDataInfo);
            }
            //
            listFilterbygroup.addAll(filters);
          }
          else {
            filterDataInfo.setUserId(userId);
            filterDataInfo.setFilterGroupId(filterGroupId);
            filterDataInfo.setRawValues(new ArrayList<>());
            filterDataInfo.getRawValues().add(savedFilterDataInfo.getRawValue());
            listFilterbygroup.add(filterDataInfo);
          }

          privateFilterMap.put(userId, listFilterbygroup);
        }
        
        // public filter
        else {
          if (publicFilterMap.containsKey(filterGroupId)) {
            filterDataInfo = publicFilterMap.get(filterGroupId);
          }
          else {
            filterDataInfo.setFilterGroupId(filterGroupId);
            filterDataInfo.setRawValues(new ArrayList<>());
          }
          
          filterDataInfo.getRawValues().add(savedFilterDataInfo.getRawValue());
          publicFilterMap.put(filterGroupId, filterDataInfo);
        }

      } catch (JsonProcessingException e) {
        Ivy.log().error("***Cannot migrate old CaseFilter id: " + savedFilterDataInfo.getId(), e);
        errors.add("CaseFilters:::Cannot migrate filter id: " + savedFilterDataInfo.getId() + e.getLocalizedMessage());
        continue;
      }
    }
    
    try {
      writePublicFilterToApp(publicFilterMap, app);
      
      writePrivateFilterToUserProperty(privateFilterMap);
      
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    removeFilterIds.forEach(filterId -> {
      Ivy.log().info("***Removing CaseFilter id: {0}", filterId);
      deleteById(filterId);
    });
  }

  private static void writePrivateFilterToUserProperty(Map<Long, List<FilterDataInfo>> privateFilterMap) throws JsonMappingException, JsonProcessingException, IOException {
    // TODO Auto-generated method stub
    if (privateFilterMap.keySet().isEmpty()) {
      return;
    }
    
    Map<Long, FilterDataInfo> filterByGroupID = new HashMap<>();
    
    for (Long userId : privateFilterMap.keySet()) {
      filterByGroupID = new HashMap<>();
      var filtersbygroup = privateFilterMap.get(userId);
      
      for (FilterDataInfo filteruser : filtersbygroup) {
        var groupID = filteruser.getFilterGroupId();
        
        var filterData = new FilterDataInfo();
        filterData.setFilterGroupId(groupID);
        filterData.setObjectType(filteruser.getObjectType());
        filterData.setRawValues(new ArrayList<>());
        
        if (filterByGroupID.containsKey(groupID)) {
          filterData.setRawValues(filterByGroupID.get(groupID).getRawValues());
        }
        
        filterData.getRawValues().addAll(filteruser.getRawValues());

        filterByGroupID.put(groupID, filterData);
      }
      
      StringWriter stringWriter = buildJSONWriter(filterByGroupID);
      
      /// write
      IUser user = Ivy.wf().getSecurityContext().users().find(userId);
      if (user != null) {
        user.setProperty(PortalVariable.CASE_FILTER.key, stringWriter.toString());
      }
    }
  }

  private static void writePublicFilterToApp(Map<Long, FilterDataInfo> publicFilterMap, IApplication app) throws IOException {
    // TODO Auto-generated method stub
    if (publicFilterMap.keySet().isEmpty()) {
      return;
    }
    
    StringWriter stringWriter = buildJSONWriter(publicFilterMap);

    /// write
    Variables.of(app).set(PortalVariable.CASE_FILTER.key, stringWriter.toString());
  }

  private static StringWriter buildJSONWriter(Map<Long, FilterDataInfo> publicFilterMap)
      throws IOException, JsonProcessingException, JsonMappingException {
    StringWriter stringWriter = new StringWriter();
    JsonFactory factory = new JsonFactory();
    JsonGenerator generator = factory.createGenerator(stringWriter);
    generator.writeStartArray();
    
    for (Long filterGroupId : publicFilterMap.keySet()) {
      var filterDataInfo = publicFilterMap.get(filterGroupId);
      generator.writeStartObject();
      generator.writeFieldName(FILTER_GROUP_ID);
      generator.writeNumber(filterGroupId);
      generator.writeFieldName(DATA_NODE);
      
      // write sub
      ArrayNode configurationNode = objectMapper.createArrayNode();
//      ObjectNode objectNode = objectMapper.createObjectNode();

      
      int refIndex = 1;
      for (String rawValue : filterDataInfo.getRawValues()) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put(CLASS_NODE, String.format(FILTER_CLASS_PATTERN, filterDataInfo.getObjectType()));
          
        JsonNode filter = objectMapper.readTree(rawValue);
        Iterator<String> fieldNames = filter.fieldNames();
        while (fieldNames.hasNext()) {
          String fieldName = fieldNames.next();
          if (fieldName.equalsIgnoreCase("@id")) {
            
            var idvalue = filter.get(fieldName).asText();
            var valuers = idvalue.split("-");
            var text = valuers[0] + "-" + refIndex;
            
            objectNode.put(fieldName, text);
            continue;
          }
//          if (fieldName.equalsIgnoreCase("filters")) {
//            ArrayNode eleArray = objectMapper.createArrayNode();
//            Iterator<JsonNode> eleFilters = filter.get("filters").elements();
//            while (eleFilters.hasNext()) {
//              ObjectNode ele = objectMapper.createObjectNode();
//              JsonNode element = eleFilters.next();
//              JsonNodeType type = element.getNodeType();
//              
//              if (JsonNodeType.OBJECT
//              
//              ele.remove("@id");
//              
//              eleArray.add(ele);
//            }
//            
//            objectNode.set(fieldName, eleArray);
//            continue;
//          }


          
          objectNode.set(fieldName, filter.get(fieldName));
        }
        
        configurationNode.add(objectNode);
        refIndex++;
      }

      String configurationAsString = String.format(DATA_PATTERN, configurationNode.toString());
      // end sub

      generator.writeRawValue(configurationAsString);
      generator.writeEndObject();
    }

    generator.writeEndArray();
    generator.close();
    return stringWriter;
  }

//  private static StringWriter buildJsonStringWriter() throws IOException {
//    StringWriter stringWriter = new StringWriter();
//    JsonFactory factory = new JsonFactory();
//    JsonGenerator generator = factory.createGenerator(stringWriter);
//    generator.writeStartArray();
//
//    // Write modified data to JSON
//    
//    
//    generator.writeStartObject();
//    generator.writeFieldName(FILTER_GROUP_ID);
//    generator.writeNumber(filterGroupId);
//    generator.writeFieldName(DATA);
//    generator.writeRawValue(filterAsString);
//    generator.writeEndObject();
//    
//    
//    
//    generator.writeEndArray();
//    generator.close();
//    return stringWriter;
//  }

  private static void migrateTaskFilters(IApplication app, List<String> errors) {

    List<String> removeFilterIds = new ArrayList<>();
    ch.ivy.addon.portalkit.taskfilter.impl.TaskFilterData newFilterData = null;

    Ivy.log().info("***Migrating old TaskFilters");

    for (var savedFilterData : findAllFiltersByApp(app, ch.ivy.addon.portalkit.taskfilter.TaskFilterData.class)) {

      Ivy.log().info("***Migrating TaskFilter id: {0}", savedFilterData.getId());

      newFilterData = new ch.ivy.addon.portalkit.taskfilter.impl.TaskFilterData();
      try {
        newFilterData.setFilters(copyTaskFilters(savedFilterData.getFilters()));
      } catch (ReflectiveOperationException e) {
        Ivy.log().error("***Cannot migrate old TaskFilter id: " + savedFilterData.getId(), e);
        errors.add("TaskFilters:::Cannot migrate filter id: " + savedFilterData.getId() + e.getLocalizedMessage());
        continue;
      }
//      mergeCommonFields(newFilterData, savedFilterData);

      var updatedFilterId = saveToRepo(newFilterData);
      removeFilterIds.add(savedFilterData.getId());

      Ivy.log().info("***Inserted new TaskFilter id: {0}", updatedFilterId);
    }

    removeFilterIds.forEach(filterId -> {
      Ivy.log().info("***Removing TaskFilter id: {0}", filterId);
      deleteById(filterId);
    });
  }

  private static void migrateTaskAnalysisFilters(IApplication app, List<String> errors) {

    List<String> removeFilterIds = new ArrayList<>();
    ch.ivy.addon.portalkit.taskfilter.impl.TaskAnalysisFilterData newFilterData = null;

    Ivy.log().info("***Migrating old TaskAnalysis filters");

    for (var savedFilterData : findAllFiltersByApp(app, ch.ivy.addon.portalkit.taskfilter.TaskAnalysisFilterData.class)) {

      Ivy.log().info("***Migrating TaskAnalysis filter id: {0}", savedFilterData.getId());

      newFilterData = new ch.ivy.addon.portalkit.taskfilter.impl.TaskAnalysisFilterData();
      try {
        newFilterData.setTaskFilters(copyTaskFilters(savedFilterData.getTaskFilters()));
        newFilterData.setCaseFilters(copyCaseFilters(savedFilterData.getCaseFilters()));

      } catch (ReflectiveOperationException e) {
        Ivy.log().error("***Cannot migrate old TaskAnalysis filter id: " + savedFilterData.getId(), e);
        errors.add("TaskAnalysisFilters:::Cannot migrate filter id: " + savedFilterData.getId() + e.getLocalizedMessage());
        continue;
      }
      mergeCommonFields(newFilterData, savedFilterData);

      var updatedFilterId = saveToRepo(newFilterData);
      removeFilterIds.add(savedFilterData.getId());

      Ivy.log().info("***Inserted new TaskAnalysis filter id: {0}", updatedFilterId);
    }

    removeFilterIds.forEach(filterId -> {
      Ivy.log().info("***Removing TaskAnalysis filter id: {0}", filterId);
      deleteById(filterId);
    });
  }

  private static ch.ivy.addon.portalkit.filter.AbstractFilterData<?> mergeCommonFields(
      ch.ivy.addon.portalkit.filter.AbstractFilterData<?> newFilterData,
      ch.ivy.addon.portalkit.filter.AbstractFilterData<?> savedFilterData) {
    newFilterData.setFilterGroupId(savedFilterData.getFilterGroupId());
    newFilterData.setFilterName(savedFilterData.getFilterName());
    newFilterData.setKeyword(savedFilterData.getKeyword());
    newFilterData.setType(savedFilterData.getType());
    newFilterData.setUserId(savedFilterData.getUserId());
    return newFilterData;
  }

  private static List<ch.ivy.addon.portalkit.taskfilter.TaskFilter> copyTaskFilters(
      List<ch.ivy.addon.portalkit.taskfilter.TaskFilter> savedFilters) throws ReflectiveOperationException {

    var filters = new ArrayList<ch.ivy.addon.portalkit.taskfilter.TaskFilter>();

    for (ch.ivy.addon.portalkit.taskfilter.TaskFilter savedFilter : savedFilters) {
      
      if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.taskfilter.impl.TaskCategoryFilter.class.getSimpleName())) {
        Ivy.log().info("***Migrating to new TaskCategoryFilter");
        var filter = new ch.ivy.addon.portalkit.taskfilter.impl.TaskCategoryFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.taskfilter.impl.TaskCreationDateFilter.class.getSimpleName())) {
        Ivy.log().info("***Migrating to new TaskCreationDateFilter");
        var filter = new ch.ivy.addon.portalkit.taskfilter.impl.TaskCreationDateFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.taskfilter.impl.TaskResponsibleFilter.class.getSimpleName())) {
        Ivy.log().info("***Migrating to new TaskResponsibleFilter");
        var filter = new ch.ivy.addon.portalkit.taskfilter.impl.TaskResponsibleFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.taskfilter.impl.TaskDescriptionFilter.class.getSimpleName())) {
        Ivy.log().info("***Migrating to new TaskDescriptionFilter");
        var filter = new ch.ivy.addon.portalkit.taskfilter.impl.TaskDescriptionFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.taskfilter.impl.TaskExpiredDateFilter.class.getSimpleName())) {
        Ivy.log().info("***Migrating to new TaskExpiredDateFilter");
        var filter = new ch.ivy.addon.portalkit.taskfilter.impl.TaskExpiredDateFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.taskfilter.impl.TaskNameFilter.class.getSimpleName())) {
        Ivy.log().info("***Migrating to new TaskNameFilter");
        var filter = new ch.ivy.addon.portalkit.taskfilter.impl.TaskNameFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.taskfilter.impl.TaskStateFilter.class.getSimpleName())) {
        Ivy.log().info("***Migrating to new TaskStateFilter");
        var filter = new ch.ivy.addon.portalkit.taskfilter.impl.TaskStateFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.taskfilter.impl.TaskWorkerFilter.class.getSimpleName())) {
        Ivy.log().info("***Migrating to new TaskWorkerFilter");
        var filter = new ch.ivy.addon.portalkit.taskfilter.impl.TaskWorkerFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.taskfilter.impl.TaskPriorityFilter.class.getSimpleName())) {
        Ivy.log().info("***Migrating to new TaskPriorityFilter");
        var filter = new ch.ivy.addon.portalkit.taskfilter.impl.TaskPriorityFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else {
        Ivy.log().info("***Copying custom taskfilter");
        filters.add(savedFilter);
      }
    }
    
    return filters;
  }

  private static List<ch.ivy.addon.portalkit.casefilter.CaseFilter> copyCaseFilters(
      List<ch.ivy.addon.portalkit.casefilter.CaseFilter> savedFilters) throws ReflectiveOperationException {

    var filters = new ArrayList<ch.ivy.addon.portalkit.casefilter.CaseFilter>();

    for (ch.ivy.addon.portalkit.casefilter.CaseFilter savedFilter : savedFilters) {
      
      if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.casefilter.impl.CaseCategoryFilter.class.getSimpleName())) {
        Ivy.log().info("***Migrating to new CaseCategoryFilter");
        var filter = new ch.ivy.addon.portalkit.casefilter.impl.CaseCategoryFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.casefilter.impl.CaseCreationDateFilter.class.getSimpleName())) {
        Ivy.log().info("***Migrating to new CaseCreationDateFilter");
        var filter = new ch.ivy.addon.portalkit.casefilter.impl.CaseCreationDateFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.casefilter.impl.CaseCreatorFilter.class.getSimpleName())) {
        Ivy.log().info("***Migrating to new CaseCreatorFilter");
        var filter = new ch.ivy.addon.portalkit.casefilter.impl.CaseCreatorFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.casefilter.impl.CaseDescriptionFilter.class.getSimpleName())) {
        Ivy.log().info("***Migrating to new CaseDescriptionFilter");
        var filter = new ch.ivy.addon.portalkit.casefilter.impl.CaseDescriptionFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.casefilter.impl.CaseFinishedDateFilter.class.getSimpleName())) {
        Ivy.log().info("***Migrating to new CaseFinishedDateFilter");
        var filter = new ch.ivy.addon.portalkit.casefilter.impl.CaseFinishedDateFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.casefilter.impl.CaseNameFilter.class.getSimpleName())) {
        Ivy.log().info("***Migrating to new CaseNameFilter");
        var filter = new ch.ivy.addon.portalkit.casefilter.impl.CaseNameFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.casefilter.impl.CaseStateFilter.class.getSimpleName())) {
        Ivy.log().info("***Migrating to new CaseStateFilter");
        var filter = new ch.ivy.addon.portalkit.casefilter.impl.CaseStateFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else {
        Ivy.log().info("***Copying custom casefilter");
        filters.add(savedFilter);
      }
    }
    
    return filters;
  }

  public static void copyFilterValues(ch.ivy.addon.portalkit.filter.AbstractFilter<?> filter,
      ch.ivy.addon.portalkit.filter.AbstractFilter<?> savedFilter) throws ReflectiveOperationException {
    Field[] fields = filter.getClass().getDeclaredFields();
    for (Field field : fields) {
      if (field.getAnnotation(JsonIgnore.class) == null) {
        String fieldName = field.getName();
        BeanUtils.copyProperty(filter, fieldName, PropertyUtils.getProperty(savedFilter, fieldName));
      }
    }
  }

  public static <T> List<T> findAll(Class<T> classType) {
    return portalmigration.util.IvyExecutor.executeAsSystem(() -> {
      Query<T> query = repo().search(classType);
      Result<T> result = query.limit(5).execute();
      long totalCount = result.totalCount();
      Ivy.log().info("***Total count of {0} in repo is {1}", classType, totalCount);
      return query.limit((int) totalCount).execute().getAll();
    });
  }

  public static <T> List<T> findAllByAppID(Class<T> classType, long appID) {
    return portalmigration.util.IvyExecutor.executeAsSystem(() -> {
      Query<T> query = repo().search(classType);
      long totalCount = query.limit(10).execute().totalCount();
      Ivy.log().info("***Total count of {0} in repo is {1}", classType, totalCount);
      
      var resultInfos = query.limit((int) totalCount).execute().getAllInfos();
      var resultIds = resultInfos.stream().filter(info -> info.getCreatedByAppId() == appID)
          .map(BusinessDataInfo::getId)
          .collect(Collectors.toList());
      
      List<T> result = new ArrayList<>();
      resultIds.forEach(entityId -> {
        Ivy.log().info("***Find entity in repo by ID {1}", entityId);
        var entity = repo().find(entityId, classType);
        if (entity != null) {
          result.add(entity);
        }
      });
      Ivy.log().info("***Total count of {0} in return list", result.size());
      return result;
    });
  }
  
  public static <T> List<BusinessDataInfo<T>> findAllBusinessInfoByAppID(Class<T> classType, long appID) {
    return portalmigration.util.IvyExecutor.executeAsSystem(() -> {
      Query<T> query = repo().search(classType);
      long totalCount = query.limit(10).execute().totalCount();
      Ivy.log().info("***Total count of {0} in repo is {1}", classType, totalCount);
      
      var resultInfos = query.limit((int) totalCount).execute().getAllInfos();
      var businessDataInfos = resultInfos.stream().filter(info -> info.getCreatedByAppId() == appID)
          .collect(Collectors.toList());
      
      Ivy.log().info("***Total count of {0} in return list", businessDataInfos.size());
      return businessDataInfos;
    });
  }

  public static <T> T findLatestObjectByAppID(Class<T> classType, long appID) {
    return portalmigration.util.IvyExecutor.executeAsSystem(() -> {
      Query<T> query = repo().search(classType);
      long totalCount = query.limit(10).execute().totalCount();
      Ivy.log().info("***Total count of {0} in repo is {1}", classType, totalCount);
      
      var resultInfos = query.limit((int) totalCount).execute().getAllInfos();
      var resultId = resultInfos.stream().filter(info -> info.getCreatedByAppId() == appID)
          .sorted(Comparator.comparingLong(BusinessDataInfo::getVersion))
          .map(BusinessDataInfo::getId)
          .sorted(Comparator.reverseOrder())
          .findFirst().orElse("");
      Ivy.log().info("***findLatestObjectByAppID: Found ID {0} of {1}", resultId, classType);
      return repo().find(resultId, classType);
    });
  }

  public static <T> List<T> findAllFiltersByApp(IApplication app, Class<T> classType) {
    return portalmigration.util.IvyExecutor.executeAsSystem(() -> {
      Result<T> result = repo().search(classType).limit(5).execute();
      long totalCount = result.totalCount();
      Ivy.log().info("***Total count of {0} in repo is {1}", classType, totalCount);
  
      Filter<T> filter = null;
      for (Long processId : getProcessModelIds(app)) {
        Ivy.log().info("***Filter data by processModel ID {0}", processId);
        if (filter == null) {
          filter = repo().search(classType).numberField(FILTER_GROUP_ID).isEqualTo(processId);
          continue;
        }
  
        filter.or().numberField(FILTER_GROUP_ID).isEqualTo(processId);
      }

      if (filter != null) {
        totalCount = filter.limit(5).execute().totalCount();
        Ivy.log().info("***Total count of {0} in app {1} is {2}", classType, Ivy.request().getApplication().getName(), totalCount);
        return filter.limit((int) totalCount).execute().getAll();
      }
      return new ArrayList<>();
    });
  }

  private static List<Long> getProcessModelIds(IApplication app) {
    return portalmigration.util.IvyExecutor.executeAsSystem(() -> {
      return app.getProcessModelsSortedByName().stream().map(IProcessModel::getId).collect(Collectors.toList());
    });
  }

  private static <T> String saveToRepo(T entity) {
    return portalmigration.util.IvyExecutor.executeAsSystem(() -> {
      return repo().save(entity).getId();
    });
  }

  private static void deleteById(String id) {
    portalmigration.util.IvyExecutor.executeAsSystem(() -> {
      repo().deleteById(id);
      return Void.class;
    });
  }

  public static BusinessDataRepository repo() {
    return portalmigration.util.IvyExecutor.executeAsSystem(() -> {
      return Ivy.repo();
    });
  }
}
