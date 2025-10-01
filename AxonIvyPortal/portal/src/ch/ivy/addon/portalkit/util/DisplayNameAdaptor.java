package ch.ivy.addon.portalkit.util;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONException;
import org.primefaces.shaded.json.JSONObject;

import ch.ivyteam.ivy.environment.Ivy;

public class DisplayNameAdaptor {

  private DisplayNameConvertor convertor;
  private Locale currentLocale;
  private List<String> supportedLanguages;

  public DisplayNameAdaptor(String jsonString, Locale currentLocale) {
    this.currentLocale = currentLocale;
    this.convertor = parseJson(jsonString, currentLocale);
    this.supportedLanguages = LanguageUtils.getSupportedLanguages();
  }

  private DisplayNameConvertor parseJson(String jsonString, Locale locale) {
    if (isValidJson(jsonString)) {
      return DisplayNameConvertor.parseJson(jsonString);
    }
    DisplayNameConvertor displayNameConvertor = new DisplayNameConvertor();
    displayNameConvertor.add(locale, jsonString);
    return displayNameConvertor;
  }

  @SuppressWarnings("unused")
  private boolean isValidJson(String jsonString) {
    try {
      new JSONObject(jsonString);
    } catch (JSONException e) {
      try {
        new JSONArray(jsonString);
      } catch (JSONException e1) {
        return false;
      }
    }
    return true;
  }

  public String getDisplayNameAsString() {
    String result = convertor.toString(currentLocale);
    if (result != null && !result.trim().isEmpty()) {
      return result;
    }
    
    String languageCode = currentLocale.getLanguage();
    Map<String, String> displayNames = convertor.getDisplayNameAsMap();
    for (Map.Entry<String, String> entry : displayNames.entrySet()) {
      if (isLanguageMatch(entry.getKey(), languageCode) && 
          entry.getValue() != null && !entry.getValue().trim().isEmpty()) {
          return entry.getValue();
        }
    }
  
    // 3. Try English default
    return getValueForLanguage(displayNames, "en");

  }
  
  private boolean isLanguageMatch(String localeTag, String languageCode) {
    try {
        Locale locale = Locale.forLanguageTag(localeTag);
        return locale.getLanguage().equalsIgnoreCase(languageCode);
    } catch (Exception e) {
        return false;
    }
}

  private String getValueForLanguage(Map<String, String> displayNames, String languageCode) {
      return displayNames.entrySet().stream()
          .filter(entry -> isLanguageMatch(entry.getKey(), languageCode))
          .map(Map.Entry::getValue)
          .filter(value -> value != null && !value.trim().isEmpty())
          .findFirst()
          .orElse(StringUtils.EMPTY);
  }

  public Map<String, String> getDisplayNameAsMap() {
    return convertor.getDisplayNameAsMap();
  }

  public void add(Locale locale, String displayName) {
    convertor.add(locale, displayName);
  }

  public String toJson() {
    return convertor.toJson();
  }
}
