package ch.ivy.addon.portalkit.util;

import java.util.Locale;
import java.util.Map;

import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONException;
import org.primefaces.shaded.json.JSONObject;

public class DisplayNameAdaptor {

	private DisplayNameConvertor convertor;
	private Locale currentLocale;

	public DisplayNameAdaptor(String jsonString, Locale currentLocale) {
		this.currentLocale = currentLocale;
		this.convertor = parseJson(jsonString, currentLocale);
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
			} catch(JSONException e1) {
				return false;
			}
		}
		return true;
	}

	public String getDisplayNameAsString() {
		return convertor.toString(currentLocale);
	}

	public Map<String, String> getDisplayNameAsMap() {
		return convertor.getDisplayNameAsMap();
	}

	public void add(Locale locale, String displayName) {
		convertor.add(locale, displayName);
	}

	public String toJson(){
		return convertor.toJson();
	}
}
