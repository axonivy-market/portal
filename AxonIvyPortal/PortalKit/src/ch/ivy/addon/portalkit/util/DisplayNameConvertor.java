package ch.ivy.addon.portalkit.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.primefaces.shaded.json.JSONException;
import org.primefaces.shaded.json.JSONObject;

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
}
