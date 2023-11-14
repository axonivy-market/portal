package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.UserDTO;

import ch.ivy.addon.portalkit.ivydata.bo.IvyAbsence;
import ch.ivy.addon.portalkit.ivydata.dto.IvyAbsenceResultDTO;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IUserAbsence;
import ch.ivyteam.ivy.security.exec.Sudo;

public class AbsenceService{

  private AbsenceService() {
  }
  
  public static AbsenceService newInstance() {
    return new AbsenceService();
  }
  
  public IvyAbsenceResultDTO findAbsences(String username) {
    return Sudo.get(() -> { 
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

  public void createAbsence(IvyAbsence ivyAbsence) {
    Sudo.get(() -> { 

      IUser user = ServiceUtilities.findUser(ivyAbsence.getUsername());
      user.createAbsence(ivyAbsence.getFrom(), ivyAbsence.getUntil(), ivyAbsence.getComment());
      return Void.class;
    });
  }
  
  public void updateAbsences(String username, Set<IvyAbsence> ivyAbsences) {
    Sudo.get(() -> { 
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

  public void deleteAbsence(IvyAbsence ivyAbsence) {
    Sudo.get(() -> { 
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
