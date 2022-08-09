package ch.ivy.addon.portalkit.ivydata.service;

import ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage;
import ch.ivy.addon.portalkit.ivydata.dto.IvyLanguageResultDTO;

public interface ILanguageService {

  /**
   * Get Languages for the current user
   * @return {@link IvyLanguageResultDTO}
   */
  IvyLanguageResultDTO findUserLanguages();

  /**
   * set Languages for the current user
   * @param language
   */
  void saveUserLanguage(IvyLanguage language);
}
