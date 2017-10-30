package ch.ivy.addon.portalkit.service;


import static ch.ivy.addon.portalkit.enums.FilterType.ALL_USERS;
import static ch.ivy.addon.portalkit.enums.FilterType.ONLY_ME;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.taskfilter.TaskFilter;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterData;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.environment.Ivy;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TaskFilterService extends BusinessDataService<TaskFilterData> {

  private static final String FILTER_NAME = "filterName";
  private static final String USER_ID = "userId";
  private static final String FILTER_TYPE = "type";

  public List<TaskFilterData> findByUserName() {
    Filter<TaskFilterData> publicFilterQuery =
        repo().search(getType()).textField(FILTER_TYPE).isEqualToIgnoringCase(ALL_USERS.name());
    Filter<TaskFilterData> privateFilterQuery =
        repo().search(getType()).numberField(USER_ID).isEqualTo(Ivy.session().getSessionUser().getId()).and()
            .textField(FILTER_TYPE).isEqualToIgnoringCase(ONLY_ME.name());
    Filter<TaskFilterData> combinedQuery =
        repo().search(getType()).filter(publicFilterQuery).or().filter(privateFilterQuery);
    return combinedQuery.orderBy().textField(FILTER_NAME).ascending().execute().getAll();
  }

  @Override
  public Class<TaskFilterData> getType() {
    return TaskFilterData.class;
  }

  public List<TaskFilterData> sortFilters(List<TaskFilterData> filters) {
    filters.sort((f1, f2) -> f1.getFilterName().compareTo(f2.getFilterName()));
    return filters;
  }

  public void applyFilter(TaskLazyDataModel dataModel, TaskFilterData savedFilterData)
      throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    List<TaskFilter> filters = dataModel.getFilterContainer().getFilters();
    dataModel.setSelectedFilters(new ArrayList<>());
    for (int i = 0; i < filters.size(); i++) {
      TaskFilter taskFilter = dataModel.getFilterContainer().getFilters().get(i);
      for (int j = 0; j < savedFilterData.getTaskFilters().size(); j++) {
        TaskFilter savedTaskFilter = savedFilterData.getTaskFilters().get(j);
        if (taskFilter.getClass().equals(savedTaskFilter.getClass())) {
          copyFilterValues(taskFilter, savedTaskFilter);
          dataModel.getSelectedFilters().add(taskFilter);
          break;
        }
      }
    }
  }

  public void copyFilterValues(TaskFilter taskFilter, TaskFilter savedTaskFilter)
      throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    Field[] fields = taskFilter.getClass().getDeclaredFields();
    for (Field field : fields) {
      if (field.getAnnotation(JsonIgnore.class) == null) {
        String fieldName = field.getName();
        BeanUtils.copyProperty(taskFilter, fieldName, PropertyUtils.getProperty(savedTaskFilter, fieldName));
      }
    }
  }
}
