package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtil;
import com.axonivy.portal.selenium.common.SystemProperties;
import com.axonivy.portal.selenium.page.LoginPage;

@IvyWebTest
public class LoginScreenshotTest extends ScreenshotBaseTest {

  @Override
  @BeforeEach
  public void setup() {
    launchBrowserAndGotoRelativeLink(PORTAL_HOME_PAGE_URL);
    if (!SystemProperties.isInServerMode()) {
      launchBrowserAndLogoutInDesigner();
    }
  }

  @Test
  public void testLogin() throws IOException {
    ScreenshotUtil.resizeBrowser(new Dimension(1024, 768));
    new LoginPage().waitForUsernameInputIsFocused();
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.LOGIN_FOLDER + "login-form");
  }
}
