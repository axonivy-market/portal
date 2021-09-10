package portalmigration.ivydata.service;

import portalmigration.ivydata.bo.IvyLanguage;
import portalmigration.ivydata.dto.IvyLanguageResultDTO;

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
