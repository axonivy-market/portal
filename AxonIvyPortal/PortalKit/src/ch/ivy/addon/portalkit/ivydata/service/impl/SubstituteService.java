package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.ivydata.bo.IvyApplication;
import ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute;
import ch.ivy.addon.portalkit.ivydata.dto.IvySubstituteResultDTO;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataErrorType;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;
import ch.ivy.addon.portalkit.ivydata.service.ISubstituteService;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.persistence.dao.ApplicationDao;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.EnvironmentNotAvailableException;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IUserSubstitute;

public class SubstituteService implements ISubstituteService {

  private static final String ROLE_EVERYBODY = "Everybody";
  
  private SubstituteService() {
  }
  
  public static SubstituteService newInstance() {
    return new SubstituteService();
  }
  
  @Override
  public IvySubstituteResultDTO findSubstitutes(String username, List<String> apps) {
    return IvyExecutor.executeAsSystem(() -> { 
      IvySubstituteResultDTO result = new IvySubstituteResultDTO();
      Map<IvyApplication, List<IvySubstitute>> ivySubstitutesByApp = new HashMap<>();
      result.setIvySubstitutesByApp(ivySubstitutesByApp);
      if (CollectionUtils.isEmpty(apps)) {
        return result;
      }

      List<PortalIvyDataException> errors = new ArrayList<>();
      List<Application> applications = new ApplicationDao().findByNames(apps);
      apps.stream().forEach(appName -> {
        try {
          String appDisplayName = applications.stream().filter(app -> StringUtils.equals(app.getName(), appName))
              .map(ApplicationMultiLanguage::getDisplayNameInCurrentLocale).findFirst().orElse(appName);
          IApplication application = ServiceUtilities.findApp(appName);
          IUser user = ServiceUtilities.findUser(username, application);
          ivySubstitutesByApp.put(ServiceUtilities.toIvyApplication(appName, appDisplayName), getIvySubstitutes(user));
        } catch (PortalIvyDataException e) {
          errors.add(e);
        } catch (Exception ex) {
          Ivy.log().error("Error in getting substitutes of user {0} within app {1}", ex, username, appName);
          errors.add(new PortalIvyDataException(appName, PortalIvyDataErrorType.FAIL_TO_LOAD_SUBSTITUTE.toString()));
        }
      });
      result.setErrors(errors);
      return result;
    });
  }
  
  private List<IvySubstitute> getIvySubstitutes(IUser user) {
    List<IvySubstitute> substitutes = user.getSubstitutes().stream()
        .map(this::getIvySubstitute)
        .collect(Collectors.toList());
    Set<IRole> existRoles = substitutes.stream()
        .map(IvySubstitute::getSubstitionRole)
        .collect(Collectors.toSet());
    
    List<IRole> iRoles = getAllRoles(user).stream()
        .filter(role -> !existRoles.contains(role))
        .collect(Collectors.toList());

    boolean doesPersonalSubstituteExist = substitutes.stream().anyMatch(substitute -> substitute.getSubstitionRole() == null);
    if (!doesPersonalSubstituteExist) {
      substitutes.add(createPersonalSubstitute());
    }
    substitutes.addAll(iRoles.stream().map(this::newIvySubtitute).collect(Collectors.toList()));
    
    return substitutes;
  }
  
  private IvySubstitute createPersonalSubstitute() {
    return new IvySubstitute();
  }
  
  private IvySubstitute newIvySubtitute(IRole role) {
    IvySubstitute substitute = new IvySubstitute();
    substitute.setSubstitionRole(role);
    return substitute;
  }
  
  private List<IRole> getAllRoles(IUser user) {
    if (user == null) {
      return new ArrayList<>();
    }
    return user.getAllRoles().stream()
        .filter(role -> !ROLE_EVERYBODY.equals(role.getName()) && Objects.isNull(role.getProperty(AdditionalProperty.HIDE.toString())))
        .collect(Collectors.toList());
  }
  
  private IvySubstitute getIvySubstitute(IUserSubstitute userSubstitute) {
    IvySubstitute ivySubstitute = new IvySubstitute();
    if (!userSubstitute.isPersonallyOnly()) {
      ivySubstitute.setSubstitionRole(userSubstitute.getSubstitutionRole());
      ivySubstitute.setSubstitionRoleDisplayName(userSubstitute.getSubstitutionRole().getDisplayName());
    }
    ivySubstitute.setSubstituteUser(new UserDTO(userSubstitute.getSubstituteUser()));
    ivySubstitute.setDescription(userSubstitute.getDescription());
    return ivySubstitute;
  }

  @Override
  public IvySubstituteResultDTO saveSubstitutes(String username, Map<IvyApplication, List<IvySubstitute>> ivySubstitutesByApp) {
    return IvyExecutor.executeAsSystem(() -> { 
      IvySubstituteResultDTO result = new IvySubstituteResultDTO();
      if (ivySubstitutesByApp == null || ivySubstitutesByApp.isEmpty()) {
        return result;
      }

      List<PortalIvyDataException> errors = new ArrayList<>();
      ivySubstitutesByApp.entrySet().stream().forEach(entry -> {
        try {
          IApplication application = ServiceUtilities.findApp(entry.getKey().getName());
          IUser user = ServiceUtilities.findUser(username, application);
          deleteSubstitutes(user);
          createSubstitutes(entry.getValue(), user, application);
        } catch (PortalIvyDataException e) {
          errors.add(e);
        } catch (Exception ex) {
          Ivy.log().error("Error in saving substitutes of user {0} within app {1}", ex, username, entry.getKey().getName());
          errors.add(new PortalIvyDataException(entry.getKey().getName(), PortalIvyDataErrorType.FAIL_TO_SAVE_SUBSTITUTE.toString()));
        }
      });
      result.setErrors(errors);
      return result;
    });
  }

  private void createSubstitutes(List<IvySubstitute> substitutes, IUser user, IApplication application) throws PersistencyException, EnvironmentNotAvailableException, PortalIvyDataException {
    for (IvySubstitute ivySubstitute : substitutes) {
      if (ivySubstitute.getSubstituteUser() != null) {
        IUser iUser = ServiceUtilities.findUser(ivySubstitute.getSubstituteUser().getName(), application);
        user.createSubstitute(iUser, ivySubstitute.getSubstitionRole(), Optional.ofNullable(ivySubstitute.getDescription()).orElse(""));
      }
    }
  }

  private void deleteSubstitutes(IUser user) {
    for (IUserSubstitute userSubstitute : user.getSubstitutes()) {
      user.deleteSubstitute(userSubstitute);
    }
  }

}
