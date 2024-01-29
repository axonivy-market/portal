package com.axonivy.portal.selenium.document.screenshot;


import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.ScreenshotBaseTest;
import com.axonivy.portal.selenium.common.ScreenshotUtils;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.PasswordResetPage;

@IvyWebTest
public class PasswordResetScreenshotTest extends ScreenshotBaseTest {

  @BeforeEach
  @Override
  public void setup() {
    super.setup();
    redirectToRelativeLink(portalKitTestHelperPasswordResetUrl);
  }

  @Test
  public void testResetPassword() throws IOException {
    login(TestAccount.ADMIN_USER);
    showNewDashboard();
    NewDashboardPage homePage = new NewDashboardPage();
    homePage.clickOnLogout();
    redirectToRelativeLink(String.format(portalPasswordResetUrl, TestAccount.TEST_FORGOT_PASSWORD_USER.getPassword(),
        TestAccount.TEST_FORGOT_PASSWORD_USER.getUsername()));
    ScreenshotUtils.resizeBrowser(new Dimension(1024, 768));
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.FORGOT_PASSWORD + "reset-password-screen");

    redirectToRelativeLink(String.format(portalPasswordResetUrl, TestAccount.TEST_FORGOT_PASSWORD_USER.getPassword(),
        TestAccount.TEST_FORGOT_PASSWORD_USER.getUsername()));
    var passwordResetPage = new PasswordResetPage();
    String newPassword = "a2C!";
    passwordResetPage.resetPassword(newPassword, true);
    refreshPage();
    ScreenshotUtils.resizeBrowser(new Dimension(1024, 768));
    ScreenshotUtils.capturePageScreenshot(ScreenshotUtils.FORGOT_PASSWORD + "reset-password-success-screen");
  }
}
