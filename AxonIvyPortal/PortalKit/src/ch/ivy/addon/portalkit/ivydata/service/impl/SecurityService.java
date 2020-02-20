package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
  public IvySecurityResultDTO findUsers(String query, List<String> apps, int startIndex, int count, List<String> fromRoles) {
    return IvyExecutor.executeAsSystem(() -> { 
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      if (CollectionUtils.isEmpty(apps)) {
        return result;
      }
      List<PortalIvyDataException> errors = new ArrayList<>();
      List<UserDTO> users = new ArrayList<>();
      for (String appName : apps) {
        try {
          IApplication app = ServiceUtilities.findApp(appName);
          users.addAll(CollectionUtils.isEmpty(fromRoles) ? queryUsers(query, app, startIndex, count) : queryUsers(query, app, startIndex, count, fromRoles));
        } catch (PortalIvyDataException e) {
          errors.add(e);
        } catch (Exception ex) {
          Ivy.log().error("Error in getting users within app {0}", ex, appName);
        }
      }
      result.setErrors(errors);
      result.setUsers(users);
      return result;
    });
  }

  @Override
  public IvySecurityResultDTO findUsers(String query, IApplication app, int startIndex, int count, List<String> fromRoles) {
    return IvyExecutor.executeAsSystem(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      List<PortalIvyDataException> errors = new ArrayList<>();
      if (app.getActivityState() != ActivityState.ACTIVE) {
        result.setErrors(Arrays.asList(new PortalIvyDataException(app.getName(), PortalIvyDataErrorType.APP_NOT_FOUND.toString())));
        return result;
      }

      try {
        List<UserDTO> userDTOs = CollectionUtils.isEmpty(fromRoles) ? queryUsers(query, app, startIndex, count) : queryUsers(query, app, startIndex, count, fromRoles);
        result.setUsers(userDTOs);
      } catch (Exception ex) {
        Ivy.log().error("Error in getting users within app {0}", ex, app.getName());
      }
      result.setErrors(errors);
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
          Ivy.log().error("Error in getting roles within app {0}", ex, appName);
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
      List<PortalIvyDataException> errors = new ArrayList<>();
      if (app.getActivityState() != ActivityState.ACTIVE) {
        result.setErrors(Arrays.asList(new PortalIvyDataException(app.getName(), PortalIvyDataErrorType.APP_NOT_FOUND.toString())));
        return result;
      }

      try {
        List<IRole> roles = ServiceUtilities.findAllRoles(app);
        roles.sort((u1, u2) -> StringUtils.compareIgnoreCase(u1.getDisplayName(), u2.getDisplayName()));
        result.setRoles(roles);
      } catch (Exception ex) {
        Ivy.log().error("Error in getting roles within app {0}", ex, app.getName());
      }
      result.setErrors(errors);
      return result;
    });
  }
  
  @Override
  public IvySecurityResultDTO findRoleDTOs(IApplication app) {
    return IvyExecutor.executeAsSystem(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      List<PortalIvyDataException> errors = new ArrayList<>();
      if (app.getActivityState() != ActivityState.ACTIVE) {
        result.setErrors(Arrays.asList(new PortalIvyDataException(app.getName(), PortalIvyDataErrorType.APP_NOT_FOUND.toString())));
        return result;
      }

      try {
        List<RoleDTO> roles = ServiceUtilities.findAllRoleDTO(app);
        roles.sort((u1, u2) -> StringUtils.compareIgnoreCase(u1.getDisplayName(), u2.getDisplayName()));
        result.setRoleDTOs(roles);
      } catch (Exception ex) {
        Ivy.log().error("Error in getting roles within app {0}", ex, app.getName());
      }
      result.setErrors(errors);
      return result;
    });
  }

  @Override
  public IvySecurityResultDTO findSecurityMembers(String query, IApplication app, int startIndex, int count) {
    return IvyExecutor.executeAsSystem(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      List<PortalIvyDataException> errors = new ArrayList<>();
      if (app.getActivityState() != ActivityState.ACTIVE) {
        result.setErrors(Arrays.asList(new PortalIvyDataException(app.getName(), PortalIvyDataErrorType.APP_NOT_FOUND.toString())));
        return result;
      }

      try {
        List<RoleDTO> roles = ServiceUtilities.findAllRoleDTO(app).stream()
            .filter(role -> StringUtils.containsIgnoreCase(role.getDisplayName(), query) || StringUtils.containsIgnoreCase(role.getName(), query))
            .sorted((r1, r2) -> StringUtils.compareIgnoreCase(r1.getDisplayName(), r2.getDisplayName()))
            .collect(Collectors.toList());
        
        List<UserDTO> users = queryUsers(query, app, startIndex, count);
        
        List<SecurityMemberDTO> members = SecurityMemberDTOMapper.mapFromUserDTOs(users);
        members.addAll(SecurityMemberDTOMapper.mapFromRoleDTOs(roles));
        int size = count;
        if (count <= 0) {
          size = members.size();
        }
        result.setSecurityMembers(members.subList(startIndex, Math.min(size, members.size())));
      } catch (Exception ex) {
        Ivy.log().error("Error in getting security members within app {0}", ex, app.getName());
      }
      result.setErrors(errors);
      return result;
    });
  }

  @Override
  public IvySecurityResultDTO findSecurityMembers(String query, List<String> apps, int startIndex, int count) {
    return IvyExecutor.executeAsSystem(() -> { 
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      List<PortalIvyDataException> errors = new ArrayList<>();
      Map<String, RoleDTO> roleByName = new HashMap<>();
      Map<String, UserDTO> userByName = new HashMap<>();
      for (String appName : apps) {
        try {
          IApplication app = ServiceUtilities.findApp(appName);
          ServiceUtilities.findAllRoleDTO(app).forEach(role -> {
            if (StringUtils.containsIgnoreCase(role.getDisplayName(), query) || StringUtils.containsIgnoreCase(role.getName(), query)) {
              roleByName.put(role.getName() + " - " + role.getMemberName(), role);
            }
          });
          queryUsers(query, app, startIndex, count).forEach(user -> userByName.put(user.getName() + " - " + user.getMemberName(), user));
        } catch (Exception ex) {
          Ivy.log().error("Error in getting security members within app {0}", ex, appName);
        }
      }
      List<RoleDTO> roles = roleByName.values().stream().sorted((r1, r2) -> StringUtils.compareIgnoreCase(r1.getDisplayName(), r2.getDisplayName())).collect(Collectors.toList());
      List<UserDTO> users = userByName.values().stream().sorted((u1, u2) -> StringUtils.compareIgnoreCase(u1.getDisplayName(), u2.getDisplayName())).collect(Collectors.toList());
      
      List<SecurityMemberDTO> members = SecurityMemberDTOMapper.mapFromUserDTOs(users);
      members.addAll(SecurityMemberDTOMapper.mapFromRoleDTOs(roles));
      int size = count;
      if (count <= 0) {
        size = members.size();
      }
      result.setSecurityMembers(members.subList(startIndex, Math.min(size, members.size())));
      result.setErrors(errors);
      return result;
    });
  }
  
  private List<UserDTO> queryUsers(String query, IApplication app, int startIndex, int count) {
    query = "%"+ StringUtils.defaultString(query, StringUtils.EMPTY) +"%";
    List<IUser> users = UserQuery.create().where()
        .fullName().isLikeIgnoreCase(query)
        .or().name().isLikeIgnoreCase(query)
        .andOverall().applicationId().isEqual(app.getId())
        .orderBy().fullName().name()
        .executor().results(startIndex, count);
    return users.stream().map(UserDTO::new).collect(Collectors.toList());
  }
  
  private List<UserDTO> queryUsers(String query, IApplication app, int startIndex, int count, List<String> fromRoles) {
    UserQuery hasRolesQuery = queryHasRoles(app, fromRoles);
    query = "%"+ StringUtils.defaultString(query, StringUtils.EMPTY) +"%";
    List<IUser> users = UserQuery.create().where()
        .fullName().isLikeIgnoreCase(query)
        .or().name().isLikeIgnoreCase(query)
        .andOverall(hasRolesQuery)
        .andOverall().applicationId().isEqual(app.getId())
        .orderBy().fullName().name()
        .executor().results(startIndex, count);
    return users.stream().map(UserDTO::new).collect(Collectors.toList());
  }

  private UserQuery queryHasRoles(IApplication app, List<String> fromRoles) {
    List<IRole> roles = fromRoles.stream().map(roleName -> app.getSecurityContext().findRole(roleName)).collect(Collectors.toList());
    UserQuery hasRolesQuery = UserQuery.create();
    IFilterQuery hasRolesFilter = hasRolesQuery.where();
    for (IRole role : roles) {
      hasRolesFilter.or().hasRole(role);
    }
    return hasRolesQuery;
  }
}
