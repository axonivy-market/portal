package ch.ivy.addon.portalkit.ivydata.service;

import java.util.List;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute;
import ch.ivy.addon.portalkit.ivydata.dto.IvySubstituteResultDTO;

public interface ISubstituteService {

  /**
   * Finds the substitutes of the given user.
   * @param username
   * @return IvySubstituteResultDTO
   */
  IvySubstituteResultDTO findSubstitutes(String username);
  
  /**
   * Finds the substitutions of the given user.
   * @param username
   * @return IvySubstituteResultDTO
   */
  IvySubstituteResultDTO findSubstitutions(String username);
  
  /**
   * Updates substitutes of multiple users, user will be mapped by application name
   * @param userPerApplication sample map: <app1, list1 > <app2, list2>
   * @param ivySubstitutesByApp sample map: <app1, demo> <app2, admin>
   * @return
   * this function will set list 1 as substitutes for user demo, list 2 as substitutes for user admin 
   */
  
  /**
   * Updates substitutes for user
   * @param userDTO
   * @param ivySubstitutes
   */
  void saveSubstitutes(UserDTO userDTO, List<IvySubstitute> ivySubstitutes);
  
//  /**
//   * find substitutes for given user on given application
//   * @param username
//   * @return substitutes
//   */
//  IvySubstituteResultDTO findSubstitutesOnApp(String username);
}
