package ch.ivy.addon.portalkit.service;

import static ch.ivy.addon.portalkit.enums.FilterType.ALL_USERS;
import static ch.ivy.addon.portalkit.enums.FilterType.ONLY_ME;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.casefilter.impl.CaseFilterData;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskAnalysisFilterData;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskFilterData;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.business.data.store.BusinessDataRepository;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.environment.Ivy;

@SuppressWarnings("deprecation")
public class DeprecatedFilterService {

  private static final String USER_ID = "userId";
  private static final String FILTER_GROUP_ID = "filterGroupId";
  private static final String FILTER_TYPE = "type";

  public static <T> void recoverOldFiler(Class<T> type, Long filterGroupId, boolean isPublicFilter) {
    if (type.getSimpleName().equals(CaseFilterData.class.getSimpleName())) {
      recoverCaseFilters(filterGroupId, isPublicFilter);
    }
    else if (type.getSimpleName().equals(TaskFilterData.class.getSimpleName())) {
      recoverTaskFilters(filterGroupId, isPublicFilter);
    }
    else if (type.getSimpleName().equals(TaskAnalysisFilterData.class.getSimpleName())) {
      recoverTaskAnalysisFilters(filterGroupId, isPublicFilter);
    }
  }

  private static List<String> recoverCaseFilters(Long filterGroupId, boolean isPublicFilter) {
    var oldFilters = findAll(ch.ivy.addon.portalkit.casefilter.CaseFilterData.class, filterGroupId, isPublicFilter);
    if (CollectionUtils.isEmpty(oldFilters)) {
      return new ArrayList<>();
    }

    List<String> filterIdList = new ArrayList<>();
    List<String> removeFilterIds = new ArrayList<>();
    ch.ivy.addon.portalkit.casefilter.impl.CaseFilterData newFilterData = null;

    for (var savedFilterData : oldFilters) {
      newFilterData = new ch.ivy.addon.portalkit.casefilter.impl.CaseFilterData();
      try {
        newFilterData.setFilters(copyCaseFilters(savedFilterData.getFilters()));
      } catch (ReflectiveOperationException e) {
        Ivy.log().error("***Cannot recover old CaseFilter id: " + savedFilterData.getId(), e);
        continue;
      }
      mergeCommonFields(newFilterData, savedFilterData);

      filterIdList.add(saveToRepo(newFilterData));
      removeFilterIds.add(savedFilterData.getId());
    }

    removeFilterIds.forEach(filterId -> {
      Ivy.log().debug("***Removing old CaseFilter id: {0}", filterId);
      deleteById(filterId);
    });
    
    return filterIdList;
  }

  private static List<String> recoverTaskFilters(Long filterGroupId, boolean isPublicFilter) {
    var oldFilters = findAll(ch.ivy.addon.portalkit.taskfilter.TaskFilterData.class, filterGroupId, isPublicFilter);
    if (CollectionUtils.isEmpty(oldFilters)) {
      return new ArrayList<>();
    }

    List<String> filterIdList = new ArrayList<>();
    List<String> removeFilterIds = new ArrayList<>();
    ch.ivy.addon.portalkit.taskfilter.impl.TaskFilterData newFilterData = null;

    for (var savedFilterData : oldFilters) {
      newFilterData = new ch.ivy.addon.portalkit.taskfilter.impl.TaskFilterData();
      try {
        newFilterData.setFilters(copyTaskFilters(savedFilterData.getFilters()));
      } catch (ReflectiveOperationException e) {
        Ivy.log().error("***Cannot migrate old TaskFilter id: " + savedFilterData.getId(), e);
        continue;
      }
      mergeCommonFields(newFilterData, savedFilterData);

      filterIdList.add(saveToRepo(newFilterData));
      removeFilterIds.add(savedFilterData.getId());
    }

    removeFilterIds.forEach(filterId -> {
      Ivy.log().debug("***Removing TaskFilter id: {0}", filterId);
      deleteById(filterId);
    });
    return filterIdList;
  }

