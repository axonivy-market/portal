package ch.ivy.addon.portalkit.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

public class DisplayNameConvertor {

  private Map<String, String> displayNames = new HashMap<String, String>();

  public DisplayNameConvertor add(Locale locale, String displayName) {
    displayNames.put(locale.getLanguage(), displayName);
    return this;
  }

  public String toJson() {
    return JSONObject.valueToString(displayNames);
  }

  public String toString(Locale locale) {
    return displayNames.get(locale.getLanguage());
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
      displayName.add(new Locale(key), value);
    }
    return displayName;
  }
}
