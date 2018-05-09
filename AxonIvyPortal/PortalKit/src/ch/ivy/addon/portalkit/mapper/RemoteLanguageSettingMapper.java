/*
 * RemoteLanguageSettingMapper.java
 *
 */
package ch.ivy.addon.portalkit.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.primefaces.json.JSONException;

import ch.ivy.addon.portalkit.bo.RemoteLanguageSetting;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;
import ch.ivy.ws.addon.IvyLanguageSetting;

/**
 * Mapper for mapping between ivy object and web service object.
 *
 * @author maonguyen
 */
public class RemoteLanguageSettingMapper {
  
  private RemoteLanguageSettingMapper() {}
  
	/**
	 * Map from list web service ivy object to list remote object.
	 *
	 * @param ivyLanguageSettings list of Web service object to map
	 * @param server ivy server working on
	 * @return list of remote language setting after mapping.
	 * @throws JSONException 
	 */
	public static List<RemoteLanguageSetting> mapToRemoteLanguageSettings(List<IvyLanguageSetting> ivyLanguageSettings, Server server) throws JSONException{
		List<RemoteLanguageSetting> remoteLanguageSettings = new ArrayList<>();
		if (ivyLanguageSettings != null) {
			for (IvyLanguageSetting item: ivyLanguageSettings) {
				RemoteLanguageSetting languageSetting = new RemoteLanguageSetting();
				languageSetting.setAppName(item.getAppName());
				setAppDisplayName(server, item, languageSetting);
		    if (languageSetting.getAppDisplayName() == null || languageSetting.getAppDisplayName().trim().isEmpty()) {
		      languageSetting.setAppDisplayName(item.getAppName());
		    }
		    
				languageSetting.setSupportedLanguages(new ArrayList<String>(Arrays.asList(item.getSupportedLanguages())));
				languageSetting.setUserLanguage(item.getUserLanguage());
				remoteLanguageSettings.add(languageSetting);
			}
		}
		
		return remoteLanguageSettings;
		
	}

	private static void setAppDisplayName(Server server, IvyLanguageSetting ivyLanguageSetting, RemoteLanguageSetting languageSetting) {
	  for (Application application: server.getApplications()) {
            if (ivyLanguageSetting.getAppName() != null && ivyLanguageSetting.getAppName().equals(application.getName())) {
                    languageSetting.setAppDisplayName(ApplicationMultiLanguage.getDisplayNameOfAllAppInSameIvyApplication(application, server));
                    break;
            }
    }
	}
	/**
	 * Map from list remote object to list web service ivy object .
	 * 
	 * @param remoteLanguageSettings list of remote language setting.
	 * 
	 * @return list of WS language setting
	 *
	 */
	public static List<IvyLanguageSetting> mapToIvyLanguageSettings(List<RemoteLanguageSetting> remoteLanguageSettings){
		List<IvyLanguageSetting> ivyLanguageSettings = new ArrayList<>();
		if (remoteLanguageSettings != null) {
			for (RemoteLanguageSetting item: remoteLanguageSettings) {
				IvyLanguageSetting languageSetting = new IvyLanguageSetting();
				languageSetting.setAppName(item.getAppName());
				languageSetting.setSupportedLanguages(item.getSupportedLanguages().toArray(new String[item.getSupportedLanguages().size()]));
				languageSetting.setUserLanguage(item.getUserLanguage());
				ivyLanguageSettings.add(languageSetting);
			}
		}
		
		return ivyLanguageSettings;
	}
}


