package ch.ivy.addon.portalkit.ivydata.service;

import ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage;
import ch.ivy.addon.portalkit.ivydata.dto.IvyLanguageResultDTO;

public interface ILanguageService {

  /**
   * Get Languages for the passed user
   * @param username
   * @return {@link IvyLanguageResultDTO}
   */
  IvyLanguageResultDTO findUserLanguages(String username);

  /**
   * set Languages for the passed user
   * @param username
   * @param language
   * @return {@link IvyLanguageResultDTO}
   */
  IvyLanguageResultDTO saveUserLanguage(String username, IvyLanguage language);

  /**
   * get supported languages of application store in portal style
   * @return {@link IvyLanguageResultDTO}
   */
  IvyLanguageResultDTO getSupportedLanguages();
}
