package ch.ivy.addon.portalkit.service;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.primefaces.shaded.json.JSONException;

import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.util.DisplayNameAdaptor;
import ch.ivyteam.ivy.environment.Ivy;

public class ApplicationMultiLanguage {

  private ApplicationMultiLanguage() {}

  public static String getDisplayNameInCurrentLocale(Application application) throws JSONException {
    Locale currentLocale = Ivy.session().getContentLocale();
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
}
