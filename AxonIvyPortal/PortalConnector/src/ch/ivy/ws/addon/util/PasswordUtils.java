package ch.ivy.ws.addon.util;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.ws.portaldata.service.IvyService;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.application.property.ICustomProperty;
import ch.ivyteam.ivy.application.restricted.IWebService;
import ch.ivyteam.ivy.application.value.WebServiceAuthentication;
import ch.ivyteam.ivy.environment.Ivy;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * For security concern, password of server is persisted to password of web service authentication. This utility
 * supports handling password.<br>
 * CAUTION: This class needs to be compatible with PasswordUtils in PortalKit.
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

  // ===============DIFFERENT PART (Compare to PasswordUtils in PortalKit)===================

  private static IApplication app() {
    return Ivy.wf().getApplication();
  }

  public static String removePasswordInformation(String serverAsString) {
    JsonObject jsonObject = parseToObject(serverAsString);
    jsonObject.remove(SERVER_PWD_KEY);
    return jsonObject.toString();
  }

  public static void createPassword(String serverAsString) {
    String path = getServerPath(serverAsString);
    String password = getServerPassword(serverAsString);
    if (StringUtils.isNotEmpty(password)) {
      PasswordUtils.save(path, password);
    }
  }

  private static String getServerPassword(String serverAsString) {
    JsonObject jsonObject = parseToObject(serverAsString);
    JsonElement passwordElement = jsonObject.get(SERVER_PWD_KEY);
    if (Objects.isNull(passwordElement)) {
      return null;
    } else {
      return passwordElement.getAsString();
    }
  }

  public static void deletePasswordRelatedTo(String keysToBeDeleted) {
    ICustomProperty customProperty = IvyService.getApplication().customProperties().property(keysToBeDeleted);
    if (customProperty.hasValue()) {
      String path = getServerPath(customProperty.getValue());
      PasswordUtils.delete(path);
    }
  }
}
