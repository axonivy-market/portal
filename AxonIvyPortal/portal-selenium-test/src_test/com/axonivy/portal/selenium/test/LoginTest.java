package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.ProxyExtension;
import com.axonivy.portal.selenium.common.RecordLoginStatusCode;
import com.axonivy.portal.selenium.common.SystemProperties;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.ForgotPasswordPage;
import com.axonivy.portal.selenium.page.LoginPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.codeborne.selenide.Selenide;

@IvyWebTest
@ExtendWith({ ProxyExtension.class })
public class LoginTest extends BaseTest {
  private LoginPage loginPage;
  private static final RecordLoginStatusCode LoginStatus = new RecordLoginStatusCode();

  @BeforeEach
  @Override
  public void setup() {
    redirectToNewDashBoard();
    Selenide.webdriver().driver().getProxy().addResponseFilter(LoginStatus.LOGIN, LoginStatus);
    if (!SystemProperties.isInServerMode()) {
      launchBrowserAndLogoutInDesigner();
    }
  }

  @Test
  public void testLoginSuccess() {
    loginPage = new LoginPage(TestAccount.ADMIN_USER);
    loginPage.login(true);
    assertEquals(LoginStatus.code, 302);
    assertEquals(LoginStatus.isAjax, false);
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitPageLoaded();
  }

  @Test
  public void testLoginFail() {
    loginPage = new LoginPage();
    loginPage.login("admin", "InputWrongPassword", false);
    assertEquals(LoginStatus.code, 401);
    assertEquals(LoginStatus.isAjax, false);
  }

  @Test
  public void testForgotPassword() {
    loginPage = new LoginPage();
    loginPage.forgotPassword();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    forgotPasswordPage.waitPageLoaded();
  }
}
