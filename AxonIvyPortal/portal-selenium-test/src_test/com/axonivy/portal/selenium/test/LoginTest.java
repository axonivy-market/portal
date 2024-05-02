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

@IvyWebTest
public class LoginTest extends BaseTest {
  private LoginPage loginPage;

  @BeforeEach
  @Override
  public void setup() {
    redirectToNewDashBoard();
    if (!SystemProperties.isInServerMode()) {
      launchBrowserAndLogoutInDesigner();
    }
  }

  @Test
  public void testLogin() {
    loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitPageLoaded();
  }

  @Test
  public void testForgotPassword() {
    loginPage = new LoginPage();
    loginPage.forgotPassword();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    forgotPasswordPage.waitPageLoaded();
  }


}
