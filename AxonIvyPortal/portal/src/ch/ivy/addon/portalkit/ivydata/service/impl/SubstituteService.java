package ch.ivy.addon.portalkit.ivydata.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.axonivy.portal.components.dto.UserDTO;

import ch.ivy.addon.portalkit.enums.AdditionalProperty;
import ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IUserSubstitute;
import ch.ivyteam.ivy.security.SubstitutionType;
import ch.ivyteam.ivy.security.exec.Sudo;

public class SubstituteService{

  private static final String ROLE_EVERYBODY = "Everybody";
  
  private SubstituteService() {
  }
  
  public static SubstituteService newInstance() {
    return new SubstituteService();
  }
  
  public List<IvySubstitute> findSubstitutes(String username) {
    return findSubstituteSubstitutionOfUser(username, true);
  }
  
  public List<IvySubstitute> findSubstitutions(String username) {
    return findSubstituteSubstitutionOfUser(username, false);
  }
  
  private List<IvySubstitute> findSubstituteSubstitutionOfUser(String username, boolean isFindSubstitute) {
    return Sudo.get(() -> { 
      IUser user = UserUtils.findUserByUsername(username);
      List<IvySubstitute> ivySubstitutions = isFindSubstitute? getIvySubstitutes(user) : getIvySubstitutions(user);
      return ivySubstitutions;
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
    
    boolean isPersonalPermanentExist = substitutes.stream().anyMatch(substitute -> substitute.getSubstitionRole() == null && SubstitutionType.PERMANENT.equals(substitute.getSubstitutionType()));
    if (!isPersonalPermanentExist) {
      substitutes.add(new IvySubstitute(SubstitutionType.PERMANENT));
    }
    boolean isPersonalOnAbsenceExist = substitutes.stream().anyMatch(substitute -> substitute.getSubstitionRole() == null && SubstitutionType.ON_ABSENCE.equals(substitute.getSubstitutionType()));
    if (!isPersonalOnAbsenceExist) {
      substitutes.add(new IvySubstitute(SubstitutionType.ON_ABSENCE));
    }

    substitutes.addAll(iRoles.stream().map(this::newIvySubtitute).collect(Collectors.toList()));
    
    return substitutes;
  }
  
  private List<IvySubstitute> getIvySubstitutions(IUser user) {
    return user.getSubstitutions()
        .stream()
        .map(this::getIvySubstitute)
        .collect(Collectors.toList());
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
    ivySubstitute.setSubstitutionType(userSubstitute.getSubstitutionType());
    ivySubstitute.setOwnerUser(userSubstitute.getUser());
    return ivySubstitute;
  }

  private void createSubstitutes(List<IvySubstitute> substitutes, IUser user){
    for (IvySubstitute ivySubstitute : substitutes) {
      if (ivySubstitute.getSubstituteUser() != null) {
        IUser iUser = UserUtils.findUserByUsername(ivySubstitute.getSubstituteUser().getName());
        if (ivySubstitute.getSubstitionRole() == null) {
          user.createSubstitute(iUser, "", ivySubstitute.getSubstitutionType());
        } else {
          user.createSubstitute(iUser, ivySubstitute.getSubstitionRole(), "");
        }
      }
    }
  }

  private void deleteSubstitutes(IUser user) {
    for (IUserSubstitute userSubstitute : user.getSubstitutes()) {
      user.deleteSubstitute(userSubstitute);
    }
  }

  public void saveSubstitutes(UserDTO userDTO, List<IvySubstitute> ivySubstitutes) {
    Sudo.get(() -> { 
      if (userDTO == null) {
        return Void.class;
      }

      IUser user = UserUtils.findUserByUsername(userDTO.getName());
      deleteSubstitutes(user);
      createSubstitutes(ivySubstitutes, user);
      return Void.class;
    });
  }

}
