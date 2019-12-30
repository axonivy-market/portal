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

public class SecurityService implements ISecurityService {

  private SecurityService() {}

  public static SecurityService newInstance() {
    return new SecurityService();
  }

  @Override
  public IvySecurityResultDTO findUsers(List<String> apps) {
    return IvyExecutor.executeAsSystem(() -> { 
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      if (CollectionUtils.isEmpty(apps)) {
        return result;
      }
      List<PortalIvyDataException> errors = new ArrayList<>();
      Map<String, List<UserDTO>> usersByApp = new HashMap<>();
      for (String appName : apps) {
        try {
          IApplication app = ServiceUtilities.findApp(appName);
          usersByApp.put(appName, ServiceUtilities.findAllUserDTOByApplication(app));
        } catch (PortalIvyDataException e) {
          errors.add(e);
        } catch (Exception ex) {
          Ivy.log().error("Error in getting users within app {0}", ex, appName);
        }
      }
      result.setErrors(errors);
      result.setUsersByApp(usersByApp);
      return result;
    });
  }
  
  @Override
  public IvySecurityResultDTO findUsers(IApplication app) {
    return IvyExecutor.executeAsSystem(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      List<PortalIvyDataException> errors = new ArrayList<>();
      if (app.getActivityState() != ActivityState.ACTIVE) {
        result.setErrors(Arrays.asList(new PortalIvyDataException(app.getName(), PortalIvyDataErrorType.APP_NOT_FOUND.toString())));
        return result;
      }

      try {
        List<UserDTO> users = ServiceUtilities.findAllUserDTOByApplication(app);
        users.sort((u1, u2) -> StringUtils.compareIgnoreCase(u1.getDisplayName(), u2.getDisplayName()));
        result.setUsers(users);
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
      Map<String, List<IRole>> rolesByApp = new HashMap<>();
      for (String appName : apps) {
        try {
          IApplication app = ServiceUtilities.findApp(appName);
          rolesByApp.put(appName, ServiceUtilities.findAllRoles(app));
        } catch (PortalIvyDataException e) {
          errors.add(e);
        } catch (Exception ex) {
          Ivy.log().error("Error in getting roles within app {0}", ex, appName);
        }
      }
      result.setErrors(errors);
      result.setRolesByApp(rolesByApp);
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
  public IvySecurityResultDTO findSecurityMembers(IApplication app) {
    return IvyExecutor.executeAsSystem(() -> {
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      List<PortalIvyDataException> errors = new ArrayList<>();
      if (app.getActivityState() != ActivityState.ACTIVE) {
        result.setErrors(Arrays.asList(new PortalIvyDataException(app.getName(), PortalIvyDataErrorType.APP_NOT_FOUND.toString())));
        return result;
      }

      try {
        List<RoleDTO> roles = ServiceUtilities.findAllRoleDTO(app);
        roles.sort((r1, r2) -> StringUtils.compareIgnoreCase(r1.getDisplayName(), r2.getDisplayName()));
        
        List<UserDTO> users = ServiceUtilities.findAllUserDTOByApplication(app);
        users.sort((u1, u2) -> StringUtils.compareIgnoreCase(u1.getDisplayName(), u2.getDisplayName()));
        
        List<SecurityMemberDTO> members = SecurityMemberDTOMapper.mapFromUserDTOs(users);
        members.addAll(SecurityMemberDTOMapper.mapFromRoleDTOs(roles));
        result.setSecurityMembers(members);
        
      } catch (Exception ex) {
        Ivy.log().error("Error in getting security members within app {0}", ex, app.getName());
      }
      result.setErrors(errors);
      return result;
    });
  }

  @Override
  public IvySecurityResultDTO findSecurityMembers(List<String> apps) {
    return IvyExecutor.executeAsSystem(() -> { 
      IvySecurityResultDTO result = new IvySecurityResultDTO();
      List<PortalIvyDataException> errors = new ArrayList<>();
      Map<String, RoleDTO> roleByName = new HashMap<>();
      Map<String, UserDTO> userByName = new HashMap<>();
      for (String appName : apps) {
        try {
          IApplication app = ServiceUtilities.findApp(appName);
          ServiceUtilities.findAllRoleDTO(app).forEach(role -> roleByName.put(role.getName() + " - " + role.getMemberName(), role));
          ServiceUtilities.findAllUserDTOByApplication(app).forEach(user -> userByName.put(user.getName() + " - " + user.getMemberName(), user));
        } catch (Exception ex) {
          Ivy.log().error("Error in getting security members within app {0}", ex, appName);
        }
      }
      List<RoleDTO> roles = roleByName.values().stream().sorted((r1, r2) -> StringUtils.compareIgnoreCase(r1.getDisplayName(), r2.getDisplayName())).collect(Collectors.toList());
      List<UserDTO> users = userByName.values().stream().sorted((u1, u2) -> StringUtils.compareIgnoreCase(u1.getDisplayName(), u2.getDisplayName())).collect(Collectors.toList());
      
      List<SecurityMemberDTO> members = SecurityMemberDTOMapper.mapFromUserDTOs(users);
      members.addAll(SecurityMemberDTOMapper.mapFromRoleDTOs(roles));
      result.setSecurityMembers(members);
      result.setErrors(errors);
      return result;
    });
  }
}
