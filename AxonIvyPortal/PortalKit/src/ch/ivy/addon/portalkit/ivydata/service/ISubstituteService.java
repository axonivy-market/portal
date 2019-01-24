package ch.ivy.addon.portalkit.ivydata.service;

import java.util.List;
import java.util.Map;

import ch.ivy.addon.portalkit.ivydata.bo.IvyApplication;
import ch.ivy.addon.portalkit.ivydata.bo.IvySubstitute;
import ch.ivy.addon.portalkit.ivydata.dto.IvySubstituteResultDTO;

public interface ISubstituteService {

  /**
   * Finds the substitutes of the given user.
   * @param username
   * @param apps
   * @return IvySubstituteResultDTO
   * @throws Exception
   */
  IvySubstituteResultDTO findSubstitutes(String username, List<String> apps);
  
  /**
   * Updates the substitutes of the given user
   * @param username
   * @param ivySubstitutesByApp
   * @return IvySubstituteResultDTO
   * @throws Exception
   */
  IvySubstituteResultDTO saveSubstitutes(String username, Map<IvyApplication, List<IvySubstitute>> ivySubstitutesByApp);
}
