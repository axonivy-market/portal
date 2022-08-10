package ch.ivy.addon.portalkit.ivydata.service;

import java.util.List;

import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;
import ch.ivyteam.ivy.application.IApplication;

public interface ISecurityService {

  /**
   * Finds the users who have the given roles and in current application
   * @param query
   * @param startIndex 0..n. The index of the first record is 0
   * @param count 0..n. Use -1 to return all beginning from the startIndex
   * @param fromRoles
   * @param excludedUsernames
   * @return {@link IvySecurityResultDTO}
   */
  IvySecurityResultDTO findUsers(String query, int startIndex, int count, List<String> fromRoles, List<String> excludedUsernames);
  
  /**
   * Finds the users who have the given roles and in current application
   * @param query
   * @param app
   * @param startIndex 0..n. The index of the first record is 0
   * @param count 0..n. Use -1 to return all beginning from the startIndex
   * @param fromRoles
   * @param excludedUsernames
   * @return {@link IvySecurityResultDTO}
   */
  IvySecurityResultDTO findUsers(String query, IApplication app, int startIndex, int count, List<String> fromRoles, List<String> excludedUsernames);
  
  /**
   * Gets roles in current application
   * @return {@link IvySecurityResultDTO}
   */
  IvySecurityResultDTO findRoles();
  
  /**
   * Finds the users and roles
   * @param query
   * @param startIndex 0..n. The index of the first record is 0
   * @param count 0..n. Use -1 to return all beginning from the startIndex
   * @return {@link IvySecurityResultDTO}
   */
  IvySecurityResultDTO findSecurityMembers(String query, int startIndex, int count);
  
  /**
   * Gets roleDTOs in current application 
   * @return {@link IvySecurityResultDTO}
   */
  IvySecurityResultDTO findRoleDTOs();

  /**
   * Finds all users which own given roles in current application
   * @param startIndex 0..n. The index of the first record is 0
   * @param count 0..n. Use -1 to return all beginning from the startIndex
   * @param fromRoles
   * @param excludedUsernames
   * @return {@link IvySecurityResultDTO}
   */
  IvySecurityResultDTO findAllUsersOfRoles(int startIndex, int count, List<String> fromRoles, List<String> excludedUsernames);

}
