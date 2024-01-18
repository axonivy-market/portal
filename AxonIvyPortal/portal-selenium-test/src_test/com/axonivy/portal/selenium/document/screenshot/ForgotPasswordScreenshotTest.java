package com.axonivy.portal.selenium.document.screenshot;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotTest;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.page.LoginPage;

@IvyWebTest
public class ForgotPasswordScreenshotTest extends ScreenshotTest {

  @Test
  public void testForgotPassword() throws IOException {
    redirectToRelativeLink(PORTAL_HOME_PAGE_URL);
    launchBrowserAndLogoutInDesigner();
    
    LoginPage loginPage = new LoginPage();
    loginPage.forgotPassword();
    ScreenshotUtils.resizeBrowser(new Dimension(1024, 768));
    loginPage.waitForEmailAddressIsFocused();
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.FORGOT_PASSWORD + "send-email-screen");
  }

}
