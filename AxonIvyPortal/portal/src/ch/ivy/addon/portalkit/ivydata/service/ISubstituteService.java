package ch.ivy.addon.portalkit.ivydata.service;

import java.util.List;

import com.axonivy.portal.component.dto.UserDTO;

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
   * Updates substitutes for user
   * @param userDTO
   * @param ivySubstitutes
   */
  void saveSubstitutes(UserDTO userDTO, List<IvySubstitute> ivySubstitutes);
  
}
