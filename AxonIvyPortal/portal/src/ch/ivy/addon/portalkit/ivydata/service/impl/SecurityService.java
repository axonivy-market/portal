package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.dto.UserDTO;

import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.ivydata.service.ISecurityService;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.security.query.UserQuery;
import ch.ivyteam.ivy.security.query.UserQuery.IFilterQuery;

public class SecurityService implements ISecurityService {

  private SecurityService() {}

  public static SecurityService newInstance() {
    return new SecurityService();
  }

  @Override
  public IvySecurityResultDTO findUsers(String query, int startIndex, int count, List<String> fromRoles, List<String> excludedUsernames) {
    return Sudo.get(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      result.setUsers(queryUsers(query, startIndex, count, fromRoles, excludedUsernames));
      return result;
    });
  }

  @Override
  public IvySecurityResultDTO findUsersWithRoles(String query, int startIndex,
      int count, List<String> fromRoles, List<String> excludedUsernames) {
    return Sudo.get(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      result.setUsers(
          queryUsersWithRoles(query, startIndex, count, fromRoles,
              excludedUsernames));
      return result;
    });
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
      filterQuery.andOverall(hasRolesQuery);
    }
    excludeUsername(excludedUsernames, filterQuery);

    List<IUser> users = userQuery
        .orderBy().fullName().name()
        .executor().results(startIndex, count);
    return users.stream().map(UserDTO::new).collect(Collectors.toList());
  }

  private List<UserDTO> queryUsersWithRoles(String query, int startIndex,
      int count, List<String> fromRoles, List<String> excludedUsernames) {
    UserQuery userQuery = ISecurityContext.current().users().query();

    IFilterQuery filterQuery = createFilterQuery(query, userQuery);

    if (CollectionUtils.isNotEmpty(fromRoles)) {
      UserQuery hasRolesQuery = queryHasRoles(fromRoles);
      filterQuery.andOverall(hasRolesQuery);
    }
    excludeUsername(excludedUsernames, filterQuery);

    List<IUser> users = userQuery.orderBy().fullName().name().executor()
        .results(startIndex, count);
    return users.stream().map(UserDTO::newUserWithRoles)
        .collect(Collectors.toList());
  }

  private void excludeUsername(List<String> excludedUsernames, IFilterQuery filterQuery) {
    if (CollectionUtils.isNotEmpty(excludedUsernames)) {
      UserQuery excludeUsernameQuery = queryExcludeUsernames(excludedUsernames);
      filterQuery.andOverall(excludeUsernameQuery);
    }
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

  @Override
  public IvySecurityResultDTO findUsers(String query, IApplication app, int startIndex, int count, List<String> fromRoles, List<String> excludedUsernames) {
    return Sudo.get(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      List<UserDTO> userDTOs = queryUsers(query, startIndex, count, fromRoles, excludedUsernames);
      result.setUsers(userDTOs);
      return result;
    });
  }

  @Override
  public IvySecurityResultDTO findRoles() {
    return Sudo.get(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      List<IRole> roles = ServiceUtilities.findAllRoles();
      result.setRoles(roles);
      return result;
    });
  }

  @Override
  public IvySecurityResultDTO findRoleDTOs() {
    return Sudo.get(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      List<RoleDTO> roles = ServiceUtilities.findAllRoleDTO();
      roles.sort(getRoleDTOComparator());
      result.setRoleDTOs(roles);
      return result;
    });
  }

  @Override
  public IvySecurityResultDTO findSecurityMembers(String query, int startIndex, int count) {
    return Sudo.get(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      List<RoleDTO> roles = ServiceUtilities.findAllRoleDTO().stream()
          .filter(role -> doesNameContainQuery(query, role))
          .sorted(getRoleDTOComparator())
          .collect(Collectors.toList());

      List<UserDTO> users = queryUsers(query, startIndex, count, null, null);

      List<SecurityMemberDTO> members = SecurityMemberDTOMapper.mapFromUserDTOs(users);
      members.addAll(SecurityMemberDTOMapper.mapFromRoleDTOs(roles));
      int size = count;
      if (count <= 0) {
        size = members.size();
      }
      result.setSecurityMembers(members.subList(startIndex, Math.min(size, members.size())));
      return result;
    });
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

    UserQuery hasRolesQuery = UserQuery.create();
    IFilterQuery hasRolesFilter = hasRolesQuery.where();
    for (IRole role : roles) {
      hasRolesFilter.or().hasRole(role);
    }
    return hasRolesQuery;
  }

  private boolean doesNameContainQuery(String query, RoleDTO role) {
    return StringUtils.containsIgnoreCase(role.getDisplayName(), query) || StringUtils.containsIgnoreCase(role.getName(), query);
  }

  private Comparator<? super RoleDTO> getRoleDTOComparator() {
    return (u1, u2) -> StringUtils.compareIgnoreCase(u1.getDisplayName(), u2.getDisplayName());
  }

  @Override
  public IvySecurityResultDTO findAllUsersOfRoles(int startIndex, int count, List<String> fromRoles,
      List<String> excludedUsernames) {
    return Sudo.get(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      result.setUsers(queryAllUsers(startIndex, count, fromRoles, excludedUsernames));
      return result;
    });
  }

  private List<UserDTO> queryAllUsers(int startIndex, int count, List<String> fromRoles, List<String> excludedUsernames) {
    UserQuery userQuery = ISecurityContext.current().users().query();
    IFilterQuery filterQuery = userQuery.where();

    if (CollectionUtils.isNotEmpty(fromRoles)) {
      UserQuery hasRolesQuery = UserQuery.create().where().or(queryHasRoles(fromRoles));
      filterQuery.andOverall(hasRolesQuery);
    }
    excludeUsername(excludedUsernames, filterQuery);

    List<IUser> users = userQuery
        .orderBy().fullName().name()
        .executor().results(startIndex, count);
    return users.stream().map(UserDTO::new).collect(Collectors.toList());
  }
}
