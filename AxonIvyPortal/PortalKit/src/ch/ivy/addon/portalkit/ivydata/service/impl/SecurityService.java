package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.RoleDTO;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataErrorType;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;
import ch.ivy.addon.portalkit.ivydata.mapper.SecurityMemberDTOMapper;
import ch.ivy.addon.portalkit.ivydata.service.ISecurityService;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.ActivityState;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.Recordset;
import ch.ivyteam.ivy.security.IRole;
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
      catch (Exception ex) {
        Ivy.log().error("Unexpected exception in getting users", ex);
      }
      return result;
    });
  }
  
  private List<UserDTO> queryUsers(String query, List<String> apps, int startIndex, int count, List<String> fromRoles, List<String> excludedUsernames) throws PortalIvyDataException {
    String containingQuery = "%"+ StringUtils.defaultString(query, StringUtils.EMPTY) +"%";
    IUserQueryExecutor executor = ServerFactory.getServer().getSecurityManager().getUserQueryExecutor();
    UserQuery userQuery = executor.createUserQuery().orderBy().fullName();
    IFilterQuery filterQuery = userQuery.where();
    
    filterQuery.fullName()
      .isLikeIgnoreCase(containingQuery)
      .or()
      .name()
      .isLikeIgnoreCase(containingQuery);
    if (CollectionUtils.isNotEmpty(fromRoles)) {
      UserQuery hasRolesQuery = UserQuery.create();
      for (String appName : apps) {
        IApplication app = ServiceUtilities.findApp(appName);
        hasRolesQuery.where().or(queryHasRoles(app, fromRoles));
      }
      filterQuery.andOverall(hasRolesQuery);
    }
    if (CollectionUtils.isNotEmpty(excludedUsernames)) {
      UserQuery excludeUsernameQuery = queryExcludeUsernames(excludedUsernames);
      filterQuery.andOverall(excludeUsernameQuery);
    }
    Recordset recordset = executor.getRecordset(userQuery, startIndex, count);
    return recordset.getRecords()
        .stream()
        .map(UserDTO::new)
        .collect(Collectors.toList());
  }

  @Override
  public IvySecurityResultDTO findUsers(String query, IApplication app, int startIndex, int count, List<String> fromRoles, List<String> excludedUsernames) {
    return IvyExecutor.executeAsSystem(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      if (app.getActivityState() != ActivityState.ACTIVE) {
        result.setErrors(Arrays.asList(new PortalIvyDataException(app.getName(), PortalIvyDataErrorType.APP_NOT_FOUND.toString())));
        return result;
      }

      try {
        List<UserDTO> userDTOs = queryUsers(query, Arrays.asList(app.getName()), startIndex, count, fromRoles, excludedUsernames);
        result.setUsers(userDTOs);
      } catch (PortalIvyDataException e) {
        result.setErrors(Arrays.asList(e));
      }
      return result;
    });
  }
  
  @Override
  public IvySecurityResultDTO findRoles(List<String> apps) {
    return IvyExecutor.executeAsSystem(() -> { 
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      if (CollectionUtils.isEmpty(apps)) {
        return result;
      }
      List<PortalIvyDataException> errors = new ArrayList<>();
      List<IRole> roles = new ArrayList<>();
      for (String appName : apps) {
        try {
          IApplication app = ServiceUtilities.findApp(appName);
          roles.addAll(ServiceUtilities.findAllRoles(app));
        } catch (PortalIvyDataException e) {
          errors.add(e);
        } catch (Exception ex) {
          Ivy.log().error("Unexpected exception in getting roles within app {0}", ex, appName);
        }
      }
      result.setErrors(errors);
      result.setRoles(roles);
      return result;
    });
  }
  
  @Override
  public IvySecurityResultDTO findRoles(IApplication app) {
    return IvyExecutor.executeAsSystem(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      if (app.getActivityState() != ActivityState.ACTIVE) {
        result.setErrors(Arrays.asList(new PortalIvyDataException(app.getName(), PortalIvyDataErrorType.APP_NOT_FOUND.toString())));
        return result;
      }

      try {
        List<IRole> roles = ServiceUtilities.findAllRoles(app);
        roles.sort((u1, u2) -> StringUtils.compareIgnoreCase(u1.getDisplayName(), u2.getDisplayName()));
        result.setRoles(roles);
      } catch (Exception ex) {
        Ivy.log().error("Unexpected exception in getting roles within app {0}", ex, app.getName());
      }
      return result;
    });
  }
  
  @Override
  public IvySecurityResultDTO findRoleDTOs(IApplication app) {
    return IvyExecutor.executeAsSystem(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      if (app.getActivityState() != ActivityState.ACTIVE) {
        result.setErrors(Arrays.asList(new PortalIvyDataException(app.getName(), PortalIvyDataErrorType.APP_NOT_FOUND.toString())));
        return result;
      }

      try {
        List<RoleDTO> roles = ServiceUtilities.findAllRoleDTO(app);
        roles.sort((u1, u2) -> StringUtils.compareIgnoreCase(u1.getDisplayName(), u2.getDisplayName()));
        result.setRoleDTOs(roles);
      } catch (Exception ex) {
        Ivy.log().error("Unexpected exception in getting roles within app {0}", ex, app.getName());
      }
      return result;
    });
  }

  @Override
  public IvySecurityResultDTO findSecurityMembers(String query, IApplication app, int startIndex, int count) {
    return IvyExecutor.executeAsSystem(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      if (app.getActivityState() != ActivityState.ACTIVE) {
        result.setErrors(Arrays.asList(new PortalIvyDataException(app.getName(), PortalIvyDataErrorType.APP_NOT_FOUND.toString())));
        return result;
      }

      try {
        List<RoleDTO> roles = ServiceUtilities.findAllRoleDTO(app).stream()
            .filter(role -> StringUtils.containsIgnoreCase(role.getDisplayName(), query) || StringUtils.containsIgnoreCase(role.getName(), query))
            .sorted((r1, r2) -> StringUtils.compareIgnoreCase(r1.getDisplayName(), r2.getDisplayName()))
            .collect(Collectors.toList());
        
        List<UserDTO> users = queryUsers(query, Arrays.asList(app.getName()), startIndex, count, null, null);
        
        List<SecurityMemberDTO> members = SecurityMemberDTOMapper.mapFromUserDTOs(users);
        members.addAll(SecurityMemberDTOMapper.mapFromRoleDTOs(roles));
        int size = count;
        if (count <= 0) {
          size = members.size();
        }
        result.setSecurityMembers(members.subList(startIndex, Math.min(size, members.size())));
      } catch (PortalIvyDataException e) {
        result.setErrors(Arrays.asList(e));
      } catch (Exception ex) {
        Ivy.log().error("Unexpected exception in getting security members within app {0}", ex, app.getName());
      }
      return result;
    });
  }

  @Override
  public IvySecurityResultDTO findSecurityMembers(String query, List<String> apps, int startIndex, int count) {
    return IvyExecutor.executeAsSystem(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      List<PortalIvyDataException> errors = new ArrayList<>();
      Map<String, RoleDTO> roleByName = new HashMap<>();
      try {
        for (String appName : apps) {
          IApplication app = ServiceUtilities.findApp(appName);
          ServiceUtilities.findAllRoleDTO(app).forEach(role -> {
            if (StringUtils.containsIgnoreCase(role.getDisplayName(), query)
                || StringUtils.containsIgnoreCase(role.getName(), query)) {
              roleByName.put(role.getName() + " - " + role.getMemberName(), role);
            }
          });
        }
        List<RoleDTO> roles = roleByName.values().stream()
            .sorted((r1, r2) -> StringUtils.compareIgnoreCase(r1.getDisplayName(), r2.getDisplayName()))
            .collect(Collectors.toList());
        List<UserDTO> users = queryUsers(query, apps, startIndex, count, null, null);

        List<SecurityMemberDTO> members = SecurityMemberDTOMapper.mapFromUserDTOs(users);
        members.addAll(SecurityMemberDTOMapper.mapFromRoleDTOs(roles));
        int size = count;
        if (count <= 0) {
          size = members.size();
        }
        result.setSecurityMembers(members.subList(startIndex, Math.min(size, members.size())));
      } catch (PortalIvyDataException e) {
        errors.add(e);
      } catch (Exception ex) {
        Ivy.log().error("Unexpected exception in getting security member", ex);
      }
      result.setErrors(errors);
      return result;
    });
  }
  
  private UserQuery queryExcludeUsernames(List<String> excludedUsernames) {
    UserQuery excludeUsernameQuery = UserQuery.create();
    IFilterQuery excludeUsernameFilter = excludeUsernameQuery.where();
    for (String username : excludedUsernames) {
      excludeUsernameFilter.or().name().isNotEqual(username);
    }
    return excludeUsernameQuery;
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
}
