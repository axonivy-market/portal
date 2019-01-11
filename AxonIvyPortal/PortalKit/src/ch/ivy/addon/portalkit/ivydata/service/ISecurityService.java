package ch.ivy.addon.portalkit.ivydata.service;

import java.util.List;

import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;
import ch.ivyteam.ivy.application.IApplication;

public interface ISecurityService {

  /**
   * Gets users of the given apps
   * @param apps
   * @return IvySecurityResultDTO
   * @throws Exception 
   */
  IvySecurityResultDTO findUsers(List<String> apps) throws Exception;
  
  /**
   * Finds the users by the given app
   * @param app
   * @return IvySecurityResultDTO
   * @throws Exception
   */
  IvySecurityResultDTO findUsers(IApplication app) throws Exception;
  
  /**
   * Gets roles of the given apps
   * @param apps
   * @return IvySecurityResultDTO
   * @throws Exception 
   */
  IvySecurityResultDTO findRoles(List<String> apps) throws Exception;
  
  /**
   * Finds the roles by the given app
   * @param app
   * @return IvySecurityResultDTO
   * @throws Exception
   */
  IvySecurityResultDTO findRoles(IApplication app) throws Exception;
  
  /**
   * Finds the users and roles
   * @param app
   * @return IvySecurityResultDTO
   * @throws Exception
   */
  IvySecurityResultDTO findSecurityMembers(IApplication app) throws Exception;
  
  /**
   * Finds the users and roles by the list of apps
   * @param apps
   * @return
   * @throws Exception
   */
  IvySecurityResultDTO findSecurityMembers(List<String> apps) throws Exception;
}
