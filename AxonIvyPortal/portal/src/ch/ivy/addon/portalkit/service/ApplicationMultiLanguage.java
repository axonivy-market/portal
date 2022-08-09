package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.primefaces.shaded.json.JSONException;

import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.publicapi.ApplicationMultiLanguageAPI;
import ch.ivy.addon.portalkit.util.DisplayNameAdaptor;
import ch.ivy.addon.portalkit.util.Locales;
import ch.ivyteam.ivy.environment.Ivy;

public class ApplicationMultiLanguage {

  private static final String SUPPORTED_LANGUAGES_CMS_URI = "/AppInfo/SupportedLanguages";

  private ApplicationMultiLanguage() {}

  public static String getDisplayNameInCurrentLocale(Application application) throws JSONException {
    Locale currentLocale = new Locales().getCurrentLocale();
    DisplayNameAdaptor displayNameAdaptor = new DisplayNameAdaptor(application.getDisplayName(), currentLocale);
    if (null != displayNameAdaptor.getDisplayNameAsString()) {
      return displayNameAdaptor.getDisplayNameAsString();
    } else {
      Map<String, String> displayNameAsMap = displayNameAdaptor.getDisplayNameAsMap();
      return getRandomDisplayName(displayNameAsMap);
    }
  }


  private static String getRandomDisplayName(Map<String, String> displayNameAsMap) {
    Set<String> keySet = displayNameAsMap.keySet();
    for (String key : keySet) {
      String displayName = displayNameAsMap.get(key);
      if (!displayName.isEmpty()) {
        return displayName;
      }
    }
    return "";
  }

  /**
   * Loads supported language for a third party application.
   *
   * @return {@link List} list of supported language
   */
  public static List<String> getThirdPartySupportedLanguage() {
    Set<String> supportedLanguages = new HashSet<>();
    String languageSupported = Ivy.cms().co(SUPPORTED_LANGUAGES_CMS_URI);
    List<String> languages = Arrays.asList(languageSupported.split(","));
    for (String language : languages) {
      if (!language.isEmpty()) {
        supportedLanguages.add(language);
      }
    }
    return new ArrayList<>(supportedLanguages);
  }

  /**
   * Get CMS value for specific URI based on current locale
   * @deprecated Use {@link ApplicationMultiLanguageAPI#getCmsValueByUserLocale(String)} instead
   * @param cmsURI cms uri
   * @return label
   */
  @Deprecated
  public static String getCmsValueByUserLocale(String cmsURI) {
    Locale currentUserLocale = new Locales().getCurrentLocale();
    return Ivy.cms().coLocale(cmsURI, currentUserLocale);
  }
}
