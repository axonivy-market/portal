package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.RoleDTO;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.ivydata.service.ISecurityService;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.query.UserQuery;
import ch.ivyteam.ivy.security.query.UserQuery.IFilterQuery;

public class SecurityService implements ISecurityService {

  private SecurityService() {}

  public static SecurityService newInstance() {
    return new SecurityService();
  }

  @Override
  public IvySecurityResultDTO findUsers(String query, int startIndex, int count, List<String> fromRoles, List<String> excludedUsernames) {
    return IvyExecutor.executeAsSystem(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      IApplication application = IApplication.current();
      result.setUsers(queryUsers(query, application, startIndex, count, fromRoles, excludedUsernames));
      return result;
    });
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
    UserQuery userQuery = app.getSecurityContext().users().query().orderBy().fullName();

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
    return IvyExecutor.executeAsSystem(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      List<UserDTO> userDTOs = queryUsers(query, app, startIndex, count, fromRoles, excludedUsernames);
      result.setUsers(userDTOs);
      return result;
    });
  }

  @Override
  public IvySecurityResultDTO findRoles() {
    return IvyExecutor.executeAsSystem(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      IApplication app = IApplication.current();
      List<IRole> roles = ServiceUtilities.findAllRoles(app);
      result.setRoles(roles);
      return result;
    });
  }

  @Override
  public IvySecurityResultDTO findRoleDTOs() {
    return IvyExecutor.executeAsSystem(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      IApplication app = IApplication.current();
      List<RoleDTO> roles = ServiceUtilities.findAllRoleDTO(app);
      roles.sort(getRoleDTOComparator());
      result.setRoleDTOs(roles);
      return result;
    });
  }

  @Override
  public IvySecurityResultDTO findSecurityMembers(String query, int startIndex, int count) {
    return IvyExecutor.executeAsSystem(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      IApplication app = IApplication.current();
      List<RoleDTO> roles = ServiceUtilities.findAllRoleDTO(app).stream()
          .filter(role -> doesNameContainQuery(query, role))
          .sorted(getRoleDTOComparator())
          .collect(Collectors.toList());

      List<UserDTO> users = queryUsers(query, app, startIndex, count, null, null);

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

  private UserQuery queryHasRoles(IApplication app, List<String> fromRoles) {
    List<IRole> roles = new ArrayList<>();
    for (String roleName : fromRoles) {
      IRole iRole = app.getSecurityContext().roles().find(roleName);
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
    return IvyExecutor.executeAsSystem(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      IApplication application = IApplication.current();
      result.setUsers(queryAllUsers(application, startIndex, count, fromRoles, excludedUsernames));
      return result;
    });
  }

  private List<UserDTO> queryAllUsers(IApplication application, int startIndex, int count, List<String> fromRoles, List<String> excludedUsernames) {
    UserQuery userQuery = application.getSecurityContext().users().query().orderBy().fullName();
    IFilterQuery filterQuery = userQuery.where();

    if (CollectionUtils.isNotEmpty(fromRoles)) {
      UserQuery hasRolesQuery = UserQuery.create().where().or(queryHasRoles(application, fromRoles));
      filterQuery.andOverall(hasRolesQuery);
    }
    excludeUsername(excludedUsernames, filterQuery);

    List<IUser> users = userQuery
        .orderBy().fullName().name()
        .executor().results(startIndex, count);
    return users.stream().map(UserDTO::new).collect(Collectors.toList());
  }
}
