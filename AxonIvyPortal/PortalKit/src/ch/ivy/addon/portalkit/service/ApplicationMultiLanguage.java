package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.json.JSONException;

import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivy.addon.portalkit.util.DisplayNameAdaptor;
import ch.ivy.addon.portalkit.util.Locales;
import ch.ivyteam.ivy.environment.Ivy;

public class ApplicationMultiLanguage {

  private static final String SUPPORTED_LANGUAGES_CMS_URI = "/PortalKit/AppInfo/SupportedLanguages";

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

  public static String getDisplayNameOfAllAppInSameIvyApplication(Application application, Server server)
      throws JSONException {
    List<String> displayNames = new ArrayList<>();
    for (Application app : server.getApplications()) {
      if (app.getName().equals(application.getName()) && app.getIsVisible()) {
        displayNames.add(getDisplayNameInCurrentLocale(app));
      }
    }
    String delimiter = ", ";
    return StringUtils.join(displayNames, delimiter);
  }

  private static String getRandomDisplayName(Map<String, String> displayNameAsMap) {
    Set<String> keySet = displayNameAsMap.keySet();
    for (String key : keySet) {
      String displayName = displayNameAsMap.get(key).toString();
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
   * @throws Exception
   */
  public static List<String> getThirdPartySupportedLanguage() throws Exception {
    Set<String> supportedLanguages = new HashSet<String>();
    String languageSupported = Ivy.cms().co(SUPPORTED_LANGUAGES_CMS_URI);
    List<String> languages = Arrays.asList(languageSupported.split(","));
    for (String language : languages) {
      if (!language.isEmpty()) {
        supportedLanguages.add(language);
      }
    }
    return new ArrayList<String>(supportedLanguages);
  }
}
