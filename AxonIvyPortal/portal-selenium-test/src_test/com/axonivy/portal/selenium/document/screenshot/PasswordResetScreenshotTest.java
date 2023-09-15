package com.axonivy.portal.selenium.document.screenshot;


import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtil;
import com.axonivy.portal.selenium.common.SystemProperties;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.PasswordResetPage;

@IvyWebTest
public class PasswordResetScreenshotTest extends ScreenshotBaseTest {

  @BeforeEach
  @Override
  public void setup() {
    launchBrowserAndGotoRelativeLink(PORTAL_HOME_PAGE_URL);
    if (!SystemProperties.isInServerMode()) {
      launchBrowserAndLogoutInDesigner();
    }
    redirectToRelativeLink(portalKitTestHelperPasswordResetUrl);
  }

  @Test
  public void testResetPassword() throws IOException {
    redirectToRelativeLink(String.format(portalPasswordResetUrl, TestAccount.TEST_FORGOT_PASSWORD_USER.getPassword(),
        TestAccount.TEST_FORGOT_PASSWORD_USER.getUsername()));
    ScreenshotUtil.resizeBrowser(new Dimension(1024, 768));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.FORGOT_PASSWORD + "reset-password-screen");
  }
  
  @Test
  public void testResetPasswordSuccess() throws IOException {
    redirectToRelativeLink(String.format(portalPasswordResetUrl, TestAccount.TEST_FORGOT_PASSWORD_USER.getPassword(), TestAccount.TEST_FORGOT_PASSWORD_USER.getUsername()));
    var passwordResetPage = new PasswordResetPage();
    String newPassword = "a2C!";
    passwordResetPage.resetPassword(newPassword, true);
    refreshPage();
    ScreenshotUtil.resizeBrowser(new Dimension(1024, 768));
    ScreenshotUtil.capturePageScreenshot(ScreenshotUtil.FORGOT_PASSWORD + "reset-password-success-screen");
  }
}