  private static List<String> recoverTaskAnalysisFilters(Long filterGroupId, boolean isPublicFilter) {
    var oldFilters = findAll(ch.ivy.addon.portalkit.taskfilter.TaskAnalysisFilterData.class, filterGroupId, isPublicFilter);
    if (CollectionUtils.isEmpty(oldFilters)) {
      return new ArrayList<>();
    }

    List<String> filterIdList = new ArrayList<>();
    List<String> removeFilterIds = new ArrayList<>();
    ch.ivy.addon.portalkit.taskfilter.impl.TaskAnalysisFilterData newFilterData = null;

    for (var savedFilterData : oldFilters) {
      newFilterData = new ch.ivy.addon.portalkit.taskfilter.impl.TaskAnalysisFilterData();
      try {
        newFilterData.setTaskFilters(copyTaskFilters(savedFilterData.getTaskFilters()));
        newFilterData.setCaseFilters(copyCaseFilters(savedFilterData.getCaseFilters()));
      } catch (ReflectiveOperationException e) {
        Ivy.log().error("***Cannot migrate old TaskAnalysis filter id: " + savedFilterData.getId(), e);
        continue;
      }
      mergeCommonFields(newFilterData, savedFilterData);

      filterIdList.add(saveToRepo(newFilterData));
      removeFilterIds.add(savedFilterData.getId());
    }

    removeFilterIds.forEach(filterId -> {
      Ivy.log().debug("***Removing TaskAnalysis filter id: {0}", filterId);
      deleteById(filterId);
    });
    return filterIdList;
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
        Ivy.log().debug("***Converting to new TaskCategoryFilter");
        var filter = new ch.ivy.addon.portalkit.taskfilter.impl.TaskCategoryFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.taskfilter.impl.TaskCreationDateFilter.class.getSimpleName())) {
        Ivy.log().debug("***Converting to new TaskCreationDateFilter");
        var filter = new ch.ivy.addon.portalkit.taskfilter.impl.TaskCreationDateFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.taskfilter.impl.TaskResponsibleFilter.class.getSimpleName())) {
        Ivy.log().debug("***Converting to new TaskResponsibleFilter");
        var filter = new ch.ivy.addon.portalkit.taskfilter.impl.TaskResponsibleFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.taskfilter.impl.TaskDescriptionFilter.class.getSimpleName())) {
        Ivy.log().debug("***Converting to new TaskDescriptionFilter");
        var filter = new ch.ivy.addon.portalkit.taskfilter.impl.TaskDescriptionFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.taskfilter.impl.TaskExpiredDateFilter.class.getSimpleName())) {
        Ivy.log().debug("***Converting to new TaskExpiredDateFilter");
        var filter = new ch.ivy.addon.portalkit.taskfilter.impl.TaskExpiredDateFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.taskfilter.impl.TaskNameFilter.class.getSimpleName())) {
        Ivy.log().debug("***Converting to new TaskNameFilter");
        var filter = new ch.ivy.addon.portalkit.taskfilter.impl.TaskNameFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.taskfilter.impl.TaskStateFilter.class.getSimpleName())) {
        Ivy.log().debug("***Converting to new TaskStateFilter");
        var filter = new ch.ivy.addon.portalkit.taskfilter.impl.TaskStateFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.taskfilter.impl.TaskWorkerFilter.class.getSimpleName())) {
        Ivy.log().debug("***Converting to new TaskWorkerFilter");
        var filter = new ch.ivy.addon.portalkit.taskfilter.impl.TaskWorkerFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.taskfilter.impl.TaskPriorityFilter.class.getSimpleName())) {
        Ivy.log().debug("***Converting to new TaskPriorityFilter");
        var filter = new ch.ivy.addon.portalkit.taskfilter.impl.TaskPriorityFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else {
        Ivy.log().debug("***Copying custom taskfilter");
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
        Ivy.log().debug("***Converting to new CaseCategoryFilter");
        var filter = new ch.ivy.addon.portalkit.casefilter.impl.CaseCategoryFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.casefilter.impl.CaseCreationDateFilter.class.getSimpleName())) {
        Ivy.log().debug("***Converting to new CaseCreationDateFilter");
        var filter = new ch.ivy.addon.portalkit.casefilter.impl.CaseCreationDateFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.casefilter.impl.CaseCreatorFilter.class.getSimpleName())) {
        Ivy.log().debug("***Converting to new CaseCreatorFilter");
        var filter = new ch.ivy.addon.portalkit.casefilter.impl.CaseCreatorFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.casefilter.impl.CaseDescriptionFilter.class.getSimpleName())) {
        Ivy.log().debug("***Converting to new CaseDescriptionFilter");
        var filter = new ch.ivy.addon.portalkit.casefilter.impl.CaseDescriptionFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.casefilter.impl.CaseFinishedDateFilter.class.getSimpleName())) {
        Ivy.log().debug("***Converting to new CaseFinishedDateFilter");
        var filter = new ch.ivy.addon.portalkit.casefilter.impl.CaseFinishedDateFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.casefilter.impl.CaseNameFilter.class.getSimpleName())) {
        Ivy.log().debug("***Converting to new CaseNameFilter");
        var filter = new ch.ivy.addon.portalkit.casefilter.impl.CaseNameFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else if (savedFilter.getClass().getSimpleName().equals(ch.ivy.addon.portalkit.casefilter.impl.CaseStateFilter.class.getSimpleName())) {
        Ivy.log().debug("***Converting to new CaseStateFilter");
        var filter = new ch.ivy.addon.portalkit.casefilter.impl.CaseStateFilter();
        copyFilterValues(filter, savedFilter);
        filters.add(filter);
      }

      else {
        Ivy.log().debug("***Copying custom casefilter");
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

  public static <T> List<T> findAll(Class<T> classType, Long filterGroupId, boolean isPublicFilter) {
    return IvyExecutor.executeAsSystem(() -> {
      Filter<T> filter = repo().search(classType).numberField(FILTER_GROUP_ID).isEqualTo(filterGroupId);
      if (isPublicFilter) {
        filter.and().textField(FILTER_TYPE).isEqualToIgnoringCase(ALL_USERS.name());
      } else {
        filter.and().numberField(USER_ID).isEqualTo(Ivy.session().getSessionUser().getId())
            .and().textField(FILTER_TYPE).isEqualToIgnoringCase(ONLY_ME.name());
      }

      long totalCount = filter.limit(5).execute().totalCount();
      return filter.limit((int) totalCount).execute().getAll();
    });
  }

  private static <T> String saveToRepo(T entity) {
    return IvyExecutor.executeAsSystem(() -> {
      return repo().save(entity).getId();
    });
  }

  private static void deleteById(String id) {
    IvyExecutor.executeAsSystem(() -> {
      repo().deleteById(id);
      return Void.class;
    });
  }

  public static BusinessDataRepository repo() {
    return IvyExecutor.executeAsSystem(() -> {
      return Ivy.repo();
    });
  }

}
