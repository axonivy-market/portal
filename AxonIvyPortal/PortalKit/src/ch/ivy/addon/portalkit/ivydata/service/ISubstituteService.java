package ch.ivy.addon.portalkit.ivydata.service;

import java.util.List;
import java.util.Map;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.ivydata.bo.IvyApplication;
import ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute;
import ch.ivy.addon.portalkit.ivydata.dto.IvySubstituteResultDTO;

public interface ISubstituteService {

  /**
   * Finds the substitutes of the given user.
   * @param username
   * @param apps
   * @return IvySubstituteResultDTO
   */
  IvySubstituteResultDTO findSubstitutes(String username, List<String> apps);
  
  /**
   * Finds the substitutions of the given user.
   * @param username
   * @param apps
   * @return IvySubstituteResultDTO
   */
  IvySubstituteResultDTO findSubstitutions(String username, List<String> apps);
  
  /**
   * Updates substitutes of multiple users, user will be mapped by application name
   * @param userPerApplication sample map: <app1, list1 > <app2, list2>
   * @param ivySubstitutesByApp sample map: <app1, demo> <app2, admin>
   * @return
   * this function will set list 1 as substitutes for user demo, list 2 as substitutes for user admin 
   */
  IvySubstituteResultDTO saveSubstitutes(Map<String, UserDTO> userPerApplication, Map<IvyApplication, List<IvySubstitute>> ivySubstitutesByApp);
  
  /**
   * find substitutes for given user on given application
   * @param username
   * @param app
   * @return substitutes
   */
  IvySubstituteResultDTO findSubstitutesOnApp(String username, String app);
}
