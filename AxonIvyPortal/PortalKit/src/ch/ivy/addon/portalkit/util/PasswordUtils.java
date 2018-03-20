package ch.ivy.addon.portalkit.util;

import java.util.Objects;

import ch.ivy.addon.portalkit.service.PortalConnectorDetector;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.restricted.IWebService;
import ch.ivyteam.ivy.application.value.WebServiceAuthentication;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * For security concern, password of server is persisted to password of web service authentication. This utility
 * supports handling password.<br>
 * CAUTION: This class needs to be compatible with PasswordUtils in PortalConnector.
 */
@SuppressWarnings("restriction")
public final class PasswordUtils {
  private static final String SERVER_KEY_PREFIX = "AxonIvyPortal.Server";
  private static final String SERVER_PATH = "path";
  private static final String SERVER_PWD_KEY = "password";

  private PasswordUtils() {}

  public static String find(String id) {
    IWebService ws = findWebService(id);
    if (Objects.isNull(ws) || Objects.isNull(ws.getAuthentication())) {
      return null;
    }
    return ws.getAuthentication().getPassword();
  }

  public static void save(String id, String password) {
    IWebService ws = findWebService(id);
    WebServiceAuthentication auth = new WebServiceAuthentication("", "", password);
    if (Objects.nonNull(ws)) {
      ws.setAuthentication(auth);
    } else {
      app().createWebService(id, "", "", "", "", false, 0, "", "", 0, auth);
    }
  }

  public static void delete(String id) {
    app().deleteWebService(id);
  }

  private static IWebService findWebService(String id) {
    IWebService ws = app().findWebService(id);
    return ws;
  }

  public static boolean isKeyOfServer(String propertyKey) {
    return propertyKey.startsWith(SERVER_KEY_PREFIX);
  }

  private static String getServerPath(String serverAsString) {
    JsonObject jsonObject = parseToObject(serverAsString);
    JsonElement pathElement = jsonObject.get(SERVER_PATH);
    if (Objects.isNull(pathElement)) {
      return null;
    } else {
      return pathElement.getAsString();
    }
  }

  private static JsonObject parseToObject(String serverAsString) {
    JsonObject jsonObject = new JsonParser().parse(serverAsString).getAsJsonObject();
    return jsonObject;
  }

  // ===============DIFFERENT PART (Compare to PasswordUtils in PortalConnector)===================
  private static IApplication app() {
    return new PortalConnectorDetector().getPortalConnectorApplication();
  }

  public static String addPasswordInformation(String serverAsString) {
    JsonObject jsonObject = parseToObject(serverAsString);
    String path = getServerPath(serverAsString);
    String password = PasswordUtils.find(path);
    if (Objects.nonNull(password)) {
      jsonObject.addProperty(SERVER_PWD_KEY, password);
    }
    return jsonObject.toString();
  }

}
