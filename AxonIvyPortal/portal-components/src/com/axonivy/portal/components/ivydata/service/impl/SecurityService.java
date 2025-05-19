package com.axonivy.portal.components.ivydata.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.UserDTO;
import com.axonivy.portal.components.enums.AdditionalProperty;
import com.axonivy.portal.components.ivydata.dto.IvySecurityResultDTO;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.security.query.UserQuery;
import ch.ivyteam.ivy.security.query.UserQuery.IFilterQuery;

public class SecurityService{

  private SecurityService() {}

  public static SecurityService newInstance() {
    return new SecurityService();
  }

  public IvySecurityResultDTO findUsers(String query, int startIndex, int count, List<String> fromRoles, List<String> excludedUsernames) {
    return Sudo.get(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      result.setUsers(queryUsers(query, startIndex, count, fromRoles, excludedUsernames));
      return result;
    });
  }
  
  public IvySecurityResultDTO findRoleDTOs() {
    return Sudo.get(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      List<RoleDTO> roles = findAllRoleDTO();
      roles.sort(getRoleDTOComparator());
      result.setRoleDTOs(roles);
      return result;
    });
  }
  
  public static List<RoleDTO> findAllRoleDTO() {
    return Sudo.get(() -> {
      return CollectionUtils.emptyIfNull(ISecurityContext.current().roles().all())
          .stream()
          .filter(role -> role.getProperty(AdditionalProperty.HIDE.toString()) == null)
          .map(role -> new RoleDTO(role))
          .collect(Collectors.toList());
    });
  }
  
  private Comparator<? super RoleDTO> getRoleDTOComparator() {
    return (u1, u2) -> StringUtils.compareIgnoreCase(u1.getDisplayName(), u2.getDisplayName());
  }
  
  /**
   * Query users in specific application
   * @param query
   * @param startIndex
   * @param count
   * @param fromRoles
   * @param excludedUsernames
   * @return {@link List}
   */
  private List<UserDTO> queryUsers(String query, int startIndex, int count, List<String> fromRoles, List<String> excludedUsernames) {
    UserQuery userQuery = ISecurityContext.current().users().query();

    IFilterQuery filterQuery = createFilterQuery(query, userQuery);

    if (CollectionUtils.isNotEmpty(fromRoles)) {
      UserQuery hasRolesQuery = queryHasRoles(fromRoles);
      if (hasRolesQuery != null) {
        filterQuery.andOverall(hasRolesQuery);
      }
    }
    excludeUsername(excludedUsernames, filterQuery);

    List<IUser> users = userQuery
        .orderBy().fullName().name()
        .executor().results(startIndex, count);
    return users.stream().map(UserDTO::new).collect(Collectors.toList());
  }

  private void excludeUsername(List<String> excludedUsernames, IFilterQuery filterQuery) {
    if (CollectionUtils.isNotEmpty(excludedUsernames)) {
      UserQuery excludeUsernameQuery = queryExcludeUsernames(excludedUsernames);
      filterQuery.andOverall(excludeUsernameQuery);
    }
  }

  private IFilterQuery createFilterQuery(String query, UserQuery userQuery) {
    String containingQuery = "%"+ Objects.toString(query, StringUtils.EMPTY) +"%";
    IFilterQuery filterQuery = userQuery.where();
    filterQuery.fullName()
      .isLikeIgnoreCase(containingQuery)
      .or()
      .name()
      .isLikeIgnoreCase(containingQuery);
    return filterQuery;
  }

  private UserQuery queryExcludeUsernames(List<String> excludedUsernames) {
    UserQuery excludeUsernameQuery = UserQuery.create();
    IFilterQuery excludeUsernameFilter = excludeUsernameQuery.where();
    for (String username : excludedUsernames) {
      excludeUsernameFilter.and().name().isNotEqual(username);
    }
    return excludeUsernameQuery;
  }

  private UserQuery queryHasRoles(List<String> fromRoles) {
    List<IRole> roles = new ArrayList<>();
    for (String roleName : fromRoles) {
      IRole iRole = ISecurityContext.current().roles().find(roleName);
      if (Objects.nonNull(iRole)) {
        roles.add(iRole);
      } else {
        Ivy.log().warn("Cannot find role name: {0}", roleName);
      }
    }

    if (CollectionUtils.isEmpty(roles)) {
      return null;
    }

    UserQuery hasRolesQuery = UserQuery.create();
    IFilterQuery hasRolesFilter = hasRolesQuery.where();
    for (IRole role : roles) {
      hasRolesFilter.or().hasRole(role);
    }
    return hasRolesQuery;
  }
}