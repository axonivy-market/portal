package ch.ivy.addon.portalkit.ivydata.service;

import java.util.List;

import ch.ivy.addon.portalkit.ivydata.bo.IvyLanguage;
import ch.ivy.addon.portalkit.ivydata.dto.IvyLanguageResultDTO;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;

public interface ILanguageService {

  /**
   * Get Languages for the current user
   * @return {@link IvyLanguageResultDTO}
   */
  IvyLanguageResultDTO findUserLanguages();

  /**
   * set Languages for the current user
   * @param language
   * @return {@link IvyLanguageResultDTO}
   * @throws PortalIvyDataException 
   */
  IvyLanguageResultDTO saveUserLanguage(IvyLanguage language) throws PortalIvyDataException;

  /**
   * get supported languages of application store in portal style
   * @return {@link IvyLanguageResultDTO}
   */
  IvyLanguageResultDTO getSupportedLanguages();
  
  /**
   * get supported languages of current application store in portal style
   * @return list of languages
   * @throws PortalIvyDataException 
   */
  List<String> getSupportedLanguagesOfCurrentApp() throws PortalIvyDataException;
}
