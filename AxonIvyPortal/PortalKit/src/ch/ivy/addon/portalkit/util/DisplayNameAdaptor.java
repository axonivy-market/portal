package ch.ivy.addon.portalkit.util;

import java.util.Locale;
import java.util.Map;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

public class DisplayNameAdaptor {

	private DisplayNameConvertor convertor;
	private Locale currentLocale;
	
	public DisplayNameAdaptor(String jsonString, Locale currentLocale) throws JSONException {
		this.currentLocale = currentLocale;
		this.convertor = parseJson(jsonString, currentLocale);
	}

	private DisplayNameConvertor parseJson(String jsonString, Locale currentLocale) throws JSONException {
		if (isValidJson(jsonString)) {
			return DisplayNameConvertor.parseJson(jsonString);
		}
		DisplayNameConvertor convertor = new DisplayNameConvertor();
		convertor.add(currentLocale, jsonString);
		return convertor;
	}

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
