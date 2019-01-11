package ch.ivy.addon.portalkit.ivydata.service;

import java.util.List;

import ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage;
import ch.ivy.addon.portalkit.ivydata.dto.IvyLanguageResultDTO;

public interface ILanguageService {

  /**
   * Get Languages for the passed user
   * @param username
   * @param apps
   * @return IvyLanguageResultDTO
   * @throws Exception 
   */
  IvyLanguageResultDTO findUserLanguages(String username, List<String> apps) throws Exception;
  
  /**
   * set Languages for the passed user
   * @param username
   * @param languages
   * @return IvyLanguageResultDTO
   * @throws Exception 
   */
  IvyLanguageResultDTO saveUserLanguages(String username, List<IvyLanguage> languages) throws Exception;
  
  
  /**
   * get supported languages of application store in portal style
   * @param appName
   * @return
   * @throws Exception
   */
  IvyLanguageResultDTO getSupportedLanguages(String appName) throws Exception;
}
