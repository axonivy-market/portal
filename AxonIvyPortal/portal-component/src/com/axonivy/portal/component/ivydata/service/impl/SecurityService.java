package com.axonivy.portal.component.ivydata.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.component.dto.UserDTO;
import com.axonivy.portal.component.ivydata.dto.IvySecurityResultDTO;
import com.axonivy.portal.component.ivydata.exception.PortalIvyDataErrorType;
import com.axonivy.portal.component.ivydata.exception.PortalIvyDataException;
import com.axonivy.portal.component.ivydata.service.ISecurityService;
import com.axonivy.portal.component.ivydata.utils.ServiceUtilities;
import com.axonivy.portal.component.util.IvyExecutor;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.Recordset;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.query.IUserQueryExecutor;
import ch.ivyteam.ivy.security.query.UserQuery;
import ch.ivyteam.ivy.security.query.UserQuery.IFilterQuery;
import ch.ivyteam.ivy.server.ServerFactory;

public class SecurityService implements ISecurityService {

  private SecurityService() {}

  public static SecurityService newInstance() {
    return new SecurityService();
  }

  @Override
  public IvySecurityResultDTO findUsers(String query, List<String> apps, int startIndex, int count, List<String> fromRoles, List<String> excludedUsernames) {
    return IvyExecutor.executeAsSystem(() -> { 
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      if (CollectionUtils.isEmpty(apps)) {
        return result;
      }
      try {
        result.setUsers(queryUsers(query, apps, startIndex, count, fromRoles, excludedUsernames));
      } catch (PortalIvyDataException e) {
        result.setErrors(Arrays.asList(e));
      } 
      return result;
    });
  }

  /**
   * Query users in engine
   * @param query
   * @param apps
   * @param startIndex
   * @param count
   * @param fromRoles
   * @param excludedUsernames
   * @return {@link List} 
   * @throws PortalIvyDataException
   */
  private List<UserDTO> queryUsers(String query, List<String> apps, int startIndex, int count, List<String> fromRoles, List<String> excludedUsernames) throws PortalIvyDataException {
    IUserQueryExecutor executor = ServerFactory.getServer().getSecurityManager().getUserQueryExecutor();
    UserQuery userQuery = executor.createUserQuery().groupBy().name().fullName().orderBy().fullName();
    IFilterQuery filterQuery = createFilterQuery(query, userQuery);
    
    if (CollectionUtils.isNotEmpty(fromRoles)) {
      UserQuery hasRolesQuery = UserQuery.create();
      for (String appName : apps) {
        IApplication app = ServiceUtilities.findApp(appName);
        hasRolesQuery.where().or(queryHasRoles(app, fromRoles));
      }
      filterQuery.andOverall(hasRolesQuery);
    }
    excludeUsername(excludedUsernames, filterQuery);
    
    Recordset recordset = executor.getRecordset(userQuery, startIndex, count);
    return recordset.getRecords()
        .stream()
        .map(UserDTO::new)
        .collect(Collectors.toList());
  }

  private IFilterQuery createFilterQuery(String query, UserQuery userQuery) {
    String containingQuery = "%"+ StringUtils.defaultString(query, StringUtils.EMPTY) +"%";
    IFilterQuery filterQuery = userQuery.where();
    filterQuery.fullName()
      .isLikeIgnoreCase(containingQuery)
      .or()
      .name()
      .isLikeIgnoreCase(containingQuery);
    return filterQuery;
  }

  private UserQuery queryHasRoles(IApplication app, List<String> fromRoles) {
    List<IRole> roles = new ArrayList<>();
    for (String roleName : fromRoles) {
      IRole iRole = app.getSecurityContext().findRole(roleName);
      if (Objects.nonNull(iRole)) {
        roles.add(iRole);
      } else {
        Ivy.log().warn("Cannot find role name: {0}", roleName);
      }
    }
    
    UserQuery hasRolesQuery = UserQuery.create();
    IFilterQuery hasRolesFilter = hasRolesQuery.where();
    for (IRole role : roles) {
      hasRolesFilter.or().hasRole(role);
    }
    return hasRolesQuery;
  }

  private void excludeUsername(List<String> excludedUsernames, IFilterQuery filterQuery) {
    if (CollectionUtils.isNotEmpty(excludedUsernames)) {
      UserQuery excludeUsernameQuery = queryExcludeUsernames(excludedUsernames);
      filterQuery.andOverall(excludeUsernameQuery);
    }
  }

  private UserQuery queryExcludeUsernames(List<String> excludedUsernames) {
    UserQuery excludeUsernameQuery = UserQuery.create();
    IFilterQuery excludeUsernameFilter = excludeUsernameQuery.where();
    for (String username : excludedUsernames) {
      excludeUsernameFilter.and().name().isNotEqual(username);
    }
    return excludeUsernameQuery;
  }

  @Override
  public IvySecurityResultDTO findUsers(String query, IApplication app, int startIndex, int count, List<String> fromRoles, List<String> excludedUsernames) {
    return IvyExecutor.executeAsSystem(() -> {
      if (app.getActivityState() != ActivityState.ACTIVE) {
        return createErrorResult(app.getName());
      }
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      List<UserDTO> userDTOs = queryUsers(query, app, startIndex, count, fromRoles, excludedUsernames);
      result.setUsers(userDTOs);
      return result;
    });
  }

  private IvySecurityResultDTO createErrorResult(String appName) {
    IvySecurityResultDTO result = new IvySecurityResultDTO();
    result.setErrors(Arrays.asList(new PortalIvyDataException(appName, PortalIvyDataErrorType.APP_NOT_FOUND.toString())));
    return result;
  }
  
  /**
   * Query users in specific application
   * @param query
   * @param app
   * @param startIndex
   * @param count
   * @param fromRoles
   * @param excludedUsernames
   * @return {@link List}
   */
  private List<UserDTO> queryUsers(String query, IApplication app, int startIndex, int count, List<String> fromRoles, List<String> excludedUsernames) {
    UserQuery userQuery = app.getSecurityContext().users().query();
    
    IFilterQuery filterQuery = createFilterQuery(query, userQuery);
    
    if (CollectionUtils.isNotEmpty(fromRoles)) {
      UserQuery hasRolesQuery = queryHasRoles(app, fromRoles);
      filterQuery.andOverall(hasRolesQuery);
    }
    excludeUsername(excludedUsernames, filterQuery);
    
    List<IUser> users = userQuery
        .orderBy().fullName().name()
        .executor().results(startIndex, count);
    return users.stream().map(UserDTO::new).collect(Collectors.toList());
  }
}
