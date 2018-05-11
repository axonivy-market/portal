package ch.ivy.ws.addon.util;

import java.util.Map;

import com.google.gson.Gson;

public class JsonConverterUtils {
  private JsonConverterUtils() {}
  
  public static <K, V> String mapToJson(Map<K, V> map) {
    Gson gsonConverter = new Gson();
    String json = "";
    if (map.size() != 0) {
      json = gsonConverter.toJson(map);
    }
    return json;
  }
}
