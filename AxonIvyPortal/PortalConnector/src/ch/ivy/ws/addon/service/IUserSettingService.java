package ch.ivy.ws.addon.service;

import java.util.List;

import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.UserSettingServiceResult;
import ch.ivy.ws.addon.types.IvyEmailSetting;
import ch.ivy.ws.addon.types.IvyUserSetting;

/**
 * Service methods for user settings
 * @author mde
 *
 */
public interface IUserSettingService {

	/**
	 * Find user settings for the passed user
	 * @param username
	 * @param appName 
	 * @return UserSettingServiceResult
	 * @throws WSException
	 */
	public UserSettingServiceResult findUserSetting(String username, String appName) throws WSException;
	
	/**
	 * Save the user settings for the passed user
	 * @param username
	 * @param setting
	 * @param appName 
	 * @throws WSException
	 */
	public void saveUserSetting(String username, IvyUserSetting setting, String appName) throws WSException;
	
	/**
	 * Get email settings for all applications
	 * @param applications
	 * @param user
	 * @return UserSettingServiceResult 
	 * @throws WSException 
	 */
	public UserSettingServiceResult getEMailSettings(List<String> applications, String user) throws WSException;

	/**
	 * Set email settings for all applications
	 * @param settings
	 * @param user
	 * @return List<WSException>
	 * @throws WSException 
	 */
	public List<WSException> setEMailSettings(List<IvyEmailSetting> settings, String user) throws WSException;

  /**
   * Change password of a user in applications
   * @param apps
   * @param username
   * @param password
   * @return List<WSException>
   * @throws WSException
   */
  public List<WSException> changePassword(List<String> apps, String username, String password) throws WSException;
}
