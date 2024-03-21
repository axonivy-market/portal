package com.axonivy.portal.selenium.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.SystemProperties;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.ForgotPasswordPage;
import com.axonivy.portal.selenium.page.LoginPage;

@IvyWebTest
public class ForgotPasswordTest extends BaseTest {

  private ForgotPasswordPage forgotPasswordPage;

  @BeforeEach
  @Override
  public void setup() {
    redirectToNewDashBoard();
    if (!SystemProperties.isInServerMode()) {
      launchBrowserAndLogoutInDesigner();
    }
    LoginPage loginPage = new LoginPage();
    loginPage.forgotPassword();
  }

  @Test
  public void testForgotPassword() {
    forgotPasswordPage = new ForgotPasswordPage(TestAccount.TEST_FORGOT_PASSWORD_USER);
    forgotPasswordPage.send();
    forgotPasswordPage.isProcessed();
  }

}
