package ch.ivy.ws.addon.service;

import java.util.List;

import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.LanguagesSettingsServiceResult;
import ch.ivy.ws.addon.types.IvyLanguageSetting;

/**
 * Service methods for user ILanguagesSettingsService settings
 * @author lptchi
 *
 */
public interface ILanguagesSettingsService {

	/**
	 * Get Languages settings for the passed user
	 * @param username
	 * @param apps
	 * @return
	 * @throws WSException
	 */
	public LanguagesSettingsServiceResult getLanguagesSettings(String username, List<String> apps, Long serverId);
	
	/**
	 * set Languages settings for the passed user
	 * @param username
	 * @param settings
	 * @return
	 * @throws WSException
	 */
	public LanguagesSettingsServiceResult setLanguagesSettings(String username, List<IvyLanguageSetting> settings);
		
	
}
