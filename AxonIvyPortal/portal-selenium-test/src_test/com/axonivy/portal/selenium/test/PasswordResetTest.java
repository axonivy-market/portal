package com.axonivy.portal.selenium.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.SystemProperties;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.ForgotPasswordPage;
import com.axonivy.portal.selenium.page.LoginPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.PasswordResetErrorPage;
import com.axonivy.portal.selenium.page.PasswordResetPage;

@IvyWebTest
public class PasswordResetTest extends BaseTest {
  private PasswordResetPage passwordResetPage;
  private PasswordResetErrorPage passwordResetErrorPage;

  @BeforeEach
  @Override
  public void setup() {
    launchBrowserAndGotoRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    if (!SystemProperties.isInServerMode()) {
      launchBrowserAndLogoutInDesigner();
    }
    redirectToRelativeLink(portalKitTestHelperPasswordResetUrl);
  }

  @Test
  public void testResetPasswordSuccess() {
    redirectToRelativeLink(String.format(portalPasswordResetUrl, TestAccount.TEST_FORGOT_PASSWORD_USER.getPassword(),
        TestAccount.TEST_FORGOT_PASSWORD_USER.getUsername()));
    passwordResetPage = new PasswordResetPage();
    String newPassword = "a2C!";
    passwordResetPage.resetPassword(newPassword, true);
    passwordResetPage.isReset();
    refreshPage();
    passwordResetPage.goHome();
    LoginPage loginPage = new LoginPage();
    loginPage.isDisplayed();
  }

  @Test
  public void testResetPasswordFailedWithWeakPassword() {
    redirectToRelativeLink(String.format(portalPasswordResetUrl, TestAccount.TEST_FORGOT_PASSWORD_USER.getPassword(),
        TestAccount.TEST_FORGOT_PASSWORD_USER.getUsername()));
    passwordResetPage = new PasswordResetPage();

    String newPassword = "abc";
    passwordResetPage.resetPassword(newPassword, false);
    passwordResetPage.isNewPasswordNotStrongEnough();
  }

  @Test
  public void testResetPasswordFailed() {
    redirectToRelativeLink(
        String.format(portalPasswordResetUrl, "1234", TestAccount.TEST_FORGOT_PASSWORD_USER.getUsername()));
    passwordResetErrorPage = new PasswordResetErrorPage();
    passwordResetErrorPage.isDisplayed();
    passwordResetErrorPage.goForgotPassword();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    forgotPasswordPage.isDisplayed();
  }
}
