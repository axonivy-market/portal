package com.axonivy.portal.selenium.document.screenshot;

import com.axonivy.portal.selenium.common.ScreenshotTest;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.page.LoginPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;

@IvyWebTest
public class LoginScreenshotTest extends ScreenshotTest{

  @Test
  public void testLogin() throws IOException {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    showNewDashboard();
    newDashboardPage.waitForDashboardPageAvailable();

    launchBrowserAndLogoutInDesigner();
    ScreenshotUtils.resizeBrowser(new Dimension(1024, 768));
    new LoginPage().waitForUsernameInputIsFocused();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.LOGIN_FOLDER + "login-form");
  }

}
