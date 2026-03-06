package ch.ivy.addon.portalkit.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.shaded.json.JSONException;
import org.primefaces.shaded.json.JSONObject;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;

public class DisplayNameConvertor {

  private Map<String, String> displayNames = new HashMap<>();

  public DisplayNameConvertor add(Locale locale, String displayName) {
    displayNames.put(locale.toLanguageTag(), displayName);
    return this;
  }

  public String toJson() {
    return JSONObject.valueToString(displayNames);
  }

  public String toString(Locale locale) {
    return displayNames.get(locale.toLanguageTag());
  }

  public Map<String, String> getDisplayNameAsMap() {
    return displayNames;
  }

  public static DisplayNameConvertor parseJson(String jsonString) throws JSONException {
    DisplayNameConvertor displayName = new DisplayNameConvertor();
    JSONObject jsonObject = new JSONObject(jsonString);
    Iterator<String> keys = jsonObject.keys();
    while (keys.hasNext()) {
      String key = keys.next();
      String value = jsonObject.getString(key);
      displayName.add(Locale.forLanguageTag(key), value);
    }
    return displayName;
  }
  
  public static void setValue(String currentValue, List<DisplayName> values) {
    String currentLanguage = UserUtils.getUserLanguage();
    Optional<DisplayName> optional = values.stream()
        .filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
    if (optional.isPresent()) {
      optional.get().setValue(currentValue);
    }
  }
  
  public static String updateCurrentValue(String currentValue, List<DisplayName> values) {
    String currentLanguage = UserUtils.getUserLanguage();
    Optional<DisplayName> optional = values.stream()
        .filter(lang -> currentLanguage.equals(lang.getLocale().getLanguage())).findFirst();
    if (optional.isPresent()) {
      return optional.get().getValue();
    }
    return currentValue;
  }
  
  public static void initMultipleLanguages(String currentValue, List<DisplayName> values) {
    Map<String, DisplayName> mapLanguage = values
                                            .stream()
                                            .collect(Collectors.toMap(o -> o.getLocale().toLanguageTag(), o -> o));
    List<String> supportedLanguages = LanguageService.getInstance().getIvyLanguageOfUser().getSupportedLanguages();
    for (String language : supportedLanguages) {
      DisplayName localeLanguage = mapLanguage.get(language);
      if (localeLanguage == null) {
        DisplayName displayName = new DisplayName();
        displayName.setLocale(Locale.forLanguageTag(language));
        displayName.setValue(currentValue);
        values.add(displayName);
      } else if (StringUtils.isBlank(localeLanguage.getValue())) {
        localeLanguage.setValue(currentValue);
      }
    }
  }
  
  public static void updateEmptyValue(String userLanguguage, List<DisplayName> values) {
    DisplayName defaultValue = values.stream().filter(item -> item.getLocale().getLanguage().equals(userLanguguage)).findFirst().orElse(null);
    for (DisplayName name : values) {
      if (StringUtils.isBlank(name.getValue()) && defaultValue != null) {
        name.setValue(defaultValue.getValue());
      }
    }
  }
}
