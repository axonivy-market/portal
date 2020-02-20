package ch.ivy.addon.portalkit.ivydata.service;

import java.util.List;

import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;
import ch.ivyteam.ivy.application.IApplication;

public interface ISecurityService {

  /**
   * Finds the users who have the given roles and in the given applications
   * @param query
   * @param apps
   * @param startIndex 0..n. The index of the first record is 0
   * @param count 0..n. Use -1 to return all beginning from the startIndex
   * @param fromRoles
   * @return {@link IvySecurityResultDTO}
   */
  IvySecurityResultDTO findUsers(String query, List<String> apps, int startIndex, int count, List<String> fromRoles);
  
  /**
   * Finds the users who have the given roles and in the given application
   * @param query
   * @param app
   * @param startIndex 0..n. The index of the first record is 0
   * @param count 0..n. Use -1 to return all beginning from the startIndex
   * @param fromRoles
   * @return {@link IvySecurityResultDTO}
   */
  IvySecurityResultDTO findUsers(String query, IApplication app, int startIndex, int count, List<String> fromRoles);
  
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
   * @param startIndex 0..n. The index of the first record is 0
   * @param count 0..n. Use -1 to return all beginning from the startIndex
   * @return {@link IvySecurityResultDTO}
   */
  IvySecurityResultDTO findSecurityMembers(String query, IApplication app, int startIndex, int count);
  
  /**
   * Finds the users and roles by the list of application names
   * @param query
   * @param apps
   * @param startIndex 0..n. The index of the first record is 0
   * @param count 0..n. Use -1 to return all beginning from the startIndex
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
