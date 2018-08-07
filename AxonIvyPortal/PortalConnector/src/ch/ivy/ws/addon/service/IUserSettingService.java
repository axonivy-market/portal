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
	 * @return
	 * @throws WSException
	 */
	public UserSettingServiceResult findUserSetting(String username, String appName) throws WSException;
	
	/**
	 * Save the user settings for the passed user
	 * @param username
	 * @param setting
	 * @throws WSException
	 */
	public void saveUserSetting(String username, IvyUserSetting setting, String appName) throws WSException;
	
	/**
	 * Get email settings for all applications
	 * @param applications
	 * @param user
	 * @return UserSettingServiceResult 
	 * @throws Exception 
	 */
	public UserSettingServiceResult getEMailSettings(List<String> applications, String user) throws Exception;

	/**
	 * Set email settings for all applications
	 * @param settings
	 * @param user
	 * @return List<WSException>
	 * @throws Exception 
	 */
	public List<WSException> setEMailSettings(List<IvyEmailSetting> settings, String user) throws Exception;
}
