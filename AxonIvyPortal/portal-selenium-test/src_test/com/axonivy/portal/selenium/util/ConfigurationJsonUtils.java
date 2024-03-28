package com.axonivy.portal.selenium.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import com.axonivy.portal.selenium.common.FileHelper;
import com.axonivy.portal.selenium.common.LinkNavigator;
import com.axonivy.portal.selenium.common.Variable;

public class ConfigurationJsonUtils {
  public static void updateJSONSetting(String fileConfig, Variable variableName) throws IOException {
    String customCaseDetais = FileHelper.getAbsolutePathToTestFile(fileConfig);
    Path path = Paths.get(customCaseDetais);
    String jsonContent = FileUtils.readFileToString(path.toFile(), StandardCharsets.UTF_8);
    updateGlobalVariable(variableName.getKey(), jsonContent);
  }

  public static void updateGlobalVariable(String variableName, String variableValue) {
    String encodeVariableName = URLEncoder.encode(variableName, StandardCharsets.UTF_8);
    String encodeVariableValue = URLEncoder.encode(variableValue, StandardCharsets.UTF_8);
    String updateGlobalVariableLink =
        "portalKitTestHelper/1749B87B8C1B77BE/updateGlobalVariable.ivp?variableName=%s&variableValue=%s";
    redirectToRelativeLink(String.format(updateGlobalVariableLink, encodeVariableName, encodeVariableValue));
  }

  public static void redirectToRelativeLink(String relativeProcessStartUrl) {
    LinkNavigator.redirectToRelativeLink(relativeProcessStartUrl);
  }
}
