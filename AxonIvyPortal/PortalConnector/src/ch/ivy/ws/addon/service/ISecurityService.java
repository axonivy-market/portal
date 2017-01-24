package ch.ivy.ws.addon.service;


import java.util.List;

import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.SecurityServiceResult;
import ch.ivyteam.ivy.workflow.ITask;

/**
 * Security service that provides services
 * for load users and roles
 * @author mde
 *
 */
public interface ISecurityService {

	/**
	 * Find all users provided by ivy
	 * @return
	 * @throws WSException
	 */
	public SecurityServiceResult findAllUsers(List<String> apps) throws WSException;
	
	
	/**
	 * Find all ivy roles 
	 * @return
	 * @throws WSException
	 */
	public SecurityServiceResult findAllRoles(List<String> apps) throws WSException;
	
	
	/**
	 * Find all users for the role
	 * @param roleId
	 * @return
	 * @throws WSException
	 */
	public SecurityServiceResult findUsersByRoleId(String app, Long roleId) throws WSException;
	
	
	 /**
   * Find all ivy security members to delegate
   * @param task 
   * @return
   * @throws WSException
   */
  public SecurityServiceResult findSecurityMembersToDelegate(ITask task) throws WSException;
}
