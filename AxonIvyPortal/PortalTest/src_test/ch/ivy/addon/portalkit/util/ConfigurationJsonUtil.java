package ch.ivy.addon.portalkit.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import portal.guitest.common.FileHelper;
import portal.guitest.common.PortalGUITestException;
import portal.guitest.common.UrlHelpers;
import portal.guitest.common.Variable;
import vn.wawa.guitest.base.client.Browser;

public class ConfigurationJsonUtil {

  public static void updateJSONSetting(String fileConfig, Variable variableName) throws IOException {
    String customCaseDetais = FileHelper.getAbsolutePathToTestFile(fileConfig);
    Path path = Paths.get(customCaseDetais);
    String jsonContent = FileUtils.readFileToString(path.toFile(), StandardCharsets.UTF_8);
    updateGlobalVariable(variableName.getKey(), jsonContent);
  }

  public static void updateGlobalVariable(String variableName, String variableValue) {
    String encodeVariableName = URLEncoder.encode(variableName, StandardCharsets.UTF_8);
    String encodeVariableValue = URLEncoder.encode(variableValue, StandardCharsets.UTF_8);
    String updateGlobalVariableLink = "portalKitTestHelper/1749B87B8C1B77BE/updateGlobalVariable.ivp?variableName=%s&variableValue=%s";
    redirectToRelativeLink(String.format(updateGlobalVariableLink, encodeVariableName, encodeVariableValue));
  }

  public static void redirectToRelativeLink(String relativeProcessStartUrl) {
    Browser browser = Browser.getBrowser();
    try {
      browser.goHome(UrlHelpers.generateAbsoluteProcessStartLink(relativeProcessStartUrl));
    } catch (Exception e) {
      throw new PortalGUITestException(e);
    }
  }
}
