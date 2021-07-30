package portal.migration.ivydata.service;

import portal.migration.ivydata.bo.IvyLanguage;
import portal.migration.ivydata.dto.IvyLanguageResultDTO;

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
