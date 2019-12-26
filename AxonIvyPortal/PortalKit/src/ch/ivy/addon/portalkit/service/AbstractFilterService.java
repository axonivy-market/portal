package ch.ivy.addon.portalkit.service;


import static ch.ivy.addon.portalkit.enums.FilterType.ALL_USERS;
import static ch.ivy.addon.portalkit.enums.FilterType.ONLY_ME;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import ch.ivy.addon.portalkit.bean.PermissionBean;
import ch.ivy.addon.portalkit.enums.FilterType;
import ch.ivy.addon.portalkit.filter.AbstractFilter;
import ch.ivy.addon.portalkit.filter.AbstractFilterData;
import ch.ivyteam.ivy.business.data.store.search.Filter;
import ch.ivyteam.ivy.business.data.store.search.Result;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractFilterService<T extends AbstractFilterData<?>> extends BusinessDataService<T> {

  private static final String FILTER_NAME = "filterName";
  private static final String USER_ID = "userId";
  private static final String FILTER_GROUP_ID = "filterGroupId";
  private static final String FILTER_TYPE = "type";

  public List<T> getPublicFilter(Long filterGroupId) {
    try {
      Filter<T> publicFilterQuery =
          repo().search(getType()).textField(FILTER_TYPE).isEqualToIgnoringCase(ALL_USERS.name()).and()
              .numberField(FILTER_GROUP_ID).isEqualTo(filterGroupId);
      Result<T> queryResult = publicFilterQuery.orderBy().textField(FILTER_NAME).ascending().limit(LIMIT_20).execute();
      long totalCount = queryResult.totalCount();
      if (totalCount > LIMIT_20) {
        queryResult =
            publicFilterQuery.orderBy().textField(FILTER_NAME).ascending().limit(Math.toIntExact(totalCount)).execute();
      }
      return queryResult.getAll();
    } catch (Exception e) {
      Ivy.log().error(e);
      return new ArrayList<>();
    }
  }

  public List<T> getPrivateFilterForCurrentUser(Long filterGroupId) {
    try {
      Filter<T> privateFilterQuery =
          repo().search(getType()).numberField(USER_ID).isEqualTo(Ivy.session().getSessionUser().getId()).and()
              .textField(FILTER_TYPE).isEqualToIgnoringCase(ONLY_ME.name()).and().numberField(FILTER_GROUP_ID)
              .isEqualTo(filterGroupId);
      Result<T> queryResult = privateFilterQuery.orderBy().textField(FILTER_NAME).ascending().limit(LIMIT_20).execute();
      long totalCount = queryResult.totalCount();
      if(totalCount > LIMIT_20) {
        queryResult = privateFilterQuery.orderBy().textField(FILTER_NAME).limit(Math.toIntExact(totalCount)).execute();
      }
      return queryResult.getAll();
    } catch (Exception e) {
      Ivy.log().error(e);
      return new ArrayList<>();
    }
  }

  public List<T> sortFilters(List<T> filters) {
    filters.sort((f1, f2) -> f1.getFilterName().toLowerCase().compareTo(f2.getFilterName().toLowerCase()));
    return filters;
  }

  public void copyFilterValues(AbstractFilter<?> filter, AbstractFilter<?> savedFilter) throws IllegalAccessException,
      InvocationTargetException, NoSuchMethodException {
    Field[] fields = filter.getClass().getDeclaredFields();
    for (Field field : fields) {
      if (field.getAnnotation(JsonIgnore.class) == null) {
        String fieldName = field.getName();
        BeanUtils.copyProperty(filter, fieldName, PropertyUtils.getProperty(savedFilter, fieldName));
      }
    }
  }

  public boolean isFilterExisted(String name, FilterType type, Long filterGroupId) {
    if (FilterType.ONLY_ME == type) {
      return repo().search(getType()).numberField(USER_ID).isEqualTo(Ivy.session().getSessionUser().getId()).and()
          .textField(FILTER_TYPE).isEqualToIgnoringCase(ONLY_ME.name()).and().textField(FILTER_NAME)
          .isEqualToIgnoringCase(name).and().numberField(FILTER_GROUP_ID).isEqualTo(filterGroupId).limit(1).execute().count() > 0;
    } else {
      return repo().search(getType()).textField(FILTER_NAME).isEqualToIgnoringCase(name).and().textField(FILTER_TYPE)
          .isEqualToIgnoringCase(ALL_USERS.name()).and().numberField(FILTER_GROUP_ID).isEqualTo(filterGroupId).limit(1)
          .execute().count() > 0;
    }
  }

  public boolean isDeleteFilterEnabledFor(T filterData) {
    if (FilterType.ONLY_ME.equals(filterData.getType())) {
      return true;
    } else {
      boolean isOwnerOfFilter =
          Optional.ofNullable(Ivy.session().getSessionUser()).map(IUser::getId).orElse(-1L)
              .equals(filterData.getUserId());
      boolean isAdmin = new PermissionBean().hasAdminPermission();
      if (isOwnerOfFilter || isAdmin) {
        return true;
      } else {
        return false;
      }
    }
  }

  public List<T> getAllPrivateFilters() {
    Filter<T> privateFilterQuery =
            repo().search(getType())
                .textField(FILTER_TYPE).isEqualToIgnoringCase(ONLY_ME.name());
    return privateFilterQuery.execute().getAll();
  }

}
