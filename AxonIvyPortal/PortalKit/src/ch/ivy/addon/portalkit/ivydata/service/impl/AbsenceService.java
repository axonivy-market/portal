package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence;
import ch.ivy.addon.portalkit.ivydata.dto.IvyAbsenceResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.IAbsenceService;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IUserAbsence;

public class AbsenceService implements IAbsenceService {

  private AbsenceService() {
  }
  
  public static AbsenceService newInstance() {
    return new AbsenceService();
  }
  
  @Override
  public IvyAbsenceResultDTO findAbsences(String username) {
    return IvyExecutor.executeAsSystem(() -> { 
      IvyAbsenceResultDTO result = new IvyAbsenceResultDTO();

      Map<String, Set<IvyAbsence>> ivyAbsencesByUser = new HashMap<>();
      if (StringUtils.isBlank(username)) {
          ServiceUtilities.findAllUsers().forEach(user -> {
          if (ivyAbsencesByUser.containsKey(user.getName())) {
            ivyAbsencesByUser.get(user.getName()).addAll(getAbsences(user));
          } else {
            ivyAbsencesByUser.put(user.getName(), getAbsences(user));
          }
        });
      } else {
        IUser user = ServiceUtilities.findUser(username);
        if (ivyAbsencesByUser.containsKey(user.getName())) {
          ivyAbsencesByUser.get(username).addAll(getAbsences(user));
        } else {
          ivyAbsencesByUser.put(username, getAbsences(user));
        }
      }
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
  public void createAbsence(IvyAbsence ivyAbsence) {
    IvyExecutor.executeAsSystem(() -> { 

      IUser user = ServiceUtilities.findUser(ivyAbsence.getUsername());
      user.createAbsence(ivyAbsence.getFrom(), ivyAbsence.getUntil(), ivyAbsence.getComment());
      return Void.class;
    });
  }
  
  @Override
  public void updateAbsences(String username, Set<IvyAbsence> ivyAbsences) {
    IvyExecutor.executeAsSystem(() -> { 
      IUser user = ServiceUtilities.findUser(username);
      for (IUserAbsence userAbsence : user.getAbsences()) {
        user.deleteAbsence(userAbsence);
      }
      for (IvyAbsence ivyAbsence : ivyAbsences) {
        user.createAbsence(ivyAbsence.getFrom(), ivyAbsence.getUntil(), ivyAbsence.getComment());
      }
      return Void.class;
    });
  }

  @Override
  public void deleteAbsence(IvyAbsence ivyAbsence) {
    IvyExecutor.executeAsSystem(() -> { 
      IUser user = ServiceUtilities.findUser(ivyAbsence.getUsername());
      for (IUserAbsence userAbsence : user.getAbsences()) {
        if (userAbsence.getStartTimestamp().equals(ivyAbsence.getFrom()) && userAbsence.getStopTimestamp().equals(ivyAbsence.getUntil())) {
          user.deleteAbsence(userAbsence);
          break;
        }
      }
      return Void.class;
    });
  }

}
