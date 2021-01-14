package ch.ivy.addon.portalkit.publicapi;

import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;

/**
 * Portal API for support multi language menu
 *
 */
public final class ApplicationMultiLanguageAPI {
  private ApplicationMultiLanguageAPI() {}
  
  /**
   * Get CMS value for specific URI based on current locale 
   * @param cmsURI CMS uri
   * @return value at CMS uri
   */
  public static String getCmsValueByUserLocale(String cmsURI) {
    return ApplicationMultiLanguage.getCmsValueByUserLocale(cmsURI);
  }
}
