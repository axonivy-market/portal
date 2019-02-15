package ch.ivy.addon.portalkit.ivydata.service;

import java.util.List;

import ch.ivy.addon.portalkit.ivydata.dto.IvyLanguageResultDTO;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;
import ch.ivyteam.ivy.application.IApplication;

public interface ISecurityService {

  /**
   * Gets users of the given application names
   * @param apps
   * @return {@link IvyLanguageResultDTO}
   */
  IvySecurityResultDTO findUsers(List<String> apps);
  
  /**
   * Finds the users by the given application
   * @param app
   * @return {@link IvyLanguageResultDTO}
   */
  IvySecurityResultDTO findUsers(IApplication app);
  
  /**
   * Gets roles of the given application name
   * @param apps
   * @return {@link IvyLanguageResultDTO}
   */
  IvySecurityResultDTO findRoles(List<String> apps);
  
  /**
   * Finds the roles by the given application
   * @param app
   * @return {@link IvyLanguageResultDTO}
   */
  IvySecurityResultDTO findRoles(IApplication app);
  
  /**
   * Finds the users and roles
   * @param app
   * @return {@link IvyLanguageResultDTO}
   */
  IvySecurityResultDTO findSecurityMembers(IApplication app);
  
  /**
   * Finds the users and roles by the list of application names
   * @param apps
   * @return {@link IvyLanguageResultDTO}
   */
  IvySecurityResultDTO findSecurityMembers(List<String> apps);
}
