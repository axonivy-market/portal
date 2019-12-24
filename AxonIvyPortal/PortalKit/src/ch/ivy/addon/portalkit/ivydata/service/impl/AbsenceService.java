package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence;
import ch.ivy.addon.portalkit.ivydata.dto.IvyAbsenceResultDTO;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataErrorType;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;
import ch.ivy.addon.portalkit.ivydata.service.IAbsenceService;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IUserAbsence;

public class AbsenceService implements IAbsenceService {

  private AbsenceService() {
  }
  
  public static AbsenceService newInstance() {
    return new AbsenceService();
  }
  
  @Override
  public IvyAbsenceResultDTO findAbsences(String username, List<String> apps) {
    return IvyExecutor.executeAsSystem(() -> { 
      IvyAbsenceResultDTO result = new IvyAbsenceResultDTO();
      if (CollectionUtils.isEmpty(apps)) {
        return result;
      }

      List<PortalIvyDataException> errors = new ArrayList<>();
      Map<String, Set<IvyAbsence>> ivyAbsencesByUser = new HashMap<>();
      apps.forEach(app -> { 
        try {
          IApplication application = ServiceUtilities.findApp(app);
          if (StringUtils.isBlank(username)) {
              ServiceUtilities.findAllUsers(application).forEach(user -> {
              if (ivyAbsencesByUser.containsKey(user.getName())) {
                ivyAbsencesByUser.get(user.getName()).addAll(getAbsences(user));
              } else {
                ivyAbsencesByUser.put(user.getName(), getAbsences(user));
              }
            });
          } else {
            IUser user = ServiceUtilities.findUser(username, application);
            if (ivyAbsencesByUser.containsKey(user.getName())) {
              ivyAbsencesByUser.get(username).addAll(getAbsences(user));
            } else {
              ivyAbsencesByUser.put(username, getAbsences(user));
            }
          }
        } catch (PortalIvyDataException e) {
          errors.add(e);
        } catch (Exception ex) {
          Ivy.log().error("Error in getting absences of user {0} within app {1}", ex, username, app);
          errors.add(new PortalIvyDataException(app, PortalIvyDataErrorType.FAIL_TO_LOAD_ABSENCE.toString()));
        }
      });
      result.setErrors(errors);
      result.setIvyAbsencesByUser(ivyAbsencesByUser);
      return result;
    });
  }

  private Set<IvyAbsence> getAbsences(IUser user) {
    Set<IUserAbsence> userAbsences = new HashSet<>(UserUtils.findAbsenceOfUser(user));
    if (CollectionUtils.isNotEmpty(userAbsences)) {
      UserDTO userDTO = new UserDTO(user);
      return userAbsences.stream().map(userAbsence -> getAbsence(userDTO, userAbsence)).collect(Collectors.toSet());
    }
    return new HashSet<>();
  }
  
  private IvyAbsence getAbsence(UserDTO user, IUserAbsence userAbsence) {
    IvyAbsence ivyAbsence = new IvyAbsence();
    ivyAbsence.setUser(user);
    ivyAbsence.setFrom(userAbsence.getStartTimestamp());
    ivyAbsence.setUntil(userAbsence.getStopTimestamp());
    ivyAbsence.setComment(userAbsence.getDescription());
    return ivyAbsence;
  }

  @Override
  public IvyAbsenceResultDTO createAbsence(IvyAbsence ivyAbsence, List<String> apps) {
    return IvyExecutor.executeAsSystem(() -> { 
      IvyAbsenceResultDTO result = new IvyAbsenceResultDTO();
      if (CollectionUtils.isEmpty(apps)) {
        return result;
      }

      List<PortalIvyDataException> errors = new ArrayList<>();
      apps.forEach(app -> {
        try {
          IApplication application = ServiceUtilities.findApp(app);
          IUser user = ServiceUtilities.findUser(ivyAbsence.getUsername(), application);
          user.createAbsence(ivyAbsence.getFrom(), ivyAbsence.getUntil(), ivyAbsence.getComment());
        } catch (PortalIvyDataException e) {
          errors.add(e);
        } catch (Exception ex) {
          Ivy.log().error("Error in create new absence of user {0} within app {1}", ex, ivyAbsence.getUsername(), app);
          errors.add(new PortalIvyDataException(app, PortalIvyDataErrorType.FAIL_TO_CREATE_ABSENCE.toString()));
        }
      });
      result.setErrors(errors);
      return result;
    });
  }
  
  @Override
  public IvyAbsenceResultDTO updateAbsences(String username, Set<IvyAbsence> ivyAbsences, List<String> apps) {
    return IvyExecutor.executeAsSystem(() -> { 
      IvyAbsenceResultDTO result = new IvyAbsenceResultDTO();
      if (CollectionUtils.isEmpty(apps)) {
        return result;
      }

      List<PortalIvyDataException> errors = new ArrayList<>();
      apps.forEach(app -> {
        try {
          IApplication application = ServiceUtilities.findApp(app);
          IUser user = ServiceUtilities.findUser(username, application);
          for (IUserAbsence userAbsence : user.getAbsences()) {
            user.deleteAbsence(userAbsence);
          }
          for (IvyAbsence ivyAbsence : ivyAbsences) {
            user.createAbsence(ivyAbsence.getFrom(), ivyAbsence.getUntil(), ivyAbsence.getComment());
          }
        } catch (PortalIvyDataException e) {
          errors.add(e);
        } catch (Exception ex) {
          Ivy.log().error("Error in updating the absences of user {0} within app {1}", ex, username, app);
          errors.add(new PortalIvyDataException(app, PortalIvyDataErrorType.FAIL_TO_UPDATE_ABSENCE.toString()));
        }
      });
      result.setErrors(errors);
      return result;
    });
  }

  @Override
  public IvyAbsenceResultDTO deleteAbsence(IvyAbsence ivyAbsence, List<String> apps) {
    return IvyExecutor.executeAsSystem(() -> { 
      IvyAbsenceResultDTO result = new IvyAbsenceResultDTO();
      if (CollectionUtils.isEmpty(apps)) {
        return result;
      }

      List<PortalIvyDataException> errors = new ArrayList<>();
      apps.forEach(app -> {
        try {
          IApplication application = ServiceUtilities.findApp(app);
          IUser user = ServiceUtilities.findUser(ivyAbsence.getUsername(), application);
          for (IUserAbsence userAbsence : user.getAbsences()) {
            if (userAbsence.getStartTimestamp().equals(ivyAbsence.getFrom()) && userAbsence.getStopTimestamp().equals(ivyAbsence.getUntil())) {
              user.deleteAbsence(userAbsence);
              break;
            }
          }
        } catch (PortalIvyDataException e) {
          errors.add(e);
        } catch (Exception ex) {
          Ivy.log().error("Error in deleting the absence of user {0} within app {1}", ex, ivyAbsence.getUsername(), app);
          errors.add(new PortalIvyDataException(app, PortalIvyDataErrorType.FAIL_TO_DELETE_ABSENCE.toString()));
        }
      });
      result.setErrors(errors);
      return result;
    });
  }

}
