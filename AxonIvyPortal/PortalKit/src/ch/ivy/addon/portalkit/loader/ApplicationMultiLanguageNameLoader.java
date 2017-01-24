package ch.ivy.addon.portalkit.loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.util.Locales;

/**
 * This class provides method for loading supported language.
 */
public class ApplicationMultiLanguageNameLoader {

  private String currentDisplayName = "";
  private Map<String, String> currentDisplayNames = new HashMap<String, String>();
  private List<String> supportedLanguages = new ArrayList<String>();

  public ApplicationMultiLanguageNameLoader() {
  }

  public ApplicationMultiLanguageNameLoader currentDisplayName(String currentDisplayName) {
    this.currentDisplayName = currentDisplayName;
    return this;
  }

  public ApplicationMultiLanguageNameLoader currentDisplayNames(List<DisplayName> currentDisplayNames) {
    this.currentDisplayNames = toMap(currentDisplayNames);
    return this;
  }

  public ApplicationMultiLanguageNameLoader currentDisplayNames(Map<String, String> currentDisplayNames) {
    this.currentDisplayNames = currentDisplayNames;
    return this;
  }

  public ApplicationMultiLanguageNameLoader supportedLanguages(List<String> supportedLanguages) {
    this.supportedLanguages = supportedLanguages;
    return this;
  }

  public List<DisplayName> load() {
    List<DisplayName> displayNames = new ArrayList<DisplayName>();

    for (String language : supportedLanguages) {
      DisplayName displayName = new DisplayName();
      Locale locale = new Locale(language);
      displayName.setLocale(locale);
      if (canUpdateDisplayNameInCurrentLanguage(locale)) {
        displayName.setValue(currentDisplayName);
      } else {
        displayName.setValue(currentDisplayNames.get(locale.getLanguage()));
      }
      displayNames.add(displayName);
    }

    return displayNames;
  }

  private boolean isCurrentLocaleAndNotNullDisplayName(Locale locale) {
    String currentLanguage = new Locales().getCurrentLocale().getLanguage();
    return null != currentDisplayName && locale.getLanguage().equals(currentLanguage);
  }

  private boolean isOverridedCurrentDisplayNames() {
    return currentDisplayNames.isEmpty();
  }

  private boolean canUpdateDisplayNameInCurrentLanguage(Locale locale) {
    return isOverridedCurrentDisplayNames() || isCurrentLocaleAndNotNullDisplayName(locale);
  }

  private Map<String, String> toMap(List<DisplayName> currentDisplayNames) {
    Map<String, String> displayNames = new HashMap<String, String>();
    for (DisplayName displayName : currentDisplayNames) {
      displayNames.put(displayName.getLocale().getLanguage(), displayName.getValue());
    }
    return displayNames;
  }
}
