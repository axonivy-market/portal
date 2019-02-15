package ch.ivy.addon.portalkit.ivydata.service;

import java.util.List;

import ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage;
import ch.ivy.addon.portalkit.ivydata.dto.IvyLanguageResultDTO;

public interface ILanguageService {

  /**
   * Get Languages for the passed user
   * @param username
   * @param apps
   * @return {@link IvyLanguageResultDTO}
   */
  IvyLanguageResultDTO findUserLanguages(String username, List<String> apps);
  
  /**
   * set Languages for the passed user
   * @param username
   * @param languages
   * @return {@link IvyLanguageResultDTO}
   */
  IvyLanguageResultDTO saveUserLanguages(String username, List<IvyLanguage> languages);
  
  
  /**
   * get supported languages of application store in portal style
   * @param appName
   * @return {@link IvyLanguageResultDTO}
   */
  IvyLanguageResultDTO getSupportedLanguages(String appName);
}
