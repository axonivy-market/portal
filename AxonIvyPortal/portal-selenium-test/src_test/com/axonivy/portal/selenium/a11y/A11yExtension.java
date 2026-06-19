package com.axonivy.portal.selenium.a11y;

import static com.codeborne.selenide.Selenide.open;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import com.axonivy.portal.selenium.common.FileHelper;
import com.axonivy.portal.selenium.common.LinkNavigator;
import com.axonivy.portal.selenium.common.PortalGUITestException;
import com.axonivy.portal.selenium.common.UrlHelpers;
import com.codeborne.selenide.WebDriverRunner;

import ch.ivy.addon.portalkit.enums.PortalVariable;

public class A11yExtension implements AfterEachCallback, BeforeEachCallback {
  private String cleanupDataLink = "portalKitTestHelper/1511A66AF619A768/cleanData.ivp";
  private String createJSonFileUrl = "PortalKitTestHelper/153CACC26D0D4C3D/createJSonFile.ivp?filePath=%s&key=%s";

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    WebDriverRunner.getWebDriver().quit();
  }

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    launchBrowserAndGotoRelativeLink(cleanupDataLink);
    createJSonFile("default-dashboard.json", PortalVariable.DASHBOARD.key);
  }
  
  public void launchBrowserAndGotoRelativeLink(String relativeProcessStartLink) {
    try {
      open(UrlHelpers.generateAbsoluteProcessStartLink(relativeProcessStartLink));
    } catch (Exception e) {
      throw new PortalGUITestException(e);
    }
  }

   public void createJSonFile(String jsonFile, String key) {
    var path = FileHelper.getAbsolutePathToTestFile(jsonFile);
    String filepath = "";
    try {
      filepath = URLEncoder.encode(path, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    LinkNavigator.redirectToRelativeLink(String.format(createJSonFileUrl, filepath, key));
  }
}
