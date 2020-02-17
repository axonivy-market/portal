package ch.ivy.addon.portalkit.ivydata.service;

import java.util.List;

import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;
import ch.ivyteam.ivy.application.IApplication;

public interface ISecurityService {

  /**
   * Finds the users which has the given roles and in the given applications
   * @param query
   * @param apps
   * @param startIndex
   * @param count
   * @param roleNames
   * @return {@link IvySecurityResultDTO}
   */
  IvySecurityResultDTO findUsers(String query, List<String> apps, int startIndex, int count, List<String> roleNames);
  
  /**
   * Finds the users which has the given roles and in the given application
   * @param query
   * @param app
   * @param startIndex
   * @param count
   * @param roleNames
   * @return {@link IvySecurityResultDTO}
   */
  IvySecurityResultDTO findUsers(String query, IApplication app, int startIndex, int count, List<String> roleNames);
  
  /**
   * Gets roles of the given application name
   * @param apps
   * @return {@link IvySecurityResultDTO}
   */
  IvySecurityResultDTO findRoles(List<String> apps);
  
  /**
   * Finds the roles by the given application
   * @param app
   * @return {@link IvySecurityResultDTO}
   */
  IvySecurityResultDTO findRoles(IApplication app);
  
  /**
   * Finds the users and roles
   * @param query
   * @param app
   * @param startIndex
   * @param count
   * @return {@link IvySecurityResultDTO}
   */
  IvySecurityResultDTO findSecurityMembers(String query, IApplication app, int startIndex, int count);
  
  /**
   * Finds the users and roles by the list of application names
   * @param query
   * @param apps
   * @param startIndex
   * @param count
   * @return {@link IvySecurityResultDTO}
   */
  IvySecurityResultDTO findSecurityMembers(String query, List<String> apps, int startIndex, int count);

  /**
   * Gets roleDTOs of the given application name
   * @param app 
   * @return {@link IvySecurityResultDTO}
   */
  IvySecurityResultDTO findRoleDTOs(IApplication app);
}
